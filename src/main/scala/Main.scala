import akka.actor.{ActorSystem, Props}

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem()
    val signUpActor = system.actorOf(Props(new SignUpActor(1)))

    signUpActor ! SignUp("Eirik")
    signUpActor ! SignUp("Lars Tore")
    signUpActor ! PrintSignUps()
    signUpActor ! PrintWaitingList()

    Thread.sleep(1000)
    system.terminate()
  }
}
