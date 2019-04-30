package bo;

import java.sql.ResultSet;

import dao.daoDungChung;

public class boNhanVien {

	public ResultSet getBang(String tenBang) {
		daoDungChung dc = new daoDungChung();
		dc.ketNoi();
		return dc.getBang(tenBang);
	}

}
