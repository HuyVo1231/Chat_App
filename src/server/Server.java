package server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import login.LoginForm;

public class Server extends JFrame {
    private Set<String> loggedInUsers;
    public static volatile ServerThreadBus serverThreadBus;
    public static Socket socketOfServer;
    private JTextArea textArea;
    public void initForm() {
        // Set up the JFrame
        setTitle("Server Log");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the textArea
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font("Arial", Font.BOLD, 18));

        // Add components to the JFrame
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
    
    public Server() throws ClassNotFoundException {
        initForm();
        loggedInUsers = new HashSet<>();
        ServerSocket listener = null;
        serverThreadBus = new ServerThreadBus();
        textArea.append("Server đang chạy \n");
        textArea.append("Server is waiting to accept user...");

        try {
            listener = new ServerSocket(7777);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        try {
            while (true) {
                socketOfServer = listener.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socketOfServer.getInputStream());
                String clientNumber = (String) objectInputStream.readObject();
                    ServerThread serverThread = new ServerThread(socketOfServer, clientNumber, textArea);
                    serverThreadBus.add(serverThread);
                    textArea.append("\n 1 client đã kết nối\n");
                    textArea.append("Số thread đang chạy là: " + serverThreadBus.getLength());
                    new Thread(serverThread).start();
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                listener.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        Server server = new Server();
    }
   
}