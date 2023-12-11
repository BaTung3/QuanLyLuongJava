package ui;

import com.toedter.calendar.JDateChooser;
import dao.HeSoLuong_Dao;
import dao.NhanVienHanhChinh_Dao;
import dao.PhongBan_Dao;
import entity.HeSoLuong;
import entity.NhanVienHanhChinh;
import entity.Phongban;
import table_model.NhanVien_Table;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Form_Admin extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblMa, lblGioiTinh, lblNamSinh, lblChungMinh, lblSDT, lblLuongCB, lblPhongBan, lblTen, lblHeSoLuong, lblPhuCap, lblDiaChi;
    JTextField txtMa, txtChungMinh, txtSDT, txtLuongCB, txtPhuCap, txtTen, txtDiaChi;
    JComboBox<String> cbcGT, cbcPhongBan, cbcHeSoLuong;
    JDateChooser namSinh, NgayVao;

    public Form_Admin() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("NHÂN VIÊN HÀNH CHÍNH");
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
        b.setPreferredSize(new Dimension(840, 230));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Nhân Viên: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Họ Tên:    "));
        b1.add(txtTen = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblGioiTinh = new JLabel("Giới Tính: "));
        cbcGT = new JComboBox<>();
        cbcGT.addItem("Nam");
        cbcGT.addItem("Nữ");
        cbcGT.setPreferredSize(new Dimension(317, 20));
        b2.add(cbcGT);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblNamSinh = new JLabel("Ngày Sinh:    "));
        namSinh = new JDateChooser();
        namSinh.setSize(new Dimension(30, 20));
        namSinh.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        namSinh.setDateFormatString("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("Date: " + date);
            namSinh.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        b2.add(namSinh);
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(lblChungMinh = new JLabel("CMND: "));
        b3.add(txtChungMinh = new JTextField(30));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblSDT = new JLabel("Số Điện Thoại:    "));
        b3.add(txtSDT = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(lblLuongCB = new JLabel("Lương Cơ Bản: "));
        b4.add(txtLuongCB = new JTextField(30));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblPhongBan = new JLabel("Phòng Ban:    "));
        cbcPhongBan = new JComboBox<>();
        PhongBan_Dao phongBan_dao = new PhongBan_Dao();
        for (Phongban phongban : phongBan_dao.getLS()) {
            cbcPhongBan.addItem(phongban.getTenPB());
        }
        cbcPhongBan.setPreferredSize(new Dimension(317, 20));
        b4.add(cbcPhongBan);
        b.add(Box.createVerticalStrut(10));

        b.add(b5 = Box.createHorizontalBox());
        b5.add(lblHeSoLuong = new JLabel("Hệ Số Lương: "));
        cbcHeSoLuong = new JComboBox<>();
        HeSoLuong_Dao heSoLuong_dao = new HeSoLuong_Dao();
        for (HeSoLuong heSoLuong : heSoLuong_dao.getLS()) {
           // cbcHeSoLuong.addItem(String.valueOf(heSoLuong.getHeSoLuong()));
           
           cbcHeSoLuong.addItem(heSoLuong.getChucVu());
        }
        cbcHeSoLuong.setPreferredSize(new Dimension(317, 20));
        b5.add(cbcHeSoLuong);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(lblPhuCap = new JLabel("Phụ Cấp:    "));
        b5.add(txtPhuCap = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        b.add(b6 = Box.createHorizontalBox());
        b6.add(lblDiaChi = new JLabel("Địa Chỉ: "));
        b6.add(txtDiaChi = new JTextField(30));
        b.add(Box.createVerticalStrut(10));

        lblMa.setPreferredSize(lblSDT.getPreferredSize());
        lblTen.setPreferredSize(lblSDT.getPreferredSize());
        lblChungMinh.setPreferredSize(lblSDT.getPreferredSize());
        lblLuongCB.setPreferredSize(lblSDT.getPreferredSize());
        lblGioiTinh.setPreferredSize(lblSDT.getPreferredSize());
        lblNamSinh.setPreferredSize(lblSDT.getPreferredSize());
        lblDiaChi.setPreferredSize(lblSDT.getPreferredSize());
        lblPhongBan.setPreferredSize(lblSDT.getPreferredSize());
        lblHeSoLuong.setPreferredSize(lblSDT.getPreferredSize());
        lblPhuCap.setPreferredSize(lblSDT.getPreferredSize());


        JButton btnThem, btnXoa, btnSua, btnThoat, btnXoaRong;
        pnCenC.add(btnThem = new JButton("Thêm Nhân Viên"));
        btnThem.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnThem.setBackground(Color.decode("#4caf50"));
        btnThem.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnXoa = new JButton("Xóa Nhân Viên"));
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/icons/delete_icon.png")));
        btnXoa.setBackground(Color.decode("#f44336"));
        btnXoa.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnSua = new JButton("Sửa Thông Tin"));
        btnSua.setIcon(new ImageIcon(getClass().getResource("/icons/update_icon.png")));
        btnSua.setBackground(Color.decode("#00bcd4"));
        btnSua.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnXoaRong = new JButton("Xóa Rỗng"));
        btnXoaRong.setIcon(new ImageIcon(getClass().getResource("/icons/clear_icon.png")));
        btnXoaRong.setBackground(Color.decode("#ff6900"));
        btnXoaRong.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnThoat = new JButton("Thoát"));
        btnThoat.setIcon(new ImageIcon(getClass().getResource("/icons/cancle_icon.png")));
        btnThoat.setBackground(Color.decode("#ff0004"));
        btnThoat.setForeground(Color.decode("#FFFFFF"));

        pnCenN.add(b);
        pnCenN.setBorder(new TitledBorder("Thông tin nhân viên hành chính"));
        pnCenter.add(pnCenN, BorderLayout.NORTH);
        pnCenter.add(pnCenC, BorderLayout.CENTER);

        //pnSouth
        pnSouth = new JPanel();
        List<NhanVienHanhChinh> ls = new ArrayList<>();
        NhanVienHanhChinh_Dao nhanVienHanhChinh_dao = new NhanVienHanhChinh_Dao();
        NhanVien_Table model = new NhanVien_Table(nhanVienHanhChinh_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtMa.setEditable(false);
                    txtMa.setText(table.getValueAt(r, 0).toString());
                    txtTen.setText(table.getValueAt(r, 1).toString());
                    txtChungMinh.setText(table.getValueAt(r, 2).toString());
                    namSinh.setDate(Date.valueOf(table.getValueAt(r,3).toString()));
                    if (table.getValueAt(r, 4).toString().equalsIgnoreCase("Nam"))
                        cbcGT.setSelectedItem("Nam");
                    else
                        cbcGT.setSelectedItem("Nữ");
                    txtDiaChi.setText(table.getValueAt(r,5).toString());
                    txtSDT.setText(table.getValueAt(r, 6).toString());
                    txtLuongCB.setText(table.getValueAt(r, 7).toString());
                    txtPhuCap.setText(table.getValueAt(r, 8).toString());
                    cbcPhongBan.setSelectedItem(table.getValueAt(r, 9).toString());
                    cbcHeSoLuong.setSelectedItem(table.getValueAt(r,10).toString());
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

        txtMa.setEditable(false);
        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Nhân Viên"));

//        Su kien luu
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTen.getText().trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Tên nhân viên không được rỗng");
                } else {
                    if (txtSDT.getText().trim().matches("\\d{10}")) {
                        if (txtChungMinh.getText().trim().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "Chưa nhập số CMND");
                        } else {
                            
                             if (!txtChungMinh.getText().trim().matches("^[0-9]{1,10}$")) {
                            JOptionPane.showMessageDialog(null, "Số CMND chỉ bao gồm 9 số");
                        }
                             else{
                            
                            String dateTime = (String) formatter.format(namSinh.getDate());
                            NhanVienHanhChinh nv = new NhanVienHanhChinh(txtMa.getText().trim(), txtTen.getText().trim(),
                                    Integer.parseInt(txtChungMinh.getText().trim()),
                                    Date.valueOf(dateTime),
                                    cbcGT.getSelectedItem().toString(),
                                    txtLuongCB.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtLuongCB.getText().trim()),
                                    txtSDT.getText().trim(),
                                    txtDiaChi.getText(),
                                    txtPhuCap.getText().trim().isEmpty() ? 0 : Double.parseDouble(txtPhuCap.getText().trim()));
                            HeSoLuong heSoLuong = heSoLuong_dao.TimKiemHeSo(Double.parseDouble(cbcHeSoLuong.getSelectedItem().toString().trim()));
                            Phongban phongban = phongBan_dao.TimKiemTen(cbcPhongBan.getSelectedItem().toString().trim());
                            if (heSoLuong != null && phongban != null) {
                                nv.setHeSoLuong(heSoLuong);
                                nv.setPhongban(phongban);
                                Boolean xong = nhanVienHanhChinh_dao.addNhanVien(nv);
                                Boolean xong2 = false;
                                
                                
                                
                                if (xong) {
                                    try {
                                        List<NhanVienHanhChinh> mau = nhanVienHanhChinh_dao.getLS();
                                        
                                        if(!mau.isEmpty()){
                                        table.setModel(new NhanVien_Table(mau));
                                        
                                        xong2 = true;
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    if(xong2){
                                    nhanVienHanhChinh_dao.addLuongNhanVien(nv);
                                    }
                                    
                                } else
                                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                            } else {
                                JOptionPane.showMessageDialog(null, "Lỗi!");
                            }
                            clearTextField();
                            table.setModel(new NhanVien_Table(nhanVienHanhChinh_dao.getLS()));
                             }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa chữ cái, số đt gồm 10 chữ số!");
                    }
                }

            }
        });

        //Sự kiện xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    int tb = JOptionPane.showConfirmDialog(null,"Bạn chắc chắn muốn xóa dòng này?","Delete",
                            JOptionPane.YES_NO_OPTION);
                    if(tb == JOptionPane.YES_OPTION) {
                        String maX = txtMa.getText().trim();
                        if (nhanVienHanhChinh_dao.deleteNV(maX)) {
                            try {
                                table.setModel(new NhanVien_Table(nhanVienHanhChinh_dao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        clearTextField();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần xóa!");
                }
            }
        });

        //Su Kien Sua
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateTime = (String) formatter.format(namSinh.getDate());
                int r = table.getSelectedRow();
                txtMa.setEnabled(false);
                if(r != -1){
                    String maS = txtMa.getText().trim();
                    NhanVienHanhChinh nv = new NhanVienHanhChinh(maS, txtTen.getText().trim(),
                            Integer.parseInt(txtChungMinh.getText().trim()),
                            Date.valueOf(dateTime),
                            cbcGT.getSelectedItem().toString(),
                            Double.parseDouble(txtLuongCB.getText().trim()),
                            txtSDT.getText().trim(),
                            txtDiaChi.getText(),
                            Double.parseDouble(txtPhuCap.getText().trim()));
                    HeSoLuong heSoLuong = heSoLuong_dao.TimKiemHeSo(Double.parseDouble(cbcHeSoLuong.getSelectedItem().toString().trim()));
                    Phongban phongban = phongBan_dao.TimKiemTen(cbcPhongBan.getSelectedItem().toString().trim());
                    nv.setHeSoLuong(heSoLuong);
                    nv.setPhongban(phongban);
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?","option",JOptionPane.YES_NO_OPTION);
                    if(lc == JOptionPane.YES_OPTION) {
                        if(nhanVienHanhChinh_dao.updateNhanVien(maS,nv)) {
                            try {
                                table.setModel(new NhanVien_Table(nhanVienHanhChinh_dao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    clearTextField();
                }else {
                    JOptionPane.showMessageDialog(null,"Bạn chưa chọn dòng cần sửa!");
                }
            }
        });

        btnXoaRong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextField();
            }
        });


        this.setLayout(new BorderLayout());
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);

    }

    public void clearTextField() {
        txtMa.setText("");
        txtTen.setText("");
        txtLuongCB.setText("");
        txtPhuCap.setText("");
        txtChungMinh.setText("");
        txtSDT.setText("");
        namSinh.setDate(Date.valueOf(LocalDate.now()));
        cbcPhongBan.setSelectedIndex(0);
        cbcGT.setSelectedItem("Nam");
        cbcHeSoLuong.setSelectedIndex(0);
        txtDiaChi.setText("");
        txtTen.requestFocus();
    }
}

