package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;

public class testChart extends JFrame {
	private SanPham_DAO sanPham_dao = new SanPham_DAO();
	private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
	private NhanVien nv;
	public testChart(TaiKhoan tk) {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		nv = nhanVien_DAO.getNhanVienTheoMaNV(tk.getNhanvien().getMaNhanVien());
        // Hiển thị biểu đồ
        ChartPanel chartPanel = new ChartPanel(bieuDoThongKeDoanhThuTheoLoai());
        
        add(chartPanel);
    }
	
	public JFreeChart bieuDoThongKeDoanhThuTheoLoai() {		
		// Tạo biểu đồ từ dữ liệu
        JFreeChart chart = ChartFactory.createStackedBarChart3D(
                "Biểu đồ thống kê doanh thu theo từng loại sản phẩm", // Tiêu đề của biểu đồ
                "Loại sản phẩm", // Nhãn trục X
                "Doanh thu", // Nhãn trục Y
                createDatabaseSet() // Dữ liệu của biểu đồ
        );
        
        return chart;
	}
	
	public DefaultCategoryDataset createDatabaseSet() {
		Map<String , Double> data = sanPham_dao.thongKeDoanhThuTheoLoai(nv.getMaNhanVien(), null, null);
		
		// Tạo dataset chứa dữ liệu của biểu đồ
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(Entry<String, Double> entry : data.entrySet()){
            dataset.setValue(entry.getValue(), entry.getKey(), entry.getKey());
        }
		
		return dataset;
	}

    public static void main(String[] args) {
            testChart example = new testChart(new TaiKhoan(new NhanVien(3000)));
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setVisible(true);
    }
}
