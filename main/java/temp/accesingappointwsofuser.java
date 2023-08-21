package temp;


import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;

import com.dao.AdminDao;
import com.dao.AppointmentDAO;
import com.dao.DoctorDao;
import com.db.DBUtil;
import com.entity.UserAppointment;

public class accesingappointwsofuser {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		AppointmentDAO obj=new AppointmentDAO(DBUtil.getConnection());
		List<UserAppointment> kkAppointments= obj.getAllAppointmentByLoginUser("123abc");
		
		System.out.println(kkAppointments.size()+"vvvvv");
		String kkString="";
		for(int i=0;i<kkAppointments.size();i++){
			kkString+=kkAppointments.get(i);
		    System.out.println(kkAppointments.get(i)+"===");
		} 
		System.out.println("\n\n0000000\n\n"+kkString);
		JSONArray array = obj.getAllPendingAppointment();
		System.out.println(array.toString());
		AdminDao ad = new AdminDao(DBUtil.getConnection());
		JSONArray json = ad.getAllUserDetails();
		System.out.println(json+"==================");
		DoctorDao d  = new DoctorDao(DBUtil.getConnection());
		System.out.println(d.countDoctors()+"ddd");
		
	}

}
