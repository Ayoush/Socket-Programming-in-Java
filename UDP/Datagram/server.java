import java.util.*;
import java.io.*;
import java.net.*;

class server implements Runnable
{
	Thread ioThread;
	boolean ioFlag;
	DatagramSocket dskt;
	LinkedList<String> states;
	LinkedList<Integer> numbers;

	server(int port)throws Exception
	{
		//open and bind datagram socket
		dskt = new DatagramSocket(port);
		Processor p =new Processor();
		numbers = p.populateNumberList();
		states = p.populateStateList("states.txt");
		//create thread
		ioFlag = true;
		ioThread = new Thread(this);
		ioThread.start();
	}//server constructor
	public static void main(String args[])
	{	
		try
		{
			server s = new server(1234);
		}catch(Exception ex)
		{
			System.out.println("Exception ::"+ex);
		}
	}//main 	
	public void run()
	{
		while(ioFlag)
		{
			try
			{
				byte buff[] = new byte[1024];
				DatagramPacket dp1 = new DatagramPacket(buff,buff.length);
				System.out.println("Waiting for a datagram .. ");
				dskt.receive(dp1);//block the current thread until the datagram is received

				System.out.println("Got the one..");
				byte content[] = dp1.getData();
				int len=dp1.getLength();
				String request = new String(content ,0,len);
				System.out.println("request: "+ request.length() +" "+ request);
				String reply = "";
				if(request.equalsIgnoreCase("states"))
				{
					if(states.size()>0)
					{
						reply = states.remove();
					}
					else
					{
						System.out.println("Data not available");
					}
				}else if(request.equalsIgnoreCase("numbers"))
				{
					if(numbers.size() > 0)
					{
						reply = numbers.remove().toString();
					}
					else
					{
						reply = "Numbers not available";
					}
				
				}else
				{
					System.out.println("Unknown request");
				}
DatagramPacket dp2 = new DatagramPacket(reply.getBytes(),reply.length(),dp1.getAddress(),dp1.getPort());
	dskt.send(dp2);
			}catch(Exception ex)
			{
			}
		}
	}
}//server class

class Processor
{
	LinkedList<String> populateStateList()
	{
		LinkedList<String> states = new LinkedList<String>();
		states.add("Maharashtra");
		states.add("Gujrat");
		states.add("MP");
		states.add("UP");
		states.add("JK");
		return states;
	}
	LinkedList<String> populateStateList(String fname)
	{
		try
		{
			LinkedList<String> states = new LinkedList<String>();
			FileReader fr = new FileReader(fname);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s = br.readLine())!= null)
			{
				states.add(s);
			}
			br.close();
			return states;
		}catch(IOException ex)
		{
			return populateStateList();
		}
	}
	LinkedList<Integer> populateNumberList()
	{
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		Random r = new Random();
		Integer rs;
		for(int i=0;i<10;i++)
		{
			rs= new Integer(r.nextInt(100));
			numbers.add(rs);
		}
		return numbers;	
	}
}//processor class
