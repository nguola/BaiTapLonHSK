package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class ThongKeDoanhThu_GUI extends JFrame implements ActionListener {
	private JRadioButton radio_doanhThuTheoLoai;
	private JComboBox<String> cbbTime;
	private NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
	private SanPham_DAO sanPham_dao = new SanPham_DAO();
	private NhanVien nv;
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	public ThongKeDoanhThu_GUI(TaiKhoan tk) {
		super();
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		nv = nhanVien_dao.getNhanVienTheoMaNV(tk.getNhanvien().getMaNhanVien());
		// Cấu hình cho trang
		setLayout(new BorderLayout());
		setSize(800, 600);

		// Code tiêu đề
		JPanel jp_North = new JPanel(new BorderLayout());
		jp_North = new JPanel();
		JLabel TieuDe = new JLabel("Thống kê");
		TieuDe.setFont(new Font("Time new roman", Font.BOLD, 35));
		TieuDe.setForeground(Color.blue);
		jp_North.add(TieuDe);
		add(jp_North, BorderLayout.NORTH);

		// Code các lựa chọn thống kê

		// Code màng hình biểu đồ thống kê
		JPanel Do_Thi = new JPanel(new BorderLayout());

		Box west = Box.createHorizontalBox();

		Box tacVu = Box.createVerticalBox();

		ButtonGroup radio_group = new ButtonGroup();
		radio_doanhThuTheoLoai = new JRadioButton("Doanh thu theo loại sản phẩm");
		radio_doanhThuTheoLoai.setSelected(true);
		radio_group.add(radio_doanhThuTheoLoai);
		JPanel chonKieuThongKe = new JPanel(new FlowLayout(FlowLayout.LEFT));
		chonKieuThongKe.add(radio_doanhThuTheoLoai);
		chonKieuThongKe.setBorder(BorderFactory.createTitledBorder("Chọn kiểu thống kê"));
		tacVu.add(chonKieuThongKe);

		String[] time = { "Tất cả", "Ngày này", "Tuần này", "Tháng này" };
		cbbTime = new JComboBox<String>(time);
		cbbTime.setFont(new Font("Arial", Font.BOLD, 15));
		JPanel chonThoiGian = new JPanel(new FlowLayout(FlowLayout.LEFT));
		chonThoiGian.add(cbbTime);
		chonThoiGian.setBorder(BorderFactory.createTitledBorder("Chọn thời gian"));
		tacVu.add(chonThoiGian);
		tacVu.add(Box.createVerticalStrut(700));

		west.add(Box.createHorizontalStrut(40));
		west.add(tacVu);
		west.add(Box.createHorizontalStrut(50));
		Do_Thi.add(west, BorderLayout.WEST);

		ChartPanel chartPanel = new ChartPanel(bieuDoThongKeDoanhThuTheoLoai(null, null));
		chartPanel.setPreferredSize(new Dimension(600, 600));
		Do_Thi.add(chartPanel, BorderLayout.CENTER);

		Do_Thi.add(Box.createHorizontalStrut(50), BorderLayout.EAST);

		add(Do_Thi, BorderLayout.CENTER);
		
		// Add actionListener
		cbbTime.addActionListener(this);
	}

	public JFreeChart bieuDoThongKeDoanhThuTheoLoai(Date dateStart, Date dateEnd) {
		// Cập nhật lại bộ giá trị của biểu đồ
		updateDataSet(dateStart, dateEnd);
		// Tạo biểu đồ từ dữ liệu
		JFreeChart chart = ChartFactory.createStackedBarChart3D("Biểu đồ thống kê doanh thu theo từng loại sản phẩm", // Tiêu đề của biểu đồ
				"Loại sản phẩm", // Nhãn trục X
				"Doanh thu", // Nhãn trục Y
				dataset // Dữ liệu của biểu đồ
		);

		return chart;
	}

	public void updateDataSet(Date dateStart, Date dateEnd) {
		Map<Date, Double> data = sanPham_dao.thongKeDoanhThuCuaHang(dateStart, dateEnd);
		
		dataset.clear();
		
		for (Entry<Date, Double> entry : data.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), entry.getKey());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cbbTime)) {
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
			
			updateDataSet(timeStart, timeEnd);
		}
	}
}
