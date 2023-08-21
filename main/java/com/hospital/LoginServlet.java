package com.hospital;
import org.json.JSONObject;

import javax.servlet.http.*;



import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{  
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 

{   
	
	JSONObject object = new JSONObject();
	System.out.println("lfjlfj");
	PrintWriter out=res.getWriter();  
	String user_id = req.getParameter("username");
	String password = req.getParameter("password");
	String module = req.getParameter("module");
	ResultSet resultSet = null;
	System.out.println(module);
	System.out.println(user_id);
	System.out.println(password);
	Connection conn = null;
	Statement stmt = null;
	

	try {
        conn = DBUtil.getConnection();
        stmt = conn.createStatement();
        String query = null;
        String User = null;
        String name = null;
        System.out.println("hi");
  
        if(module.equals("User")) {
        	System.out.println("a");
        	User = module;
        	query =  " select userName, userPassword,UserName from [bob].[userTable] where user_id ='"+user_id+"';";
        }
        if (module.equals("Admin")) {
        	System.out.println("b");
        	User = module;
        	query =  "select Userid,userPassword,UserName from [bob].[LOG] where Userid ='"+user_id+"';";
		}
        if(module.equals("Doctor")){
        	System.out.println("c");
        	User = module;
        	query =  "select DocID,Password,DocNamefrom  where Userid ='"+user_id+"';";
		}
        resultSet = stmt.executeQuery(query);
        if(resultSet.next()) {
        	if(resultSet.getString("userPassword").equals(password)){
                HttpSession session = req.getSession();
                session.setAttribute("role", module);
                session.setAttribute("user_id",user_id);
                session.setAttribute("name",resultSet.getString(3) );
                object.put("username", "true");
                object.put("password", "true");
                res.getWriter().println(object.toString());
            }
            else {
                object.put("username", "true");
                object.put("password", "false");
                res.getWriter().println(object.toString());
            }
        }
        else {
            object.put("username", "false");
            object.put("password", "false");
            res.getWriter().println(object.toString());
        }
    } 
	catch(SQLException e) {
    	 //System.out.println("hi");
        e.printStackTrace();
    
    } finally{
    	if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle closing ResultSet exception
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Db Connection close
        DBUtil.closeConnection(conn);
    }
 

 
out.close();  
}
}