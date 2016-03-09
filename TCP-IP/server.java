import java.util.*;
import java.io.*;
import java.net.*;

class server implements Runnable
{
	ServerSocket svrSkt;
	Thread connThread;
	boolean connFlag;
	
	server(int port) throws IOException
	{
	 	svrSkt=new ServerSocket(port);
		connFlag=true;
		connThread =new Thread(this);
		connThread.start();
	}
	
	public void run()
	{
		try
		{
			svrSkt.setSoTimeout(1000);
		}catch(Exception ex)
		{}
		while(connFlag)
		{
			acceptConnections();
		}
		try
		{
			svrSkt.close();
		}catch(Exception ex)
		{}
	}
	
	void acceptConnections()
	{
		try
		{
			System.out.println("Waiting for the connections...");
			Socket s=svrSkt.accept();
			System.out.println("Got the Connection....");
			new Processor(s);
		}catch(IOException ex)
		{}
	}
	public static void main(String args[])
	{
		try
		{
			server s=new server(8989);
		}
		catch(IOException ex)
		{
			System.out.println("Exception "+ex);	
		}
	}//main
}//server

class Processor extends Thread
{
	Socket client;
	Processor(Socket t)
	{
		client = t;
		start();
	}
	public void run()
	{
		try{
		InputStream ips =client.getInputStream();
		OutputStream ops=client.getOutputStream();
		boolean flag=false;
		DataInputStream din=new DataInputStream(ips);
		DataOutputStream dout=new DataOutputStream(ops);
		String s1,s2;
		do
		{
			s1=din.readUTF();
			s2=echo(s1);
			dout.writeUTF(s2);
			flag=din.readBoolean();
		}while(flag);
		client.close();
		}catch(IOException ex)
		{
			System.out.println("Exception .."+ex);
		}	
	}
	String echo(String s)
	{
		String sol;
		sol="{"+s.toUpperCase()+"}";
		return sol;
	}
}//processor

