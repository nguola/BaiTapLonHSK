package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class Panel_ThongKe extends JPanel implements ActionListener {
	private JRadioButton radio_doanhThuTheoLoai;
	private JComboBox<String> cbbTime;
	private NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
	private SanPham_DAO sanPham_dao = new SanPham_DAO();
	private NhanVien nv;
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private JRadioButton radio_time;
	private JRadioButton radio_date;
	private JDateChooser startDate;
	private JDateChooser enddate;
	private JRadioButton radio_doanhThuThoiGian;
	private JPanel pn_keuThongKe;
	private JPanel Do_Thi;
	private JPanel pn_chart;
	private JButton btn_thongKe;

	public Panel_ThongKe(TaiKhoan tk) {
		nv = nhanVien_dao.getNhanVienTheoMaNV(tk.getNhanvien().getMaNhanVien());

		// Cấu hình cho trang
		setLayout(new BorderLayout());

		// Code tiêu đề
		JPanel jp_North = new JPanel(new BorderLayout());
		jp_North = new JPanel();
		String text = "Thống kê";
		JLabel TieuDe = new JLabel(text);
		TieuDe.setFont(new Font("Time new roman", Font.BOLD, 35));
		TieuDe.setForeground(Color.blue);
		jp_North.add(TieuDe);
		add(jp_North, BorderLayout.NORTH);

		// Code các lựa chọn thống kê

		// Code màng hình biểu đồ thống kê
		Do_Thi = new JPanel(new BorderLayout());

		Box west = Box.createHorizontalBox();

		Box tacVu = Box.createVerticalBox();

		ButtonGroup radio_group = new ButtonGroup();
		radio_doanhThuTheoLoai = new JRadioButton("Doanh thu theo loại sản phẩm");
		radio_doanhThuTheoLoai.setSelected(true);
		radio_doanhThuThoiGian = new JRadioButton("Doanh thu theo thời gian");
		radio_group.add(radio_doanhThuTheoLoai);
		radio_group.add(radio_doanhThuThoiGian);
		pn_keuThongKe = new JPanel();
		Box chonKieuThongKe = Box.createVerticalBox();
		chonKieuThongKe.add(radio_doanhThuTheoLoai);
		chonKieuThongKe.add(radio_doanhThuThoiGian);
		chonKieuThongKe.setBorder(BorderFactory.createTitledBorder("Chọn kiểu thống kê"));
		pn_keuThongKe.add(chonKieuThongKe);
		tacVu.add(pn_keuThongKe);

		Box pn_chonThoiGian = Box.createVerticalBox();

		radio_time = new JRadioButton();
		radio_time.setSelected(true);
		String[] time = { "Tất cả", "Ngày này", "Tuần này", "Tháng này" };
		cbbTime = new JComboBox<String>(time);
		cbbTime.setFont(new Font("Arial", Font.BOLD, 15));
		JPanel chonThoiGian = new JPanel(new FlowLayout(FlowLayout.LEFT));
		chonThoiGian.add(radio_time);
		chonThoiGian.add(cbbTime);

		Box pn_chonNgay = Box.createHorizontalBox();
		radio_date = new JRadioButton();
		ButtonGroup group_time = new ButtonGroup();
		Box chonNgay = Box.createVerticalBox();
		startDate = new JDateChooser();
		enddate = new JDateChooser();
		btn_thongKe = new JButton("Thống kê");
		pn_chonNgay.add(radio_date);
		chonNgay.add(startDate);
		chonNgay.add(enddate);
		chonNgay.add(btn_thongKe);
		pn_chonNgay.add(chonNgay);

		pn_chonThoiGian.add(chonThoiGian);
		pn_chonThoiGian.add(Box.createHorizontalStrut(20));
		pn_chonThoiGian.add(pn_chonNgay);
		pn_chonThoiGian.setBorder(BorderFactory.createTitledBorder("Chọn thời gian"));
		group_time.add(radio_date);
		group_time.add(radio_time);
		tacVu.add(pn_chonThoiGian);
		tacVu.add(Box.createVerticalStrut(480));
		west.add(Box.createHorizontalStrut(40));
		west.add(tacVu);
		west.add(Box.createHorizontalStrut(30));
		Do_Thi.add(west, BorderLayout.WEST);
		pn_chart = new JPanel(new CardLayout());
		pn_chart.add(new ChartPanel(bieuDoThongKeDoanhThuTheoLoai(null, null)));
		Do_Thi.add(pn_chart, BorderLayout.CENTER);

		Do_Thi.add(Box.createHorizontalStrut(50), BorderLayout.EAST);

		add(Do_Thi, BorderLayout.CENTER);

		// Add actionListener
		cbbTime.addActionListener(this);
		radio_date.addActionListener(this);
		radio_time.addActionListener(this);
		radio_doanhThuTheoLoai.addActionListener(this);
		radio_doanhThuThoiGian.addActionListener(this);
		btn_thongKe.addActionListener(this);
	}

	public JFreeChart bieuDoThongKeDoanhThuTheoLoai(Date dateStart, Date dateEnd) {
		// Cập nhật lại bộ giá trị của biểu đồ
		updateDataSetTheoLoai(dateStart, dateEnd);
		// Tạo biểu đồ từ dữ liệu
		JFreeChart chart = ChartFactory.createStackedBarChart3D("Biểu đồ thống kê doanh thu theo từng loại sản phẩm", // Tiêu
																														// đề
																														// của
																														// biểu
																														// đồ
				"Loại sản phẩm", // Nhãn trục X
				"Doanh thu", // Nhãn trục Y
				dataset // Dữ liệu của biểu đồ
		);

		return chart;
	}

	public JFreeChart bieuDoThongKeDoanhThuTheoThoiGian(Date dateStart, Date dateEnd) {
		// Cập nhật lại bộ giá trị của biểu đồ
		updateDataSetTheoThoiGian(dateStart, dateEnd);
		// Tạo biểu đồ từ dữ liệu
		JFreeChart chart = ChartFactory.createStackedBarChart3D("Biểu đồ thống kê doanh thu theo thời gian", // Tiêu đề
																												// của
																												// biểu
																												// đồ
				"Thời gian", // Nhãn trục X
				"Doanh thu", // Nhãn trục Y
				dataset // Dữ liệu của biểu đồ
		);

		return chart;
	}

	public void updateDataSetTheoLoai(Date dateStart, Date dateEnd) {
		Map<String, Double> data = sanPham_dao.thongKeDoanhThuTheoLoai(nv.getMaNhanVien(), dateStart, dateEnd);

		dataset.clear();

		for (Entry<String, Double> entry : data.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), entry.getKey());
		}

	}

	public void updateDataSetTheoThoiGian(Date dateStart, Date dateEnd) {
		Map<String, Double> data = sanPham_dao.thongKeDoanhThuTheoThoiGian(nv.getMaNhanVien(), dateStart, dateEnd);

		dataset.clear();

		for (Entry<String, Double> entry : data.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), entry.getKey());
		}
	}

	public void chuyenBieuDo(JFreeChart chart) {
		pn_chart.removeAll();
		pn_chart.add(new ChartPanel(chart), BorderLayout.CENTER);
		pn_chart.validate();
		pn_chart.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cbbTime)) {
			if (radio_time.isSelected()) {
				Date timeStart = null;
				Date timeEnd = null;

				String item = (String) cbbTime.getSelectedItem();
				LocalDate now = LocalDate.now();

				switch (item) {
				case "Ngày này": {
					timeStart = Date.valueOf(now);
					timeEnd = Date.valueOf(now);
					break;
				}
				case "Tháng này": {
					LocalDate firstDate = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
					timeStart = Date.valueOf(firstDate);
					timeEnd = Date.valueOf(firstDate.plusMonths(1).minusDays(1));
					break;
				}
				case "Tuần này": {
					timeStart = Date.valueOf(now.with(DayOfWeek.MONDAY));
					timeEnd = Date.valueOf(now.with(DayOfWeek.SUNDAY));
					break;
				}
				}

				if (radio_doanhThuTheoLoai.isSelected()) {
					updateDataSetTheoLoai(timeStart, timeEnd);
				} else {
					updateDataSetTheoThoiGian(timeStart, timeEnd);
				}
			}
		} else if (o.equals(radio_doanhThuThoiGian)) {
			radio_time.setSelected(true);
			cbbTime.setSelectedIndex(0);
			startDate.setDate(null);
			enddate.setDate(null);
			chuyenBieuDo(bieuDoThongKeDoanhThuTheoThoiGian(null, null));
		} else if (o.equals(radio_doanhThuTheoLoai)) {
			radio_time.setSelected(true);
			cbbTime.setSelectedIndex(0);
			startDate.setDate(null);
			enddate.setDate(null);
			chuyenBieuDo(bieuDoThongKeDoanhThuTheoLoai(null, null));
		} else if (o.equals(btn_thongKe)) {
			if (radio_date.isSelected()) {
				Date start = null;
				Date end = null;
				if (startDate.getDate() == null) {
					JOptionPane.showMessageDialog(this, "Hãy chọn ngày bắt đầu");
				} else if (enddate.getDate() == null) {
					JOptionPane.showMessageDialog(this, "Hãy chọn ngày kết thúc");
				} else {
					start = new Date(startDate.getDate().getTime());
					end = new Date(enddate.getDate().getTime());

					if (start.after(end)) {
						JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc");
						startDate.setDate(null);
						enddate.setDate(null);
					} else {
						if (radio_doanhThuTheoLoai.isSelected()) {
							updateDataSetTheoLoai(start, end);
						} else {
							updateDataSetTheoThoiGian(start, end);
						}
					}
				}
			}
		}
		else if(o.equals(radio_date) || o.equals(radio_time)) {
			cbbTime.setSelectedIndex(0);
			startDate.setDate(null);
			enddate.setDate(null);
		}
	}
}
