package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10001);
		    Socket clientSocket = serverSocket.accept();
		    System.out.println("client connected from " + clientSocket.getInetAddress());
		    BufferedReader stdIn = new BufferedReader(
		            new InputStreamReader(System.in));
		    PrintWriter out =
		        new PrintWriter(clientSocket.getOutputStream(), true);
		    Read(clientSocket);
		    String inputLine;

		    while ((inputLine = stdIn.readLine()) != null) {
		        out.println(inputLine);
		   
		        if (inputLine.equals("exit"))
		            break;
		    }
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			
			System.out.println("Server closed");
		}
	}
	
	public static void Read(Socket clientSocket) {
		Thread thread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
    			    BufferedReader in = new BufferedReader(
    			            new InputStreamReader(clientSocket.getInputStream()));
    			    String userInput;
    			    while ((userInput = in.readLine()) != null) {
    			        System.out.println("server response: " + userInput);
    			    }
    			    
                } catch (Throwable t) {
                    System.out.println(t.getMessage());
                }
            }
        });
        thread.start();
	}

}
