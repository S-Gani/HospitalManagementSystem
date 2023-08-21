package com.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.*;

import com.dao.AppointmentDAO;
import com.db.DBUtil;
import com.entity.Appointment;
import com.entity.UserAppointment;
@WebServlet("/viewAppointmet")
public class AppointmentStatus extends HttpServlet{
	String userId = "123abc";
	public AppointmentDAO dao = null;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AppointmentDAO dao = null;
		String jsonString = null;
		try {
			dao = new AppointmentDAO(DBUtil.getConnection());
			List<UserAppointment> Appointments= dao.getAllAppointmentByLoginUser(userId);
			Gson gson = new Gson();
			jsonString = gson.toJson(Appointments);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("hi");
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();
		out.print(jsonString);
		

	}
}
