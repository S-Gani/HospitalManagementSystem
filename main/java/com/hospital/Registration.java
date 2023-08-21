package com.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


@WebServlet("/com/register")
public class Registration extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		// TODO Auto-generated method stub
		System.out.println(req.getParameter("userName"));
		
		PrintWriter out = resp.getWriter();
		try {
	        conn = DBUtil.getConnection();
	        String sqlQuery = "INSERT INTO [bob].[userTable] values(?,?,?,?,?,?,?,?)";
	        JSONObject jsonObject = new JSONObject();
	        stmt = conn.prepareStatement(sqlQuery);
	        stmt.setString(1, req.getParameter("user_id"));
	        stmt.setString(2, req.getParameter("userPassword"));
	        stmt.setString(3, req.getParameter("userName"));
	        stmt.setString(4, req.getParameter("gender"));
	        stmt.setInt(5, Integer.parseInt(req.getParameter("age")));
	        stmt.setString(6, req.getParameter("phoneNumber"));
	        stmt.setString(7, "user");
	        System.out.println(req.getParameter("Email"));
	        stmt.setString(8, String.valueOf(req.getParameter("Email")));
	        if(stmt.executeUpdate()==1) {
	        	jsonObject.put("sucessmsg", "Registration Successful");
	        }else {
	        	jsonObject.put("errormsg", "Registration Failed");
	        }
	        
	        out.print(jsonObject);
	        
	    } catch (SQLException e) {
	    	 System.out.println("hi");
	        e.printStackTrace();
	  
	    } finally {
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                System.out.println("st");
	                // Handle closing PreparedStatement exception
	            }
	        }
	        // Close the Connection in the class that created it (DBConnectionUtil)
	        DBUtil.closeConnection(conn);
	    }
	}
	
}
