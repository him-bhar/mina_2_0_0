package com.himanshu.MinaClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: Himanshu
 * Date: 29 Aug, 2010
 * Time: 2:50:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MinaClientTest {
    public static void main(String[] args) throws IOException {
        System.out.println("Invoking Mina Client");
        //Double inputData = Math.random();
        String inputData = "HIMANSHU.BHARDWAJ|";
        //String inputData = "BHARD";
        Socket clientSocket = new Socket("127.0.0.1", 8893);
        PrintWriter pwriterOut = new PrintWriter(clientSocket.getOutputStream(), true);
        pwriterOut.print(inputData);
        pwriterOut.flush();
        char[] outputData = new char[500];
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        in.read(outputData);
        System.out.println("Output is   "+outputData);
        String responseData = new String(outputData);
        System.out.println("Response from server- "+responseData);
        pwriterOut.close();
        in.close();
        clientSocket.close();
    }
}
