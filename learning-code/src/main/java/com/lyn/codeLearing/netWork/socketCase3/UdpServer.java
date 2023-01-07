package com.lyn.codeLearing.netWork.socketCase3;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket =new DatagramSocket();

        String str ="mdzz";

        DatagramPacket packet =new DatagramPacket(str.getBytes(),str.length(), InetAddress.getByName("localhost"),7000);

        datagramSocket.send(packet);
    }
}
