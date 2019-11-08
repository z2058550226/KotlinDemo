package com.suikajy.kotlinDemo.java_test.socket;

import java.net.Socket;

public class Client1 {
    public static void main(String args[]) {
        int arr[] = new int[10];
        int length = 6, i, counter_1;
        ParentObj obj1;
        for (i = 0; i < length; i++) {
            arr[i] = i + 10;
        }
        try {
            Socket s = new Socket("localhost", 6789);
            IntString obj = new IntString(arr, length);
            /*
             * OutputStream os = s.getOutputStream();
             * ObjectOutputStream oos = new ObjectOutputStream(os);
             *
             * oos.writeObject(obj);
             * oos.close();
             * os.close();
             */
            Send_recv snd = new Send_recv(s);
            snd.sendObj((ParentObj) obj);

            if (s.isClosed()) {
                System.out.println("Closed");
                // s.connect(null);
            }
            obj1 = snd.recObj();
            obj = (IntString) obj1;
            if (obj != null) {
                for (counter_1 = 0; counter_1 < obj.length_of_row; counter_1++) {
                    System.out.println(obj.row[counter_1]);
                    obj.row[counter_1]++;
                }
            }

            // s.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
