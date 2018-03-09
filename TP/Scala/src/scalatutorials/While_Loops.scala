package scalatutorials

object Loops {
    def main (args:Array[String]){
      //Loops using while ---> QUESTION .???? WHY 45
      var i, sum = 0
      while ( i < 10) {
        sum += i
        println("i = " + i + " --> sum = " + sum)
        i+=1  
      }
      
      //Loops using for  
      var sum2 = 0  
      for ( i <- 0 until 10) {  
        sum2 += i  
      }  
      println(sum2)
      
      var a = 0;
      var b = 0;
      
      // for loop execution with a range
      for( a <- 1 to 3; b <- 1 to 3){
         println( "Value of a: " + a );
         println( "Value of b: " + b );
      }
      
      //var a = 0;
      val numList = List(1,2,3,4,5,6);

      // for loop execution with a collection
      for( a <- numList ){
         println( "Value of a: " + a );
      }
      
      val numList2 = List(1,2,3,4,5,6,7,8,9,10);

      // for loop execution with multiple filters
      for( a <- numList2
           if a != 3; if a < 8 ){
         println( "Value of a: " + a );
      }
      
      val numList3 = List(1,2,3,4,5,6,7,8,9,10);

      // for loop execution with a yield
      var retVal = for{ a <- numList3 if a != 3; if a < 8 }yield a

      // Now print returned values using another loop.
      for( a <- retVal){
         println( "Value of a: " + a );
      }
    }
}