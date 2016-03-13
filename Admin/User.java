package Admin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class User extends javax.swing.JFrame{
	private Connection con=null;
	private Statement s=null;
	private JTextField userid= new JTextField();
	private JTextField password= new JTextField();
	private JButton login= new JButton("LOGIN"); 
	public static String userID;
	  public User(){
	        init();}
	    
	    public User(Connection c) {
	        init();
	        try{
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libdb", "root","maulik123");
	            s = con.createStatement();
	            
	        }catch(Exception e){
	            System.err.println("ERROR: "+e);
	        }}

private void init() {
		
			userid.setHorizontalAlignment(JTextField.LEFT);// Set inputs alignments
			password.setHorizontalAlignment(JTextField.LEFT);
			
			JPanel p1=new JPanel(new GridLayout(1,1,0,0)); //Creating a panel for GUI
			
			p1.add(new JLabel("READER ID"));
			p1.add(userid);
			p1.add(new JLabel("PASSWORD"));
			p1.add(password);
			p1.setBorder(new TitledBorder("READER LOGIN"));
			add(p1,BorderLayout.NORTH);
			setSize(400,300);
			JPanel p2= new JPanel(new FlowLayout(FlowLayout.CENTER));
			p2.add(login);
			add(p2,BorderLayout.CENTER);
			
			login.addActionListener(new ButtonListener());
			
		}
private class ButtonListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				if(e.getActionCommand().equals("LOGIN")){
					
					userID = userid.getText();
			        String pwd = password.getText();
			        String query =   "select password from reader where readerid = '"+userID+"'";
			      
			        try {
			        	ResultSet r = s.executeQuery(query);
			            if(r == null){
			                System.out.print("off");
			            }
			            if(r.next() == false){
			                JOptionPane op = new JOptionPane();
			                op.setMessage("The user "+userID+" doesn't exist  ");
			                op.setMessageType(0);
			                JDialog dia = op.createDialog(null,"Error");
			                dia.setTitle("LOGIN ERROR");
			                dia.setVisible(true);
			            }
			            else{
			            if(r.getString("password").equals(pwd)){
			                dispose();
			               UserMenu uMenu = new UserMenu();
			                uMenu.setVisible(true);;
			            }else{
			                JOptionPane op = new JOptionPane();
			                op.setMessage("The password for "+userID+" user is different  ");
			                op.setMessageType(0);
			                JDialog dia = op.createDialog(null,"Error");
			                dia.setTitle("LOGIN ERROR");
			                dia.setVisible(true);
			                
			            }
			           }
			        } catch (SQLException ex) {
			            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
			        }     
			        
			    }
			}
		}
}
