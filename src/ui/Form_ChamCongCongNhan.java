package ui;

import com.toedter.calendar.JDateChooser;
import dao.*;
import entity.*;
import table_model.ChamCongCN_Table;
import table_model.ChamCongNV_Table;
import table_model.PhanCong_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

public class Form_ChamCongCongNhan extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblSanPham, lblCongNhan, lblNgayCham, lblCaLam, lblCongDoan, lblGioLam, lblLuongCa, lblSLDaLam, lblConLai;
    JTextField txtSanPham, txtCongNhan, txtGioLam, txtLuongCa, txtConLai, txtCongDoan;
    JComboBox cbcCaLam;
    JCheckBox cbTrangThai, cbNghiPhep;
    JSpinner slDaLam;
    JDateChooser ngayCham;

    public Form_ChamCongCongNhan(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("CHẤM CÔNG CÔNG NHÂN");
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
        b.setPreferredSize(new Dimension(680,140));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblSanPham = new JLabel("Sản Phẩm:"));
        b1.add(txtSanPham = new JTextField(20));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblCongNhan = new JLabel("Tên Công Nhân: "));
        b1.add(txtCongNhan = new JTextField());
        b.add(Box.createVerticalStrut(10));

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
        for (CaLamViec caLamViec: caLamViec_dao.getLS()) {
            cbcCaLam.addItem(caLamViec.getCaLam());
        }
        cbcCaLam.setPreferredSize(new Dimension(130, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblCongDoan = new JLabel("Công Đoạn: "));
        b3.add(txtCongDoan = new JTextField(20));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblGioLam = new JLabel("Giờ Làm: "));
        b3.add(txtGioLam = new JTextField());
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblLuongCa = new JLabel("Lương Ca: "));
        b4.add(txtLuongCa = new JTextField(20));
        b4.add(Box.createHorizontalStrut(20));
        b4.add(lblSLDaLam = new JLabel("SL Đã Làm: "));
        b4.add(slDaLam = new JSpinner());
        slDaLam.setPreferredSize(new Dimension(130, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b5 = Box.createHorizontalBox());
        b5.add(Box.createHorizontalStrut(20));
        b5.add(cbTrangThai = new JCheckBox("Có Mặt"));
        b5.add(Box.createHorizontalStrut(20));
        b5.add(cbNghiPhep = new JCheckBox("Có Phép"));
        b.add(Box.createVerticalStrut(10));

        b.add(b6 = Box.createHorizontalBox());
        b6.add(Box.createHorizontalStrut(20));
        b6.add(lblConLai = new JLabel("SL Còn Lại: "));
        b6.add(txtConLai = new JTextField());
        b.add(Box.createVerticalStrut(10));

        SanPham_Dao sanPham_dao = new SanPham_Dao();
        CongDoan_Dao congDoan_dao = new CongDoan_Dao();
        CongNhan_Dao congNhan_dao = new CongNhan_Dao();

        txtSanPham.setEditable(false);
        txtCongDoan.setEditable(false);
        txtCongNhan.setEditable(false);
        txtGioLam.setEditable(false);
        txtLuongCa.setEditable(false);
        txtConLai.setEditable(false);

        slDaLam.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SanPham sp = sanPham_dao.TimKiemTen(txtSanPham.getText());
                txtConLai.setText(String.valueOf(congDoan_dao.TimKiemTenCDSP(txtCongDoan.getText(), sp.getMaSP()).getSoLuong() - Integer.parseInt(slDaLam.getValue().toString())));
            }
        });

        CaLamViec caLamViec = caLamViec_dao.TimKiemCaLam(cbcCaLam.getSelectedItem().toString());
        txtGioLam.setText(caLamViec.getGioLam());
        txtLuongCa.setText(String.valueOf(caLamViec.getLuongCa()));


        lblSanPham.setPreferredSize(lblCongNhan.getPreferredSize());
        lblCongDoan.setPreferredSize(lblCongNhan.getPreferredSize());
        lblCaLam.setPreferredSize(lblCongNhan.getPreferredSize());
        lblConLai.setPreferredSize(lblCongNhan.getPreferredSize());
        lblNgayCham.setPreferredSize(lblCongNhan.getPreferredSize());
        lblSLDaLam.setPreferredSize(lblCongNhan.getPreferredSize());
        lblGioLam.setPreferredSize(lblCongNhan.getPreferredSize());
        lblLuongCa.setPreferredSize(lblCongNhan.getPreferredSize());


        JButton btnThem,btnXoa,btnSua,btnThoat,btnXoaRong;
        pnCenC.add(btnThem = new JButton("Chấm Công"));
        btnThem.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnThem.setBackground(Color.decode("#4caf50"));
        btnThem.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnXoa = new JButton("Xóa Chấm Công"));
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
        java.util.List<PhanCong> phanCongList = new ArrayList<>();
        PhanCong_Dao phanCong_dao = new PhanCong_Dao();
        PhanCong_Table phancong_model = new PhanCong_Table(phanCong_dao.getLS());
        JTable tablePhanCong = new JTable();
        tablePhanCong.setModel(phancong_model);
        tablePhanCong.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = tablePhanCong.getSelectedRow();
                if(r != -1) {
                    SanPham sanPham = sanPham_dao.TimKiemTen(tablePhanCong.getValueAt(r,2).toString());
                    CongNhan congNhan = congNhan_dao.TimKiemTen(tablePhanCong.getValueAt(r,1).toString());
                    CongDoan congDoan = congDoan_dao.TimKiemTen(tablePhanCong.getValueAt(r,3).toString());

                    txtSanPham.setText(tablePhanCong.getValueAt(r,2).toString());
                    txtCongNhan.setText(tablePhanCong.getValueAt(r,1).toString());
                    txtCongDoan.setText(tablePhanCong.getValueAt(r,3).toString());
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
        JScrollPane scPhanCong = new JScrollPane(tablePhanCong,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scPhanCong.setPreferredSize(new Dimension(600,220));
        pnCenW.add(scPhanCong);
        pnCenW.setBorder(new TitledBorder("Danh Sách Phân Công"));

        pnCenN.setLayout(new BorderLayout());

        pnCenN.add(b, BorderLayout.WEST);
        pnCenN.add(pnCenW, BorderLayout.EAST);
        pnCenN.setBorder(new TitledBorder("Thông tin chấm công"));


        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);

        //pnSouth
        pnSouth = new JPanel();
        List<ChamCongCongNhan> ls = new ArrayList<>();
        ChamCongCongNhan_Dao chamCongCongNhan_dao = new ChamCongCongNhan_Dao();
        ChamCongCN_Table model = new ChamCongCN_Table(chamCongCongNhan_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {

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
                if (tablePhanCong.getSelectedRow() != -1) {
                    SanPham sanPham = sanPham_dao.TimKiemTen(tablePhanCong.getValueAt(tablePhanCong.getSelectedRow(),2).toString());
                    CongNhan congNhan = congNhan_dao.TimKiemTen(tablePhanCong.getValueAt(tablePhanCong.getSelectedRow(),1).toString());
                    CongDoan congDoan = congDoan_dao.TimKiemTen(tablePhanCong.getValueAt(tablePhanCong.getSelectedRow(),3).toString());
                    int trangThai, nghiPhep;
                    if (cbTrangThai.isSelected()) {
                        trangThai = 1;
                    } else trangThai = 0;
                    if (cbNghiPhep.isSelected()) nghiPhep = 1;
                    else nghiPhep = 0;
                    String dateTime = (String) formatter.format(ngayCham.getDate());
                    ChamCongCongNhan chamCongCongNhan = new ChamCongCongNhan("CC001",
                            Date.valueOf(dateTime),
                            Integer.parseInt(slDaLam.getValue().toString()),
                            trangThai,
                            nghiPhep);
                    chamCongCongNhan.setCongNhan(congNhan);
                    chamCongCongNhan.setSanPham(sanPham);
                    chamCongCongNhan.setCongDoan(congDoan);
                    chamCongCongNhan.setCaLamViec(caLamViec_dao.TimKiemCaLam(cbcCaLam.getSelectedItem().toString()));
                    if (chamCongCongNhan_dao.addChamCongCN(chamCongCongNhan)) {
                        try {
                            table.setModel(new ChamCongCN_Table(chamCongCongNhan_dao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                    clearTextField();
                    table.setModel(new ChamCongCN_Table(chamCongCongNhan_dao.getLS()));
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
                        if (chamCongCongNhan_dao.deleteChamCong(maX)) {
                            try {
                                table.setModel(new ChamCongCN_Table(chamCongCongNhan_dao.getLS()));
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


        this.setLayout(new BorderLayout());
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public void clearTextField(){
    }
}

