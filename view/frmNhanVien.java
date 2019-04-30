package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.daoDungChung;
import dao.nhanVienDao;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmNhanVien extends JFrame {
	

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNhanVien frame = new frmNhanVien();
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
	private JTextField txtMaNV;
	private JTextField txtTenNhanVien;
	private JTextField txtGioiTinh;
	private JTextField txtNgaySinh;
	private JTextField txtHsl;
	private JTextField txtMaDV;
	void napBang() {
		try {
			dc.ketNoi();
			dfTab = new DefaultTableModel();
			dfTab.addColumn("maNhanVien");
			dfTab.addColumn("tenNhanVien");
			dfTab.addColumn("gioiTinh");
			dfTab.addColumn("ngaySinh");
			dfTab.addColumn("heSoLuong");
			dfTab.addColumn("maDonVi");
			ResultSet rs = dc.getBang("nhanVien");
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
	public frmNhanVien() {
		txtMaDV = new JTextField();
		txtHsl = new JTextField();
		txtNgaySinh = new JTextField();
		txtGioiTinh = new JTextField();
		txtMaNV = new JTextField();
		txtTenNhanVien = new JTextField();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				napBang();
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 200, 660, 244);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab(null, null, scrollPane, null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				txtMaNV.setText(table.getValueAt(row,0).toString());
				txtTenNhanVien.setText(table.getValueAt(row,1).toString());
				txtGioiTinh.setText(table.getValueAt(row,2).toString());
				txtNgaySinh.setText(table.getValueAt(row,3).toString());
				txtHsl.setText(table.getValueAt(row,4).toString());
				txtMaDV.setText(table.getValueAt(row,5).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		txtMaNV.setBounds(77, 11, 204, 20);
		contentPane.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(77, 42, 204, 20);
		contentPane.add(txtTenNhanVien);
		
		txtGioiTinh.setColumns(10);
		txtGioiTinh.setBounds(77, 73, 204, 20);
		contentPane.add(txtGioiTinh);
		
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(77, 104, 204, 20);
		contentPane.add(txtNgaySinh);
		
		txtHsl.setColumns(10);
		txtHsl.setBounds(77, 135, 204, 20);
		contentPane.add(txtHsl);
		
		
		txtMaDV.setColumns(10);
		txtMaDV.setBounds(77, 169, 204, 20);
		contentPane.add(txtMaDV);
		
		JLabel lblManv = new JLabel("maNV");
		lblManv.setBounds(21, 14, 46, 14);
		contentPane.add(lblManv);
		
		JLabel lblTennv = new JLabel("tenNV");
		lblTennv.setBounds(21, 45, 46, 14);
		contentPane.add(lblTennv);
		
		JLabel lblGioitinh = new JLabel("gioiTinh");
		lblGioitinh.setBounds(21, 76, 46, 14);
		contentPane.add(lblGioitinh);
		
		JLabel lblHsl = new JLabel("ngaySinh");
		lblHsl.setBounds(21, 107, 46, 14);
		contentPane.add(lblHsl);
		
		JLabel lblHsl_1 = new JLabel("hsl");
		lblHsl_1.setBounds(21, 138, 46, 14);
		contentPane.add(lblHsl_1);
		
		JLabel lblMadonvi = new JLabel("maDonVi");
		lblMadonvi.setBounds(21, 172, 46, 14);
		contentPane.add(lblMadonvi);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nhanVienDao nv = new nhanVienDao();
					String maNv = txtMaNV.getText();
					String tenNv =txtTenNhanVien.getText();
					Boolean gioiTinh = Boolean.parseBoolean(txtGioiTinh.getText());
					Double hsl= Double.parseDouble(txtHsl.getText());
					String madv = txtMaDV.getText();
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					java.util.Date ngaySinh =formatter.parse(txtNgaySinh.getText());
					
					nv.Them(maNv, tenNv, gioiTinh, ngaySinh, hsl, madv);
					napBang();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(319, 9, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nhanVienDao nv = new nhanVienDao();
				String manv = txtMaNV.getText();
				try {
					nv.xoa(manv);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				napBang();
			}
		});
		btnDelete.setBounds(319, 41, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnFix = new JButton("Modify");
		btnFix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFix.setBounds(319, 72, 89, 23);
		contentPane.add(btnFix);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dk = JOptionPane.showInputDialog("Nhap ho ten can tim");
				nhanVienDao nv = new nhanVienDao();
				nv.Tim(dk);
			}
		});
		btnFind.setBounds(319, 134, 89, 23);
		contentPane.add(btnFind);
		
		JButton btnNewButton = new JButton("Import");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					nhanVienDao nv = new nhanVienDao();
					FileReader f = new FileReader("input.txt");
					BufferedReader in= new BufferedReader(f);
					while(true) {
						String st = in.readLine();
						if(st==""||st==null) {
							break;
						}
						//Tach du lieu
						String []t = st.split("[;]");
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						java.util.Date ngaySinh = formatter.parse(t[3]);
						nv.Them(t[0], t[1], Boolean.parseBoolean(t[2]), ngaySinh, Double.parseDouble(t[4]), t[5]);
						napBang();
					}
					in.close();
					f.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(319, 103, 89, 23);
		contentPane.add(btnNewButton);
	}
}
