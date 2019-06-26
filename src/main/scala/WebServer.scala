import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn

object WebServer extends App {

  implicit val system = ActorSystem("product-management-service")
  implicit val materializer = ActorMaterializer()

  implicit val executionContext = system.dispatcher

  val signUpActor = system.actorOf(Props(new SignUpActor(1)))

  val route =
    path("hemsedalen") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")


  signUpActor ! SignUp("Eirik")
  signUpActor ! SignUp("Lars Tore")
  signUpActor ! PrintSignUps()
  signUpActor ! PrintWaitingList()

  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}