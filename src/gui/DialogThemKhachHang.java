package gui;

import javax.swing.*;

import dao.KhachHang_DAO;
import entity.KhachHang;

import java.awt.*;
import java.awt.event.*;

public class DialogThemKhachHang extends JDialog implements MouseListener {
	private final JPanel contentPanel = new JPanel();
	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblDiaChi, lblLoaiKhachHang, lblTitle;
	private boolean trangThaiThem = false;
	private JTextField txtMa, txtTen, txtSoDienThoai, txtDiaChi;
	JComboBox<String> cbxLoai;
	private JButton btnThem;
	private KhachHang khachhang = new KhachHang();

	public boolean isTrangThaiThem() {
		return trangThaiThem;
	}

	public void setTrangThaiThem(boolean trangThaiThem) {
		this.trangThaiThem = trangThaiThem;
	}

	public DialogThemKhachHang(String type) {
		setModal(true);// chặn tất cả các sự kiện tương tác với các cửa sổ khác của ứng dụng cho đến
						// khi nó được đóng
		setSize(360, 500);
		this.setResizable(false);
		setLocationRelativeTo(null);

		lblTitle = new JLabel("Thêm Khách Hàng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));

		lblTenKhachHang = new JLabel("Tên khách hàng:");
		txtTen = new JTextField(30);
		txtTen.setPreferredSize(new Dimension(100, 30));

		lblSoDienThoai = new JLabel("Số điện thoại:");
		txtSoDienThoai = new JTextField(30);
		txtSoDienThoai.setPreferredSize(new Dimension(100, 30));

		lblDiaChi = new JLabel("Địa chỉ:");
		txtDiaChi = new JTextField(30);
		txtDiaChi.setPreferredSize(new Dimension(100, 30));

		lblLoaiKhachHang = new JLabel("Loại khách hàng:");
		String[] item = {"Thường","VIP"};
		cbxLoai = new JComboBox<String>(item);
		cbxLoai.setPreferredSize(new Dimension(100, 30));
		cbxLoai.setEnabled(false);

		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(0, 255, 204));
		btnThem.setPreferredSize(new Dimension(140, 40));
		btnThem.setFont(new Font("Arial", Font.BOLD, 16));

		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		// title
		JPanel jpTitle = new JPanel();
		jpTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		jpTitle.setPreferredSize(new Dimension(100, 50));
		jpTitle.setBackground(new Color(7, 65, 115));
		jpTitle.add(lblTitle);
		add(jpTitle, BorderLayout.NORTH);

		// prow2
		JPanel prow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prow2.add(lblTenKhachHang);
		prow2.add(txtTen);
		contentPanel.add(prow2);

		// prow3
		JPanel prow3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prow3.add(lblSoDienThoai);
		prow3.add(Box.createHorizontalStrut(10));
		prow3.add(txtSoDienThoai);
		contentPanel.add(prow3);

		// prow4
		JPanel prow4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prow4.add(lblDiaChi);
		prow4.add(Box.createHorizontalStrut(40));
		prow4.add(txtDiaChi);
		contentPanel.add(prow4);

		// prow5
		JPanel prow5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prow5.add(lblLoaiKhachHang);
		prow5.add(cbxLoai);
		contentPanel.add(prow5);

		// Button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(btnThem);
		contentPanel.add(buttonPanel);

		add(contentPanel);

		if (type.equals("them")) {
			btnThem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					 if (!validateData()) {
				            return; 
				        }
					// TODO Auto-generated method stub
					KhachHang khachHang = new KhachHang();
					khachHang.setTen(txtTen.getText());
					khachHang.setSoDienThoai(txtSoDienThoai.getText());
					khachHang.setDiaChi(txtDiaChi.getText());
					khachHang.setLoaiKhachHang(cbxLoai.getSelectedItem().toString());
					if (!new KhachHang_DAO().create(khachHang)) {
						JOptionPane.showMessageDialog(null, "Thêm không thành công!");
					} else {
						JOptionPane.showMessageDialog(null, "Thêm thành công.");
						setTrangThaiThem(true);
						dispose();
					}
				}
			});
		}
	}
	
	 // Hàm validate regex
    private boolean validateData() {
        String tenRegex = "^[a-zA-Z\\s]+";
        String sdtRegex = "^0\\d{9,10}$"; // Số điện thoại bắt đầu bằng số 0 và có độ dài từ 10-11 chữ số
        
        String ten = txtTen.getText();
        String sdt = txtSoDienThoai.getText();

        // Kiểm tra tên
        if (!ten.matches(tenRegex)) {
            JOptionPane.showMessageDialog(null, "Tên không hợp lệ! Vui lòng nhập lại.");
            return false;
        }

        // Kiểm tra số điện thoại
        if (!sdt.matches(sdtRegex)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ! Vui lòng nhập lại.");
            return false;
        }

        return true;
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
