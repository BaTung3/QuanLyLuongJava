package ui;

import com.toedter.calendar.JDateChooser;
import dao.CaLamViec_Dao;
import dao.ChamCongNhanVien_Dao;
import dao.NhanVienHanhChinh_Dao;
import dao.SanPham_Dao;
import entity.CaLamViec;
import entity.ChamCongNhanVien;
import entity.NhanVienHanhChinh;
import entity.PhanCong;
import table_model.ChamCongNV_Table;
import table_model.NhanVien_Table;
import table_model.PhanCong_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Form_ChamCongNhanVien extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblPhongBan, lblNhanVien, lblNgayCham, lblCaLam, lblGioLam, lblLuongCa;
    JTextField txtNhanVien, txtPhongBan, txtGioLam, txtLuongCa;
    JComboBox cbcCaLam;
    JDateChooser ngayCham;
    private JRadioButton cbTrangThai, cbNghiPhep;
    private ButtonGroup btnGR;
    
    boolean coCaLam = true;
    
    CaLamViec caLamViecMau = new CaLamViec("","","","",0);

    public Form_ChamCongNhanVien(){
        doShow();
    }
    public void doShow(){
        
     
        
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("BÁO CÁO NGHỈ PHÉP NHÂN VIÊN");
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
        b.add(Box.createVerticalStrut(60));
        b.setPreferredSize(new Dimension(680,140));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNgayCham = new JLabel("Ngày Chấm:"));
        ngayCham = new JDateChooser();
        ngayCham.setSize(new Dimension(30,20));
        ngayCham.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        ngayCham.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            ngayCham.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b2.add(ngayCham);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblCaLam = new JLabel("Ca Làm:"));
        b2.add(cbcCaLam = new JComboBox<>());
        CaLamViec_Dao caLamViec_dao = new CaLamViec_Dao();
        if(caLamViec_dao.getLS().isEmpty()){
            cbcCaLam.addItem(caLamViecMau);
            coCaLam = false;
        }else{
        for (CaLamViec caLamViec: caLamViec_dao.getLS()) {
            cbcCaLam.addItem(caLamViec.getCaLam());
            coCaLam = true;
        }
        }
        cbcCaLam.setPreferredSize(new Dimension(250, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblNhanVien = new JLabel("Nhân Viên: "));
        b4.add(txtNhanVien = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblPhongBan = new JLabel("Phòng Ban: "));
        b4.add(txtPhongBan = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblGioLam = new JLabel("Giờ Làm: "));
        b3.add(txtGioLam = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblLuongCa = new JLabel("Lương Ca: "));
        b3.add(txtLuongCa = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b5 = Box.createHorizontalBox());
        b5.add(Box.createHorizontalStrut(20));
//        b5.add(cbTrangThai = new JRadioButton("Không phép"));
//        b5.add(Box.createHorizontalStrut(20));
//        b5.add(cbNghiPhep = new JRadioButton("Có Phép"));
//        b.add(Box.createVerticalStrut(40));

        btnGR = new ButtonGroup();
//        btnGR.add(cbNghiPhep);
//        btnGR.add(cbTrangThai);

        txtNhanVien.setEditable(false);
        txtPhongBan.setEditable(false);
        txtGioLam.setEditable(false);
        txtLuongCa.setEditable(false);

        lblCaLam.setPreferredSize(lblPhongBan.getPreferredSize());
        lblNgayCham.setPreferredSize(lblPhongBan.getPreferredSize());
        lblGioLam.setPreferredSize(lblPhongBan.getPreferredSize());
        lblLuongCa.setPreferredSize(lblPhongBan.getPreferredSize());
        lblNhanVien.setPreferredSize(lblPhongBan.getPreferredSize());

        System.out.print("coCaLam " + coCaLam);
        
        if(coCaLam){
        CaLamViec caLamViec = caLamViec_dao.TimKiemCaLam(cbcCaLam.getSelectedItem().toString());
        txtGioLam.setText(caLamViec.getGioLam());
        txtLuongCa.setText(String.valueOf(caLamViec.getLuongCa()));
        }
        else{
             txtGioLam.setText(caLamViecMau.getGioLam());
        txtLuongCa.setText(String.valueOf(caLamViecMau.getLuongCa()));
        }

        cbcCaLam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(coCaLam){
                CaLamViec caLamViec = caLamViec_dao.TimKiemCaLam(cbcCaLam.getSelectedItem().toString());
                txtGioLam.setText(caLamViec.getGioLam());
                txtLuongCa.setText(String.valueOf(caLamViec.getLuongCa()));
                }
                else{
                     txtGioLam.setText(caLamViecMau.getGioLam());
                txtLuongCa.setText(String.valueOf(caLamViecMau.getLuongCa()));
                }
            }
        });


        JButton btnThem,btnXoa,btnSua,btnThoat,btnXoaRong;
        pnCenC.add(btnThem = new JButton("Báo nghỉ phép"));
        btnThem.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnThem.setBackground(Color.decode("#4caf50"));
        btnThem.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnXoa = new JButton("Xóa Báo cáo"));
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/icons/delete_icon.png")));
        btnXoa.setBackground(Color.decode("#f44336"));
        btnXoa.setForeground(Color.decode("#FFFFFF"));
