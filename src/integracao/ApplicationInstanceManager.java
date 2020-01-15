/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Sergik
 */
class ApplicationInstanceManager {

    private static ApplicationInstanceListener subListener;
    /**
     * Randomly chosen, but static, high socket number
     */
    public static final int SINGLE_INSTANCE_NETWORK_SOCKET = 44331;
    /**
     * Must end with newline
     */
    public static final String SINGLE_INSTANCE_SHARED_KEY = "$$NewInstance$$\n";

    /**
     * Registers this instance of the application.
     *
     * @return true if first instance, false if not.
     */
    public static boolean registerInstance() {
        boolean returnValueOnError = true;
        try {
            final ServerSocket socket = new ServerSocket(SINGLE_INSTANCE_NETWORK_SOCKET, 10, InetAddress.getLocalHost());
            System.out.println("Listening for application instances on socket " + SINGLE_INSTANCE_NETWORK_SOCKET);
            Thread instanceListenerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean socketClosed = false;
                    while (!socketClosed) {
                        if (socket.isClosed()) {
                            socketClosed = true;
                        } else {
                            try {
                                Socket client = socket.accept();
                                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                String message = in.readLine();
                                if (SINGLE_INSTANCE_SHARED_KEY.trim().equals(message.trim())) {
                                    System.out.println("Shared key matched - new application instance found");
                                    fireNewInstance();
                                }
                                in.close();
                                client.close();
                            } catch (IOException e) {
                                socketClosed = true;
                            }
                        }
                    }
                }
            });
            instanceListenerThread.start();
        } catch (UnknownHostException e) {
            return returnValueOnError;
        } catch (IOException e) {
            System.out.println("Port is already taken.  Notifying first instance.");
            try {
                Socket clientSocket = new Socket(InetAddress.getLocalHost(), SINGLE_INSTANCE_NETWORK_SOCKET);
                OutputStream out = clientSocket.getOutputStream();
                out.write(SINGLE_INSTANCE_SHARED_KEY.getBytes());
                out.close();
                clientSocket.close();
                System.out.println("Successfully notified first instance.");
                return false;
            } catch (UnknownHostException e1) {
                return returnValueOnError;
            } catch (IOException e1) {
                System.out.println("Error connecting to local port for single instance notification");
                return returnValueOnError;
            }
        }
        return true;
    }


    public static void setApplicationInstanceListener(ApplicationInstanceListener listener) {
        subListener = listener;
    }

    private static void fireNewInstance() {
        if (subListener != null) {
            subListener.newInstanceCreated();
        }
    }

    public interface ApplicationInstanceListener {

        public void newInstanceCreated();
    }
}
