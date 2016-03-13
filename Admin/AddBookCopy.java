package Admin;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddBookCopy extends javax.swing.JFrame {
	private static Connection con=null;
	private static Statement s=null;
	private JTextField title= new JTextField();
	private JTextField isbn= new JTextField();
	private JTextField pubName= new JTextField();
	private JTextField pubAdd= new JTextField();
	private JTextField pubDate= new JTextField();
	private JTextField branchId= new JTextField();
	private JTextField authorName= new JTextField();
	private JTextField copy= new JTextField();
	
	
	private JButton add= new JButton("ADD >>"); 
	private JButton menu= new JButton("<< MAIN MENU"); 
	 public AddBookCopy(){
	        init();
	        connectsql();}
	    
	   

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

@SuppressWarnings("unchecked")
private void init()
{
	title.setHorizontalAlignment(JTextField.LEFT);// Set inputs alignments
	isbn.setHorizontalAlignment(JTextField.LEFT);
	pubName.setHorizontalAlignment(JTextField.LEFT);
	pubAdd.setHorizontalAlignment(JTextField.LEFT);// Set inputs alignments
	pubDate.setHorizontalAlignment(JTextField.LEFT);
	authorName.setHorizontalAlignment(JTextField.LEFT);// Set inputs alignments
	branchId.setHorizontalAlignment(JTextField.LEFT);
	copy.setHorizontalAlignment(JTextField.LEFT);
	
JPanel p1=new JPanel(new GridLayout(8,0)); //Creating a panel for GUI
	
	p1.add(new JLabel("Title of the Book"));
	p1.add(title);
	p1.add(new JLabel("ISBN"));
	p1.add(isbn);
	p1.add(new JLabel("Publisher Name"));
	p1.add(pubName);
	p1.add(new JLabel("Publisher Location"));
	p1.add(pubAdd);
	p1.add(new JLabel("Publication Date"));
	p1.add(pubDate);
	p1.add(new JLabel("Author Name"));
	p1.add(authorName);
	p1.add(new JLabel("Branch ID"));
	p1.add(branchId);
	p1.add(new JLabel("Number of Copy"));
	p1.add(copy);
	p1.setBorder(new TitledBorder("Add a Book"));
	add(p1,BorderLayout.NORTH);
	setSize(400,400);
	
	JPanel p2= new JPanel(new FlowLayout(FlowLayout.CENTER));
	p2.add(menu);
	p2.add(add);
	add(p2,BorderLayout.CENTER);
	add.addActionListener(new ButtonListener());
	menu.addActionListener(new ButtonListener());
	
}
private class ButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		
	if(e.getActionCommand().equals("<< MAIN MENU")){
    	dispose();
    	AdminMenu aMenu=new AdminMenu();
    	aMenu.setVisible(true);
    	}
	
		
		if(e.getActionCommand().equals("ADD >>")){
			String btitle = title.getText();
	        String bisbn = isbn.getText();
	        String pname = pubName.getText();
	        String pdate = pubDate.getText();
	        String padd = pubAdd.getText();
	        String aname = authorName.getText();
	        String branch=branchId.getText();
	        String cpy= copy.getText();
	        
	        String q =  "INSERT INTO book (isbn) "
                     + "values ('"+bisbn+"')";
	        String q1= "INSERT INTO author (name) "
                    + "values ('"+aname+"')";
	        String q2= "INSERT INTO publisher (publishername,location) "
                    + "values ('"+pname+"','"+padd+"')";
	        String q3= "INSERT INTO copy (branchid, isbn, numcopy) "
                    + "values ('"+branch+"','"+bisbn+"','"+cpy+"')";
	        
	         try {
	        	int r = s.executeUpdate(q);
	        	if(r == 0){
	                System.out.print("off");
	            }
	        	int r1= s.executeUpdate(q1);
	        	if(r1 == 0){
	                System.out.print("off");
	            }
	        	int r2 = s.executeUpdate(q2);
	        	if(r2== 0){
	                System.out.print("off");
	            }
	        	int r3 = s.executeUpdate(q3);
	        	if(r3 == 0){
	                System.out.print("off");
	            }
	           }
	            catch (SQLException ex) {
	            Logger.getLogger(AddBookCopy.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	         	         
	         
	 	    }
				JOptionPane.showMessageDialog(
 	            null, "The Book has been successfully added! ", " ",
 	                JOptionPane.INFORMATION_MESSAGE);
	        
	         }
		
	}
}
		