//        pnCenC.add(btnSua = new JButton("Sửa Phân Công"));
//        btnSua.setIcon(new ImageIcon(getClass().getResource("/icons/update_icon.png")));
//        btnSua.setBackground(Color.decode("#00bcd4"));
//        btnSua.setForeground(Color.decode("#FFFFFF"));
//        pnCenC.add(btnXoaRong = new JButton("Xóa Rỗng"));
//        btnXoaRong.setIcon(new ImageIcon(getClass().getResource("/icons/clear_icon.png")));
//        btnXoaRong.setBackground(Color.decode("#ff6900"));
//        btnXoaRong.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnThoat = new JButton("Thoát"));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/icons/cancle_icon.png")));
        btnThoat.setBackground(Color.decode("#ff0004"));
        btnThoat.setForeground(Color.decode("#FFFFFF"));

        JPanel pnCenW = new JPanel();
        java.util.List<NhanVienHanhChinh> nvList = new ArrayList<>();
        NhanVienHanhChinh_Dao nhanVienHanhChinh_dao = new NhanVienHanhChinh_Dao();
        NhanVien_Table nhanVien_table = new NhanVien_Table(nhanVienHanhChinh_dao.getLS());
        JTable tableNV = new JTable();
        tableNV.setModel(nhanVien_table);
        tableNV.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = tableNV.getSelectedRow();
                if(r != -1) {
                    txtNhanVien.setText(tableNV.getValueAt(r,1).toString());
                    txtPhongBan.setText(tableNV.getValueAt(r,9).toString());
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
        JScrollPane scPhanCong = new JScrollPane(tableNV,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scPhanCong.setPreferredSize(new Dimension(600,220));
        pnCenW.add(scPhanCong);
        pnCenW.setBorder(new TitledBorder("Danh Sách Nhân Viên"));

        pnCenN.setLayout(new BorderLayout());

        pnCenN.add(b, BorderLayout.WEST);
        pnCenN.add(pnCenW, BorderLayout.EAST);
        pnCenN.setBorder(new TitledBorder("Thông tin chấm công"));


        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);

        //pnSouth
        pnSouth = new JPanel();
        List<ChamCongNhanVien> ls = new ArrayList<>();
        ChamCongNhanVien_Dao chamCongNhanVien_dao = new ChamCongNhanVien_Dao();
        ChamCongNV_Table model = new ChamCongNV_Table(chamCongNhanVien_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    txtNhanVien.setText(table.getValueAt(r,1).toString());
                    NhanVienHanhChinh nv = nhanVienHanhChinh_dao.TimKiemTen(txtNhanVien.getText());
                    txtPhongBan.setText(nv.getPhongban().getTenPB());
                    ngayCham.setDate(Date.valueOf(table.getValueAt(r,2).toString()));
                    cbcCaLam.setSelectedItem(table.getValueAt(r,3).toString());
                    
                    
                     if(coCaLam){
               txtGioLam.setText(caLamViec_dao.TimKiemCaLam(cbcCaLam.getSelectedItem().toString()).getGioLam());
                    txtLuongCa.setText(String.valueOf(caLamViec_dao.TimKiemCaLam(cbcCaLam.getSelectedItem().toString()).getLuongCa()));
                }
                else{
                     txtGioLam.setText(caLamViecMau.getGioLam());
                txtLuongCa.setText(String.valueOf(caLamViecMau.getLuongCa()));
                }
                    
                   
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
        pnSouth.setBorder(new TitledBorder("Danh Sách Chấm Công"));

        //Sự Kiện Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableNV.getSelectedRow() != -1) {
                    int trangThai, nghiPhep;
                    
                    trangThai = 0;
                    nghiPhep = 1;
                    
//                    if (cbTrangThai.isSelected()) {
//                        trangThai = 1;
//                    } else trangThai = 0;
//                    if (cbNghiPhep.isSelected()) nghiPhep = 1;
//                    else nghiPhep = 0;
                    String dateTime = (String) formatter.format(ngayCham.getDate());
                    ChamCongNhanVien chamCongNhanVien = new ChamCongNhanVien("CC001",
                            Date.valueOf(dateTime),
                            trangThai,
                            nghiPhep);
                    chamCongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemTen(txtNhanVien.getText()));
                    chamCongNhanVien.setCaLamViec(caLamViec_dao.TimKiemCaLam(cbcCaLam.getSelectedItem().toString()));
                    if (chamCongNhanVien_dao.addChamCongNV(chamCongNhanVien)) {
                        try {
                            table.setModel(new ChamCongNV_Table(chamCongNhanVien_dao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                    clearTextField();
                    table.setModel(new ChamCongNV_Table(chamCongNhanVien_dao.getLS()));
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên !");
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
                        if (chamCongNhanVien_dao.deleteChamCong(maX)) {
                            try {
                                table.setModel(new ChamCongNV_Table(chamCongNhanVien_dao.getLS()));
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
        //Sự Kiện Sửa
//        btnSua.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int r = table.getSelectedRow();
//                txtMa.setEnabled(false);
//                if (r != -1) {
//                    String maS = txtMa.getText().trim();
//                    PhanCong phanCong = new PhanCong(maS,
//                            Integer.parseInt(txtSLCanLam.getText()));
//                    phanCong.setCongNhan(congNhan_dao.TimKiemTen(cbcTenCongNhan.getSelectedItem().toString()));
//                    phanCong.setCongDoan(congDoan_dao.TimKiemTen(cbcTenCD.getSelectedItem().toString()));
//                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?", "option", JOptionPane.YES_NO_OPTION);
//                    if (lc == JOptionPane.YES_OPTION) {
//                        if (phanCong_dao.updatePhanCong(phanCong)) {
//                            try {
//                                table.setModel(new PhanCong_Table(phanCong_dao.getLS()));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                    }
//                    clearTextField();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần sửa!");
//                }
//            }
//        });
        //Sự Kiện Xóa Rỗng
//        btnXoaRong.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                clearTextField();
//            }
//        });

        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public void clearTextField(){
        txtNhanVien.setText("");
        txtPhongBan.setText("");
    }
}

