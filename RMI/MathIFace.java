import java.rmi.*;

interface MathIFace extends Remote
{
 //remote methods of choice
 double addNos(double a, double b) throws RemoteException;
 double subtractNos(double a, double b) throws RemoteException;
 double multiplyNos(double a, double b) throws RemoteException;
 double divideNos(double a, double b) throws RemoteException;

}