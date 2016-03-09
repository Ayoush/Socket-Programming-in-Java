import java.io.*;
import java.net.*;
import java.util.*;

class client 
{
	Socket s;
	client(String ip,int port) throws IOException
	{
		s=new Socket(ip,port);	
	}
	
	static String scanString() throws IOException
	{
		byte arr[]=new byte[100];
		int x=System.in.read(arr);
		String s=new String(arr,0,x-2);
		return s;
	}
	
	static char scanChar() throws IOException
	{
		int x=System.in.read();
		System.in.skip(2);
		return (char)x;
	}
	void performIO() throws IOException
	{
		boolean flag=false;
		char ch;
		InputStream ips  = s.getInputStream();
		OutputStream ops = s.getOutputStream();
		String a,b;
		DataInputStream din = new DataInputStream(ips);
		DataOutputStream dout = new DataOutputStream(ops);
		do
		{
			System.out.println("Enter the String..");
			a=scanString();
			dout.writeUTF(a);
			b=din.readUTF();
			System.out.println("Output.."+b);
			System.out.println("want to enter more string(y/n)");
			ch=scanChar();
			if(ch=='Y' || ch=='y')
			{
				flag=true;	
			}else
				flag=false;
			dout.writeBoolean(flag);
		}while(flag);
	}
	void closeconnection() throws IOException
	{
		s.close();
	}	
	

	public static void main(String args[])
	{
		try
		{
			client c=new client("127.0.0.1",8989);
			c.performIO();
		}catch(Exception ex)
		{
			System.out.println("Exception .."+ex);
		}
	
	}

}
