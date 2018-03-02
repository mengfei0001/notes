package com.net.chat.v1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
public class Service {

    public Service(){

    }

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8884);
        //拿到管道
        while (true){
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.err.println("client link");
            String rev;
            while ((rev = br.readLine())!= null){
                System.err.println(rev);
                bw.write("服务端：hello\r\n");
                bw.flush();
            }
        }

    }
}
