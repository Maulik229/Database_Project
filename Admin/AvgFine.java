package Admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



public class AvgFine extends javax.swing.JFrame {
	
	
	private static Connection con=null;
	private static Statement s=null;
	private JTextField readerid= new JTextField();
	private JTextField name= new JTextField();
	private JTextField fine= new JTextField();
	private JButton search= new JButton("Search"); 
	private JButton menu= new JButton("<< MAIN MENU"); 
	 ArrayList finelist=new ArrayList();
	 double d = 0.0;
	 String st1=null;
    public AvgFine() {
        initComponents();
        connectsql();
    }
    
    private void displayGUI() {
        JOptionPane.showMessageDialog(
            null, getPanel(), "Output : ",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        JLabel Label1 = getLabel("Average Fine : " + d);
       
        panel.add(Label1);
       

        return panel;
    }	

    private JLabel getLabel(String string) {
   	 return new JLabel(string);
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
    
    private void initComponents() {
    	readerid.setHorizontalAlignment(JTextField.LEFT);// Set inputs alignments
		
		
    	JPanel p1=new JPanel(new GridLayout(3,0)); //Creating a panel for GUI
    	
    		p1.add(new JLabel("Enter the Reader ID"));
    		p1.add(readerid);
    		p1.add(new JLabel("Name"));
    		p1.add(name);
    		//p1.add(search,BorderLayout.CENTER);
    		
    		setSize(500,400);
    		p1.setBorder(new TitledBorder("Average Fine"));
    		add(p1,BorderLayout.NORTH);
    		
    		
    		JPanel p2= new JPanel(new FlowLayout(FlowLayout.CENTER));
    		//p2.add(new JLabel("Average Fine"));
    		p2.add(search);
    		//p2.add(menu);
    		add(p2,BorderLayout.CENTER);
    		
    		
    		
    		
    		search.addActionListener(new ButtonListener());
    		menu.addActionListener(new ButtonListener());
       
    }

    private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
        String id = readerid.getText();
        String query = "Select name from reader where readerid="+id+"";
        String rname = null;
       
        try {
            ResultSet r1 = s.executeQuery(query);            
            while(r1.next()){
                rname = r1.getString("name");
            }r1.close();
            name.setText(rname);
            
            if(rname==null){
            	JOptionPane.showMessageDialog(
    	 	            null, "The Reader ID does not exist!! ", " ",
    	 	                JOptionPane.ERROR_MESSAGE);
            }
            else {
            String q1 = "Select avg(fine) as avg from borrow where readerid ="+id+"";
            ResultSet r2 = s.executeQuery(q1);
            //String av =null;
            
            while(r2.next()){
                d = r2.getDouble("avg");
            }r2.close();
           
            
             displayGUI();     }
           
        } catch (SQLException ex) {
            Logger.getLogger(AvgFine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
 }}
