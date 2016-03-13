package Admin;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BranchForReaders extends javax.swing.JFrame {
    Connection con; Statement s;
    private javax.swing.JComboBox branchcom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    
    public BranchForReaders() {
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

	public BranchForReaders(Connection c){
        init();
        this.con =c;
    }

    private void init() {

        branchcom = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        branchcom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Miller", "Domino", "Grease", "Helmet", "Circle", "Ted's", "Kramer", "Joyce", "Gyoza", "Kimchi", "Wall", "Battery", "Shake", "Eataly", "Lobster Tail", "Dumbo", "Park Slope", "Sunny", "Wood", "Island", "Brooklyn College" }));

        jLabel1.setText("BRANCH");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
           .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
           .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                 .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(branchcom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branchcom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(28, 28, 28))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	String bname = branchcom.getSelectedItem().toString();
        String q1 = "Select * from branch where name = '"+bname+"'";
        String bid = null;
        try {
            ResultSet r1 = this.con.createStatement().executeQuery(q1);
            while(r1.next()){
                bid = r1.getString("branchid");
            }
            r1.close();
            
            String q2 = "Select readerid,count(*) as count from borrow where branchid ='"+bid+"' group by readerid order by count desc";
            ResultSet r2 = this.con.createStatement().executeQuery(q2);
            List<String> books = new ArrayList();
            while(r2.next()){
                String name = r2.getString("readerid");
                String q3 = "Select * from reader where readerid = '"+name+"'";
                ResultSet r3 = this.con.createStatement().executeQuery(q3);
                while(r3.next()){
                    books.add(r3.getString("name"));
                }
                books.add(r2.getString("count"));
            }
            
            dispose();
            TopTenReader top = new TopTenReader(books);
            top.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(BranchForReaders.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BranchForReaders().setVisible(true);
            }
        });
    }
   
}