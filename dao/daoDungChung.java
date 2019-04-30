package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class daoDungChung {
	public static Connection cn;
	public ResultSet getBang(String tenBang) {
		try {
			String sql= "select * from " + tenBang;
			PreparedStatement cmd = cn.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void ketNoi() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Da xac dinh hqtcsdl");
			cn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-J4H20NA\\MSSQLSV:1433;databaseName=nhanVien;user=sa;password=123");
			System.out.println("Da ket noi!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void them(String tenBang,String maDonVi,String tenDonVi) {
		try {
			String sql = "insert into "+tenBang+" values(?,?);";
			PreparedStatement cmd = cn.prepareStatement(sql);
			cmd.setString(1, maDonVi);
			cmd.setString(2, tenDonVi);
			cmd.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "maDonVi bi trung!");
		}
	}
	public void xoa(String tenBang,String maDonVi) {
		try {
			String sql = "delete from "+tenBang+" where maDv = '"+maDonVi+"'";
			PreparedStatement cmd = cn.prepareStatement(sql);
			cmd.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void sua(String tenBang,String maDonViCu,String maDonViMoi,String tenDonViMoi) {
		try {
			String sql = "update donVi set maDv = '"+maDonViMoi+"', tenDv = '"+tenDonViMoi+"' where maDv = '"+maDonViCu+"'";
			PreparedStatement cmd = cn.prepareStatement(sql);
			cmd.executeUpdate();
			JOptionPane.showMessageDialog(null, "Da sua.");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
