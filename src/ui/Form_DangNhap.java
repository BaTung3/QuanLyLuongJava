package ui;

import application.RunApplication;
import dao.TaiKhoan_Dao;
import entity.TaiKhoan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_DangNhap extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -1197763560953830596L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblTenDangNhap, lblMatKhau;
    JTextField txtTenDN;
    JPasswordField tfMatKhau;
    JCheckBox cbxHienThi;
    JButton btnDangNhap, btnThoat, btnDangKy;

    public Form_DangNhap() {
        doShow();
    }

    public void doShow() {
        setSize(600, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Đăng Nhập Hệ Thống");

        //pnNorth
        pnNorth = new JPanel();
        JLabel lblTieuDe = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        lblTieuDe.setForeground(Color.RED);
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
        pnNorth.add(lblTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        Box b, b1, b2, b3;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(450, 180));
        b.add(Box.createVerticalStrut(30));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblTenDangNhap = new JLabel("Tên Đăng Nhập: "));
        b1.add(Box.createHorizontalStrut(30));
        b1.add(txtTenDN = new JTextField());
        b.add(Box.createVerticalStrut(15));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblMatKhau = new JLabel("Mật Khẩu: "));
        b2.add(Box.createHorizontalStrut(30));
        b2.add(tfMatKhau = new JPasswordField());
        tfMatKhau.setEchoChar('*');
        b.add(Box.createVerticalStrut(15));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(26));
        b3.add(cbxHienThi = new JCheckBox("Hiển Thị Mật Khẩu"));
        b.add(Box.createVerticalStrut(30));

        lblMatKhau.setPreferredSize(lblTenDangNhap.getPreferredSize());

        pnCenter.add(b);

        //pnSouth
        pnSouth = new JPanel();
        Box bc = Box.createHorizontalBox();
        btnDangKy = new JButton("Đăng Ký Tài Khoản");
        //bc.add(btnDangKy = new JButton("Đăng Ký Tài Khoản"));
        btnDangKy.setIcon(new ImageIcon(getClass().getResource("/icons/register_icon.png")));
        btnDangKy.setBackground(Color.decode("#00bcd4"));
        btnDangKy.setForeground(Color.decode("#FFFFFF"));
        bc.add(Box.createHorizontalStrut(90));
        bc.add(btnDangNhap = new JButton("Đăng Nhập"));
        bc.add(Box.createHorizontalStrut(90));
        btnDangNhap.setIcon(new ImageIcon(getClass().getResource("/icons/login_icon.png")));
        btnDangNhap.setBackground(Color.decode("#4caf50"));
        btnDangNhap.setForeground(Color.decode("#FFFFFF"));
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
                if (cbxHienThi.isSelected())
                    tfMatKhau.setEchoChar((char) 0);
                else
                    tfMatKhau.setEchoChar('*');
            }
        });
        btnDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaiKhoan_Dao taiKhoan_dao = new TaiKhoan_Dao();
                TaiKhoan taiKhoan = taiKhoan_dao.TimKiemMa(txtTenDN.getText().trim(), String.valueOf(tfMatKhau.getPassword()).trim());
               
                if (taiKhoan != null ) {
                    
                     if(!RunApplication.IsAdmin(taiKhoan) &&!RunApplication.CheckTrongGioLam()){
                    JOptionPane.showMessageDialog(null, "Nhân viên chỉ được đăng nhập trong giờ làm!");
                    }
                     else{
                    System.out.println("TenDN"+taiKhoan.getTenDN());

                    
                    JOptionPane.showMessageDialog(null, "Đăng nhập hệ thống thành công!");
                    Form_GiaoDienChinh gd = new Form_GiaoDienChinh(taiKhoan, taiKhoan.getTenDN().equalsIgnoreCase("admin") ? true : false  );
                    gd.setVisible(true);
                    setVisible(false);
                     }

                } else {
                    JOptionPane.showMessageDialog(null, "Sai Tên Đăng Nhập Hoặc Mật Khẩu!");
                }
            }
        });

        btnDangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Form_DangKy dangKyTaiKhoan_form = new Form_DangKy();
                dangKyTaiKhoan_form.setVisible(true);
                setVisible(false);
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
                System.exit(0);
            
            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Form_DangNhap().setVisible(true);
    }
}
