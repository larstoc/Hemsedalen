
import akka.actor.Actor

import scala.collection.mutable.ListBuffer

class SignUpActor(capacity: Int) extends Actor {

  val signUps: ListBuffer[String] = ListBuffer()

  override def receive: Receive = {
    case SignUp(name) => signUps += name
    case PrintSignUps() => println(sigUpMessage(signUps.toList.take(capacity)))
    case PrintWaitingList() => println(waitingListMessage(signUps.toList.drop(capacity)))
  }

  def sigUpMessage(signedUp: List[String]): String = s"${signedUp.mkString(", ")} you are signed up!"

  def waitingListMessage(waitingList: List[String]): String = s"${waitingList.mkString(", ")}.. Sucks to be you.."
}

case class SignUp(name: String)

case class PrintSignUps()

case class PrintWaitingList()

