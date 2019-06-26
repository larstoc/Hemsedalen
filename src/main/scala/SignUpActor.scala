
import akka.actor.Actor

class SignUpActor(capacity: Int) extends Actor {

  var signUps: List[String] = List()

  override def receive: Receive = {
    case SignUp(name) => signUps :+ name
    case PrintSignUps() => println(sigUpMessage(signUps.take(capacity)))
    case PrintWaitingList() => println(waitingListMessage(signUps.drop(capacity)))
  }

  def sigUpMessage(signedUp: List[String]): String = s"${signedUp.mkString(", ")} You are signed up!"

  def waitingListMessage(waitingList: List[String]): String = s"${waitingList.mkString(", ")}.. Sucks to be you.."
}

case class SignUp(name: String)

case class PrintSignUps()

case class PrintWaitingList()

