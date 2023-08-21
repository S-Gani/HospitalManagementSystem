package com.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AppointmentDAO;
import com.db.DBUtil;
import com.entity.Appointment;
@WebServlet("/appAppointment")
public class AppointmentServlet extends HttpServlet{
	String userId = "123abc";
	public AppointmentDAO dao = null;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String appoint_date = req.getParameter("appointmentDate");
		String problem = req.getParameter("problemDescription");
		int status = 0;
		System.out.println(appoint_date);
		Appointment ap = new Appointment(userId,appoint_date,problem,status);
		AppointmentDAO dao = null;
		try {
			dao = new AppointmentDAO(DBUtil.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("hi");
			e.printStackTrace();
		}
		PrintWriter out = resp.getWriter();

		if (dao.addAppointment(ap)) {
			out.println("Appointment Booked sucessful ");
		} else {
			out.println("cannot book ");
		}

	}
}
