package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;


public class nhanVienDao {

	public int Them(String maNv,String tenNv,boolean gt,Date ngaysinh,Double hsl,String madv) {
//		insert into nhanVien(maNv,tenNv,gioiTinh,ngaySinh,hsl,maDv) values(?,?,?,?,?,? )
		try {
			String sql ="insert into nhanVien(maNv,tenNv,gioiTinh,ngaySinh,hsl,maDv) values(?,?,?,?,?,? )";
			//tao cau len
			PreparedStatement cmd = daoDungChung.cn.prepareStatement(sql);
			cmd.setString(1, maNv);
			cmd.setString(2, tenNv);
			cmd.setBoolean(3, gt);
			//doi nga util sang sql
			cmd.setDate(4, new java.sql.Date(ngaysinh.getTime()));
			cmd.setDouble(5, hsl);
			cmd.setString(6, madv);
			return cmd.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	public int xoa (String manv) throws SQLException {
		int kq = JOptionPane.showConfirmDialog(null, "Xoa khong ? " );
		if(kq==1 ) return 0;
		String sql = "delete from nhanVien where maNv = ?";
		PreparedStatement cmd= daoDungChung.cn.prepareStatement(sql);
		cmd.setString(1, manv);
		return cmd.executeUpdate();
	}
	public void Tim (String dk) {
		JTable table = null ;
		try {
			
			DefaultTableModel dfTab;
			daoDungChung dc = new daoDungChung();
			dfTab = new DefaultTableModel();
			dfTab.addColumn("maNhanVien");
			dfTab.addColumn("tenNhanVien");
			dfTab.addColumn("gioiTinh");
			dfTab.addColumn("ngaySinh");
			dfTab.addColumn("heSoLuong");
			dfTab.addColumn("maDonVi");
			ResultSet rs = dc.getBang(dk);
			Object []tam = new Object[6];
			while(rs.next()) {
				tam[0] = rs.getString(1);
				tam[1] = rs.getString(2);
				tam[2] = (rs.getString(3).equals("1"))?"Nam":"Nu";
				tam[3] = rs.getString(4);
				tam[4] = rs.getString(5);
				tam[5] = rs.getString(6);
				dfTab.addRow(tam);
			}
			rs.close();
			table.setModel(dfTab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

