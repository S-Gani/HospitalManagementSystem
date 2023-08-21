package com.doctor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.DoctorDao;
import com.db.DBUtil;
import com.entity.Doctor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/deletedoc")
public class DeleteDoctor extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 DoctorDao dao = null;
	     String jsonString = null;
	     Doctor doctor = new Doctor();
	     PrintWriter out = resp.getWriter();;
	     Gson gson = new Gson();
	     JsonObject jsonObject = new JsonObject();
	     resp.setContentType("application/json");
         resp.setCharacterEncoding("UTF-8");
         if(req.getParameter("action").equals("datatable")) {
        	 try {
 	            dao = new DoctorDao(DBUtil.getConnection());
 	            ArrayList<Doctor> doctors = dao.getAllDoctors(doctor);
 	            System.out.println(doctors);

 	           
 	            jsonString = gson.toJson(doctors);
 	            System.out.println(jsonString);
 	            
 	            resp.setContentType("application/json");
 	            resp.setCharacterEncoding("UTF-8");
 	        } catch (SQLException e) {
 	            e.printStackTrace();
 	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 	            return;
 	        }

 	       
 	        out.print(jsonString);
         }
         else if((req.getParameter("action").equals("delete"))){
        	 try {
  	            dao = new DoctorDao(DBUtil.getConnection());
  	            System.out.println(req.getParameter("docId"));
  	          System.out.println(dao.delecteDoctor(req.getParameter("docId")));
  	            if((dao.delecteDoctor(req.getParameter("docId"))==1)){
  	            	 resp.setContentType("application/json");
  	 	            resp.setCharacterEncoding("UTF-8");
	  	 	        jsonObject.addProperty("delete", true);
	  	 	        System.out.println(jsonObject);
  	 	            out.println(jsonObject);
  	            }
  	            
  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
  	            return;
  	        }
         }
        
		
	}

	
}