package Admin;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class TopTenBooks extends javax.swing.JFrame {
    String branch; Statement s;
    Connection con;
    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    
    public TopTenBooks() {
        init();
        connectsql();
    }
    public TopTenBooks(String b) {
       this.branch = b;
       init();
       connectsql();
        find();
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
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Book Name"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("<< BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap(15, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
             .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(30, 30, 30))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        AdminMenu amenu=new AdminMenu();
        amenu.setVisible(true);
    }
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TopTenBooks().setVisible(true);
            }
        });
    }
   

    private void find(){
    	System.out.println(branch);
        String q1 = "select * from branch where name='"+branch+"'";
        String bid = null;
        try {
            ResultSet r1 = this.con.createStatement().executeQuery(q1);
            while(r1.next()){
               bid = r1.getString("branchid");
            }
            r1.close();
            String q2 = "Select branchid,bookid,count(*) as count from borrow where branchid='"+bid+"' group by bookid";
            ResultSet r2 = this.con.createStatement().executeQuery(q2);
            List<String> l = new ArrayList();
            while(r2.next()){
                l.add(r2.getString("bookid"));
                
            }
            
            List<String> isbn = new ArrayList();
            if(l.size() == 0){
                 JOptionPane op = new JOptionPane();
                 op.setMessage("There is no books borrowed at "+this.branch+" branch");
                 op.setMessageType(1);
                 JDialog dia = op.createDialog(null,"Info");
                 dia.setTitle("INFO");
                 dia.setVisible(true);   
                 
                 
            }else{       
             for(String s:l){
                   String q = "Select * from book where bookid='"+s+"'";
                   ResultSet rs = this.con.createStatement().executeQuery(q);
                   while(rs.next()){
                        isbn.add(rs.getString("isbn"));
                    }
                }
                List<String> names = new ArrayList();
                r2.close();
            
                for(String s:isbn){
                  String que = "Select * from bookinfo where isbn = '"+s+"'";
                  ResultSet res = this.con.createStatement().executeQuery(que);
                  while(res.next()){
                      if(!names.contains(res.getString("title")))
                       {
                           names.add(res.getString("title"));
                       }
                    }
                   }
            
               if(names.size() > 10){
                       for(int i = 0; i< 10 ; i++){
                      jTable1.setValueAt(names.get(i), i, 0);
                   }
               }else{
                     for(int i = 0; i< names.size() ; i++){
                          jTable1.setValueAt(names.get(i), i, 0);
                     }
              
           }
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(TopTenBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
