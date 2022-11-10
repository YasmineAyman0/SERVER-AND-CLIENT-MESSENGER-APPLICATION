package server;

import java.io.*;   // for BufferReader & PrintWriter
import java.net.*;  // for Socket
import java.util.*; // for ArrayList

public class Server_Frame extends javax.swing.JFrame {

    ArrayList clientOutputStreams;
    ArrayList <String> users;
    
     public class ClientHandler implements Runnable{
         
        BufferedReader reader;
        Socket socket;
        PrintWriter client;
        
        public ClientHandler(Socket clientSocket , PrintWriter user)
        {
            client = user;
            try
            {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch(IOException ex)
            {
                TxtArea.append("Unexpected error....\n");
            }
        }
        @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    TxtArea.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                        TxtArea.append(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellEveryone(message);
                    } 
                    else 
                    {
                       TxtArea.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (IOException ex) 
             {
               TxtArea.append("Lost a connection. \n");
                //ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }
    
    public Server_Frame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtArea = new javax.swing.JTextArea();
        S_Exit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Clients_N = new javax.swing.JTextPane();
        B_Start = new javax.swing.JButton();
        B_Counter = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server\n");

        TxtArea.setBackground(new java.awt.Color(204, 255, 204));
        TxtArea.setColumns(20);
        TxtArea.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        TxtArea.setRows(5);
        jScrollPane1.setViewportView(TxtArea);

        S_Exit.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        S_Exit.setForeground(new java.awt.Color(255, 51, 0));
        S_Exit.setText("Exit Server");
        S_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                S_ExitActionPerformed(evt);
            }
        });

        Clients_N.setBackground(new java.awt.Color(204, 255, 204));
        Clients_N.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Clients_N.setForeground(new java.awt.Color(0, 0, 204));
        jScrollPane2.setViewportView(Clients_N);

        B_Start.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        B_Start.setForeground(new java.awt.Color(0, 51, 204));
        B_Start.setText("Start");
        B_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_StartActionPerformed(evt);
            }
        });

        B_Counter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_Counter.setText("Clients Connected:");
        B_Counter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_CounterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(S_Exit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 256, Short.MAX_VALUE)
                .addComponent(B_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(B_Counter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(S_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B_Counter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void S_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_S_ExitActionPerformed

        try
        {
            Thread.sleep(5000);  // sleep after 5 seconds
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        tellEveryone("Server: is stopping and all users will be diconnected! \n :chat");
        TxtArea.append("Server is stopping! \n");
        TxtArea.setText("");
    }//GEN-LAST:event_S_ExitActionPerformed

    private void B_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_StartActionPerformed
        Thread Starter = new Thread(new ServerStart());
        Starter.start();
        TxtArea.append("Server Started!\n");
    }//GEN-LAST:event_B_StartActionPerformed

    // counter for connected users
    private void B_CounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_CounterActionPerformed
     
        Clients_N.setText(String.valueOf(users.size()));
    }//GEN-LAST:event_B_CounterActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Server_Frame().setVisible(true);
            }
        });
    }
    
    // Server Socket class 
    public class ServerStart implements Runnable
    {
        @Override
        public void run()
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();
            
            try
            {
                ServerSocket serverSocket = new ServerSocket(2222);
                
                while(true)
                {
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream() , true);
                    clientOutputStreams.add(writer);
                    
                    // Server Thread
                    Thread listener = new Thread(new ClientHandler(clientSocket, writer ));
                    listener.start();
                    TxtArea.append("Got a connection! \n");
                }
            }
            catch(IOException ex)
            {
                TxtArea.append("Error making a connection! \n");
            }
        }
    }
    
    // Add users 
    public void userAdd(String data)
    {
        String message , add=":: Connect" , done ="Server::Done" , name=data;
        TxtArea.append(name + ": added! \n");
        users.add(name);
        
        // users list
        String[] tempList = new String [(users.size())];
        users.toArray(tempList);
         
        // loop for users tocken messages
        for(String token:tempList)
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    // Remove users
    public void userRemove(String data)
    {
        String message, add = "::Connect", done="Server:: Done" , name=data;
        users.remove(name);
        String[] tempList = new String [(users.size())];
        users.toArray(tempList);
        
        for(String token:tempList)
        {
            message = (token +add);
            tellEveryone(message);
        }
        tellEveryone(done);//
    }
    
    // Show messages in TxtArea for everyone
    public void tellEveryone(String message)
    {
        Iterator it = clientOutputStreams.iterator();
        
        while(it.hasNext())
        {
            try
            {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                TxtArea.append("Sending: "+ message + "\n");
                writer.flush();
                TxtArea.setCaretPosition(TxtArea.getDocument().getLength());
            }
            catch (Exception ex)
            {
                TxtArea.append("Error telling everyone! \n");
            }
        }        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Counter;
    private javax.swing.JButton B_Start;
    private javax.swing.JTextPane Clients_N;
    private javax.swing.JButton S_Exit;
    private javax.swing.JTextArea TxtArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
