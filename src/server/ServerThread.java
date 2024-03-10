/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Base64;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import login.LoginForm;
import static server.Server.serverThreadBus;

/**
 *
 * @author Admin
 */
public class ServerThread implements Runnable {
    private JTextArea textArea;
    private Socket socketOfServer;
    private String clientNumber;
    private BufferedReader is;
    private BufferedWriter os;
    private boolean isClosed;

    public BufferedReader getIs() {
        return is;
    }

    public BufferedWriter getOs() {
        return os;
    }
    public Socket getSocketOfServer() {
        return socketOfServer;
    }
    public String getClientNumber() {
        return clientNumber;
    }
    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }
    public ServerThread(Socket socketOfServer, String clientNumber, JTextArea textArea) {
        this.socketOfServer = socketOfServer;
        this.clientNumber = clientNumber;
        this.textArea = textArea;
        textArea.append("\nServer thread number " + clientNumber + " Started\n");
        isClosed = false;
    }

    @Override
    public void run() {
        try {
            // Mở luồng vào ra trên Socket tại Server.
            is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            DataInputStream dataInputStream = new DataInputStream(socketOfServer.getInputStream());
            textArea.append("\nKhởi động luồng mới thành công, ID là: " + clientNumber + "\n");
            write("get-id" + "," + this.clientNumber);
            serverThreadBus.sendOnlineList();
            serverThreadBus.mutilCastSend("global-message"+","+"---Client "+this.clientNumber+" đã đăng nhập---");
            String message;
            while (!isClosed) {
                message = is.readLine();
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split(",");
                if(messageSplit[0].equals("send-to-global")){
                    serverThreadBus.boardCast(this.getClientNumber(),"global-message"+","+"Client "+messageSplit[2]+": "+messageSplit[1]);
                }
                if(messageSplit[0].equals("send-to-person")){
                    serverThreadBus.sendMessageToPersion(messageSplit[3],"Client "+ messageSplit[2]+" (tới bạn): "+messageSplit[1]);
                }
                if(messageSplit[0].equals("send-file-to-person")){
                    serverThreadBus.sendMessageToPersionFile(messageSplit[1], messageSplit[2]);
                }
                if(messageSplit[0].equals("send-file-to-all")) {
                    serverThreadBus.boardCastFile(this.getClientNumber(), messageSplit[1]);
                }
                if(messageSplit[0].equals("send-to-person-icon")) {
                    serverThreadBus.sendIconToPersion(messageSplit[3],"Client "+ messageSplit[2]+" (tới bạn): "+messageSplit[1]);
                }
                if(messageSplit[0].equals("send-to-global-icon")) {
                    serverThreadBus.boardCast(this.getClientNumber(),"global-icon"+","+"Client "+messageSplit[2]+": "+messageSplit[1]);
                }
            }              
        } catch (IOException e) {
            isClosed = true;
            serverThreadBus.remove(clientNumber);
            textArea.append(this.clientNumber+" đã thoát");
            serverThreadBus.sendOnlineList();
            serverThreadBus.mutilCastSend("global-message"+","+"---Client "+this.clientNumber+" đã thoát---");
        }
    }
        
    public void write(String message) throws IOException{
        os.write(message);
        os.newLine();
        os.flush();
    }
    

}