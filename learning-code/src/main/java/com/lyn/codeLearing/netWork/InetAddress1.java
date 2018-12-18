package com.lyn.codeLearing.netWork;

import java.net.InetAddress;

public class InetAddress1 {

    public static void main(String[] args) throws Exception {
        InetAddress address =InetAddress.getLocalHost();

        System.out.println(address);

        address=InetAddress.getByName("www.chtwm.com");

        System.out.println(address);

    }
}
