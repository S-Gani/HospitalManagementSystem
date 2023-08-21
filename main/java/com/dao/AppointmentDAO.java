package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Appointment;
import com.entity.GetUserAppointment;
import com.entity.UserAppointment;


import org.json.JSONArray;
import org.json.JSONObject;

public class AppointmentDAO {

	private Connection conn;

	public AppointmentDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean addAppointment(Appointment ap) {
		boolean f = false;

		try {
			System.out.println("iiiiii"+ap.getappoint_date());
			String sql = "insert into [bob].[AppointmentTable](user_id,appointmentDate,problem,status,doctor_id) values(?,?,?,?,null)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ap.getUserId());
			
			ps.setString(2,ap.getappoint_date());
			ps.setString(3,ap.getproblem());
			ps.setInt(4, ap.getStatus());

			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}
	
	public List<UserAppointment> getAllAppointmentByLoginUser(String userId) {
		List<UserAppointment> list = new ArrayList<UserAppointment>();
		UserAppointment ap = null;
		try {
			String sql = "select dt.DocId,at.appointmentDate,at.problem,at.status,dt.DocName,dt.Phone,dt.Specialization from [bob].[AppointmentTable] as at inner join [bob].[DotorTable] as dt on at.doctor_id = dt.DocId where user_id=?"	;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ap = new UserAppointment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7) );
//				ap.setDocId(rs.getInt(1));
//				ap.setUserId(rs.getInt(2));
//				ap.setFullName(rs.getString(3));
//				ap.setGender(rs.getString(4));
//				ap.setAge(rs.getString(5));
//				ap.setAppoinDate(rs.getString(6));
//				ap.setEmail(rs.getString(7));
//				ap.setPhNo(rs.getString(8));
//				ap.setDiseases(rs.getString(9));
//				ap.setDoctorId(rs.getInt(10));
//				ap.setAddress(rs.getString(11));
//				ap.setStatus(rs.getString(12));
				list.add(ap);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public JSONArray getAllPendingAppointment() {
		JSONArray jsonArray = new JSONArray();
		Appointment ap = null;
		UserAppointment userAppointment = null;
		try {

			String sql = "select us.UserName,us.gender,us.age,us.PhoneNumber,app.appointmentDate,app.problem from [bob].[UserTable] as us\r\n"
					+ " inner join [bob].[AppointmentTable] as app on us.user_id = app.user_id where app.status = 0;";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GetUserAppointment user = new GetUserAppointment();
				user.setUserName(rs.getString("UserName"));
				user.setGender(rs.getString("gender"));
				user.setAge(rs.getInt("age"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setAppointmentDate(rs.getString("appointmentDate"));
				user.setProblemString(rs.getString("problem"));
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("UserName", user.getUserName());
				jsonObject.put("gender",user.getGender());
				jsonObject.put("age", user.getAge());
				jsonObject.put("PhoneNumber", user.getPhoneNumber());
				jsonObject.put("appointmentDate",user.getAppointmentDate());
				jsonObject.put("problem", user.getProblemString());
				jsonArray.put(jsonObject);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}
