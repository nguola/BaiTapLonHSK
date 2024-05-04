package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import dao.PhieuDat_DAO;
import entity.HoaDon;
import entity.PhieuDat;

public class Panel_ThongKePhieuDat extends JPanel{
	private PhieuDat_DAO phieuDat_DAO = new PhieuDat_DAO();
	private ArrayList<PhieuDat> listPDTheoThang = new ArrayList<>();
	private DefaultCategoryDataset datasetTheoNgay;
	private DefaultCategoryDataset datasetTheoThang;
	public Panel_ThongKePhieuDat() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		listPDTheoThang = phieuDat_DAO.getAllPhieuDatTheoThang(LocalDate.now());
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));
		add(pnNorth, BorderLayout.NORTH);
		
		JLabel lbkq = new JLabel("Thống kê phiếu đặt trong tháng");
		lbkq.setFont(new Font("Times New Roman", Font.BOLD, 25));
		JTextField txtKQBH = new JTextField("Phiếu Đặt trong tháng: " + listPDTheoThang.size());
		txtKQBH.setEditable(false);
		txtKQBH.setFont(new Font("Times New Roman", Font.BOLD, 20));
		double total = 0;
		for (PhieuDat phieuDat : listPDTheoThang) {
			total += phieuDat.getTongTien();
		}
		JTextField txtKQDT = new JTextField("Tổng Chi Phí: " + total);
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
                "Thống kê Chi Phí theo ngày trong tháng", // Tiêu đề biểu đồ
                "Ngày", // Nhãn trục x
                "Chi Phí", // Nhãn trục y
                datasetTheoNgay
        );
		
		ChartPanel chartPanelNgay = new ChartPanel(chartNgay); 
		
		
		datasetTheoThang = createDatasetTheoThang();
		JFreeChart chartThang = ChartFactory.createLineChart(
                "Thống kê Chi Phí theo tháng trong năm", // Tiêu đề biểu đồ
                "Tháng", // Nhãn trục x
                "Chi Phí", // Nhãn trục y
                datasetTheoThang
        );
		
		ChartPanel chartPanelThang = new ChartPanel(chartThang); 
		
		//Tạo tab panel
		JTabbedPane tabbedPane = new JTabbedPane();
		pnCen.add(tabbedPane);
		
		tabbedPane.add("Chi Phí theo ngày trong tháng", chartPanelNgay);
		tabbedPane.add("Chi Phí theo tháng trong năm", chartPanelThang);
		
	}
	
	private DefaultCategoryDataset createDatasetTheoNgay() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		LocalDate currentDate = LocalDate.now();
		Map<Integer, Double> data = phieuDat_DAO.thongKeChiPhiTheoNgay(currentDate.getMonthValue(), currentDate.getYear());
		for (int i = 1; i <= currentDate.lengthOfMonth(); i++) {
			if (data.containsKey(i)) {
				dataset.setValue(data.get(i), "Chi Phí", String.valueOf(i));
			} else {
				dataset.setValue(0, "Chi Phí", String.valueOf(i));
			}
		}
		
		return dataset;
	}
	
	private DefaultCategoryDataset createDatasetTheoThang() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		LocalDate currentDate = LocalDate.now();
		Map<Integer, Double> data = phieuDat_DAO.thongKeChiPhiTheoThang(currentDate.getYear());
		for (int i = 1; i <= 12; i++) {
			if (data.containsKey(i)) {
				dataset.setValue(data.get(i), "Chi Phí", String.valueOf(i));
			} else {
				dataset.setValue(0, "Chi Phí", String.valueOf(i));
			}
		}
		return dataset;
	}
 }
