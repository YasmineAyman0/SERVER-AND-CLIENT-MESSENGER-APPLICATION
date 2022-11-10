package client;

import java.io.*;     // For BufferedReader & PrintWriter
import java.net.*;    // For Socket
import java.util.*;   // For ArrayList
import javax.swing.JOptionPane;

public class Client_Frame extends javax.swing.JFrame {

    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    
    String username,address="127.0.0.1";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    
    //-----------------------------------------------//
    
    public void ListenThread()
    {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }
    
    //-----------------------------------------------//
    
    public void userAdd(String data)
    {
        users.add(data);
    }
    
    //-----------------------------------------------//
    
    public void userRemove(String data)
    {
        TxtArea.append(data + " is now offline! \n");
    }
    
    //-----------------------------------------------//
    
    public void writeUsers()
    {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for(String token:tempList)
        {
           // TxtArea.append(token+"\n");
        }
    }
    
    //-----------------------------------------------//
    
    // Disconnect message " bye "
    public void sendDisConnect()
    {
        String bye = (username +":: disconnected");
        try
        {
            writer.println("bye \n" + bye);
            writer.flush();
        }
        catch(Exception e)
        {
            TxtArea.append("Couldn't send Disconnect message! \n");
        }
    }
    
    //-----------------------------------------------//
    
    public void Disconnect()
    {
        try
        {
            TxtArea.append(username +": Disconnected!");
            socket.close();
        }
        catch(IOException ex)
        {
            TxtArea.append("Failed to disconnect! \n");
        }
        isConnected = false;
    }
    
    public Client_Frame()
    {
        initComponents();
    }
    
