package com.net.chat.v1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
public class Client  {
    private static boolean swth = true;
    public static void main(String args[])  {
        try{
            Socket socket = new Socket("localhost",8884);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            while (swth){
                System.err.println("输入信息回车。发送消息个服务端！");
                if(scanner.hasNext()){
                    String msg = scanner.next();
                    if(msg.equalsIgnoreCase("end")){
                        swth = false;
                        socket.close();
                        break;
                    }
                    bw.write(msg+"\r\n");
                    bw.flush();
                    String rev = br.readLine();
                    System.err.println("rev-->"+rev);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
