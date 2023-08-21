package com.doctor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.google.gson.*;

import com.dao.AppointmentDAO;
import com.db.DBUtil;
import com.entity.UserAppointment;
@WebServlet("/pendingAppointment")
public class PendingAppointments extends HttpServlet{
	public AppointmentDAO dao = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AppointmentDAO dao = null;
		JSONArray array = null;
		try {
			dao = new AppointmentDAO(DBUtil.getConnection());
			array = dao.getAllPendingAppointment();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("hi");
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.print(array);
		

	}
}

