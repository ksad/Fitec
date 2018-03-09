package scalatutorials
import scala.util.Random

object Match_Statement {
    def main (args:Array[String]){
      
      val x: Int = Random.nextInt(10)
      
      x match {
        case 0 => println("zero")
        case 1 => println("one")
        case 2 => println("two")
        case _ => println("many")
      }
      
      // Define match in a method
      def matchTest(x: Int): String = x match {
        case 1 => "one"
        case 2 => "two"
        case _ => "many"
      }
      println(matchTest(3))  // many
      println(matchTest(1))  // one
    }
    
    // case classes
    abstract class Notification
    
    case class Email(sender: String, title: String, body: String) extends Notification
    
    case class SMS(caller: String, message: String) extends Notification
    
    case class VoiceRecording(contactName: String, link: String) extends Notification
    
    def showNotification(notification: Notification): String = {
      notification match {
        case Email(email, title, _) =>
          s"You got an email from $email with title: $title"
        case SMS(number, message) =>
          s"You got an SMS from $number! Message: $message"
        case VoiceRecording(name, link) =>
          s"you received a Voice Recording from $name! Click the link to hear it: $link"
      }
    }
      val someSms = SMS("12345", "Are you there?")
      val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
      
      //println(showNotification(someSms))  // prints You got an SMS from 12345! Message: Are you there?
      
      //println(showNotification(someVoiceRecording))  // you received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123
      
      
    def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
      notification match {
        case Email(email, _, _) if importantPeopleInfo.contains(email) =>
          "You got an email from special someone!"
        case SMS(number, _) if importantPeopleInfo.contains(number) =>
          "You got an SMS from special someone!"
        case other =>
          showNotification(other) // nothing special, delegate to our original showNotification function
      }
    }

    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")
    
    val someSms2 = SMS("867-5309", "Are you there?")
    val someVoiceRecording2 = VoiceRecording("Tom", "voicerecording.org/id/123")
    val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
    val importantSms = SMS("867-5309", "I'm here! Where are you?")
    
    println(showImportantNotification(someSms2, importantPeopleInfo))
    println(showImportantNotification(someVoiceRecording2, importantPeopleInfo))
    println(showImportantNotification(importantEmail, importantPeopleInfo))
    println(showImportantNotification(importantSms, importantPeopleInfo))
}