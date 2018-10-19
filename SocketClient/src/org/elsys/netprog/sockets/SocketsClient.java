package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketsClient {

	public static void main(String[] args) throws IOException {
		Socket echoSocket = null;
		try {
			    echoSocket = new Socket("localhost", 10001);
			    PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			    BufferedReader stdIn = new BufferedReader(
			            new InputStreamReader(System.in));
			    Read(echoSocket);
			    String userInput;
			    while ((userInput = stdIn.readLine()) != null) {
			        out.println(userInput);
			    }
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (echoSocket != null && !echoSocket.isClosed()) {
				echoSocket.close();
			}
		}
	}
	
	public static void Read(Socket echoSocket) {
		Thread thread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                try {
    			    BufferedReader in = new BufferedReader(
    			            new InputStreamReader(echoSocket.getInputStream()));
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
