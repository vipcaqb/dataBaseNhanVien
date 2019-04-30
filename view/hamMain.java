package view;

import java.sql.ResultSet;

import dao.daoDungChung;

public abstract class hamMain {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			daoDungChung dc = new daoDungChung();
			dc.ketNoi();
			
			ResultSet rs = dc.getBang("donVi");
			while(rs.next()) {
				System.out.println(rs.getString(1)+":"+rs.getString(2));
				
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
