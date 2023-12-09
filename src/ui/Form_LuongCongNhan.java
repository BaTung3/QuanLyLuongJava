package ui;

import dao.*;
import entity.*;
import table_model.CongNhan_Table;
import table_model.LuongCongNhan_Table;
import table_model.LuongNhanVien_Table;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Form_LuongCongNhan extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblNam, lblCongNhan, lblMaCongNhan, lblThang, lblMaLuong, lblThucLanh;
    JTextField txtMaLuong, txtThucLanh;
    JComboBox cbcThang, cbcNam, cbcMaCN, cbcTenCN;

    public Form_LuongCongNhan(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("LƯƠNG CÔNG NHÂN");
        lblTieuDe.setFont(new Font("arial", Font.BOLD,20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        Box b,b1,b2,b3,b4,b5,b6;
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(70));
        b.setPreferredSize(new Dimension(680,140));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblMaCongNhan = new JLabel("Mã Công Nhân:"));
        b2.add(cbcMaCN = new JComboBox<>());
        cbcMaCN.addItem("Tất Cả");
        CongNhan_Dao congNhan_dao = new CongNhan_Dao();
        for (CongNhan congNhan : congNhan_dao.getLS()) {
            cbcMaCN.addItem(congNhan.getMaCN());
        }
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblCongNhan = new JLabel("Công Nhân: "));
        b2.add(cbcTenCN = new JComboBox<>());
        cbcTenCN.addItem("Tất Cả");
        for (CongNhan congNhan : congNhan_dao.getLS()) {
            cbcTenCN.addItem(congNhan.getHoTen());
        }
        cbcTenCN.setPreferredSize(new Dimension(260, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblMaLuong = new JLabel("Mã Lương: "));
        b3.add(txtMaLuong = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblThang = new JLabel("Tháng:"));
        b3.add(cbcThang = new JComboBox<>());
        for (int i = 1; i <= 12; i++) {
            cbcThang.addItem(i);
        }
        cbcThang.setPreferredSize(new Dimension(260, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblThucLanh = new JLabel("Thực Lãnh: "));
        b4.add(txtThucLanh = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblNam = new JLabel("Năm: "));
        b4.add(cbcNam = new JComboBox<>());
        for (int i = 2022; i <= 2030; i++) {
            cbcNam.addItem(i);
        }
        cbcNam.setPreferredSize(new Dimension(260, 20));
        b.add(Box.createVerticalStrut(60));

        txtMaLuong.setEditable(false);
        txtThucLanh.setEditable(false);

        if (cbcMaCN.getSelectedIndex() == 0) {
            cbcTenCN.setSelectedIndex(0);
        }

        cbcMaCN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbcTenCN.setSelectedIndex(cbcMaCN.getSelectedIndex());
            }
        });

        cbcTenCN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbcMaCN.setSelectedIndex(cbcTenCN.getSelectedIndex());
            }
        });


        lblThang.setPreferredSize(lblMaCongNhan.getPreferredSize());
        lblThucLanh.setPreferredSize(lblMaCongNhan.getPreferredSize());
        lblMaLuong.setPreferredSize(lblMaCongNhan.getPreferredSize());
        lblNam.setPreferredSize(lblMaCongNhan.getPreferredSize());
        lblCongNhan.setPreferredSize(lblMaCongNhan.getPreferredSize());


        JButton btnThem,btnXoa,btnSua,btnThoat,btnExcel;
        pnCenC.add(btnThem = new JButton("Tính Lương"));
        btnThem.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnThem.setBackground(Color.decode("#4caf50"));
        btnThem.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnXoa = new JButton("Xóa Lương"));
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/icons/delete_icon.png")));
        btnXoa.setBackground(Color.decode("#f44336"));
        btnXoa.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnSua = new JButton("Làm Mới"));
        btnSua.setIcon(new ImageIcon(getClass().getResource("/icons/update_icon.png")));
        btnSua.setBackground(Color.decode("#00bcd4"));
        btnSua.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnExcel = new JButton("Xuất Excel"));
        btnExcel.setIcon(new ImageIcon(getClass().getResource("/icons/print_icon.png")));
        btnExcel.setBackground(Color.decode("#ff6900"));
        btnExcel.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnThoat = new JButton("Thoát"));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/icons/cancle_icon.png")));
        btnThoat.setBackground(Color.decode("#ff0004"));
        btnThoat.setForeground(Color.decode("#FFFFFF"));

        JPanel pnCenW = new JPanel();
        java.util.List<CongNhan> cnList = new ArrayList<>();
        SanPham_Dao sanPham_dao = new SanPham_Dao();
        CongNhan_Table congNhan_model = new CongNhan_Table(congNhan_dao.getLS());
        JTable congnhan_table = new JTable();
        congnhan_table.setModel(congNhan_model);
        congnhan_table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = congnhan_table.getSelectedRow();
                if(r != -1) {
                    cbcMaCN.setSelectedItem(congnhan_table.getValueAt(r, 0).toString());
                    cbcTenCN.setSelectedItem(congnhan_table.getValueAt(r, 1).toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane scPhanCong = new JScrollPane(congnhan_table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scPhanCong.setPreferredSize(new Dimension(600,220));
        pnCenW.add(scPhanCong);
        pnCenW.setBorder(new TitledBorder("Danh Sách Công Nhân"));

        pnCenN.setLayout(new BorderLayout());

        pnCenN.add(b, BorderLayout.WEST);
        pnCenN.add(pnCenW, BorderLayout.EAST);
        pnCenN.setBorder(new TitledBorder("Thông tin tính lương"));


        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);

        //pnSouth
        pnSouth = new JPanel();
        List<LuongCongNhan> ls = new ArrayList<>();
        LuongCongNhan_Dao luongCongNhan_dao = new LuongCongNhan_Dao();
        LuongCongNhan_Table model = new LuongCongNhan_Table(luongCongNhan_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    txtMaLuong.setText(table.getValueAt(r, 0).toString());
                    cbcMaCN.setSelectedItem(table.getValueAt(r, 1).toString());
                    cbcTenCN.setSelectedItem(table.getValueAt(r, 2).toString());
                    cbcThang.setSelectedItem(table.getValueAt(r, 8).toString());
                    cbcNam.setSelectedItem(table.getValueAt(r, 9).toString());
                    txtThucLanh.setText(table.getValueAt(r, 10).toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(1100,330));


        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Lương"));

        ChamCongCongNhan_Dao chamCongCongNhan_dao = new ChamCongCongNhan_Dao();
        //Sự Kiện Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbcMaCN.getSelectedIndex() == 0) {
                    for (CongNhan congNhan : congNhan_dao.getLS()) {
                        List<ChamCongCongNhan> congList = chamCongCongNhan_dao.TimKiem(congNhan.getMaCN(), (Integer.parseInt(cbcThang.getSelectedItem().toString())));
                        double luong = 0;
                        for (ChamCongCongNhan cccn: congList) {
                            luong += cccn.getCaLam().getLuongCa() * 200000;
                        }
                        LuongCongNhan luongCongNhan = new LuongCongNhan("L001", Integer.parseInt(cbcThang.getSelectedItem().toString()),
                                Integer.parseInt(cbcNam.getSelectedItem().toString()), luong + congNhan.getPhuCap());
                        luongCongNhan.setCongNhan(congNhan);
                        if (!luongCongNhan_dao.TimKiem(congNhan.getMaCN(), luongCongNhan.getThang(), luongCongNhan.getNam())) {
                            luongCongNhan_dao.addLuongCongNhan(luongCongNhan);
                        }
                    }
                    table.setModel(new LuongCongNhan_Table(luongCongNhan_dao.getLS()));
                } else {
                    CongNhan congNhan = congNhan_dao.TimKiemMa(cbcMaCN.getSelectedItem().toString());
                    List<ChamCongCongNhan> congList = chamCongCongNhan_dao.TimKiem(congNhan.getMaCN(), (Integer.parseInt(cbcThang.getSelectedItem().toString())));
                    double luong = 0;
                    for (ChamCongCongNhan cccn: congList) {
                        luong += cccn.getCaLam().getLuongCa() * 200000;
                    }
                    LuongCongNhan luongCongNhan = new LuongCongNhan("L001", Integer.parseInt(cbcThang.getSelectedItem().toString()),
                            Integer.parseInt(cbcNam.getSelectedItem().toString()), luong + congNhan.getPhuCap());
                    luongCongNhan.setCongNhan(congNhan);
                    if (!luongCongNhan_dao.TimKiem(congNhan.getMaCN(), luongCongNhan.getThang(), luongCongNhan.getNam())) {
                        luongCongNhan_dao.addLuongCongNhan(luongCongNhan);
                    }
                    table.setModel(new LuongCongNhan_Table(luongCongNhan_dao.getLS()));
                }

            }
        });

        //Sự Kiện Xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    int tb = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa dòng này?", "Delete",
                            JOptionPane.YES_NO_OPTION);
                    if (tb == JOptionPane.YES_OPTION) {
                        String maX = table.getValueAt(r,0).toString();
                        if (luongCongNhan_dao.deleteLuongCN(maX)) {
                            try {
                                table.setModel(new LuongCongNhan_Table(luongCongNhan_dao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        clearTextField();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần xóa!");
                }
            }
        });

        btnExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LuongCongNhan_Table model = new LuongCongNhan_Table(luongCongNhan_dao.TimKiemThangNam(Integer.parseInt(cbcThang.getSelectedItem().toString()),Integer.parseInt(cbcNam.getSelectedItem().toString())));
                JTable table = new JTable();
                table.setModel(model);
                exportExcel(table);
            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public void clearTextField(){
    }

    public void exportExcel(JTable table) {
        JFileChooser chooser = new JFileChooser();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file + ".xls");
                BufferedWriter bwrite = new BufferedWriter(out);
                LuongNhanVien_Table model = (LuongNhanVien_Table) table.getModel();
                // ten Cot
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bwrite.write(model.getColumnName(j) + "\t");
                }
                bwrite.write("\n");
                // Lay du lieu dong
                for (int j = 0; j < model.getRowCount(); j++) {
                    for (int k = 0; k < model.getColumnCount(); k++) {
                        bwrite.write(model.getValueAt(j, k) + "\t");
                        System.out.println(model.getValueAt(j, k));
                    }
                    bwrite.write("\n");
                }
                bwrite.close();
                JOptionPane.showMessageDialog(null, "Lưu file thành công!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
            }
        }
    }
}
