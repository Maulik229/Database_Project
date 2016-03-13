package Usermenu;
import Admin.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class BookSearch extends javax.swing.JFrame {
    Connection con; Statement s;
    
    private javax.swing.JTextField bookidtxt;
    private javax.swing.JLabel infolabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;

    public BookSearch() {
        init();
        connectsql();
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

        jLabel1 = new javax.swing.JLabel();
        bookidtxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        infolabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("BOOK ID:");

        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
           .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
           .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
         .addComponent(infolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGroup(layout.createSequentialGroup()
              .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(190, 190, 190)
             .addComponent(bookidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(bookidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        UserMenu umenu=new UserMenu();
        umenu.setVisible(true);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String msg = "";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            List<String> dates = new ArrayList();
            String da = dateFormat.format(date);
            String bid = bookidtxt.getText();
            String q1 = "Select * from borrow where bookid='"+bid+"'";
            String q2 = "Select * from reserve where bookid='"+bid+"'order by date desc";
            ResultSet r1 = this.con.createStatement().executeQuery(q1);
            ResultSet r2 = this.con.createStatement().executeQuery(q2);
            while(r2.next()){
                dates.add(r2.getString("date"));
            }
            if(r1.next() == false){
                if(dates.size() > 0 && dates.get(0) .equals(da)){
                    msg = "Book is reserved by another user";
                }else{
                        msg = "Available at ";
                        String ql = "Select * from location where bookid ='"+bid+"'";
                        ResultSet r3 = this.con.createStatement().executeQuery(ql);
                        while(r3.next()){
                            String qb = "Select * from branch where branchid ='"+r3.getString("branchid")+"'";
                            ResultSet r4 =  this.con.createStatement().executeQuery(qb);
                            while(r4.next()){
                                msg += r4.getString("name")+" ("+r4.getString("location")+") branch\nat position "+r3.getString("position");
                            }
                        }
                    }                
            }else{
                if(r1.getString("rdate") == null){
                    msg = "Book is borrowed by another user";
                }else{
                    if(dates.size() > 0 && dates.get(0) .equals(da)){                       
                         msg = "Book is reserved by another user";
                    }else{
                            msg = "Book is available at ";
                            String ql = "Select * from location where bookid ='"+bid+"'";
                            ResultSet r3 = this.con.createStatement().executeQuery(ql);
                            while(r3.next()){
                                  String qb = "Select * from branch where branchid ='"+r3.getString("branchid")+"'";
                                  ResultSet r4 =  this.con.createStatement().executeQuery(qb);
                                  while(r4.next()){
                                     msg += r4.getString("name")+"("+r4.getString("location")+") branch\nat position "+r3.getString("position");
                             }
                        }
                    }
                    }
                }            
            infolabel.setText(msg);
        } catch (SQLException ex) {
            Logger.getLogger(BookSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  }