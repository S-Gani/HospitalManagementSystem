package com.dao; 

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray; 
import org.json.JSONObject; 
import com.entity.Appointment; 
import com.entity.Doctor; 
import com.entity.GetUserAppointment; 
import com.entity.UserAppointment; 
public class AdminDao { 
private Connection conn; 
public AdminDao(Connection conn) { 
	super(); 
	this.conn = conn; 
} 
public JSONArray getAllUserDetails() { 
	JSONArray jsonArray = new JSONArray(); 
	Appointment ap = null; 
//UserAppointment userAppointment = null; 
try { 
			String sql = "SELECT\r\n" 
			+ "    u.user_id AS userId,\r\n" 
			+ "    u.userName,\r\n" 
			+ "    u.gender,\r\n" 
			+ "    u.age,\r\n" 
			+ "    u.phoneNumber,\r\n" 
			+ "    a.appointmentDate,\r\n" 
			+ "    a.problem,\r\n" 
			+ "    a.status,\r\n" 
			+ "    d.DocName AS doctorName,\r\n" 
			+ "    a.doctor_id AS doctorId\r\n" 
			+ "FROM\r\n" 
			+ "    [bob].[userTable] u\r\n" 
			+ " JOIN\r\n" 
			+ "    [bob].[AppointmentTable] a ON u.user_id = a.user_id\r\n" 
			+ " JOIN\r\n" 
			+ "    [bob].[DotorTable] d ON a.doctor_id = d.DocId\r\n" 
			+ "ORDER BY\r\n" 
			+ "    a.appointmentDate;\r\n"; 

			PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet resultSet = stmt.executeQuery(); 
			while (resultSet.next()) { 

				GetUserAppointment user = new GetUserAppointment(); 
				ap = new Appointment(); 
				//userAppointment = new UserAppointment(); 
				Doctor doc = new Doctor(); 
				ap.setUserId(resultSet.getString("userId")); 
				System.out.println(resultSet.getString("userId")+"66666");
				user.setUserName(resultSet.getString("UserName")); 
				user.setGender(resultSet.getString("gender")); 
				user.setAge(resultSet.getInt("age")); 
				user.setPhoneNumber(resultSet.getString("PhoneNumber")); 
				user.setAppointmentDate(resultSet.getString("appointmentDate")); 
				user.setProblemString(resultSet.getString("problem")); 
				//userAppointment.setStatus(resultSet.getString("status")); 
				doc.setDocName(resultSet.getString("doctorName")); 
				doc.setDocId(resultSet.getString("doctorId")); 
				System.out.println(resultSet.getString("doctorId"));
				JSONObject jsonObject = new JSONObject(); 
				jsonObject.put("UserId",ap.getUserId()); 
				jsonObject.put("UserName", user.getUserName()); 
				jsonObject.put("gender",user.getGender()); 
				jsonObject.put("age", user.getAge()); 
				jsonObject.put("PhoneNumber", user.getPhoneNumber()); 
				jsonObject.put("appointmentDate",user.getAppointmentDate()); 
				jsonObject.put("problem", user.getProblemString()); 
				//jsonObject.put("status", userAppointment.getStatus()); 
				jsonObject.put("doctorName", doc.getDocName()); 
				jsonObject.put("doctorId", doc.getDocId()); 
				jsonArray.put(jsonObject);  
				System.out.println(jsonObject+"11------");
			} 
} catch (Exception e) { 
	e.printStackTrace(); 
} 
	return jsonArray; 
} 
} 