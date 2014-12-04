/**
 * 
 */
package client;

/**
 * @author philippe
 *
 */


import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Client {
	
	public static String serverName = "localhost";
	
	public static int port = 8080;
	
	private String address;
	
	private static String pseudo;
	
	public boolean access = false;
	
	public static void main(String [] args) throws IOException
	   {
		
		 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	      
	      try
	      {
		 //creation of the client socket and connection to the server socket (implicitly)
	   
	   
		 Socket client = new Socket(serverName, port);
		 //or you can manage connection explicitly
		 //Socket client = new Socket();
		 //InetSocketAddress serverSocket = new InetSocketAddress(serverName, port);
		 //client.connect(serverSocket);
	         System.out.println("Trying connecting to..." + serverName + " on port " + port);
	         System.out.println("Local socket :" + client.getLocalSocketAddress());
	         System.out.println("Just connected to "+ client.getRemoteSocketAddress());

	        Command command = new Command(client);    
	        
	        Display display = new Display(command);

	        display.start();
	        
	        String inMessage;
			while(!inMessage.equals("exit")){
	       	 //recovering the message to send from the console (stdIn)
	                System.out.println("Please type message to send : ");
	       	 String outMessage = stdIn.readLine();

	       	 //sending message through client socket (using DataOutputStream)
	       	      	//writeUTF method allows for directly send "UTF-8" Strings
	       	      	out.writeUTF(outMessage);
	       	      	//or, you can send bytes
	       	      	//toSendBytes = outMessage.getBytes(Charset.forName("UTF-8"));
	       		//out.write(toSendBytes);	

	       	 //receiving message from client socket (using DataInputStream)
	       		//readUTF method allows for directly receive "UTF-8" Strings
	       		inMessage = in.readUTF(); 	
	       		//or, you can read bytes and interpret them as "UTF-8" Strings
	       		//in.read(receivedBytes);
	       		//inMessage = new String(receivedBytes, "UTF-8");
	                System.out.println("[Received message] -> "+inMessage);
	       	 }

	        
	        
	        
	         //closing socket
	         client.close();
		 System.out.println("Connection closed");

	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	         
	         
	      }
	   }

