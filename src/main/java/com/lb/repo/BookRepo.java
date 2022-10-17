package com.lb.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.lb.entities.Book;
import com.lb.exception.CPException;
import com.lb.jdbc.DBManager;

public class BookRepo {
	
public void insertEmployee(String bk_name, String bk_author) throws CPException {
		
//		
//		DBManager dbm = null;
//		Connection con = null;
//		try {
//		final String SQL_INSERT = "INSERT INTO BOOK ( bk_name, bk_author) VALUES (?,?)";
//		
//			java.util.Date today = new java.util.Date();
//			java.sql.Date sqlDate = new java.sql.Date(today.getTime());
//			dbm = DBManager.getDBManager();
//			con = dbm.getConnection();
//			PreparedStatement st = con.prepareStatement(SQL_INSERT);
//			st.setString(1, bk_name.toUpperCase());
//			st.setString(2, bk_author.toUpperCase());
//			st.setDate(3, sqlDate);
//	
//			int row = st.executeUpdate();
//			System.out.println("Book added successfully");
//		} catch (CPException exp) {
//			exp.printStackTrace();
//		}
//		catch(PSQLException psqlException)
//		{
//			System.out.println("Book Already Exists ");
//			
//			
//		}catch (Exception exp1) {
//			exp1.printStackTrace();
//		} finally {
//			dbm.closeConnection(con);
//		}
	DBManager dbm = DBManager.getDBManager();
	Connection con = null;
	String insertQuery = "INSERT INTO book ( bk_name , bk_author) VALUES (?,?)";
	PreparedStatement ps = null;
	try {
		con = dbm.getConnection();
		ps = con.prepareStatement(insertQuery);
		ps.setString(1, Book.getBk_name());
		ps.setString(2, Book.getBk_author());
		ps.execute();
		con.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public List<Book> getCustomerDetails() throws CPException {
	DBManager dbm = DBManager.getDBManager();
	Connection con = null;
	con = dbm.getConnection();
	String dataQuery = "select * from customer";
	Statement stmt = null;
	List<Book> listBook = new ArrayList<Book>();
	try {
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(dataQuery);
		while (rs.next()) {
			int custId = rs.getInt("bk_id");
			String bookName = rs.getString("bk_name");
			String bookAuthor = rs.getString("bk_author");
			
			Book bk = new Book( bookName , bookAuthor);
			listBook.add(bk);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return listBook;
}
	
}