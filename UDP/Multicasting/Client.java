import java.net.*;
class Client implements Runnable
{
    MulticastSocket mskt;
    Thread mcasrThrd;
    
    Client() throws Exception
    {
        mskt = new MulticastSocket();
        mcasrThrd = new Thread(this);
        mcasrThrd.start();
    }
    public void run()
    {
        try
        {
            int i;
            byte buff[];
            InetAddress dip = InetAddress.getByName("224.5.6.7");
            int port = 7890;
            DatagramPacket pkt;
            for(i=0;i<20;i++)
            {
             try
             {
                 System.out.println("Transmitting "+i);
                 buff = String.valueOf(i).getBytes();
                 pkt = new DatagramPacket(buff,buff.length , dip , port);
                 mskt.send(pkt);
                 Thread.sleep(500);
             } catch(Exception e){}  
            mskt.close();
            }
        }catch(Exception e){}
    }
    public static void main(String args[])
    {
        try
        {
            new Client();
        }catch(Exception ex){}
    }
}