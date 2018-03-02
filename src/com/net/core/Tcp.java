package com.net.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Tcp简单示例  主要类：服务器端 ServiceSocket  客户端 Socket
 * Created by Administrator on 2018/3/1 0001.
 */
public class Tcp {}

/**
 * 服务端
 */
class TcpService{
    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(8889);
        while (true){
            // 阻塞式 等待连接
            Socket socket = server.accept();
            System.err.println("建立连接......");
            //响应客户端
            String msg = "TcpService  .....";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(msg);
            bw.newLine();  // 不加这个，相当于 msg没有、\r\n 在客户端使用readLine时会出问题
            bw.flush();
            //  不要close close会把socket管道关闭
        }

    }
}

/**
 * 客户端
 */
class TcpClient{
    public static void main(String args[]) throws IOException {
        Socket socket = new Socket("localhost",8889);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //阻塞式
        String rev = br.readLine();
        System.err.println("rev"+rev);
    }
}