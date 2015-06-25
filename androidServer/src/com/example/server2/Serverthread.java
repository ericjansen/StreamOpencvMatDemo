package com.example.server2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverthread extends Thread {
    private Handler handler;
    private MainActivity ctx;
    private ServerSocket serverSocket;
    public static final int SERVERPORT = 3200;
    private BufferedReader in;
    @Override
    public void run() {
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(SERVERPORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!Thread.currentThread().isInterrupted()) {
            try {
                socket = serverSocket.accept();
                CommunicationThread commThread = new CommunicationThread(socket,this.handler);
                new Thread(commThread).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Serverthread(MainActivity ctx){
        this.ctx=ctx;
        this.handler=ctx.getHandler();
    }
}class CommunicationThread implements Runnable {
    public static byte imageByte[];
    private Handler handler;
    private Socket clientSocket;
    private BufferedReader input;
    public CommunicationThread(Socket clientSocket,Handler handler) {
        this.handler=handler;
        this.clientSocket = clientSocket;
    }
    public void run() {
        try {
           // this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            int imageSize=230400;//expected image size 640X480X3
            InputStream in = this.clientSocket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte buffer[] = new byte[1024];
            int remainingBytes = imageSize; //
            while (remainingBytes > 0) {
                int bytesRead = in.read(buffer);
                if (bytesRead < 0) {
                    throw new IOException("Unexpected end of data");
                }
                baos.write(buffer, 0, bytesRead);
                remainingBytes -= bytesRead;
            }
            in.close();
            baos.close();
            imageByte = baos.toByteArray();
            this.clientSocket.close();//close socket，one connect for one image            int nrOfPixels = imageByte.length / 3; // Three bytes per pixel.
            int pixels[] = new int[imageByte.length / 3];
            for(int i = 0; i < imageByte.length / 3; i++) {
                int r = imageByte[3*i];
                int g = imageByte[3*i + 1];
                int b = imageByte[3*i + 2];
                if (r < 0)
                    r = r + 256; //Convert to positive
                if (g < 0)
                    g = g + 256; //Convert to positive
                if (b < 0)
                    b = b + 256; //Convert to positive
                pixels[i] = Color.rgb(b, g, r);
            }
            Bitmap bitmap = Bitmap.createBitmap(pixels, 320, 240, Bitmap.Config.ARGB_8888);
            Message msg = handler.obtainMessage();
            msg.obj = bitmap;
            handler.sendMessage(msg);//sent bitmap to activity
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

