package com.suikajy.kotlinDemo.java_test.socket;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Send_recv {
    Socket s;
    IntString obj1;

    public Send_recv(Socket s) {
        this.s = s;
    }

    public void sendObj(ParentObj obj) {
        try {
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(obj);
//            oos.close();
//            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ParentObj recObj() {

        try {
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            obj1 = (IntString) ois.readObject();
//            ois.close();
//            is.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return (obj1);
    }
}
