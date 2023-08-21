package com.doctor.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.dao.DoctorDao;
import com.db.DBUtil;
import com.entity.Doctor;

@WebServlet("/addoctor")
public class AddDoctor extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("entered");
		System.out.println(req.getParameter("doctorID"));
		doPost(req, resp);
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		
		try {
			
			DoctorDao doctorDao = new DoctorDao(DBUtil.getConnection());
			Doctor doctor = new Doctor();
			JSONObject jsonObject = new JSONObject();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
				System.out.println(req.getParameter("doctorID"));
				System.out.println(req.getParameter("doctorPassword"));
				System.out.println(req.getParameter("doctorName"));
				System.out.println(req.getParameter("email"));
				System.out.println(req.getParameter("phone"));
				System.out.println(req.getParameter("specialization"));
				doctor.setDocId(req.getParameter("doctorID"));
				doctor.setPassword(req.getParameter("doctorPassword"));
				doctor.setDocName(req.getParameter("doctorName"));
				doctor.setDocEmail(req.getParameter("email"));
				doctor.setDocPhone(req.getParameter("phone"));
				doctor.setSpecialization(req.getParameter("specialization"));
				doctor.setIs_delete(0);
				System.out.println(doctorDao.isExistMember(doctor));
			if(doctorDao.isExistMember(doctor)) {
				if(doctorDao.addDoctor(doctor)==1) {
					jsonObject.put("successmsg","Added Sucessfully");
				}else if(doctorDao.addDoctor(doctor)==0 || doctorDao.addDoctor(doctor)== -1) {
					jsonObject.put("errormsg","Failed to add");
				}
				
			}else{
				jsonObject.put("exist","Member already Exists");
			}
			System.out.println(jsonObject);
			resp.getWriter().print(jsonObject);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
