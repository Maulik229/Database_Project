package Admin;

import java.applet.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Menu extends javax.swing.JFrame {
	 Connection con;
	
	private JButton admin= new JButton("ADMIN");
	private JButton reader= new JButton("READER");
	private JButton quit= new JButton("QUIT");

	private Statement st;
	
	public Menu(){
		init();
		connectsql();
	}
	

private void connectsql() {
	 try{
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libdb", "root","maulik123");
         st = con.createStatement();
         
     }catch(Exception e){
         System.err.println("ERROR: "+e);
     }
 }
		
public void init(){
	
	JPanel p1=new JPanel();
	p1.setBorder(new TitledBorder("Welcome to NYC public Library"));
	
	p1.add(admin);
	p1.add(reader);
	p1.add(quit);
	setSize(400,300); 
	add(p1,BorderLayout.CENTER);
	
	admin.addActionListener(new ButtonListener());
	reader.addActionListener(new ButtonListener());
	quit.addActionListener(new ButtonListener());
}
private class ButtonListener implements ActionListener{
	
	private Connection con;

	public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("ADMIN")){
				dispose();
		        Admin admin = new Admin(this.con);
		        admin.setVisible(true);
			}
		
		
		if(e.getActionCommand().equals("READER")){
			dispose();
			User user=new User(this.con);
			user.setVisible(true);
		}
		
		if(e.getActionCommand().equals("QUIT"))
			{System.exit(0);}
			
		}
	}
	
	
  
public static void main(String args[]) {
	java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Menu().setVisible(true);
        }
});
}

}

