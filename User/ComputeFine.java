package Usermenu;
import Admin.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;




public class ComputeFine extends javax.swing.JFrame {
    Connection con;
    Statement s; String id=User.userID;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea maintxt;
   
  
    public ComputeFine() {
        init();
        connectsql();
        initialize();
        
    }
  
    private void connectsql() {
    	try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libdb", "root","maulik123");
            s = con.createStatement();
          
            System.out.println("connected");
        }catch(Exception e){
            System.err.println("ERROR: "+e);
        }
	
		
	}
    private void init() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        maintxt = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        maintxt.setColumns(20);
        maintxt.setRows(5);
        jScrollPane1.setViewportView(maintxt);

        jLabel2.setText("Book ID      |  Borrow Date   |    Passed Day Count   |     Fine");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                 .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    	dispose();
        UserMenu umenu=new UserMenu();
        umenu.setVisible(true);
    }
      public static void main(String args[]) {
        
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComputeFine().setVisible(true);
            }
        });
    }
   
  

    private void initialize() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String da = dateFormat.format(date);
            Date d1 = null;
            try {
                d1 = dateFormat.parse(da);
            } catch (ParseException ex) {
                Logger.getLogger(ComputeFine.class.getName()).log(Level.SEVERE, null, ex);
            }
            String query = "Select * from borrow where readerid ='"+this.id+"'";
            ResultSet r1 = this.con.createStatement().executeQuery(query);
            Date d2 = new Date();
            String bid = "";
            String bdate ="";
            long diff = 0;
            while(r1.next()){
                if(r1.getString("rdate")==null){
                    try {
                        bdate = r1.getString("bdate");
                        d2 = dateFormat.parse(bdate);
                        bid = r1.getString("bookid");
                        diff = d1.getTime() - d2.getTime();
                    } catch (ParseException ex) {
                        Logger.getLogger(ComputeFine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            r1.close();
            diff = diff / (1000*60*60*24);
            float f = 0;
            if(diff > 20){
                diff = diff - 20;
                f = (float) (0.2 * diff);
            }else{
                diff = 0;
            }
            
            maintxt.append(bid+"      |      "+bdate+"      |      "+diff+"      |      "+f);
        } catch (SQLException ex) {
            Logger.getLogger(ComputeFine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    
}