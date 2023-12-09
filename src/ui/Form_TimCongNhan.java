package ui;

import dao.CongNhan_Dao;
import dao.PhongBan_Dao;
import entity.CongNhan;
import entity.Phongban;
import table_model.CongNhan_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Form_TimCongNhan extends JPanel {
    JPanel pnNorth, pnCenter;
    JTextField txtTenCN, txtCMND, txtDiaChi;
    JRadioButton rdTenCN, rdCMND, rdDiaChi, rdPhongBan;
    JComboBox cbcPhongBan;
    ButtonGroup btnGR;
    JButton btnTim, btnThoat;

    public Form_TimCongNhan() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnNorth_N = new JPanel();
        JLabel lblTieuDe = new JLabel("TÌM KIẾM THÔNG TIN CÔNG NHÂN");
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.RED);
        pnNorth_N.add(lblTieuDe);

        JPanel pnNorth_C = new JPanel();
        Box b, b1, b2, b3;
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(700, 160));
        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(rdTenCN = new JRadioButton("Tên Công Nhân"));
        b1.add(txtTenCN = new JTextField());
        b1.add(Box.createHorizontalStrut(30));
        b1.add(rdCMND = new JRadioButton("Số CMND"));
        b1.add(txtCMND = new JTextField());
        b.add(Box.createVerticalStrut(20));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(rdDiaChi = new JRadioButton("Địa Chỉ"));
        b3.add(txtDiaChi = new JTextField());
        b3.add(Box.createHorizontalStrut(30));
        b3.add(rdPhongBan = new JRadioButton("Phòng Ban"));
        b3.add(cbcPhongBan = new JComboBox<>());
        PhongBan_Dao phongBan_dao = new PhongBan_Dao();
        for (Phongban phongban : phongBan_dao.getLS()) {
            cbcPhongBan.addItem(phongban.getTenPB());
        }
        cbcPhongBan.setPreferredSize(new Dimension(225, 20));
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


        rdPhongBan.setPreferredSize(rdTenCN.getPreferredSize());
        rdDiaChi.setPreferredSize(rdTenCN.getPreferredSize());
        rdCMND.setPreferredSize(rdTenCN.getPreferredSize());

        btnGR = new ButtonGroup();
        btnGR.add(rdTenCN);
        btnGR.add(rdCMND);
        btnGR.add(rdDiaChi);
        btnGR.add(rdPhongBan);
        pnNorth_C.add(b);

        pnNorth.setLayout(new BorderLayout());
        pnNorth.add(pnNorth_N, BorderLayout.NORTH);
        pnNorth.add(pnNorth_C, BorderLayout.CENTER);

        txtTenCN.setEditable(false);
        txtCMND.setEditable(false);
        txtDiaChi.setEditable(false);
        cbcPhongBan.setEnabled(false);

        rdTenCN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenCN.setEditable(true);
                txtCMND.setEditable(false);
                txtDiaChi.setEditable(false);
                cbcPhongBan.setEnabled(false);
            }
        });

        rdCMND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenCN.setEditable(false);
                txtCMND.setEditable(true);
                txtDiaChi.setEditable(false);
                cbcPhongBan.setEnabled(false);
            }
        });

        rdDiaChi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenCN.setEditable(false);
                txtCMND.setEditable(false);
                txtDiaChi.setEditable(true);
                cbcPhongBan.setEnabled(false);
            }
        });

        rdPhongBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenCN.setEditable(false);
                txtCMND.setEditable(false);
                txtDiaChi.setEditable(false);
                cbcPhongBan.setEnabled(true);
            }
        });

        this.setLayout(new BorderLayout());
        pnNorth_C.setBorder(new TitledBorder("Tìm Kiếm Công Nhân Theo"));


        //pnCenter
        pnCenter = new JPanel();
        java.util.List<CongNhan> ls = new ArrayList<>();
        CongNhan_Table model = new CongNhan_Table(ls);
        JTable table = new JTable();
        table.setModel(model);
        JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(1100, 300));
        pnCenter.add(sc);
        pnCenter.setBorder(new TitledBorder("Kết Quả Tìm Kiếm"));

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdTenCN.isSelected()) {
                    if (!txtTenCN.getText().trim().equals("")) {
                        CongNhan_Dao congNhan_dao = new CongNhan_Dao();
                        if (congNhan_dao.TimKiemTenTD(txtTenCN.getText().trim()) != null) {
                            java.util.List<CongNhan> ls = congNhan_dao.TimKiemTenTD(txtTenCN.getText().trim());
                            table.setModel(new CongNhan_Table(ls));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy!");
                            table.setModel(new CongNhan_Table(ls));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập tên cần tìm!");
                    }
                } else if (rdCMND.isSelected()) {
                    if (!txtCMND.getText().trim().equals("")) {
                        CongNhan_Dao congNhan_dao = new CongNhan_Dao();
                        if (congNhan_dao.TimKiemCMTD(txtCMND.getText().trim()) != null) {
                            java.util.List<CongNhan> ls = congNhan_dao.TimKiemCMTD(txtCMND.getText().trim());
                            table.setModel(new CongNhan_Table(ls));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy!");
                            table.setModel(new CongNhan_Table(ls));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập số điện thoại cần tìm!");
                    }
                } else if (rdDiaChi.isSelected()) {
                    if (!txtDiaChi.getText().trim().equals("")) {
                        CongNhan_Dao congNhan_dao = new CongNhan_Dao();
                        if (congNhan_dao.TimKiemDCTD(txtDiaChi.getText().trim()) != null) {
                            java.util.List<CongNhan> ls = congNhan_dao.TimKiemDCTD(txtDiaChi.getText().trim());
                            table.setModel(new CongNhan_Table(ls));
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy!");
                            table.setModel(new CongNhan_Table(ls));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập Địa Chỉ cần tìm!");
                    }
                } else {
                    CongNhan_Dao congNhan_dao = new CongNhan_Dao();
                    Phongban phongban = phongBan_dao.TimKiemTen(cbcPhongBan.getSelectedItem().toString());
                    if (congNhan_dao.TimKiemPCTD(phongban.getMaPB()) != null) {
                        java.util.List<CongNhan> ls = congNhan_dao.TimKiemPCTD(phongban.getMaPB());
                        table.setModel(new CongNhan_Table(ls));
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy!");
                        table.setModel(new CongNhan_Table(ls));
                    }

                }
            }
        });


        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
    }
}