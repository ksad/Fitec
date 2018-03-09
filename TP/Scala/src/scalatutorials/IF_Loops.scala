package scalatutorials

object IF_Loops {
    def main (args:Array[String]){
      if (true)   
        println("no braces on a single expression")  
      
      if (1 + 1 == 2) {  
        println("multiple")  
        println("statements")  
        println("require")  
        println("braces")  
      } else {  
        println("new math is found!")  
        println("or your CPU went crazy")  
      }  
      
      val likeEggs = false  
      val breakfast = if (likeEggs) "scrambled eggs" else "Apple"  
      
      println(breakfast)
      
      var x = 30;

      if( x == 10 )
         println("Value of X is 10");
      else if( x == 20 )
         println("Value of X is 20");
      else if( x == 30 )
         println("Value of X is 30");
      else
         println("This is else statement");
      
      var x2 = 30;
      var y2 = 10;
      
      if( x2 == 30 ){
         if( y2 == 10 )
            println("X = 30 and Y = 10");
      }
  }
}