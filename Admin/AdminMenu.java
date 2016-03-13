package Admin;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminMenu extends javax.swing.JFrame {
	
	JCheckBox addCopy, searchBook, addReader,printBranch,top10FreqBorrower, top10MostBorrowedBook,avgFine, quit; 
	CheckboxGroup group; 
	
	JPanel panel=new JPanel(new GridLayout(8,0));
	private Connection con;
	public AdminMenu(){
		init();
	}
	 public AdminMenu(Connection c) {
	        init();
	        this.con = c;}
	
	private void init() {
		group=new CheckboxGroup();
		
		addCopy=new JCheckBox("Add a book copy",false);
		searchBook=new JCheckBox("Search book copy and check its status",false);
		addReader=new JCheckBox("Add new reader",false);
		printBranch=new JCheckBox("Print branch information (name and location)",false);
		top10FreqBorrower=new JCheckBox("Print top 10 most frequent borrowers in a branch and the number of books each has borrowed",false);
		top10MostBorrowedBook=new JCheckBox("Print top 10 most borrowed books in a branch",false);
		avgFine=new JCheckBox("Find the average fine paid per reader",false);
		quit=new JCheckBox("Quit",false);
		
		setSize(600,500);
		panel.add(addCopy); 
		
		panel.add(searchBook); 
		
		panel.add(addReader);
		
		panel.add(printBranch); 
		
		panel.add(top10FreqBorrower); 
		
		panel.add(top10MostBorrowedBook);
		
		panel.add(avgFine); 
		
		panel.add(quit); 
		add(panel,BorderLayout.CENTER);
		 panel.setVisible(true);
		
		ActionListener actionListener = new ActionHandler();
		addCopy.addActionListener(actionListener);
		searchBook.addActionListener(actionListener);
		addReader.addActionListener(actionListener);
		printBranch.addActionListener(actionListener);
		top10FreqBorrower.addActionListener(actionListener);
		top10MostBorrowedBook.addActionListener(actionListener);
		avgFine.addActionListener(actionListener);
		quit.addActionListener(actionListener);
		
}
		
	class ActionHandler implements ActionListener {
	   
	    public void actionPerformed(ActionEvent event) {
	        JCheckBox checkbox = (JCheckBox) event.getSource();
	        if (checkbox == addCopy) {
	           dispose();
	           AddBookCopy abCopy=new AddBookCopy();
	           abCopy.setVisible(true);
	        } else if (checkbox == searchBook) {
	        	 dispose();
		           SearchById sCopy=new SearchById();
		           sCopy.setVisible(true);
	        } else if (checkbox == addReader) {
	            dispose();
	            AddReader addreader=new AddReader();
	            addreader.setVisible(true);
	        }else if (checkbox == printBranch) {
	        	dispose();
	            PrintBranch printbranch=new PrintBranch();
	            printbranch.setVisible(true);
	        } else if (checkbox == top10FreqBorrower) {
	        	dispose();
	            BranchForReaders topb=new BranchForReaders();
	            topb.setVisible(true);
	        }else if (checkbox == top10MostBorrowedBook) {
	           dispose();
	           BranchForBooks ttb=new BranchForBooks();
	           ttb.setVisible(true);
	        }else if (checkbox == avgFine) {
	            dispose();
	            AvgFine fine=new AvgFine();
	            fine.setVisible(true);
	        } else if (checkbox == quit) {
	            dispose();
	        }
	    }}		
}

	


