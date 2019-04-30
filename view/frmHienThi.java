package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.boNhanVien;
import dao.daoDungChung;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmHienThi extends JFrame {
	JLabel lblMadonvi = new JLabel("maDonVi");
	JLabel lblTendonvi = new JLabel("tenDonVi");
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmHienThi frame = new frmHienThi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	DefaultTableModel dfTab;
	daoDungChung dc = new daoDungChung();
	private JTextField txtMaDonVi;
	private JTextField txtTenDonVi;
	void napBang() {
		try {
			dc.ketNoi();
			dfTab = new DefaultTableModel();
			dfTab.addColumn("maDonVi");
			dfTab.addColumn("tenDonVi");
			ResultSet rs = dc.getBang("donVi");
			Object []tam = new Object[2];
			while(rs.next()) {
				
				tam[0] = rs.getString(1);
				tam[1] = rs.getString(2);
				dfTab.addRow(tam);
			}
			rs.close();
			table.setModel(dfTab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public frmHienThi() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				napBang();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 132, 654, 305);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tabbedPane.addTab("Don vi", null, scrollPane, null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMaDonVi.setText(table.getValueAt(row, 0).toString());
				txtTenDonVi.setText(table.getValueAt(row, 1).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnSua = new JButton("Sua");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				String maDonViCu = table.getValueAt(row, 0).toString();
				String maDonViMoi = txtMaDonVi.getText();
				String tenDonViMoi = txtTenDonVi.getText();
				dc.sua("donVi", maDonViCu, maDonViMoi, tenDonViMoi);
				napBang();
			}
		});
		btnSua.setBounds(547, 55, 89, 33);
		contentPane.add(btnSua);
		
		JButton btnXoa = new JButton("Xoa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String maDonVi = txtMaDonVi.getText();
					dc.xoa("donVi", maDonVi);
					napBang();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnXoa.setBounds(547, 11, 89, 33);
		contentPane.add(btnXoa);
		
		JButton btnThem = new JButton("Them");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String maDonVi = txtMaDonVi.getText();
					String tenDonVi = txtTenDonVi.getText();
					dc.them("donVi",maDonVi, tenDonVi);
					napBang();
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
		btnThem.setBounds(448, 11, 89, 33);
		contentPane.add(btnThem);
		
		
		lblMadonvi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMadonvi.setBounds(21, 20, 65, 24);
		contentPane.add(lblMadonvi);
		
		lblTendonvi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTendonvi.setBounds(21, 83, 65, 24);
		contentPane.add(lblTendonvi);
		
		txtMaDonVi = new JTextField();
		txtMaDonVi.setBounds(96, 18, 233, 33);
		contentPane.add(txtMaDonVi);
		txtMaDonVi.setColumns(10);
		
		txtTenDonVi = new JTextField();
		txtTenDonVi.setColumns(10);
		txtTenDonVi.setBounds(96, 81, 233, 33);
		contentPane.add(txtTenDonVi);
		
		JButton btnTimKiem = new JButton("Tim Kiem");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String madv = txtMaDonVi.getText();
					ResultSet rs = dc.getBang("donVi");
					int dem=0;
					while(rs.next()) {
						if(rs.getString(1).equalsIgnoreCase(madv))
						{
							txtTenDonVi.setText(rs.getString(2));
							dem=1;
							break;
						}
					}
					if(dem==0) JOptionPane.showMessageDialog(null, "Khong tim thay");
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					JOptionPane.showConfirmDialog(null, "khong tim thay");
				}
			}
		});
		btnTimKiem.setBounds(547, 99, 89, 33);
		contentPane.add(btnTimKiem);
	}
}
