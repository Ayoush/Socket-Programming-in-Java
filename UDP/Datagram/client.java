import java.io.*;
import java.util.*;
import java.net.*;

class client
{
	String req;
	DatagramSocket ds;
	client(String input) throws Exception
	{
		if(input.equalsIgnoreCase("states"))
		{
			req=input;
		}else if(input.equalsIgnoreCase("numbers"))
		{
			req=input;
		}else
		{
			req="states";
		}
		ds = new DatagramSocket();
	}//client

	void performIO() throws Exception
	{
		int i;
		byte ar[]=req.getBytes();
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		int port=1234;

		for(i=0;i<3;i++)
		{
			DatagramPacket dp1 = new DatagramPacket(ar,ar.length,ip,port);
			ds.send(dp1);

			byte arr[]=new  byte[1024];
			DatagramPacket dp2 = new DatagramPacket(arr,arr.length);
			ds.receive(dp2);

			byte response[] =dp2.getData();
			int len = dp2.getLength();
			String resp = new String(response , 0 ,len);
			System.out.println("Server says : "+resp);
		}
	}
	void close() throws Exception
	{
		ds.close();
	}
	public static void main(String args[])
	{
		try
		{
			String temp="no data";
			if(args.length>0)
			{
				temp=args[0];
			}

			client c = new client(temp);
			c.performIO();
			c.close();
		}catch(Exception ex)
		{
			System.out.println("Err:.."+ex);
		}
	}
} 
