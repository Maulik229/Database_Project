package Admin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class AddReader extends javax.swing.JFrame {
	
	private static Connection con=null;
	private static Statement s=null;
	private JTextField name= new JTextField();
	private JTextField address= new JTextField();
	private JTextField phn= new JTextField();
	
	private JButton add= new JButton("ADD >>"); 
	private JButton menu= new JButton("<< MAIN MENU"); 
	
	public AddReader(){
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

	private void init() {

		name.setHorizontalAlignment(JTextField.LEFT);// Set inputs alignments
		address.setHorizontalAlignment(JTextField.LEFT);
		phn.setHorizontalAlignment(JTextField.LEFT);
		
		
	JPanel p1=new JPanel(new GridLayout(3,0)); //Creating a panel for GUI
		
		p1.add(new JLabel("Name"));
		p1.add(name);
		p1.add(new JLabel("Address"));
		p1.add(address);
		p1.add(new JLabel("Phone Number"));
		p1.add(phn);
		
		p1.setBorder(new TitledBorder("Add the Reader"));
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
				String rname = name.getText();
		        String raddress = address.getText();
		        String rphn = phn.getText();
		        
		        String q =  "INSERT INTO reader (name,address,phone) "
	                     + "values ('"+rname+"','"+raddress+"','"+rphn+"')";
		       
		        
		         try {
		        	int r = s.executeUpdate(q);
		        	if(r == 0){
		                System.out.print("off");
		            }
		        	int r1= s.executeUpdate(q);
		        	
		           }
		            catch (SQLException ex) {
		            Logger.getLogger(AddBookCopy.class.getName()).log(Level.SEVERE, null, ex);
		        } 
		         JOptionPane.showMessageDialog(
			 	            null, "The Reader has been successfully added! ", " ",
			 	                JOptionPane.INFORMATION_MESSAGE);
		         }
			
		}
	}
			
	

}
