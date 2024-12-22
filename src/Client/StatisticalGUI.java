package Client;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTabbedPane;

public class StatisticalGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticalGUI frame = new StatisticalGUI();
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
	public StatisticalGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STATISTICAL");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(295, 11, 154, 26);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 47, 695, 353);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
	    panel.setLayout(null); // Sử dụng layout tùy chỉnh nếu cần
	    tabbedPane.addTab("STUDENT COUNT", null, panel, null);
	    
	 // Gắn biểu đồ vào tab "Student"
	    setDataToChart1(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null); // Sử dụng layout tùy chỉnh nếu cần
		tabbedPane.addTab("STUDENT SCORE", null, panel_1, null);
		
		setDataToChart2(panel_1);
		
	}
	 public void setDataToChart1(JPanel jpnItem) {
		 GetData getData = new GetData();
		 List<String> listItem = getData.getThongke();

	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        if (listItem != null) {
	            for (String item : listItem) {
	            	String[] parts = item.split("-"); // Tách chuỗi bằng dấu "-"
	                String ngayLam = parts[0];
	                int soLuongHocVien = Integer.valueOf(parts[1]) ;
	                dataset.addValue(soLuongHocVien, "Số lượng sinh viên", ngayLam);
	            }
	        }

	        JFreeChart barChart = ChartFactory.createBarChart(
	                "Biểu đồ thống kê số lượng sinh viên kiểm tra thi trong ngày".toUpperCase(),
	                "Thời gian", "Số lượng",
	                dataset, PlotOrientation.VERTICAL, false, true, false);

	        ChartPanel chartPanel = new ChartPanel(barChart);
	        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

	        jpnItem.removeAll();
	        jpnItem.setLayout(new CardLayout());
	        jpnItem.add(chartPanel);
	        jpnItem.validate();
	        jpnItem.repaint();
	   }
	 public void setDataToChart2(JPanel jpnItem) {
		    // Lấy dữ liệu thống kê từ bảng thongke
		    GetData getData = new GetData();
		    List<String> listItem = getData.getThongkeDiemThi();

		    // Tạo dataset cho biểu đồ tròn
		    DefaultPieDataset dataset = new DefaultPieDataset();

		    if (listItem != null) {
		        for (String item : listItem) {
		            // Giả sử chuỗi item có định dạng "diem-soluong"
		            String[] parts = item.split("-"); // Tách chuỗi bằng dấu "-"
		            String diem = parts[0];          // Cột điểm
		            int soLuong = Integer.parseInt(parts[1]); // Số lượng sinh viên có điểm này
		            dataset.setValue("Điểm " + diem, soLuong);
		        }
		    }

		    // Tạo biểu đồ tròn
		    JFreeChart chart = ChartFactory.createPieChart(
		            "BIỂU ĐỒ THỐNG KÊ ĐIỂM THI",   
		            dataset,             
		            true,                 
		            true,                 
		            false                 
		    );

		 
		    ChartPanel chartPanel = new ChartPanel(chart);
		    chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

		    jpnItem.removeAll();
		    jpnItem.setLayout(new CardLayout());
		    jpnItem.add(chartPanel);
		    jpnItem.validate();
		    jpnItem.repaint();
		}

}
