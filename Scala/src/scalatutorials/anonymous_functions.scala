package scalatutorials

object anonymous_functions {
  
  //a method that requires a function as a parameter  
  //the function's type is (Int,Int) => Int  
  //e.g. maps from 2 Ints to an Int  
  def doWithOneAndTwo(f: (Int, Int) => Int) = {  
    f(10, 5)  
  }  
  
  //Explicit type declaration  
  val call1 = doWithOneAndTwo((x: Int, y: Int) => x + y)  
  
  //The compiler expects 2 ints so x and y types are inferred  
  val call2 = doWithOneAndTwo((x, y) => x * y)  
  
  //Even more concise syntax  
  val call3 = doWithOneAndTwo(_ / _)
  
  val getTheAnswer = () => 42
  
  
  def main (args:Array[String]){
      println(call1, call2, call3)
      println(getTheAnswer())
  }
}