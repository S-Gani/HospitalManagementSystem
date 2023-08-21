package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




import com.entity.Doctor;

public class DoctorDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet resultSet;

	public DoctorDao(Connection conn) {
		super();
		this.conn = conn;
		this.stmt=null;
		this.resultSet=null;
	}  
	public int delecteDoctor (String d) {
		String qry =" update [bob].[DotorTable] set is_delete=1  where  DocId =?  ";
		int sta=0;
		try {
			stmt=conn.prepareStatement(qry);
			stmt.setString(1, d);
			sta=stmt.executeUpdate();
			if(sta==1)
				return 1;
		} catch (Exception e) { e.printStackTrace();return -1;
		}
		return 0;
	}
	public int editDoctord(Doctor d,String modifer) {
		String qry =" update [bob].[DotorTable] set DocName=?,Email=?,Password=?,  Phone=?, Specialization=?,Last_update_by=?, Last_updated_date=getdate() where  DocId =?  ";
		int sta=0;
		try {
			stmt=conn.prepareStatement(qry);
			stmt.setString(1, d.getDocName());
			stmt.setString(2, d.getDocEmail());
			stmt.setString(3, d.getPassword());
			stmt.setString(4, d.getDocPhone());
			stmt.setString(5, d.getSpecialization());
//			HttpSession session
			stmt.setString(6, modifer);
			stmt.setString(7, d.getDocId());
			sta=stmt.executeUpdate();
			if(sta==1)
				return 1;
		} catch (Exception e) { e.printStackTrace();return -1;
		}
		return 0;
	}
	
	public int addDoctor(Doctor d) {
		if(isExistMember(d)) {
			System.out.println("du[plicate part started");
			return -2;
		}
		System.out.println("insert part started");
		String qry ="insert into [bob].[DotorTable] (DocId,DocName,Email,Password,  Phone, Specialization,Is_delete) values(?,?,?,?,?,?,0)";
		int sta=0;
		try {
			stmt=conn.prepareStatement(qry);
			stmt.setString(1, d.getDocId());
			stmt.setString(2, d.getDocName());
			stmt.setString(3, d.getDocEmail());
			stmt.setString(4, d.getPassword());
			stmt.setString(5, d.getDocPhone());
			stmt.setString(6, d.getSpecialization());
			sta=stmt.executeUpdate();
			System.out.println("insert successfully");
			
		} catch (Exception e) { e.printStackTrace(); return 0;
		}
		if(sta==1)
			return 1;
		else {
			return -1;
		}
	}
	public boolean isExistMember(Doctor d) {
		String qry =" select * from [bob].[DotorTable] where is_delete=0 and  DocId =? and Email= ?  ";
		int sta=0;
		try {
			stmt=conn.prepareStatement(qry);
			stmt.setString(1, d.getDocId());
			stmt.setString(2, d.getDocName());
			resultSet=stmt.executeQuery();
			if(resultSet.next())
				return true;
		} catch (Exception e) { e.printStackTrace();return false;
		}
		return false;
	}
	
	public ArrayList<Doctor> getAllDoctors (Doctor doctr){
		String query = "select DocName,Email,Phone,Specialization,DocId,Password from [bob].[DotorTable] where is_delete = 0";
		ArrayList<Doctor> list = new ArrayList<Doctor>();
		try {
			stmt=conn.prepareStatement(query);
			resultSet = stmt.executeQuery();
			while(resultSet.next()){
				Doctor doctor = new Doctor();
				doctor.setDocName(resultSet.getString("DocName"));
				doctor.setDocEmail(resultSet.getString("Email"));
				doctor.setDocPhone(resultSet.getString("Phone"));
				doctor.setSpecialization(resultSet.getString("Specialization"));
				doctor.setDocId(resultSet.getString("DocId"));
				doctor.setPassword(resultSet.getString("Password"));
				list.add(doctor);
				System.out.println(doctor);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} 
    // Count the Number of Doctors 
	public int countDoctors() {
		String query = "select count(*) from [bob].[DotorTable] WHERE Is_delete = 0";
		try {
			stmt = conn.prepareStatement(query);
			resultSet = stmt.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace(); return -1;
		}
		
	}
}
