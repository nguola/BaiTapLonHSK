package bai2_tuan1_congTruNhanChia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

public class CongTruNhanChia extends JFrame implements ActionListener {
    JButton BGiai, BXoa, BThoat;
    JTextField JT1, JT2, JT3;
    JRadioButton JR1, JR2, JR3, JR4;

    public CongTruNhanChia() {
        super("Cộng trừ nhân chia");
        setSize(400, 320);
        setLayout(new BorderLayout());

        JLabel jNorth = new JLabel("Cộng trừ nhân chia");
        jNorth.setFont(new Font("Times New Roman", Font.BOLD, 30));
        jNorth.setForeground(Color.blue);
        jNorth.setHorizontalAlignment(JLabel.CENTER);

        add(jNorth, BorderLayout.NORTH);

        JPanel jChonTacVu = new JPanel();
        jChonTacVu.setLayout(new BoxLayout(jChonTacVu, BoxLayout.Y_AXIS));
        jChonTacVu.setBackground(Color.lightGray);

        JPanel JBT1 = new JPanel();
        JPanel JBT2 = new JPanel();
        JPanel JBT3 = new JPanel();

        JBT1.add(BGiai = new JButton("Giải"));
        JBT1.setBackground(Color.lightGray);
        BGiai.setPreferredSize(new Dimension(70, 30));
        BGiai.addActionListener(this);
        jChonTacVu.add(JBT1);

        JBT2.add(BXoa = new JButton("Xóa"));
        JBT2.setBackground(Color.lightGray);
        BXoa.addActionListener(this);
        BXoa.setPreferredSize(new Dimension(70, 30));
        jChonTacVu.add(JBT2);

        JBT3.add(BThoat = new JButton("Thoát"));
        JBT3.setBackground(Color.lightGray);
        BThoat.addActionListener(this);
        BThoat.setPreferredSize(new Dimension(70, 30));
        jChonTacVu.add(JBT3);

        JPanel jWest = new JPanel();
        jWest.setBackground(Color.lightGray);

        jWest.add(jChonTacVu);
        jWest.setBorder(BorderFactory.createTitledBorder("Chọn tác vụ"));

        add(jWest, BorderLayout.WEST);

        JPanel jNhapA, jNhapB, jPhepToan, jKetQua;
        JLabel JL1, JL2, JL3;
        JL1 = new JLabel("Nhập a:");
        JT1 = new JTextField(17);
        jNhapA = new JPanel();
        jNhapA.add(JL1);
        jNhapA.add(JT1);

        JL2 = new JLabel("Nhập b:");
        JT2 = new JTextField(17);
        jNhapB = new JPanel();
        jNhapB.add(JL2);
        jNhapB.add(JT2);

        jPhepToan = new JPanel(new GridLayout(2, 2, 5, 0));
        jPhepToan.setPreferredSize(new Dimension(175, 100));
        JR1 = new JRadioButton("Cộng", false);
        JR2 = new JRadioButton("Trừ", false);
        JR3 = new JRadioButton("Nhân", false);
        JR4 = new JRadioButton("chia", false);

        JPanel C = new JPanel();

        ButtonGroup bg = new ButtonGroup();

        bg.add(JR1);
        bg.add(JR2);
        bg.add(JR3);
        bg.add(JR4);
        jPhepToan.setBorder(BorderFactory.createTitledBorder("Phép toán"));

        jPhepToan.add(JR1);
        jPhepToan.add(JR2);
        jPhepToan.add(JR3);
        jPhepToan.add(JR4);

        C.add(new JLabel("              "));
        C.add(jPhepToan);

        JL3 = new JLabel("Kết quả");
        JT3 = new JTextField(17);
        JT3.setEditable(false);
        jKetQua = new JPanel();
        jKetQua.add(JL3);
        jKetQua.add(JT3);

        JPanel jCenter = new JPanel();
        jCenter.setLayout(new BoxLayout(jCenter, BoxLayout.Y_AXIS));
        jCenter.setBorder(BorderFactory.createTitledBorder("Tính toán"));

        jCenter.add(jNhapA);
        jCenter.add(jNhapB);
        jCenter.add(C);
        jCenter.add(jKetQua);

        add(jCenter, BorderLayout.CENTER);

        JPanel jSouth = new JPanel();
        jSouth.setBackground(Color.PINK);

        JButton bBLue = new JButton();
        bBLue.setBackground(Color.BLUE);
        bBLue.setPreferredSize(new Dimension(20, 20));

        JButton bRed = new JButton();
        bRed.setBackground(Color.RED);
        bRed.setPreferredSize(new Dimension(20, 20));

        JButton bYellow = new JButton();
        bYellow.setBackground(Color.YELLOW);
        bYellow.setPreferredSize(new Dimension(20, 20));

        jSouth.add(bBLue);
        jSouth.add(bRed);
        jSouth.add(bYellow);
        add(jSouth, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        try {
            if (source.equals(BGiai)) {
                String s = JT1.getText();
                int a = Integer.parseInt(s);
                s = JT2.getText();
                int b = Integer.parseInt(s);
                if (JR1.isSelected()) {
                    JT3.setText("" + (a + b));
                } else if (JR2.isSelected()) {
                    JT3.setText("" + (a - b));
                } else if (JR3.isSelected()) {
                    JT3.setText("" + (a * b));
                } else if (JR4.isSelected()) {
                    double chia = (Math.ceil((double) a / b * 100) / 100);
                    JT3.setText("" + chia);
                } else {
                    JT3.setText("Hãy chọn phép toán!");
                }
            } else if (source.equals(BXoa)) {
                JT1.setText("");
                JT2.setText("");
                JT3.setText("");
            } else if (source.equals(BThoat)) exit(1);
        }
        catch (NumberFormatException errol){
            JOptionPane.showMessageDialog(this ,"Dữ liêuj không hợp lệ");
        }
    }
}
