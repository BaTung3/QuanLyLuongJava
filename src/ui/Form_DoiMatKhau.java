package ui;

import dao.TaiKhoan_Dao;
import entity.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_DoiMatKhau extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -1197763560953830596L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblTenDangNhap, lblMatKhau;
    JTextField txtTenDN;
    JPasswordField tfMatKhau;
    JCheckBox cbxHienThi;
    JButton btnThoat, btnDangKy;
    private JLabel lblNhapLaiMatKhau;
    private JPasswordField tfNhapLaiMatKhau;
    private JLabel lblQuyen;
    private JTextField txtQuyen;
    boolean rs = false;
    String maCD;
    TaiKhoan taikhoan;
    
    JPanel pnCneter;


    public Form_DoiMatKhau() {
        doShow();
    }
    
      public Form_DoiMatKhau( TaiKhoan taikhoan,JPanel pnCneter) {
         this.taikhoan = taikhoan;
         this.pnCenter = pnCenter;
        doShow();
    }
    
     public Form_DoiMatKhau(JPanel pnCneter) {
         this.pnCenter = pnCenter;
        doShow();
    }
     
   


    public void doShow() {
        setSize(600, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Đổi mật khẩu Tài Khoản");

        //pnNorth
        pnNorth = new JPanel();
        JLabel lblTieuDe = new JLabel("ĐỔI MẬT KHẨU");
        lblTieuDe.setForeground(Color.RED);
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
        pnNorth.add(lblTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        Box b, b1, b2, b3, b4, b5;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(450, 180));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblTenDangNhap = new JLabel("Tên Đăng Nhập: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(txtTenDN = new JTextField());
        b.add(Box.createVerticalStrut(10));
        txtTenDN.setEditable(false);
        txtTenDN.setText(taikhoan.getTenDN());

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblMatKhau = new JLabel("Mật Khẩu Mới: "));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(tfMatKhau = new JPasswordField());
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblNhapLaiMatKhau = new JLabel("Nhập Lại Mật KhẩuMới: "));
        b4.add(Box.createHorizontalStrut(30));
        b4.add(tfNhapLaiMatKhau = new JPasswordField());
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(26));
        b3.add(cbxHienThi = new JCheckBox("Hiển Thị Mật Khẩu"));
        b.add(Box.createVerticalStrut(20));

        lblMatKhau.setPreferredSize(lblNhapLaiMatKhau.getPreferredSize());
        lblTenDangNhap.setPreferredSize(lblNhapLaiMatKhau.getPreferredSize());

        pnCenter.add(b);

        //pnSouth
        pnSouth = new JPanel();
        Box bc = Box.createHorizontalBox();
        bc.add(btnDangKy = new JButton("Đổi mật khẩu"));
        btnDangKy.setIcon(new ImageIcon(getClass().getResource("/icons/register_icon.png")));
        btnDangKy.setBackground(Color.decode("#00bcd4"));
        btnDangKy.setForeground(Color.decode("#FFFFFF"));
        bc.add(Box.createHorizontalStrut(90));
        bc.add(btnThoat = new JButton("Thoát"));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/icons/cancle_icon.png")));
        btnThoat.setBackground(Color.decode("#ff0004"));
        btnThoat.setForeground(Color.decode("#FFFFFF"));

        pnSouth.add(bc);
        pnSouth.setPreferredSize(new Dimension(0, 70));

        cbxHienThi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(String.valueOf(tfMatKhau.getPassword()));
                if (cbxHienThi.isSelected()) {
                    tfMatKhau.setEchoChar((char) 0);
                    tfNhapLaiMatKhau.setEchoChar((char) 0);
                } else {
                    tfMatKhau.setEchoChar('*');
                    tfNhapLaiMatKhau.setEchoChar('*');
                }
            }
        });
        TaiKhoan_Dao taiKhoan_dao = new TaiKhoan_Dao();
        btnDangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTenDN.getText().isEmpty() || tfMatKhau.getPassword().toString().isEmpty() || tfNhapLaiMatKhau.getPassword().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bạn vui lòng điền đầy đủ thông tin!");
                    txtTenDN.requestFocus();
                } else {
                    if (String.valueOf(tfNhapLaiMatKhau.getPassword()).trim().equalsIgnoreCase(String.valueOf(tfMatKhau.getPassword()).trim())) {
                        TaiKhoan taiKhoan = new TaiKhoan("DN001", txtTenDN.getText().trim(), String.valueOf(tfMatKhau.getPassword()).trim());
                        if (taiKhoan != null) {
                            taiKhoan_dao.addTaiKhoan(taiKhoan);
                            //JOptionPane.showMessageDialog(null, "Đăng ký tài khoản thành công, mời bạn đăng nhập để vào hệ thống!");
                            JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                            setVisible(false);
                           // Form_DangNhap form_dangNhap = new Form_DangNhap();
                           // form_dangNhap.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Mật khẩu không khớp!");
                    }
                }
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                 pnCneter.removeAll();
                 validate();
//                Form_DangNhap dangNhap_form = new Form_DangNhap();
//                dangNhap_form.setVisible(true);
            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);
    }
}
