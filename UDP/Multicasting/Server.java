import java.net.*;
class Server
{
    MulticastSocket mkst;
    Server() throws Exception
    {
        int port=7890;
        mkst = new MulticastSocket(port);
        InetAddress dip=InetAddress.getByName("224.5.6.7");
        mkst.joinGroup(dip);
    }
    
    void receivePackets() throws Exception
    {
        int i;
        DatagramPacket pkt;
        byte buffer[];
        for(i=0;i<20;i++)
        {
            buffer=new byte[100];
            pkt=new DatagramPacket(buffer,buffer.length);
            mkst.receive(pkt);
            String s = new String(pkt.getData() , 0 , pkt.getLength());
            System.out.println("Received : " + s);
        }//for
        mkst.close();
    }
    public static void main(String args[])
    {
        try
        {
            Server mrcv = new Server();
            mrcv.receivePackets();
        }catch(Exception ex)
        {}
    }//main
}//server class
