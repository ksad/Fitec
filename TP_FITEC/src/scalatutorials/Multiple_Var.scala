package scalatutorials

object Multiple_Var {
    def main (args:Array[String]){
      var x1, y1, z1 = 0  
      var c1, python1, java1 = false  
      println(x1, y1, z1, c1, python1, java1)
      
      var (x, y, z, c, python, java) = (1, 2, 3, true, false, "no!")  
      println(x, y, z, c, python, java)  
    }
}