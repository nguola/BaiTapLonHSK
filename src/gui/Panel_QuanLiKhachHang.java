package gui;

import java.awt.*;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;

public class Panel_QuanLiKhachHang extends JPanel implements ActionListener, MouseListener {

	private JTextField txtTimKiem;
	private JTable tableKhachHang;
	private DefaultTableModel khachHangModel;
	private KhachHang_DAO khachHangDAO;

	public Panel_QuanLiKhachHang() {
		try {
			ConnectDB.getInstance().connect();
			System.out.println("Connected!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		khachHangDAO = new KhachHang_DAO();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));

		// Phần tiêu đề
		JPanel pNor = new JPanel();
		pNor.setLayout(new BoxLayout(pNor, BoxLayout.Y_AXIS));
		JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTitle.setBackground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 34));
		pNor.add(lblTitle);
		pNor.add(Box.createVerticalStrut(20));

		// Phần chứa các nút chức năng
		JPanel pCompon = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCompon.add(Box.createHorizontalStrut(20));
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(35, 177, 77));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThem.setPreferredSize(new Dimension(140, 40));
		pCompon.add(btnThem);
		pCompon.add(Box.createHorizontalStrut(10));

		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(35, 177, 77));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSua.setPreferredSize(new Dimension(140, 40));
		pCompon.add(btnSua);
		pCompon.add(Box.createHorizontalStrut(10));

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(35, 177, 77));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa.setPreferredSize(new Dimension(140, 40));
		pCompon.add(btnXoa);
		pCompon.add(Box.createHorizontalStrut(50));

		txtTimKiem = new JTextField();
		txtTimKiem.setColumns(20);
		txtTimKiem.setPreferredSize(new Dimension(70, 40));
		pCompon.add(txtTimKiem);
		pCompon.add(Box.createHorizontalStrut(10));

		JButton btnTim = new JButton("Tìm");
		btnTim.setBackground(SystemColor.control);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setPreferredSize(new Dimension(70, 40));
		pCompon.add(btnTim);
		pNor.add(pCompon, BorderLayout.SOUTH);
		pNor.add(Box.createVerticalStrut(10));
		add(pNor, BorderLayout.NORTH);

		// Phần bảng hiển thị khách hàng
		String[] col = { "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Loại khách hàng" };
		khachHangModel = new DefaultTableModel(col, 0);
		tableKhachHang = new JTable(khachHangModel);
		JScrollPane scroll = new JScrollPane(tableKhachHang);
		add(scroll, BorderLayout.CENTER);

		setSize(1480, 680);
		// setSize(1000,680);
		setVisible(true);

		// Đọc dữ liệu từ cơ sở dữ liệu vào bảng
		docDuLieuVaoTable();

		// Xử lý sự kiện nút
		btnThem.addActionListener(this);
		btnThem.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Hiển thị DialogThemKhachHang
				DialogThemKhachHang dialogThem = new DialogThemKhachHang("them");
				dialogThem.setVisible(true);
				dialogThem.setResizable(false);
				dialogThem.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
					}

					@Override
					public void windowIconified(WindowEvent e) {
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
					}

					@Override
					public void windowClosing(WindowEvent e) {
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						if (dialogThem.isTrangThaiThem()) {
							docDuLieuVaoTable();
						}
					}

					@Override
					public void windowActivated(WindowEvent e) {
					}
				});
			}
		});

		btnSua.addActionListener(this);
		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Hiển thị DialogSua
				int index = tableKhachHang.getSelectedRow();
				if (index < 0) {
					JOptionPane.showMessageDialog(null, "Chọn người cần sửa");
				} else {
					int maKhachHang = Integer.parseInt(tableKhachHang.getValueAt(index, 0).toString());
					KhachHang khachHang = khachHangDAO.getKhachHangTheoMa(maKhachHang); // Lấy thông tin khách hàng từ cơ sở dữ liệu
					try {
						DialogSuaKhachHang dialog = new DialogSuaKhachHang("sua", maKhachHang,Panel_QuanLiKhachHang.this);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dialog.addWindowListener(new WindowListener() {
							@Override
							public void windowClosed(WindowEvent e) {
								if (dialog.isTrangThaiSua()) {
									docDuLieuVaoTable();
								}

							}

							@Override
							public void windowOpened(WindowEvent e) {
							}

							@Override
							public void windowClosing(WindowEvent e) {
							}

							@Override
							public void windowIconified(WindowEvent e) {
							}

							@Override
							public void windowDeiconified(WindowEvent e) {
							}

							@Override
							public void windowActivated(WindowEvent e) {
							}

							@Override
							public void windowDeactivated(WindowEvent e) {
							}
						});
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnTim.addActionListener(this);
		btnXoa.addActionListener(this);
		tableKhachHang.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = tableKhachHang.getSelectedRow();
		        if (row >= 0) {
		            // Lấy dữ liệu từ hàng được chọn
		            int maKhachHang = Integer.parseInt(tableKhachHang.getValueAt(row, 0).toString());
		            String tenKhachHang = tableKhachHang.getValueAt(row, 1).toString();
		            String soDienThoai = tableKhachHang.getValueAt(row, 2).toString();
		            String diaChi = tableKhachHang.getValueAt(row, 3).toString();
		            String loaiKhachHang = tableKhachHang.getValueAt(row, 4).toString();
		            
		            // Hiển thị DialogSuaKhachHang với dữ liệu của hàng được chọn
		            DialogSuaKhachHang dialog = new DialogSuaKhachHang("sua", maKhachHang,Panel_QuanLiKhachHang.this);
		            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		            
		            // Đổ dữ liệu vào các JTextField trong dialog
		            dialog.getTxtTen().setText(tenKhachHang);
		            dialog.getTxtSoDienThoai().setText(soDienThoai);
		            dialog.getTxtDiaChi().setText(diaChi);
		            dialog.getTxtLoai().setText(loaiKhachHang);
		            
		            dialog.setVisible(true);
		            
		            // Thêm WindowListener để theo dõi sự kiện đóng của dialog
		            dialog.addWindowListener(new WindowListener() {
		                @Override
		                public void windowClosed(WindowEvent e) {
		                    // Kiểm tra nếu trạng thái sửa của dialog là true (đã thực hiện sửa thông tin)
		                    if (dialog.isTrangThaiSua()) {
		                        // Cập nhật lại bảng sau khi sửa thông tin khách hàng
		                        docDuLieuVaoTable();
		                    }
		                }
		                
		                // Các phương thức khác của WindowListener
		                @Override
		                public void windowOpened(WindowEvent e) {}
		                @Override
		                public void windowClosing(WindowEvent e) {}
		                @Override
		                public void windowIconified(WindowEvent e) {}
		                @Override
		                public void windowDeiconified(WindowEvent e) {}
		                @Override
		                public void windowActivated(WindowEvent e) {}
		                @Override
		                public void windowDeactivated(WindowEvent e) {}
		            });
		        }
		    }
		});

	}

	// Phương thức đọc dữ liệu từ cơ sở dữ liệu vào bảng
	public void docDuLieuVaoTable() {
		ArrayList<KhachHang> list = khachHangDAO.getalltbKhachHang();
		for (KhachHang kh : list) {
			((DefaultTableModel) khachHangModel).addRow(new Object[] { kh.getMaKhachHang(), kh.getTen(),
					kh.getSoDienThoai(), kh.getDiaChi(), kh.getLoaiKhachHang() });
		}
	}
	
	public void UpdateTable() {
		DefaultTableModel model = (DefaultTableModel) tableKhachHang.getModel();
		model.setRowCount(0);
	}
	// Xử lý sự kiện cho các nút chức năng và nút Tìm
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

//  public static void main(String[] args) {
//		new Panel_QuanLiKhachHang();
//	}

}
