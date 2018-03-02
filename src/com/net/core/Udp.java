package com.net.core;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Udp 基本演示
 * Created by mengfei on 2018/3/1 0001.
 */
public class Udp {}


/**
 * 服务端
 */
class Server {
    public static void main(String args[]) throws IOException {
        DatagramSocket server = new DatagramSocket(8888);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data,data.length);
        server.receive(packet);
        byte[] recData =  packet.getData();
        System.err.println(new String(recData));
        server.close();
    }
}

/**
 * 客户端
 */
class Client {
    public static void main(String args[]) throws IOException {
        DatagramSocket client = new DatagramSocket();
        String sendData = "client send a msg!";
        byte[] data =sendData.getBytes();
        DatagramPacket packet = new DatagramPacket(data,data.length,new InetSocketAddress("localhost",8888));
        client.send(packet);
        client.close();
    }
}