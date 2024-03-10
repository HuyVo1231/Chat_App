/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import static server.Server.serverThreadBus;

public class ServerThreadBus {

    private List<ServerThread> listServerThreads;


    public ServerThreadBus() {
        listServerThreads = new ArrayList<>();

    }


    public List<ServerThread> getListServerThreads() {
        return listServerThreads;
    }

    public void add(ServerThread serverThread) {
        listServerThreads.add(serverThread);
    }

    public void mutilCastSend(String message) {
        for (ServerThread serverThread : listServerThreads) {
            try {
                serverThread.write(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void boardCast(String id, String message) {
        for (ServerThread serverThread : listServerThreads) {
            if (serverThread.getClientNumber().equals(id)) {
                continue;
            } else {
                try {
                    serverThread.write(message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void boardCastFile(String id, String message) {
        for (ServerThread serverThread : listServerThreads) {
            if (serverThread.getClientNumber().equals(id)) {
                continue;
            } else {
                try {
                    serverThread.write("file," + message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void sendMessageToPersionFile(String id, String message) {
        for (ServerThread serverThread : listServerThreads) {
            if (id.equals(serverThread.getClientNumber())) {
                try {
                    serverThread.write("file" + "," + message);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public int getLength() {
        return listServerThreads.size();
    }

    public void sendOnlineList() {
        StringBuilder res = new StringBuilder();
        for (ServerThread serverThread : listServerThreads) {
            res.append(serverThread.getClientNumber()).append("-");
        }
        mutilCastSend("update-online-list" + "," + res.toString());
    }

    public void sendMessageToPersion(String id, String message) {
        for (ServerThread serverThread : listServerThreads) {
            if (id.equals(serverThread.getClientNumber())) {
                try {
                    serverThread.write("global-message" + "," + message);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void sendIconToPersion(String id, String message) {
        for (ServerThread serverThread : listServerThreads) {
            if (id.equals(serverThread.getClientNumber())) {
                try {
                    serverThread.write("global-icon" + "," + message);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void remove(String id) {
        listServerThreads.removeIf(serverThread -> serverThread.getClientNumber().equals(id));
    }
}
