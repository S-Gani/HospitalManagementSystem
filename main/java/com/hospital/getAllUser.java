package com.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import com.dao.AdminDao;
import com.dao.DoctorDao;
import com.db.DBUtil;
@WebServlet("/getAllUser")
public class getAllUser extends HttpServlet{
	public AdminDao adao = null;
	public DoctorDao ddao = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getParameter("action"));
		if(req.getParameter("action").equals("display")) {
			try {
				adao = new AdminDao(DBUtil.getConnection());
				JSONArray jsonArray = adao.getAllUserDetails();
				if(jsonArray.length()>0) {
					PrintWriter out = resp.getWriter();
					out.println(jsonArray);
					resp.setContentType("application/json");
	 	            resp.setCharacterEncoding("UTF-8");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
		 else if((req.getParameter("action").equals("display"))) {
        	 try {
        		 ddao = new DoctorDao(DBUtil.getConnection());
        	 } catch (SQLException e) {
   	            e.printStackTrace();
   	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
   	            return;
   	        }
        	 
         }


	}
}