    //-----------------------------------------------//
    public class IncomingReader implements Runnable
    {
        @Override
        public void run()
        {
            String[] data;
            String stream , done="Done" ,  connect="Connect" , disconnect="Disconnect" , chat="Chat";
        
            try
            {
                while ((stream = reader.readLine()) != null)
                {
                    data=stream.split(":");
                
                    if(data[2].equals(chat))
                    {
                        TxtArea.append(data[0] + ": " + data[1] + "\n");
                        TxtArea.setCaretPosition(TxtArea.getDocument().getLength());
                    }
                    else if(data[2].equals(connect))
                    {
                        TxtArea.removeAll();
                        userAdd(data[0]);
                    }
                    else if(data[2].equals(disconnect))
                    {
                        userRemove(data[0]);
                    }
                    else if(data[2].equals(done))
                    {
                        writeUsers();
                        users.clear();
                    }
                }
            }catch (Exception ex){}
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TxtArea = new javax.swing.JTextArea();
        IP = new javax.swing.JTextField();
        B_disConnect = new javax.swing.JButton();
        B1 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        T_Msg = new javax.swing.JTextField();
        B_Send = new javax.swing.JButton();
        B_Connect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TxtArea.setBackground(new java.awt.Color(204, 255, 204));
        TxtArea.setColumns(20);
        TxtArea.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        TxtArea.setRows(5);
        jScrollPane1.setViewportView(TxtArea);

        IP.setBackground(new java.awt.Color(204, 255, 204));
        IP.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        IP.setText("IP:");
        IP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IPMouseClicked(evt);
            }
        });
        IP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IPActionPerformed(evt);
            }
        });

        B_disConnect.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        B_disConnect.setForeground(new java.awt.Color(153, 0, 0));
        B_disConnect.setText("Disconnect");
        B_disConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_disConnectActionPerformed(evt);
            }
        });

        B1.setText(":)");
        B1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B1MouseClicked(evt);
            }
        });

        B2.setText(":D");
        B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B2ActionPerformed(evt);
            }
        });

        B3.setText(":P");
        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });

        B4.setText("^_^");
        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });

        B5.setText(";)");
        B5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B5ActionPerformed(evt);
            }
        });

        B6.setText(":(");
        B6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B6ActionPerformed(evt);
            }
        });

        B7.setText(":'(");
        B7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B7ActionPerformed(evt);
            }
        });

        T_Msg.setBackground(new java.awt.Color(204, 255, 204));
        T_Msg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        T_Msg.setText("Type your message here! ^_^");
        T_Msg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                T_MsgMouseClicked(evt);
            }
        });

        B_Send.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        B_Send.setForeground(new java.awt.Color(0, 153, 0));
        B_Send.setText("Send");
        B_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_SendActionPerformed(evt);
            }
        });

        B_Connect.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        B_Connect.setForeground(new java.awt.Color(0, 0, 204));
        B_Connect.setText("Connect");
        B_Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(B1)
                        .addGap(3, 3, 3)
                        .addComponent(B2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(T_Msg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(IP)
                    .addComponent(B_disConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B_Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B_Connect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(IP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(B_Connect, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(B_disConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 267, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B1)
                    .addComponent(B2)
                    .addComponent(B3)
                    .addComponent(B4)
                    .addComponent(B5)
                    .addComponent(B6)
                    .addComponent(B7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(T_Msg, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(B_Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_SendActionPerformed
     String nothing = "";
        if ((T_Msg.getText()).equals(nothing)) {
            T_Msg.setText("");
            T_Msg.requestFocus();
        } else {
            try {
               writer.println(username + ":" + T_Msg.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                TxtArea.append("Message was not sent. \n");
            }
            T_Msg.setText("");
           T_Msg.requestFocus();
        }

       T_Msg.setText("");
        T_Msg.requestFocus();
    }//GEN-LAST:event_B_SendActionPerformed

    // " T_Msg "  ->> deleting the defult message with mouse one click
    private void T_MsgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T_MsgMouseClicked
        T_Msg.setText(null);
    }//GEN-LAST:event_T_MsgMouseClicked

    
    //************* Emotions *************//
    // emotion #1
    private void B1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B1MouseClicked
        T_Msg.setText( " :) ");
    }//GEN-LAST:event_B1MouseClicked

    // emotion #2
    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B2ActionPerformed
       T_Msg.setText( " :D ");
    }//GEN-LAST:event_B2ActionPerformed

    // emotion #3
    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        T_Msg.setText( " :P ");
    }//GEN-LAST:event_B3ActionPerformed

    // emotion #4
    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
       T_Msg.setText( " ^_^ ");
    }//GEN-LAST:event_B4ActionPerformed

    // emotion #5
    private void B5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B5ActionPerformed
       T_Msg.setText( " ;) ");
    }//GEN-LAST:event_B5ActionPerformed

    // emotion #6
    private void B6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B6ActionPerformed
        T_Msg.setText( " ;( ");
    }//GEN-LAST:event_B6ActionPerformed

    // emotion #7
    private void B7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B7ActionPerformed
        T_Msg.setText( " :'( ");
    }//GEN-LAST:event_B7ActionPerformed

    // IP address
    private void IPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IPActionPerformed
       IP.getText();
    }//GEN-LAST:event_IPActionPerformed

    private void B_ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ConnectActionPerformed
       
       username = JOptionPane.showInputDialog(null,"Enter your name!"); 
       this.setTitle("Client: " + username);
      
       if(isConnected == false)
       {
           address = IP.getText();
           try
           {
                socket = new Socket(address, port); 
                InputStreamReader streamreader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(socket.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            }
            catch(Exception ex)
            {
               TxtArea.append("Cannot Connect , Try again! \n");
            }
            ListenThread(); 
       }
       else if(isConnected == true)
               {
                   TxtArea.append("You are already connected. \n");
               }
    }//GEN-LAST:event_B_ConnectActionPerformed

    private void B_disConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_disConnectActionPerformed
    
        sendDisConnect();
        Disconnect();
    }//GEN-LAST:event_B_disConnectActionPerformed

    private void IPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IPMouseClicked
        IP.setText(null);
    }//GEN-LAST:event_IPMouseClicked

   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B1;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B_Connect;
    private javax.swing.JButton B_Send;
    private javax.swing.JButton B_disConnect;
    private javax.swing.JTextField IP;
    private javax.swing.JTextField T_Msg;
    private javax.swing.JTextArea TxtArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
