package Admin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PrintBranch extends javax.swing.JFrame {
	
	private static Connection con=null;
	private static Statement s=null;
	private JTextField branchid= new JTextField();
	
	private JButton print= new JButton("Print >>"); 
	private JButton menu= new JButton("<< MAIN MENU"); 
	
	String st1=null,st2=null;
	
	public PrintBranch(){
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
	 private void displayGUI() {
	        JOptionPane.showMessageDialog(
	            null, getPanel(), "Output : ",
	                JOptionPane.INFORMATION_MESSAGE);
	    }

	    private JPanel getPanel() {
	        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
	        JLabel Label1 = getLabel("Branch Name : " + st1);
	        JLabel Label2 = getLabel("Branch Location : " + st2);
	        
	        panel.add(Label1);
	        panel.add(Label2);
	       
	        return panel;
	    }	
 
private JLabel getLabel(String string) {
	 return new JLabel(string);
		}
	private void init() {

		branchid.setHorizontalAlignment(JTextField.LEFT);// Set inputs alignments
		
		
	JPanel p1=new JPanel(new GridLayout(1,0)); //Creating a panel for GUI
		
		p1.add(new JLabel("Enter the Branch ID"));
		p1.add(branchid);
		
		
		p1.setBorder(new TitledBorder("Branch Info"));
		add(p1,BorderLayout.NORTH);
		setSize(300,200);
		
		JPanel p2= new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.add(menu);
		p2.add(print);
		add(p2,BorderLayout.CENTER);
		print.addActionListener(new ButtonListener());
		menu.addActionListener(new ButtonListener());
		
}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		if(e.getActionCommand().equals("<< MAIN MENU")){
	    	dispose();
	    	AdminMenu aMenu=new AdminMenu();
	    	aMenu.setVisible(true);
	    	}
		
			
			if(e.getActionCommand().equals("Print >>")){
				String bid = branchid.getText();
		        
		        
		        String q = "select name,location from branch where branchid="+bid+"";
		        
		         try {
		        	ResultSet rs = s.executeQuery(q);
		        	while(rs.next()){
		              st1=rs.getString("name");
		              st2=rs.getString("location");
		            }
		        	
		        	}
		            catch (SQLException ex) {
		            Logger.getLogger(AddBookCopy.class.getName()).log(Level.SEVERE, null, ex);
		        } displayGUI();
		         
		         }
			
		}
	}
			
	

}
