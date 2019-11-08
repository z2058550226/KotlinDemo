package com.suikajy.kotlinDemo.java_test.socket;

import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String args[]) {
        IntString obj;
        ParentObj obj1;
        int port = 6789, counter_1;
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();
            Send_recv rev = new Send_recv(s);
            /*
             * InputStream is = s.getInputStream();
             * ObjectInputStream ois = new ObjectInputStream(is);
             * IntString obj = (IntString)ois.readObject();
             */
            obj1 = rev.recObj();
            obj = (IntString) obj1;
            System.out.println(s.getInetAddress());
            System.out.println(s.getLocalAddress());
            if (obj != null) {
                for (counter_1 = 0; counter_1 < obj.length_of_row; counter_1++) {
                    System.out.println(obj.row[counter_1]);
                    obj.row[counter_1]++;
                }
            }
            if (ss.isClosed()) {
                System.out.println("Closed ss");

            }
            if (s.isClosed()) {
                System.out.println("Closed in Server");

            }
            Send_recv snd = new Send_recv(s);
            snd.sendObj((ParentObj) obj);

        } catch (Exception e) {
            System.out.println(e + "In Server");
        }

    }
}
