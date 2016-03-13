package Admin;
import Usermenu.*;

import java.awt.BorderLayout;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JPanel;


public class UserMenu extends javax.swing.JFrame{

	JCheckBox searchBook, bookCheckout,bookReturm,bookReserve, computeFine,printReserved, printPublisher,quit; 
	CheckboxGroup group; 
	Label lab; Statement s;
	JPanel panel=new JPanel(new GridLayout(8,0));
	private Connection con;
	
	public UserMenu(){
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
		group=new CheckboxGroup();
		
		searchBook=new JCheckBox("Search a book by ID, title, or publisher name",false);
		bookCheckout=new JCheckBox("Book checkout",false);
		bookReturm=new JCheckBox("Book return",false);
		bookReserve=new JCheckBox("Book reserve",false);
		computeFine=new JCheckBox("Compute fine for a book copy borrowed by a reader based on the current date",false);
		printReserved=new JCheckBox("Print the list of book reserved by a reader and their status",false);
		printPublisher=new JCheckBox("Print the book id and titles of books published by a publisher",false);
		quit=new JCheckBox("Quit",false);
		
		setSize(600,500);
		panel.add(searchBook); 
		
		panel.add(bookCheckout); 
		
		panel.add(bookReturm);
		
		panel.add(bookReserve); 
		
		panel.add(computeFine); 
		
		panel.add(printReserved);
		
		panel.add(printPublisher); 
		
		panel.add(quit); 
		add(panel,BorderLayout.CENTER);
		 panel.setVisible(true);
		
		ActionListener actionListener = new ActionHandler();
		searchBook.addActionListener(actionListener);
		bookCheckout.addActionListener(actionListener);
		bookReturm.addActionListener(actionListener);
		bookReserve.addActionListener(actionListener);
		computeFine.addActionListener(actionListener);
		printReserved.addActionListener(actionListener);
		printPublisher.addActionListener(actionListener);
		quit.addActionListener(actionListener);
		
}
		
	class ActionHandler implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent event) {
	        JCheckBox checkbox = (JCheckBox) event.getSource();
	        if (checkbox == searchBook) {
	            dispose();
	            BookSearch bsearch= new BookSearch();
	            bsearch.setVisible(true);
	        } else if (checkbox == bookCheckout) {
	        	dispose();
	            BookCheckout bc= new BookCheckout();
	            bc.setVisible(true);
	        } else if (checkbox == bookReturm) {
	        	dispose();
	            BookReturn br= new BookReturn();
	            br.setVisible(true);
	        }else if (checkbox == bookReserve) {
	        	dispose();
	            BookReserve bres= new BookReserve();
	            bres.setVisible(true);
	        }else if (checkbox == computeFine) {
	        	dispose();
	            ComputeFine cf= new ComputeFine();
	            cf.setVisible(true);
	           
	        }else if (checkbox == printReserved) {
	        	dispose();
	            PrintBookList printbook= new PrintBookList();
	            printbook.setVisible(true);
	        } else if (checkbox == printPublisher) {
	        	dispose();
	            PrintPublisher printpub= new PrintPublisher();
	            printpub.setVisible(true);
	        }else if (checkbox == quit) {
	            dispose();
	        }


	    }}		
}
