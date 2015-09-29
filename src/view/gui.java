package view;
import java.awt.EventQueue;

import javax.swing.JFrame; 
import javax.swing.JTabbedPane; 

import java.awt.BorderLayout; 

import javax.swing.JButton; 
import javax.swing.JScrollPane;
import javax.swing.JTextPane; 
import javax.swing.JTextField; 
import javax.swing.JLabel; 
import javax.swing.JTable; 
import javax.swing.JComboBox; 
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints; 
import java.awt.Insets; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;
import connector.*;
import controller.*;

public class gui {

private JFrame frame;
private JTextField txtSkrivInKurskodpersonnummer;
private JTextField txtPersonnummer;
private JTextField txtsNamn;
private JTextField txtAdress;
private JTextField txtKursnummer;
private JTextField textField_1;
private static Controller controller;
private JTextField txtPong;
private JTextField txtcNamn;
private JTable Student;
private JTable Kurs;
private JTextField TxtSpnrRegStudied;
private JTextField txtCcodetoStudied;

/**
 * Launch the application.
 */
public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
        	controller = new Controller();
            try {
                gui window = new gui();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}

/**
 * Create the application.
 */
public gui() {
    initialize();
}

/**
 * Initialize the contents of the frame.
 */
private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 627, 599);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(0, 0, 606, 21);
    frame.getContentPane().add(tabbedPane);

    JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.addTab("New tab", null, tabbedPane_1, null);

    JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.addTab("New tab", null, tabbedPane_2, null);

    JLabel lblAngeKurskodpersonnummer = new JLabel("Ange kurskod/personnummer");
    lblAngeKurskodpersonnummer.setBounds(10, 19, 190, 16);
    frame.getContentPane().add(lblAngeKurskodpersonnummer);

    txtSkrivInKurskodpersonnummer = new JTextField();
    txtSkrivInKurskodpersonnummer.setBounds(10, 40, 134, 28);
    frame.getContentPane().add(txtSkrivInKurskodpersonnummer);
    txtSkrivInKurskodpersonnummer.setColumns(10);

    JScrollPane spKurs = new JScrollPane();
    spKurs.setBounds(323, 114, 294, 153);
    frame.getContentPane().add(spKurs);
    
    String[] colkurs = {"Course ID", "Course Name", "Course Points"};
    DefaultTableModel tableKurs = new DefaultTableModel (colkurs, 0);
    
    
     Kurs = new JTable(tableKurs);
    spKurs.setViewportView(Kurs);

    JButton btnSkKurs = new JButton("Sök Kurs");
    DefaultTableModel model = new DefaultTableModel();
    btnSkKurs.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    String ccode = txtSkrivInKurskodpersonnummer.getText();
	if(!ccode.isEmpty()) {
	Course c = null;
	try {
		c = controller.findCourse(ccode);
	} catch (SQLException e1) {
		
		e1.printStackTrace();
	}
	Object[] courseData = {c.getCcode(), c.getcName(), c.getPoints() };
	tableKurs.addRow(courseData);
	}
}
});
    /*btnSkKurs.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {

    			ArrayList<Course> courseList = controller.getCoursesList();

    			for (Course c : courseList) {
    				String[] col = new String[3];
    				col[0] = c.getCcode();
    				col[1] = c.getcName();
    				col[2] = c.getPoints();
    			}} catch (SQLException e5) {
    				}  
    	    	 try {
    	    		 ArrayList<Course> courseList = controller.getCoursesList();
    	    		 
    	    		    for (Course c : courseList) {
    	    		    	
    	    		        model.addRow(new String[] {c.getCcode(), c.getcName(), c.getPoints()});
    	    		    }
    	    		    } catch (Exception e7) {}
    	}
    });*/
    btnSkKurs.setBounds(127, 73, 99, 29);
    frame.getContentPane().add(btnSkKurs);
    
    JScrollPane spStudent = new JScrollPane();
    spStudent.setBounds(10, 114, 294, 153);
    frame.getContentPane().add(spStudent);
    
    String[] colstudent = {"Student ID", "Student Name", "Student Adress"};
    DefaultTableModel tableStudent = new DefaultTableModel (colstudent, 0);
    
    
     Student = new JTable(tableStudent);
    spStudent.setViewportView(Student);
    

    JButton btnSkStudent = new JButton("Sök Student");
    btnSkStudent.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String spnr = txtSkrivInKurskodpersonnummer.getText();
    		if(!spnr.isEmpty()) {
    		Student s = null;
			try {
				s = controller.findStudent(spnr);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
    		Object[] studData = {s.getSpnr(), s.getsName(), s.getsAdress() };
    		tableStudent.addRow(studData);
    		}
    	}
    });
    
    btnSkStudent.setBounds(10, 73, 119, 29);
    frame.getContentPane().add(btnSkStudent);

    JLabel lblLggTillStudent = new JLabel("Lägg till Student");
    lblLggTillStudent.setBounds(10, 325, 103, 16);
    frame.getContentPane().add(lblLggTillStudent);

    txtPersonnummer = new JTextField();
    txtPersonnummer.setBounds(10, 347, 134, 28);
    txtPersonnummer.setText("Personnummer");
    frame.getContentPane().add(txtPersonnummer);
    txtPersonnummer.setColumns(10);

    txtsNamn = new JTextField();
    txtsNamn.setBounds(10, 380, 134, 28);
    txtsNamn.setText("Namn");
    frame.getContentPane().add(txtsNamn);
    txtsNamn.setColumns(10);

    txtAdress = new JTextField();
    txtAdress.setBounds(10, 413, 134, 28);
    txtAdress.setText("Adress");
    txtAdress.setColumns(10);
    frame.getContentPane().add(txtAdress);

    JLabel lblVljKurs = new JLabel("Välj kurs");
    lblVljKurs.setBounds(14, 513, 99, 16);
    frame.getContentPane().add(lblVljKurs);

    JComboBox comboBox = new JComboBox();
    comboBox.setBounds(10, 529, 134, 27);
    frame.getContentPane().add(comboBox);
   	try {
   		
   		String[] strArray = controller.convertCoursestoArray();
   		for (String s : strArray){
   			comboBox.addItem(s);
   		}
   	} catch (SQLException e1) {
   		e1.printStackTrace();
   	}


    JButton btnRegisterastud = new JButton("Registera Student"); 
    btnRegisterastud.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
    		String spnr = txtPersonnummer.getText().trim();
    		String sName = txtsNamn.getText().trim();
    		String adress = txtAdress.getText().trim();
    		
    		
    		try {
    			controller.addStudent(spnr, sName, adress);
    			
    			}catch(SQLException e2) {
    		} 
    	}
    });
    btnRegisterastud.setBounds(10, 448, 134, 29);
    frame.getContentPane().add(btnRegisterastud);

    JLabel lblLggTillKurs = new JLabel("Lägg till Kurs");
    lblLggTillKurs.setBounds(174, 325, 103, 16);
    frame.getContentPane().add(lblLggTillKurs);

    txtKursnummer = new JTextField();
    txtKursnummer.setText("Kurskod");
    txtKursnummer.setColumns(10);
    txtKursnummer.setBounds(174, 347, 134, 28);
    frame.getContentPane().add(txtKursnummer);
    
    txtcNamn = new JTextField();
    txtcNamn.setText("Namn");
    txtcNamn.setColumns(10);
    txtcNamn.setBounds(174, 380, 134, 28);
    frame.getContentPane().add(txtcNamn);
    
    txtPong = new JTextField();
    txtPong.setText("Poäng");
    txtPong.setColumns(10);
    txtPong.setBounds(174, 413, 134, 28);
    frame.getContentPane().add(txtPong);

   

    JButton regcurbutton = new JButton("Registera Kurs");
    regcurbutton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String ccode = txtKursnummer.getText();
    		String cName = txtcNamn.getText();
    		String points = txtPong.getText();
    		
    		try {
    			controller.addCourse(ccode, cName, points);
    		}catch (SQLException e3) {
    			e3.printStackTrace();
    			
    		}
    		

    	}
    }); 
    regcurbutton.setBounds(175, 448, 133, 29);
    frame.getContentPane().add(regcurbutton);

    JLabel lblSttBetyg = new JLabel("Sätt betyg");
    lblSttBetyg.setBounds(354, 325, 80, 16);
    frame.getContentPane().add(lblSttBetyg);


    JComboBox<String> comboBoxGrade = new JComboBox<String>();
	comboBoxGrade.addItem("A");
	comboBoxGrade.addItem("B");
	comboBoxGrade.addItem("C");
	comboBoxGrade.addItem("D");
	comboBoxGrade.addItem("E");
	comboBoxGrade.addItem("U");
    comboBoxGrade.setBounds(354, 415, 119, 27);
    frame.getContentPane().add(comboBoxGrade);
 
	
	TxtSpnrRegStudied = new JTextField();
    TxtSpnrRegStudied.setText("Personnummer");
    TxtSpnrRegStudied.setColumns(10);
    TxtSpnrRegStudied.setBounds(354, 347, 119, 28);
    frame.getContentPane().add(TxtSpnrRegStudied);
    
    txtCcodetoStudied = new JTextField();
    txtCcodetoStudied.setText("Kurskod");
    txtCcodetoStudied.setColumns(10);
    txtCcodetoStudied.setBounds(354, 380, 119, 28);
    frame.getContentPane().add(txtCcodetoStudied);
    
    JButton btnNewButton = new JButton("Sätt betyg");
    btnNewButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	String spnr = TxtSpnrRegStudied.getText();
        	String ccode = txtCcodetoStudied.getText();
        	String grade = comboBoxGrade.getSelectedItem().toString();
        	
        	try {
        		controller.addStudentToStudied(spnr, ccode, grade);
        	} catch (SQLException e11) {
        		
        	}
        	
        }
    });
    btnNewButton.setBounds(356, 448, 117, 29);
    frame.getContentPane().add(btnNewButton);
    
    JButton registrera1 = new JButton("Registera");
    btnRegisterastud.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
    		String spnr = txtPersonnummer.getText().trim();
    		String ccode = comboBox.getSelectedItem().toString();
    		try {
    			controller.addStudentToCourse(spnr, ccode);
    			
    			}catch(SQLException e12) 
    		{
    				e12.printStackTrace();
    		} 
    	}
    });
    registrera1.setBounds(6, 554, 87, 29);
    frame.getContentPane().add(registrera1);
    
    JButton btnRaderaStudent = new JButton("Radera Student");
    btnRaderaStudent.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String spnr = txtPersonnummer.getText().trim();
    		try {
    			controller.deleteStudent(spnr);
    		} catch (SQLException e13) {
    			e13.printStackTrace();
    		}
    	}
    	
    });
    btnRaderaStudent.setBounds(12, 477, 132, 29);
    frame.getContentPane().add(btnRaderaStudent);
    
    JButton btnRaderaKurs = new JButton("Radera Kurs");
    btnRaderaKurs.addActionListener ( new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String ccode = txtKursnummer.getText().trim();
    		try {
    			controller.deleteCourse(ccode);
    		} catch (SQLException e14) {
    			e14.printStackTrace();
    		}
    	}    
});
    btnRaderaKurs.setBounds(174, 477, 134, 29);
    frame.getContentPane().add(btnRaderaKurs);
    
}
}