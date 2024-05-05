package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.HoaDon_DAO;
import entity.HoaDon;

public class Panel_ThongKeHoaDon extends JPanel{
	private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
	private ArrayList<HoaDon> listHDTheoThang = new ArrayList<HoaDon>();
	private DefaultCategoryDataset datasetTheoNgay;
	private DefaultCategoryDataset datasetTheoThang;
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
	public Panel_ThongKeHoaDon() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		listHDTheoThang = hoaDon_DAO.getAllHoaDonTheoThang(LocalDate.now());
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));
		add(pnNorth, BorderLayout.NORTH);
		
		JLabel lbkq = new JLabel("Kết Quả Bán Hàng Trong Tháng Này");
		lbkq.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JTextField txtKQBH = new JTextField("Hóa Đơn trong tháng: " + listHDTheoThang.size());
		txtKQBH.setEditable(false);
		txtKQBH.setFont(new Font("Times New Roman", Font.BOLD, 20));
		double total = 0;
		for (HoaDon hoaDon : listHDTheoThang) {
			total += hoaDon.getTongTien();
		}
		JTextField txtKQDT = new JTextField("Tổng Doanh Thu: " + currencyFormat.format(total));
		txtKQDT.setEditable(false);
		txtKQDT.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		pnNorth.add(lbkq);
		pnNorth.add(Box.createVerticalStrut(10));
		pnNorth.add(txtKQBH);
		pnNorth.add(Box.createVerticalStrut(10));
		pnNorth.add(txtKQDT);
		
		JPanel pnCen = new JPanel();
		pnCen.setLayout(new BoxLayout(pnCen, BoxLayout.Y_AXIS));
		add(pnCen, BorderLayout.CENTER);
		pnCen.add(Box.createVerticalStrut(10));
		
		datasetTheoNgay = createDatasetTheoNgay();
		JFreeChart chartNgay = ChartFactory.createLineChart(
                "Thống kê doanh thu theo ngày trong tháng", // Tiêu đề biểu đồ
                "Ngày", // Nhãn trục x
                "Doanh thu", // Nhãn trục y
                datasetTheoNgay
        );
		
		ChartPanel chartPanelNgay = new ChartPanel(chartNgay); 
		
		
		datasetTheoThang = createDatasetTheoThang();
		JFreeChart chartThang = ChartFactory.createLineChart(
                "Thống kê doanh thu theo tháng trong năm", // Tiêu đề biểu đồ
                "Tháng", // Nhãn trục x
                "Doanh thu", // Nhãn trục y
                datasetTheoThang
        );
		
		ChartPanel chartPanelThang = new ChartPanel(chartThang); 
		
		//Tạo tab panel
		JTabbedPane tabbedPane = new JTabbedPane();
		pnCen.add(tabbedPane);
		
		tabbedPane.add("Doanh thu theo ngày trong tháng", chartPanelNgay);
		tabbedPane.add("Doanh thu theo tháng trong năm", chartPanelThang);
		
	}
	
	private DefaultCategoryDataset createDatasetTheoNgay() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		LocalDate currentDate = LocalDate.now();
		Map<Integer, Double> data = hoaDon_DAO.thongKeDoanhThuTheoNgay(currentDate.getMonthValue(), currentDate.getYear());
		for (int i = 1; i <= currentDate.lengthOfMonth(); i++) {
			if (data.containsKey(i)) {
				dataset.setValue(data.get(i), "Doanh Thu", String.valueOf(i));
			} else {
				dataset.setValue(0, "Doanh Thu", String.valueOf(i));
			}
		}
		
		return dataset;
	}
	
	private DefaultCategoryDataset createDatasetTheoThang() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		LocalDate currentDate = LocalDate.now();
		Map<Integer, Double> data = hoaDon_DAO.thongKeDoanhThuTheoThang(currentDate.getYear());
		for (int i = 1; i <= 12; i++) {
			if (data.containsKey(i)) {
				dataset.setValue(data.get(i), "Doanh Thu", String.valueOf(i));
			} else {
				dataset.setValue(0, "Doanh Thu", String.valueOf(i));
			}
		}
		return dataset;
	}
 }
