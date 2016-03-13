package Usermenu;
import Admin.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class BookReserve extends javax.swing.JFrame {
    Connection con; Statement s;
    String id=User.userID;
    private PreparedStatement preparedStatement;
    
    private javax.swing.JComboBox branchcom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
  
   
    public BookReserve() {
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

        branchcom = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        branchcom.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Miller, Downtown", "Domino, Mid West", "Grease, Upper East", "Helmet, Upper West", "Circle, Midtown", "Ted's, Gramercy", "Kramer, Kips Bay", "Joyce, Murray Hill", "Gyoza, Chinatown", "Kimchi, KTown", "Wall, Financial District", "Battery, Battery Park", "Shake, Harlem", "Eataly, East Willage", "Lobster Tail, Chelsea", "Dumbo, Brooklyn", "Park Slope, Brooklyn", "Sunny, Queens", "Wood, Queens", "Island, Roosevelt", "Brooklyn College, Brooklyn" }));
        branchcom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchcomActionPerformed(evt);
            }
        });

        jLabel2.setText("BRANCH");

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

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setToolTipText("");
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
             .addGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                 .addComponent(jButton2)
              .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addGap(49, 49, 49)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(branchcom, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                  .addComponent(jScrollPane1))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(branchcom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(19, 19, 19))
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        UserMenu uMenu=new UserMenu();
        uMenu.setVisible(true);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String book = jList1.getSelectedValue().toString();
        book = book.substring(book.indexOf(':')+2,book.indexOf('T')-1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String da = dateFormat.format(date);
        try {
             // the mysql insert statement
         String query = " insert into reserve (readerid, bookid, date)"
                         + " values (?, ?, ?)";

            preparedStatement = con
                 .prepareStatement(query);
            preparedStatement.setString(1, this.id);
            preparedStatement.setString(2, book);
            preparedStatement.setString(3, da);
           

          preparedStatement.execute();
          
          
          JOptionPane op = new JOptionPane();
          op.setMessage("The book "+book+" will remain reserved for 24 hours for you!");
          op.setMessageType(1);
          JDialog dia = op.createDialog(null,"INFO");
          dia.setTitle("RESERVED");
          dia.setVisible(true);

        } catch (SQLException e) {
            System.err.println("ERROR: "+e);
        }
        
    }

    private void branchcomActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            List<String> books = new ArrayList();
            DefaultListModel dl = new DefaultListModel();
            jList1.setModel(dl);
            String s = branchcom.getSelectedItem().toString();
            s = s.substring(0,s.indexOf(","));
            String query = "Select * from branch where name = '"+s+"'";
            ResultSet res = this.con.createStatement().executeQuery(query);
            while(res.next()){
                String q1 = "Select * from location where branchid = '"+res.getString("branchid")+"'";
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
		Date date = new Date();
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
   
 }