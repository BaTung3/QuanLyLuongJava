package ui;

import application.RunApplication;
import dao.ChamCongNhanVien_Dao;
import dao.LuongNhanVien_Dao;
import dao.NhanVienHanhChinh_Dao;
import entity.*;
import table_model.ChamCongNV_Table;
import table_model.LuongNhanVien_Table;
import table_model.NhanVien_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Form_LuongNhanVien extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblNam, lblNhanVien, lblMaNhanVien, lblThang, lblMaLuong, lblThucLanh;
    JTextField txtMaLuong; 
    JFormattedTextField     txtThucLanh;
    JComboBox cbcThang, cbcNam, cbcMaNV, cbcTenNV;

    public Form_LuongNhanVien() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("LƯƠNG NHÂN VIÊN");
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        Box b, b1, b2, b3, b4, b5, b6;
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        b = Box.createVerticalBox();
        b.add(Box.createVerticalStrut(70));
        b.setPreferredSize(new Dimension(680, 140));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblMaNhanVien = new JLabel("Mã Nhân Viên:"));
        b2.add(cbcMaNV = new JComboBox<>());
        cbcMaNV.addItem("Tất Cả");
        NhanVienHanhChinh_Dao nhanVienHanhChinh_dao = new NhanVienHanhChinh_Dao();
        for (NhanVienHanhChinh nv : nhanVienHanhChinh_dao.getLS()) {
            cbcMaNV.addItem(nv.getMaNV());
        }
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNhanVien = new JLabel("Nhân Viên: "));
        b2.add(cbcTenNV = new JComboBox<>());
        cbcTenNV.addItem("Tất Cả");
        for (NhanVienHanhChinh nv : nhanVienHanhChinh_dao.getLS()) {
            cbcTenNV.addItem(nv.getHoTen());
        }
        cbcTenNV.setPreferredSize(new Dimension(260, 20));
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
        b4.add(txtThucLanh = new JFormattedTextField(30));
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

        if (cbcMaNV.getSelectedIndex() == 0) {
            cbcTenNV.setSelectedIndex(0);
        }

        cbcMaNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbcTenNV.setSelectedIndex(cbcMaNV.getSelectedIndex());
            }
        });

        cbcTenNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbcMaNV.setSelectedIndex(cbcTenNV.getSelectedIndex());
            }
        });

        lblThang.setPreferredSize(lblMaNhanVien.getPreferredSize());
        lblThucLanh.setPreferredSize(lblMaNhanVien.getPreferredSize());
        lblMaLuong.setPreferredSize(lblMaNhanVien.getPreferredSize());
        lblNam.setPreferredSize(lblMaNhanVien.getPreferredSize());
        lblNhanVien.setPreferredSize(lblMaNhanVien.getPreferredSize());


        JButton btnThem, btnXoa, btnSua, btnThoat, btnIn;
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
        pnCenC.add(btnIn = new JButton("Xuất Excel"));
        btnIn.setIcon(new ImageIcon(getClass().getResource("/icons/print_icon.png")));
        btnIn.setBackground(Color.decode("#ff6900"));
        btnIn.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnThoat = new JButton("Thoát"));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/icons/cancle_icon.png")));
        btnThoat.setBackground(Color.decode("#ff0004"));
        btnThoat.setForeground(Color.decode("#FFFFFF"));

        JPanel pnCenW = new JPanel();
        java.util.List<NhanVienHanhChinh> nvList = new ArrayList<>();
        NhanVien_Table nhanVien_table = new NhanVien_Table(nhanVienHanhChinh_dao.getLS());
        JTable nhanvien_model = new JTable();
        nhanvien_model.setModel(nhanVien_table);
        nhanvien_model.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = nhanvien_model.getSelectedRow();
                if (r != -1) {
                    cbcMaNV.setSelectedItem(nhanVien_table.getValueAt(r, 0).toString());
                    cbcTenNV.setSelectedItem(nhanVien_table.getValueAt(r, 1).toString());
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
        JScrollPane scPhanCong = new JScrollPane(nhanvien_model, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scPhanCong.setPreferredSize(new Dimension(600, 220));
        pnCenW.add(scPhanCong);
        pnCenW.setBorder(new TitledBorder("Danh Sách Nhân Viên"));

        pnCenN.setLayout(new BorderLayout());

        pnCenN.add(b, BorderLayout.WEST);
        pnCenN.add(pnCenW, BorderLayout.EAST);
        pnCenN.setBorder(new TitledBorder("Thông tin tính lương"));


        pnCenter.add(pnCenN, BorderLayout.NORTH);
        pnCenter.add(pnCenC, BorderLayout.CENTER);

        //pnSouth
        pnSouth = new JPanel();
        List<LuongNhanVien> ls = new ArrayList<>();
        LuongNhanVien_Dao luongNhanVien_dao = new LuongNhanVien_Dao();
        LuongNhanVien_Table model = new LuongNhanVien_Table(luongNhanVien_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtMaLuong.setText(table.getValueAt(r, 0).toString());
                    cbcMaNV.setSelectedItem(table.getValueAt(r, 1).toString());
                    cbcTenNV.setSelectedItem(table.getValueAt(r, 2).toString());
                    cbcThang.setSelectedItem(table.getValueAt(r, 8).toString());
                    cbcNam.setSelectedItem(table.getValueAt(r, 9).toString());
                    txtThucLanh.setValue(table.getValueAt(r, 10));
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
        JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setPreferredSize(new Dimension(1100, 330));


        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Lương"));

        //Sự Kiện Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                LuongNhanVien_Dao luongNhanVien_Dao = new LuongNhanVien_Dao();
                
                if (cbcMaNV.getSelectedIndex() == 0) {
                    for (NhanVienHanhChinh nv : nhanVienHanhChinh_dao.getLS()) {
                        LuongNhanVien luongNhanVien = new LuongNhanVien("L00"+table.getRowCount()+1, Integer.parseInt(cbcThang.getSelectedItem().toString()),
                                Integer.parseInt(cbcNam.getSelectedItem().toString()), nv.getLuongCoBan() * nv.getHeSoLuong().getHeSoLuong() + nv.getPhuCap());
                        luongNhanVien.setNhanVienHanhChinh(nv);
                        if (!luongNhanVien_dao.TimKiem(nv.getMaNV(), luongNhanVien.getThang(), luongNhanVien.getNam())) {
                            luongNhanVien_dao.addLuongNhanVien(luongNhanVien);
                        }
                    }
                    table.setModel(new LuongNhanVien_Table(luongNhanVien_dao.getLS()));
                } else {
                    NhanVienHanhChinh nhanVienHanhChinh = nhanVienHanhChinh_dao.TimKiemMa(cbcMaNV.getSelectedItem().toString());
                    LuongNhanVien luongNhanVien = new LuongNhanVien("L00"+table.getRowCount()+1, Integer.parseInt(cbcThang.getSelectedItem().toString()),
                            Integer.parseInt(cbcNam.getSelectedItem().toString()),   RunApplication.LuongPhaiTra(nhanVienHanhChinh.getLuongCoBan(),nhanVienHanhChinh.getHeSoLuong().getHeSoLuong(),luongNhanVien_Dao.GetTongTruLuongCa(nhanVienHanhChinh.getMaNV(),Integer.parseInt(cbcThang.getSelectedItem().toString()),Integer.parseInt(cbcNam.getSelectedItem().toString())),(float)nhanVienHanhChinh.getPhuCap())         );
                    luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh);
                    if (!luongNhanVien_dao.TimKiem(nhanVienHanhChinh.getMaNV(), luongNhanVien.getThang(), luongNhanVien.getNam())) {
                        luongNhanVien_dao.addLuongNhanVien(luongNhanVien);
                    }
                    table.setModel(new LuongNhanVien_Table(luongNhanVien_dao.getLS()));
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
                        if (luongNhanVien_dao.deleteLuongNV(maX)) {
                            try {
                                table.setModel(new LuongNhanVien_Table(luongNhanVien_dao.getLS()));
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
        //Su Kien Sua
//        btnSua.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String dateTime = (String) formatter.format(namSinh.getDate());
//                String ngayVao1 = (String) formatter.format(NgayVao.getDate());
//                int r = table.getSelectedRow();
//                System.out.println(table.getRowCount());
//                txtMa.setEnabled(false);
//                if(r != -1){
//                    String maS = txtMa.getText().trim();
//                    NhanVien nvSua = new NhanVien(maS, txtTen.getText(), cbcGT.getSelectedItem().toString(), Date.valueOf(dateTime),
//                            txtChungMinh.getText(), txtSDT.getText(),Integer.parseInt(txtCM.getText()), txtPhuCap.getText(),
//                            Date.valueOf(ngayVao1));
//                    ChucVu_DAO cvDao = new ChucVu_DAO();
//                    ChucVu cv = cvDao.TimKiemTen(cbcPhongBan.getSelectedItem().toString());
//                    nvSua.setChucVu(cv);
//                    System.out.println(nvSua);
//                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?","option",JOptionPane.YES_NO_OPTION);
//                    if(lc == JOptionPane.YES_OPTION) {
//                        if(nvDao.updateNhanVien(maS,nvSua)) {
//                            try {
//                                table.setModel(new NV_TableModel(nvDao.getLS()));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                    }
//
//                    clearTextField();
//
//                }else {
//                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần sửa!");
//                }
//            }
//        });

        //Su kien luu
//        btnLuu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                NhanVien_DAO nvDao = new NhanVien_DAO();
//                if(txtTen.getText().trim().equalsIgnoreCase("")){
//                    JOptionPane.showMessageDialog(null,"Tên nhân viên không được rỗng");
//                }else {
//                    if (txtSDT.getText().trim().matches("\\d{10}")) {
//                        if (txtCM.getText().trim().equalsIgnoreCase("")) {
//                            JOptionPane.showMessageDialog(null, "Chưa nhập số CMND");
//                        } else {
//                            if (nvDao.TimKiemCM(Integer.parseInt(txtCM.getText().trim())) != null) {
//                                JOptionPane.showMessageDialog(null, "Nhân viên đã tồn tại (Trùng số CMND)");
//                                txtCM.requestFocus();
//                            } else {
//                                System.out.println("NamSinhNV" + namSinh.getDate());
//                                String dateTime = (String) formatter.format(namSinh.getDate());
//                                String ngayVao1 = (String) formatter.format(NgayVao.getDate());
//                                NhanVien nv = new NhanVien(txtMa.getText().trim(), txtTen.getText().trim(),
//                                        cbcGT.getSelectedItem().toString(),
//                                        Date.valueOf(dateTime),
//                                        txtChungMinh.getText().trim(),
//                                        txtSDT.getText().trim(),
//                                        Integer.valueOf(txtCM.getText().trim()),
//                                        txtPhuCap.getText().trim(),
//                                        Date.valueOf(ngayVao1));
//                                ChucVu_DAO cvDao = new ChucVu_DAO();
//                                ChucVu cv;
//                                if (cvDao.TimKiemTen(cbcPhongBan.getSelectedItem().toString()) != null) {
//                                    cv = cvDao.TimKiemTen(cbcPhongBan.getSelectedItem().toString());
//                                    nv.setChucVu(cv);
//                                    if (nvDao.addNhanVien(nv)) {
//                                        try {
//                                            table.setModel(new NV_TableModel(nvDao.getLS()));
//                                        } catch (Exception ex) {
//                                            ex.printStackTrace();
//                                        }
//                                    } else
//                                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
//                                } else {
//                                    JOptionPane.showMessageDialog(null, "Lỗi!");
//                                }
//                                clearTextField();
//                                table.setModel(new NV_TableModel(nvDao.getLS()));
//                                System.out.println(table.getRowCount());
//
//                                // tableModel.fireTableDataChanged();
//                            }
//                        }
//                    }else{
//                        JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ cái, số đt gồm 10 chữ số!");
//                    }
//                }
//
//            }
//        });

        btnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LuongNhanVien_Dao luongNhanVien_dao = new LuongNhanVien_Dao();
                LuongNhanVien_Table model = new LuongNhanVien_Table(luongNhanVien_dao.TimKiemThangNam(Integer.parseInt(cbcThang.getSelectedItem().toString()),Integer.parseInt(cbcNam.getSelectedItem().toString())));
                JTable table = new JTable();
                table.setModel(model);
                exportExcel(table);
            }
        });

        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);

    }

    public void clearTextField() {
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


