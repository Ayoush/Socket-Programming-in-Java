import java.rmi.*;
import java.rmi.server.*;

class MathServer extends UnicastRemoteObject implements MathIFace
{
 public MathServer() throws Exception
 {
   super();
 }
 public double addNos(double a, double b) throws RemoteException
 {
  System.out.println("in addNos");
  return a+b;
 }

 public double subtractNos(double a, double b) throws RemoteException
 {
  System.out.println("in subtractNos");
  return a-b;
 }

 public double multiplyNos(double a, double b) throws RemoteException
 {
  System.out.println("in multiplyNos");
  return a*b;
 }

 public double divideNos(double a, double b) throws RemoteException
 {
  System.out.println("in divideNos");
  return a/b;
 }

 public static void main(String args[])
 {
  try
  {
  //create a Remote object 
  MathServer ms = new MathServer();

  //make its reference accessible to clients
  //for this use RMI registry
  Naming.rebind("math", ms);
 
  //wait for executions
  //no code reqd
  System.out.println("Server waiting for remote method calls ...");
  }
  catch(Exception ex)
  {}
 }//main
}//MathServer