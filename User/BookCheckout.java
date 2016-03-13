package Usermenu;
import Admin.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;





public class BookCheckout extends javax.swing.JFrame {
    Connection con; Statement s;
    String id=User.userID;
    String bid;
    private javax.swing.JComboBox branchcom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    
    public BookCheckout() {
        init();
        connectsql();
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        branchcom = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("BOOK");

        jButton1.setText("SEARCH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        branchcom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Miller, Downtown", "Domino, Mid West", "Grease, Upper East", "Helmet, Upper West", "Circle, Midtown", "Ted's, Gramercy", "Kramer, Kips Bay", "Joyce, Murray Hill", "Gyoza, Chinatown", "Kimchi, KTown", "Wall, Financial District", "Battery, Battery Park", "Shake, Harlem", "Eataly, East Willage", "Lobster Tail, Chelsea", "Dumbo, Brooklyn", "Park Slope, Brooklyn", "Sunny, Queens", "Wood, Queens", "Island, Roosevelt", "Brooklyn College, Brooklyn" }));

        jScrollPane1.setViewportView(jList1);

        jButton2.setText("CHECKOUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("<< BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
         .addComponent(jScrollPane1)
            .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
           .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
        .addComponent(branchcom, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
          .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(branchcom, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         try {
            List<String> books = new ArrayList();
            DefaultListModel dl = new DefaultListModel();
            jList1.setModel(dl);
            String s = branchcom.getSelectedItem().toString();
            s = s.substring(0,s.indexOf(","));
            String query = "Select * from branch where name ='"+s+"'";
            ResultSet res = this.con.createStatement().executeQuery(query);
            while(res.next()){
                this.bid = res.getString("branchid");
                String q1 = "Select * from location where branchid = '"+this.bid+"'";
                ResultSet r1 = this.con.createStatement().executeQuery(q1);
                while(r1.next()){
                    System.out.println(r1.getString("bookid"));
                    books.add(r1.getString("bookid"));
                }r1.close();
            }res.close();
            List<String> books2 = new ArrayList();
            for(String bo:books){
                String q2 = "Select * from borrow where bookid = '"+bo+"'";
                ResultSet r3 = this.con.createStatement().executeQuery(q2);
                while(r3.next()){
                    if(r3.getString("rdate") == null){
                        books2.add(bo);
                    }
                }
            }
            for(String bo:books){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		String da = dateFormat.format(date);
                String q2 = "Select * from reserve where bookid = '"+bo+"'";
                ResultSet r3 = this.con.createStatement().executeQuery(q2);
                while(r3.next()){
                    if( !r3.getString("date").equals(da) ){
                        books2.add(bo);
                    }
                }
            }
            books.removeAll(books2);
            for(String bf : books){
                System.out.println(books);
            }
            if(books.size() == 0){
                dispose();
                JOptionPane op = new JOptionPane();
                op.setMessage("There are no available books at this time in that branch");
                op.setMessageType(0);
                JDialog dia = op.createDialog(null,"Error");
                dia.setTitle("OOPS");
                dia.setVisible(true);
            }else{
                List<String> names = new ArrayList();
                for(String b: books){
                    String q5 = "Select * from book where bookid='"+b+"'";
                    ResultSet r5 = this.con.createStatement().executeQuery(q5);
                    while(r5.next()){
                        String q6 = "Select * from bookinfo where isbn='"+r5.getString("isbn")+"'";
                        ResultSet r6 = this.con.createStatement().executeQuery(q6);
                        while (r6.next()){
                            names.add(r6.getString("title"));
                        }r6.close();
                    }r5.close();
                }
                for(int i = 0 ; i<names.size() ; i++){
                   dl.addElement("ID: "+books.get(i)+" TITLE: "+names.get(i));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BookReserve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
        	System.out.println(id);
            String book = jList1.getSelectedValue().toString();
            book = book.substring(book.indexOf(':')+2,book.indexOf('T')-1);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = new java.util.Date();
            String da = dateFormat.format(date);
            String query = " insert into borrow (bookid, readerid, branchid, bdate)"
                             + " values (?, ?, ?, ? )";

                PreparedStatement preparedStatement = this.con.prepareStatement(query);
                preparedStatement.setString(1, book);
                preparedStatement.setString(2, User.userID);
                preparedStatement.setString(3, bid);
                preparedStatement.setString(4, da);
                
                preparedStatement.execute();
               JOptionPane op = new JOptionPane();
                op.setMessage("Thank you for borrowing a book ");
                op.setMessageType(1);
                JDialog dia = op.createDialog(null,"Info");
                dia.setTitle("INFO");
                dia.setVisible(true);
              dispose();
        } catch (SQLException ex) {
            Logger.getLogger(BookCheckout.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    	 dispose();
         UserMenu uMenu = new UserMenu();
          uMenu.setVisible(true);
    }

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookCheckout().setVisible(true);
            }
        });
    }
   
   
}