package ui;

import dao.NhanVienHanhChinh_Dao;
import dao.PhongBan_Dao;
import dao.SanPham_Dao;
import entity.NhanVienHanhChinh;
import entity.Phongban;
import entity.SanPham;
import table_model.NhanVien_Table;
import table_model.SanPham_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Form_TimSanPham extends JPanel {
    JPanel pnNorth, pnCenter;
    JTextField txtMaSP, txtTenSP, txtKieuDang, txtChatLieu;
    JRadioButton rdMaSP, rdTenSP, rdKieuDang, rdChatLieu;
    ButtonGroup btnGR;
    JButton btnTim, btnThoat;

    public Form_TimSanPham() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_N = new JPanel();
        JLabel lblTieuDe = new JLabel("TÌM KIẾM THÔNG TIN SẢN PHẨM");
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.RED);
        pnNorth_N.add(lblTieuDe);

        JPanel pnNorth_C = new JPanel();
        Box b, b1, b2, b3;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(700, 160));
        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(rdMaSP = new JRadioButton("Mã Sản Phẩm"));
        b1.add(txtMaSP = new JTextField());
        b1.add(Box.createHorizontalStrut(30));
        b1.add(rdTenSP = new JRadioButton("Tên Sản Phẩm"));
        b1.add(txtTenSP = new JTextField());
        b.add(Box.createVerticalStrut(20));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(rdKieuDang = new JRadioButton("Kiểu Dáng"));
        b3.add(txtKieuDang = new JTextField());
        b3.add(Box.createHorizontalStrut(30));
        b3.add(rdChatLieu = new JRadioButton("Chất Liệu"));
        b3.add(txtChatLieu = new JTextField());
        b.add(Box.createVerticalStrut(20));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(btnTim = new JButton("Tìm Kiếm"));
        btnTim.setIcon(new ImageIcon(getClass().getResource("/icons/search_client_icon.png")));
        btnTim.setBackground(Color.decode("#00bcd4"));
        btnTim.setForeground(Color.decode("#FFFFFF"));
        b2.add(Box.createHorizontalStrut(100));
        b2.add(btnThoat = new JButton("Thoát"));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/icons/cancle_icon.png")));
        btnThoat.setBackground(Color.decode("#ff0004"));
        btnThoat.setForeground(Color.decode("#FFFFFF"));
        b.add(Box.createVerticalStrut(60));


        rdChatLieu.setPreferredSize(rdTenSP.getPreferredSize());
        rdKieuDang.setPreferredSize(rdTenSP.getPreferredSize());
        rdMaSP.setPreferredSize(rdTenSP.getPreferredSize());

        btnGR = new ButtonGroup();
        btnGR.add(rdMaSP);
        btnGR.add(rdTenSP);
        btnGR.add(rdKieuDang);
        btnGR.add(rdChatLieu);
        pnNorth_C.add(b);

        pnNorth.setLayout(new BorderLayout());
        pnNorth.add(pnNorth_N, BorderLayout.NORTH);
        pnNorth.add(pnNorth_C, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        pnNorth_C.setBorder(new TitledBorder("Tìm Kiếm Sản Phẩm Theo"));


        //pnCenter
        pnCenter = new JPanel();
        java.util.List<SanPham> ls = new ArrayList<>();
        SanPham_Table model = new SanPham_Table(ls);
        JTable table = new JTable();
        table.setModel(model);
        JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(1100, 300));
        pnCenter.add(sc);
        pnCenter.setBorder(new TitledBorder("Kết Quả Tìm Kiếm"));

        txtMaSP.setEditable(false);
        txtTenSP.setEditable(false);
        txtKieuDang.setEditable(false);
        txtChatLieu.setEditable(false);

        rdMaSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaSP.setEditable(true);
                txtTenSP.setEditable(false);
                txtKieuDang.setEditable(false);
                txtChatLieu.setEditable(false);
            }
        });

        rdTenSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaSP.setEditable(false);
                txtTenSP.setEditable(true);
                txtKieuDang.setEditable(false);
                txtChatLieu.setEditable(false);
            }
        });

        rdKieuDang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaSP.setEditable(false);
                txtTenSP.setEditable(false);
                txtKieuDang.setEditable(true);
                txtChatLieu.setEditable(false);
            }
        });

        rdChatLieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMaSP.setEditable(false);
                txtTenSP.setEditable(false);
                txtKieuDang.setEditable(false);
                txtChatLieu.setEditable(true);
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdMaSP.isSelected()) {
                    if (!txtMaSP.getText().trim().equals("")) {
                        SanPham_Dao sanPham_dao = new SanPham_Dao();
                        if (sanPham_dao.TimKiemMa(txtMaSP.getText().trim()) != null) {
                            java.util.List<SanPham> ls = new ArrayList<>();
                            ls.add(sanPham_dao.TimKiemMa(txtMaSP.getText().trim()));
                            table.setModel(new SanPham_Table(ls));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy!");
                            table.setModel(new SanPham_Table(ls));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập mã sản phẩm cần tìm!");
                    }
                } else if (rdTenSP.isSelected()) {
                    if (!txtTenSP.getText().trim().equals("")) {
                        SanPham_Dao sanPham_dao = new SanPham_Dao();
                        if (sanPham_dao.TimKiemTenTD(txtTenSP.getText().trim()) != null) {
                            java.util.List<SanPham> ls = sanPham_dao.TimKiemTenTD(txtTenSP.getText().trim());
                            table.setModel(new SanPham_Table(ls));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy!");
                            table.setModel(new SanPham_Table(ls));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập tên sản phẩm cần tìm!");
                    }
                } else if (rdKieuDang.isSelected()) {
                    if (!txtKieuDang.getText().trim().equals("")) {
                        SanPham_Dao sanPham_dao = new SanPham_Dao();
                        if (sanPham_dao.TimKiemKDTD(txtKieuDang.getText().trim()) != null) {
                            java.util.List<SanPham> ls = sanPham_dao.TimKiemKDTD(txtKieuDang.getText().trim());
                            table.setModel(new SanPham_Table(ls));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy!");
                            table.setModel(new SanPham_Table(ls));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập Kiểu Dáng cần tìm!");
                    }
                } else {
                    if (!txtChatLieu.getText().trim().equals("")) {
                        SanPham_Dao sanPham_dao = new SanPham_Dao();
                        if (sanPham_dao.TimKiemCLTD(txtChatLieu.getText().trim()) != null) {
                            java.util.List<SanPham> ls = sanPham_dao.TimKiemCLTD(txtChatLieu.getText().trim());
                            table.setModel(new SanPham_Table(ls));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy!");
                            table.setModel(new SanPham_Table(ls));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập Chất liệu cần tìm!");
                    }

                }
            }
        });


        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }
}

