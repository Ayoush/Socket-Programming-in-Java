import java.rmi.*;

class MathClient 
{
 public static void main(String args[])
 {
  try
  {
   //Gets a Remote reference 
   Remote r = Naming.lookup("rmi://127.0.0.1/math");
   //r refers to the stub of RemoteObject

   MathIFace mi = (MathIFace) r;
   //m1 refers to the stub of RemoteObject
   double ans;
   ans = mi.addNos(10,4);
   System.out.println("10 + 4 = " + ans);
   ans = mi.subtractNos(10,4);
   System.out.println("10 - 4 = " + ans);
   ans = mi.multiplyNos(10,4);
   System.out.println("10 * 4 = " + ans);
   ans = mi.divideNos(10,4);
   System.out.println("10 / 4 = " + ans);
  }
  catch(Exception ex)
  {}
 }//main
}//MathClient