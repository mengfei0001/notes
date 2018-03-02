package com.net.chat.v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
public class Server {

    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(7777);
        Socket socket = server.accept();

    }

    class Client implements  Runnable{
        private BufferedReader br;
        private BufferedWriter bw;
        public  Client(Socket socket){
            br = Server.getReader(socket);
            bw = Server.getWriter(socket);
        }
        @Override
        public void run() {

        }
    }
    public static BufferedReader getReader(Socket socket) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedWriter getWriter(Socket socket)  {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
