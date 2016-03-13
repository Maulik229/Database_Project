package Admin;

import java.sql.*;
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class TopTenReader extends javax.swing.JFrame {
    Connection con; Statement s;
    List<String> books;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablecik;
    
    public TopTenReader() {
        init();
        connectsql();
    }
    
	public TopTenReader(List<String> lis) {
        init();
        
        this.books = lis;
        fill();
    }
    
	private void connectsql() {
		
			try{
		        Class.forName("com.mysql.jdbc.Driver");
		        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libdb", "root","maulik123");
		        s = con.createStatement();
		        System.out.println("Connected to db");
		        
		    }catch(Exception e){
		        System.err.println("ERROR: "+e);
		    }
		
		
	}
    private void init() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablecik = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablecik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Reader Name", "Book Count"
            }
        ));
        jScrollPane1.setViewportView(tablecik);
        
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jButton2)
              .addComponent(jButton1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
           .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        BranchForReaders bfr=new BranchForReaders();
        bfr.setVisible(true);
       
    }
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TopTenReader().setVisible(true);
            }
        });
    }
    

    private void fill() {
        List<String> b = this.books;
     
      if(b.size() == 0){
              JOptionPane op = new JOptionPane();
              op.setMessage("There is no readers borrowed any books from this branch");
              op.setMessageType(1);
              JDialog dia = op.createDialog(null,"Info");
              dia.setTitle("INFO");
              dia.setVisible(true); 
        }else if(b.size()>20){
                int j = 0;
                for(int i = 0 ; i < 10 ; i++){
                    tablecik.setValueAt(b.get(j), i, 0);
                    tablecik.setValueAt(b.get(j+1), i, 1);
                    j = j+2;
                }
         }else{
                int j = 0;
                for(int i = 0 ; i < b.size()/2 ; i++){
                    tablecik.setValueAt(b.get(j), i, 0);
                    tablecik.setValueAt(b.get(j+1), i, 1);
                    j = j+2;
                }
            }
        for(String b12:books){
        System.out.println(b12);}
    }
}