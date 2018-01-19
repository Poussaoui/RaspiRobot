package com.raspirobot;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Client extends AsyncTask<Void, Void, Void> {
    String messgToSend;
    int port;
    String IpAdress;

    Client(String Ipadd,int port,String messgToSend) {
        this.messgToSend=messgToSend;
        this.IpAdress = Ipadd;
        this.port = port;
    }
    @Override
    protected Void doInBackground(Void... arg0) {
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(IpAdress,port);
            //Send the message to the server
            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            String number = messgToSend;
            String sendMessage = number;
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : "+sendMessage);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.print(e.toString());
        }
        finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        System.out.println("Done Message sent to the server : "+messgToSend);
        super.onPostExecute(result);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //messageDisplay(msg.obj.toString());
        }
    };

}
