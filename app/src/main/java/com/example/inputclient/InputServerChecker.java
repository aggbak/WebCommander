package com.example.inputclient;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.net.UnknownHostException;

// Create new thread then join on it
public class InputServerChecker {
    public boolean isServerUp(String ip) {
        AtomicBoolean server_up = new AtomicBoolean();
        server_up.set(false);
        NetThreadServerChecker netThreadServerChecker = new NetThreadServerChecker(ip, server_up);
        netThreadServerChecker.start();
        try {
            netThreadServerChecker.join();
        } catch (Exception exception) {
            return false;
        }
        return server_up.get();
    }

    public InputServerChecker() {

    }

    public class NetThreadServerChecker extends Thread {
        private AtomicBoolean server_up;
        private String ip;
        public NetThreadServerChecker(String ip_, AtomicBoolean serverisup) {
            this.server_up = serverisup;
            this.ip = ip_;
        }
        public void run() {
            Socket socket;
            try {
                socket = new Socket(ip, 42033);
                socket.close();
                server_up.set(true);
            } catch (Exception unknownHostException) {
                // Server is down and connection failes
                unknownHostException.printStackTrace();
                server_up.set(false);
            }
        }

    }
}
