package dal;
import java.sql.*;

import modell.*;

import java.util.ArrayList;
import java.util.Vector;

import connector.Connector;
import modell.*;

import java.util.Arrays;
public class Dal {
	private String ccode;
	private String cName;
	private String sName;
	private String sAdress;
	private String sqlString;
	private Student student;
	private String points;
	private Studied studied;
	private Course course;
	private ArrayList<Course> courseList = new ArrayList<Course>();
	private ArrayList <Student> studiesList = new ArrayList<Student>();
	
	
	
/* Lägger in student */
	public void addStudent(String spnr, String sName, String adress) throws SQLException {
		Connection conn = Connector.startConnection();
		Statement stmt = conn.createStatement();
		
		String sqlString = "INSERT INTO Student VALUES ('" + spnr + "', '"
		+ sName + "', '" + adress + "');";
		
		stmt.executeUpdate(sqlString);
		stmt.close();
		
	}
	/*Lägger till kurs */
	public void addCourse(String ccode, String cName, String points) throws SQLException {
		Connection conn = Connector.startConnection();
		Statement stmt = conn.createStatement();
		
		String sqlString = "INSERT INTO Course VALUES ('" + ccode + "','" + cName + "','" + points + "');";
		stmt.executeUpdate(sqlString);
		stmt.close();
	}
	/*Lägger till student på kurs*/
	public void addStudentToCourse(String spnr, String ccode) throws SQLException {
		Connection conn = Connector.startConnection();
		Statement stmt = conn.createStatement();
		
		String sqlString = "INSERT INTO Studies VALUES('" + spnr + "','" + ccode +"');";
		stmt.executeQuery(sqlString);
		stmt.close();
	}
	//Lägger till student i studied och tilldelar betyg
	public void addStudentToStudied(String spnr, String ccode, String grade) throws SQLException {
		Connection conn = Connector.startConnection();
		Statement stmt = conn.createStatement();
		
		String sqlString  = "INSERT INTO Studied VALUES('" + spnr + "' , '" + ccode + "','" + grade +"');";
		stmt.executeQuery(sqlString);
		stmt.close();
	}
	/* Hämta student */
	public Student findStudent(String spnr) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString = "SELECT * FROM Student Where spnr = '" + spnr +"'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		rs.next();
		spnr = rs.getString(1);
		sName = rs.getString(2);
		sAdress = rs.getString(3);
		
		Student  s = new Student (spnr, sName, sAdress);
		
		return  s;
	}
	/* Hämta kurs */
	public Course findCourse(String ccode) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString ="SELECT * FROM COURSE WHERE ccode = '" + ccode + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		rs.next();
		ccode = rs.getString(1);
		cName = rs.getString(2);
		points = rs.getString(3);
		
		Course course = new Course (ccode, cName, points);
		
		return course;
	}
	/*Hitta resutatet för studenter på en specifik kurs*/
	public ArrayList<Studied> getGradeForCourse(String spnr, String ccode) throws SQLException {
		ArrayList<Studied> studiedList = new ArrayList<Studied>();
		
		Connection conn = Connector.startConnection();
		sqlString = "SELECT spnr, ccode, grade FROM Studied WHERE spnr = '" + spnr + "' AND ccode = '" + ccode + "'";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while (rs.next()) {
			studied = new Studied(rs.getString(1), rs.getString(2), rs.getString(3));
			studiedList.add(studied);
		}
		return studiedList;
	}
	
	/*Hämta samtliga kurser*/
	/*public ArrayList<Course> getAllCourses() throws SQLException {
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		Connection conn = Connector.startConnection();
		sqlString = "SELECT * FROM Course";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while(rs.next()) {
			course = new Course (rs.getString(1), rs.getString(2), rs.getString(3));
			courseList.add(course);
		}
		return courseList;
	}*/
	//Bajskod för objektkuk
	/*
	public Object [][] getAllData(String from) throws SQLException {
		String[] result = new String [3];
		ArrayList<Object[]> datalist = new ArrayList<Object[]>();
		Connection conn = Connector.startConnection();
		sqlString = "SELECT * FROM [" + from + "];";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		
		int j = 3;
		
		while(rs.next()) {
			Object[] sa = new Object[3];
			
		for (int i = 1; i<= j; i++) {
			sa[i - 1] = rs.getString(i);
		}
		datalist.add(sa);
		}
	}*/
	
	
	//Bajskod för objektkuk
	/*
		public Object[] getAllMetaData(String from) throws SQLException {
			Connection conn = Connector.startConnection();
			sqlString = "SELECT * FROM [" + from + "];";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			

			Object[] result = new Object [3];
			int j = 3;
			for (int i = 1; i<= j; i++) {
				result[i - 1] = rsmd.getColumnLabel(i);
			} 
			return result;
		}*/
	
	
	// Visa studenter som tagit kursen och deras betyg
	public ArrayList<Student> getGradeList(String ccode) throws SQLException {
		ArrayList<Student> studentList = new ArrayList<Student>();
		Connection conn = Connector.startConnection();
		sqlString = "SELECT h.ssid, s.sname, h.grade FROM Studied h JOIN Student s "
				+ "ON h.ssid = s.ssid WHERE h.ccode = '"+ ccode + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while(rs.next()) {
			student = new Student (rs.getString(1), rs.getString(2), rs.getString(3));
			studentList.add(student);
			
		}
		return studentList;
	}
	
	//Visa studenter på en specifik kurs
	public ArrayList<Student> getCurrentStudies(String ccode) throws SQLException {
		ArrayList<Student> studiesList = new ArrayList<Student>();
		Connection conn = Connector.startConnection();
		sqlString = "SELECT student.spnr, student.name FROM Student INNER JOIN Studies" + "ON student.spnr = studies.spnr WHERE ccode = '"+ ccode + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while(rs.next()) {
			student = new Student (rs.getString(1), rs.getString(2), rs.getString(3));
			studiesList.add(student);
		}
		return studiesList;
	}
	
	/*Ta bort student */
	public void deleteStudent(String spnr) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString = "DELETE FROM Student WHERE spnr ='" + spnr + "'";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlString);
		conn.close();
	}
	/*Ta bort kurs */
	public void deleteCourse (String ccode) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString = "DELETE FROM Course WHERE ccode ='" + ccode + "'";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlString);
		conn.close();
}
	/*Ta bort student från en kurs*/
	public void removeStudentFromStudying(String spnr, String ccode) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString = "DELETE FROM Studying WHERE spnr = '" + spnr +"' AND ccode ='" + ccode + "'";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlString);
		conn.close();
	}
	/* Gör om Arraylist till array för alla kurser*/
	public String[] convertCoursestoArray() throws SQLException {
		Connection conn = Connector.startConnection();
		sqlString = "SELECT * FROM Course";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		
		
		while(rs.next()) {
			course = new Course (rs.getString(1), rs.getString(2), rs.getString(3));
			courseList.add(course);
		}
		String[] cList = new String[courseList.size()];
		int i = 0;
		for(Course c : courseList){
			cList[i] = c.getCcode();
			i++;
		}
		
		return cList;
}
	}

