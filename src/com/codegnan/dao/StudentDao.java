package com.codegnan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.codegnan.beans.Student;
import com.codegnan.exception.InvalidStudentIdException;
import com.codegnan.util.ConnectionHelper;

public class StudentDao extends Dao {
	public boolean save(Student student) throws ClassNotFoundException, SQLException {
		boolean res = false;

		con = ConnectionHelper.getConnection();
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO student VALUES (" + student.getId() + ", '" + student.getName() + "', '"
				+ student.getEmail() + "', '" + student.getBranch() + "')";
		int noRec = stmt.executeUpdate(sql);
		if (noRec == 1) {
			res = true;
		} else {
			// No specific logic to execute here.
		}
		return res;
	}
	
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		boolean res = false;

		con = ConnectionHelper.getConnection();
		Statement stmt = con.createStatement();
		String sql = "DELETE FROM st"
				+ "udent WHERE id = "+id;
		int noRec = stmt.executeUpdate(sql);
		if (noRec == 1) {
			res = true;
		} else {
			// No specific logic to execute here.
		}
		return res;
	}
	
	public boolean edit(Student student) throws ClassNotFoundException, SQLException {
		boolean res = false;

		con = ConnectionHelper.getConnection();
		Statement stmt = con.createStatement();
		String sql = "UPDATE student SET "
							+ "name='"+student.getName()+"', "
							+ "email='"+student.getEmail()+"', "
							+ "branch = '" +student.getBranch()+ "' "
							+ "WHERE id = " + student.getId();
		
		int noRec = stmt.executeUpdate(sql);
		if (noRec == 1) {
			res = true;
		} else {
			// No specific logic to execute here.
		}
		return res;
	}
	
	public Student findById(int id) throws ClassNotFoundException, SQLException, InvalidStudentIdException {
		Student student = null;
		String sql = "SELECT * FROM student WHERE id = "+id ;
		Connection con = ConnectionHelper.getConnection();
		con.commit();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		try {
			if(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String branch = rs.getString("branch");
				student = new Student(id, name, email, branch);
			}
			else {
				InvalidStudentIdException e = new InvalidStudentIdException("There is no student with id "+id);
				throw e;
			}
		}
		finally {
			con.close();
		}
		
		return student;
	}
	
	public ArrayList<Student> findAll() throws ClassNotFoundException, SQLException{
		ArrayList<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM student";
		Connection con = ConnectionHelper.getConnection();
		con.commit();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String branch = rs.getString("branch");
			Student student = new Student(id, name, email, branch);
			students.add(student);
		}
		con.close();
		return students;
	}
}
