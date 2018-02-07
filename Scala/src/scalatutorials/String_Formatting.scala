package scalatutorials

object String_Formatting {
      def main (args:Array[String]){
      printf("Now you have %.16f problems.", Math.nextAfter(2.0, 3))
      
      /* for more details see this page about string formatting :
      https://alvinalexander.com/programming/printf-format-cheat-sheet*/
    }
}