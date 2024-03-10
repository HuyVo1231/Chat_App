/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import Connect.ketnoi;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import server.Server;
import static server.Server.serverThreadBus;

public class Client extends javax.swing.JFrame 
{    private boolean isOnline;
    
    private StringBuilder htmlContent = new StringBuilder();
    OutputStream outputStream;
    private JFileChooser fileChooser;
    private List<JButton> userButtons;
    private Connection conn = ketnoi.getConnection();
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement pre = null;
    private Thread thread;
    private BufferedWriter os;
    private BufferedReader is;
    private Socket socketOfClient;
    private List<String> onlineList;
    private String id;


public Client(String id) {
        fileChooser = new JFileChooser();
        userButtons = new ArrayList<>();
        initComponents();
        initComponents2();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jEditorPane1.setEditable(false);
        jTextArea2.setEditable(false);
        onlineList = new ArrayList<>();
        setUpSocket();
        this.id = id;
        init();
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jEditorPane1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        // Thay đổi font và màu sắc cho JComboBox
        jComboBox1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jComboBox1.setForeground(Color.BLACK);
        jComboBox1.setBackground(Color.WHITE);
        jComboBox1.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    setBackground(new Color(33, 150, 243)); // Màu khi item được chọn
                    setForeground(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
                return this;
            }
        }); 
    }
    private void createBtn(String username) {
        
            JButton button = new JButton(username);
            ImageIcon icon = new ImageIcon("D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\user_3177440.png");
            button.setIcon(icon);            
            button.setBackground(new Color(33, 150, 243));
            button.setFont(new Font("Segoe UI", Font.BOLD, 18));
            button.setPreferredSize(new Dimension(140, 50));
            button.setHorizontalAlignment(SwingConstants.LEFT); // Set text alignment to left
            button.addActionListener(e -> {
                int selectedIndex = jTabbedPane1.getSelectedIndex();
                if (selectedIndex == 0) {
                    jTabbedPane1.setSelectedIndex(1);
                }
                jLabel3.setText("Đang nhắn với " + button.getText());
                jComboBox1.setSelectedItem("Client " + button.getText());
            });
            right.add(button);
            userButtons.add(button);
            setButtonColor(username);
    }

    private void init() {
    right.setBackground(new Color(255, 255, 255));
    right.setPreferredSize(new Dimension(150, getHeight()));
    right.setLayout(new GridLayout(0, 1, 5, 5));

    try {
        String select = "select * from user";
        statement = conn.createStatement();
        resultSet = statement.executeQuery(select);

        while (resultSet.next()) {
            String username = resultSet.getString("username");
            createBtn(username);
        }

        ketnoi.closeConnection(conn);
    } catch (Exception e) {
        e.printStackTrace();
    }

    right.revalidate();
    right.repaint();
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        right = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Danh sách online", jPanel1);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/send-message.png"))); // NOI18N
        jButton1.setText("Gửi");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Chọn người nhân");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Nhập tin nhắn");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("{Người nhận}");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login_and_connect/icon/folder.png"))); // NOI18N
        jButton2.setText("File");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jEditorPane1.setBorder(null);
        jEditorPane1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jScrollPane3.setViewportView(jEditorPane1);

        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 633, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Nhắn tin", jPanel2);

        right.setPreferredSize(new java.awt.Dimension(150, 394));

        javax.swing.GroupLayout rightLayout = new javax.swing.GroupLayout(right);
        right.setLayout(rightLayout);
        rightLayout.setHorizontalGroup(
            rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );
        rightLayout.setVerticalGroup(
            rightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String messageContent = jTextField1.getText();
    if (messageContent.isEmpty()) {
        JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tin nhắn");
        return;
    }
    if (jComboBox1.getSelectedIndex() == 0) {
        try {
            write("send-to-global" + "," + messageContent + "," + this.id);
            addMessage("Bạn: " + messageContent,"right");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
        }
    } else {
        try {
            String[] parner = ((String) jComboBox1.getSelectedItem()).split(" ");
            // send-to-person,hello,0
            write("send-to-person" + "," + messageContent + "," + this.id + "," + parner[1]);
            addMessage("Bạn (tới Client " + parner[1] + "): " + messageContent,"right");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
        }
    }
        jTextField1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

private void addMessage(String message, String alignment) {
    jEditorPane1.setContentType("text/html");
    jEditorPane1.setEditable(false);

    HTMLEditorKit editorKit = (HTMLEditorKit) jEditorPane1.getEditorKit();
    HTMLDocument htmlDocument = (HTMLDocument) jEditorPane1.getDocument();

    try {
        String fontColor = "#000000"; 
        String backgroundColor = "#E6F7FF";
        String fontFamily = "Segoe UI, sans-serif";
        int fontSize = 14;

        
        String newHtml = String.format("<div style='text-align: %s; font-family: %s; font-size: %dpx;'><span style='background-color: %s; padding: 2px;'>%s</span></div><br/>",
                alignment, fontFamily, fontSize, backgroundColor, message);

        htmlContent.append(newHtml);

        jEditorPane1.setText(htmlContent.toString());
        jEditorPane1.setCaretPosition(jEditorPane1.getDocument().getLength());
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(jComboBox1.getSelectedIndex()==0){
            jLabel3.setText("Global");
        }
        else{
            jLabel3.setText("Đang nhắn với "+jComboBox1.getSelectedItem());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();

                if (jComboBox1.getSelectedIndex() == 0) {
                    // Gửi file tới tất cả client
                    sendFileToAll(filePath);
                } else {
                    // Gửi file tới một client cụ thể
                    String receiverId = ((String) jComboBox1.getSelectedItem()).split(" ")[1];
                    sendFileToPerson(filePath, receiverId);
                }
            }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        
            
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited

    }//GEN-LAST:event_jButton2MouseExited

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered

    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited

    }//GEN-LAST:event_jButton1MouseExited

private void addIcon(String message, String alignment) {
    jEditorPane1.setContentType("text/html");
    jEditorPane1.setEditable(false);
    String mess1[] = message.split(",");
    HTMLEditorKit editorKit = (HTMLEditorKit) jEditorPane1.getEditorKit();
    HTMLDocument htmlDocument = (HTMLDocument) jEditorPane1.getDocument();

    try {
        String fontColor = "#000000"; // Text color (black), you can change it as needed
        String backgroundColor = "#E6F7FF"; // Background color (light yellow), change as needed
        String fontFamily = "Segoe UI, sans-serif"; // Font family, change as needed
        int fontSize = 14; // Font size, change as needed

        // Set HTML for the message with the selected font, size, color, and background
        String newHtml = String.format("<div style='text-align:%s;'><img src='%s' width='48px' height='48px'/></div>",
                alignment,mess1[1]);
        htmlContent.append(newHtml);
        jEditorPane1.setText(htmlContent.toString());
        jEditorPane1.setCaretPosition(jEditorPane1.getDocument().getLength());
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}    

    
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        String messageContent = "file:///D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\smile.png";
    if (jComboBox1.getSelectedIndex() == 0) {
            try {
                write("send-to-global-icon" + "," + messageContent + "," + this.id);
                addIcon("Bạn :,"+messageContent,"right");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    else {  
        try {
            String[] parner = ((String) jComboBox1.getSelectedItem()).split(" ");
            write("send-to-person-icon" + "," + messageContent + "," + this.id + "," + parner[1]);
            addIcon("Bạn :,"+messageContent,"right");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
                String messageContent = "file:///D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\icons8-crying-48.png";
    if (jComboBox1.getSelectedIndex() == 0) {
            try {
                write("send-to-global-icon" + "," + messageContent + "," + this.id);
                addIcon("Bạn :,"+messageContent,"right");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    else {
        try {
            String[] parner = ((String) jComboBox1.getSelectedItem()).split(" ");
            write("send-to-person-icon" + "," + messageContent + "," + this.id + "," + parner[1]);
            addIcon("Bạn :,"+messageContent,"right");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        
        String messageContent = "file:///D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\icons8-angry-48.png";
        if (jComboBox1.getSelectedIndex() == 0) {
                try {
                    write("send-to-global-icon" + "," + messageContent + "," + this.id);
                    addIcon("Bạn :,"+messageContent,"right");
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else {
            try {
            String[] parner = ((String) jComboBox1.getSelectedItem()).split(" ");
            write("send-to-person-icon" + "," + messageContent + "," + this.id + "," + parner[1]);
                addIcon("Bạn :,"+messageContent,"right");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        
        String messageContent = "file:///D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\icons8-like-48.png";
        if (jComboBox1.getSelectedIndex() == 0) {
                try {
                    write("send-to-global-icon" + "," + messageContent + "," + this.id);
                    addIcon("Bạn :,"+messageContent,"right");
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else {
            try {
            String[] parner = ((String) jComboBox1.getSelectedItem()).split(" ");
            write("send-to-person-icon" + "," + messageContent + "," + this.id + "," + parner[1]);
                addIcon("Bạn :,"+messageContent,"right");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        
        String messageContent = "file:///D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\icons8-heart-48.png";
        if (jComboBox1.getSelectedIndex() == 0) {
                try {
                    write("send-to-global-icon" + "," + messageContent + "," + this.id);
                    addIcon("Bạn :,"+messageContent,"right");
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else {
            try {
            String[] parner = ((String) jComboBox1.getSelectedItem()).split(" ");
            write("send-to-person-icon" + "," + messageContent + "," + this.id + "," + parner[1]);
                addIcon("Bạn :,"+messageContent,"right");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void sendFileToAll(String filePath) {
    try {
        // Đọc dữ liệu từ file thành mảng byte
        byte[] fileData = Files.readAllBytes(Paths.get(filePath));

        // Encode mảng byte thành Base64 để gửi dữ liệu qua mạng
        String encodedFile = Base64.getEncoder().encodeToString(fileData);

        // Gửi message và file tới server
        write("send-file-to-all," + encodedFile);
        JOptionPane.showMessageDialog(null, "Đã gửi file ");

    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

    private void sendFileToPerson(String filePath, String receiverId) {
    try {
        // Đọc dữ liệu từ file thành mảng byte
        byte[] fileData = Files.readAllBytes(Paths.get(filePath));

        // Encode mảng byte thành Base64 để gửi dữ liệu qua mạng
        String encodedFile = Base64.getEncoder().encodeToString(fileData);

        // Gửi message và file tới server
        write("send-file-to-person," + receiverId + "," + encodedFile);
        JOptionPane.showMessageDialog(null, "Đã gửi file ");
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}
    
    private String getReceiverId() {
    if (jComboBox1.getSelectedIndex() == 0) {
        return "global";
    } else {
        String[] partner = ((String) jComboBox1.getSelectedItem()).split(" ");
        return partner[1];
        }
    }
    private void setUpSocket() {
        try {
            thread = new Thread() {
                @Override
                public void run() {

                    try {
                        // Gửi yêu cầu kết nối tới Server đang lắng nghe
                        // trên máy 'localhost' cổng 7777.
                        socketOfClient = new Socket("localhost", 7777);
                        System.out.println("Kết nối thành công!");
                        // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
                        os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
                        // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
                        is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
                        
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketOfClient.getOutputStream());

                    // Gửi tên tài khoản cho ServerThread
                    objectOutputStream.writeObject(id);
                        String message;
                        while (true) {
                            
                            message = is.readLine();
                            if(message==null){
                                break;
                            }
                            String[] messageSplit = message.split(",");
                            if(messageSplit[0].equals("get-id")){
                                setID(messageSplit[1]);
                                setIDTitle();
                            }
                            if (messageSplit[0].equals("update-online-list")) {
                                onlineList = new ArrayList<>();
                                String online ="";
                                String[] onlineSplit = messageSplit[1].split("-");
                                for(int i=0; i<onlineSplit.length; i++){
                                    onlineList.add(onlineSplit[i]);
                                    online+="Client "+onlineSplit[i]+" đang online\n";
                                    setButtonColor(onlineSplit[i]);
                                    
                                }
                                jTextArea2.setText(online);
                                updateCombobox();
                            }
                            if (messageSplit[0].equals("global-message")) {
                                    addMessage(messageSplit[1],"left");
                                    jEditorPane1.setCaretPosition(jEditorPane1.getDocument().getLength());
                                }
                            if (messageSplit[0].equals("global-icon")) {
                                String[] c = messageSplit[1].split(": ");
                                String cong = "a," + c[1];
                                addIcon(cong,"left");
                                jEditorPane1.setCaretPosition(jEditorPane1.getDocument().getLength());
                                }            
                            if(messageSplit[0].equals("file")){
                              try {
                                // Decode Base64 thành mảng byte
                                byte[] fileData = Base64.getDecoder().decode(messageSplit[1]);

                                // Hiển thị hộp thoại để chọn vị trí lưu file
                                JFileChooser fileChooser = new JFileChooser();
                                int result = fileChooser.showSaveDialog(null);

                                if (result == JFileChooser.APPROVE_OPTION) {
                                    File selectedFile = fileChooser.getSelectedFile();
                                    String filePath = selectedFile.getAbsolutePath();

                                    // Lưu mảng byte thành file
                                    Files.write(Paths.get(filePath), fileData);
                                    JOptionPane.showMessageDialog(null, "Đã nhận file");
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }  
                            }
                            
                        }   
                    } catch (UnknownHostException e) {
                        return;
                    } catch (IOException e) {
                        return;
                    }
                }
            };
            thread.start();
        } catch (Exception e) {
        }
    }

    
    private void updateCombobox(){
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Gửi tất cả");
        String idString = ""+this.id;
        for(String e : onlineList){
            if(!e.equals(idString)){
                jComboBox1.addItem("Client "+e);
            }
        }
    }

    private void setButtonColor(String username) {
            if (onlineList.contains(username)) {
                getUserButton(username).setBackground(new Color(33, 150, 243));
            } else {
                getUserButton(username).setBackground(new Color(255, 0, 0));
            }
        }

        private JButton getUserButton(String username) {
            for (JButton button : userButtons) {
                if (button.getText().equals(username)) {
                    return button;
                }
            }
            return null;
        }
    
    
    private void setIDTitle(){
        this.setTitle("Client "+this.id);
    }
    private void setID(String id){
        this.id = id;
    }
    private void write(String message) throws IOException{
        os.write(message);
        os.newLine();
        os.flush();
    }
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String args[]) {
        new Client("Huy");
    }
    private void initComponents2() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Customize the appearance of the JFrame
        getContentPane().setBackground(new Color(240, 240, 240));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        // Customize the appearance of the tabs
        jTabbedPane1.setBackground(new Color(255, 255, 255));
        jTabbedPane1.setOpaque(true);

        // Customize the appearance of the JTextArea
        jTextArea2.setBackground(new Color(230, 230, 230));
        jTextArea2.setLineWrap(true);

        // Customize the appearance of the JPanel
        right.setBackground(new Color(255, 255, 255));
        
        right.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(200, 200, 200)));
        // Customize the appearance of the JTextField
        jTextField1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        jTextField1.setBackground(new Color(240, 240, 240));
        // Emoji Smile
        jButton3.setForeground(Color.WHITE);
        jButton3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jButton3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        ImageIcon smile = new ImageIcon("D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\smile.png");
        jButton3.setIcon(smile);
        // Emoji Crying
        jButton4.setForeground(Color.WHITE);
        jButton4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jButton4.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        ImageIcon cry = new ImageIcon("D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\icons8-crying-48.png");
        jButton4.setIcon(cry);
        // Emoji Angry
        jButton5.setForeground(Color.WHITE);
        jButton5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jButton5.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        ImageIcon angry = new ImageIcon("D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\icons8-angry-48.png");
        jButton5.setIcon(angry);
        // Emoji Like
        jButton6.setForeground(Color.WHITE);
        jButton6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jButton6.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        ImageIcon like = new ImageIcon("D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\icons8-like-48.png");
        jButton6.setIcon(like);
        // Emoji Heart
        jButton7.setForeground(Color.WHITE);
        jButton7.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jButton7.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        ImageIcon heart = new ImageIcon("D:\\hoc\\nam 3\\HK2\\LapTrinh_Mang\\Project_ChatSocket\\src\\login_and_connect\\icon\\icons8-heart-48.png");
        jButton7.setIcon(heart);  
        
        
        // Customize the appearance of the JButton
        jButton1.setBackground(new Color(33, 150, 243));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jButton1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jTabbedPane1.setBorder(BorderFactory.createEmptyBorder());
        jTabbedPane1.setUI(new NoBorderTabbedPaneUI());
        jComboBox1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jComboBox1.setForeground(Color.BLACK);
        jComboBox1.setBackground(Color.WHITE);
        jComboBox1.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    setBackground(new Color(33, 150, 243)); // Màu khi item được chọn
                    setForeground(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
                return this;
            }
        });
    }
    
    private static class NoBorderTabbedPaneUI extends BasicTabbedPaneUI {
        protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            // Do nothing to remove the border
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel right;
    // End of variables declaration//GEN-END:variables
}
