package ui;

import dao.CongDoan_Dao;
import dao.CongNhan_Dao;
import dao.PhanCong_Dao;
import dao.SanPham_Dao;
import entity.CongDoan;
import entity.CongNhan;
import entity.PhanCong;
import entity.SanPham;
import table_model.CongDoan_Table;
import table_model.PhanCong_Table;
import table_model.SanPham_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Form_PhanCong extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblMa, lblTenSP, lblTenCD, lblTen, lblTenCongDoan, lblSLCanLam;
    JTextField txtMa, txtTenSP, txtSLCanLam;
    JComboBox<String> cbcTenCongNhan;
    JComboBox cbcTenCD;
    JComboBox cbcCongDoanYC;

    public Form_PhanCong() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("PHÂN CÔNG");
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
        b.add(Box.createVerticalStrut(50));
        b.setPreferredSize(new Dimension(680, 140));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblMa = new JLabel("Mã Phân Công:"));
        b1.add(txtMa = new JTextField(20));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Công Nhân: "));
        b1.add(cbcTenCongNhan = new JComboBox<>());
        CongNhan_Dao congNhan_dao = new CongNhan_Dao();
        for (CongNhan cn : congNhan_dao.getLS()) {
            cbcTenCongNhan.addItem(cn.getHoTen());
        }
        cbcTenCongNhan.setPreferredSize(new Dimension(180, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblTenSP = new JLabel("Tên Sản Phẩm:"));
        b2.add(txtTenSP = new JTextField(20));
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblTenCD = new JLabel("Tên Công Đoạn:"));
        b2.add(cbcTenCD = new JComboBox<>());
        cbcTenCD.setPreferredSize(new Dimension(180, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblSLCanLam = new JLabel("SL Cần Làm: "));
        b3.add(txtSLCanLam = new JTextField(20));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblTenCongDoan = new JLabel("Công Đoạn YC: "));
        b3.add(cbcCongDoanYC = new JComboBox<>());
        cbcCongDoanYC.setPreferredSize(new Dimension(180, 20));
        b.add(Box.createVerticalStrut(60));

        txtTenSP.setEditable(false);
        cbcCongDoanYC.setEnabled(false);
        CongDoan_Dao congDoan_dao = new CongDoan_Dao();

        cbcTenCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.printf("PK");
                if (cbcTenCD.getSelectedItem() != null) {
                    CongDoan cd = congDoan_dao.TimKiemTen(cbcTenCD.getSelectedItem().toString());
                    if (cd.getCongDoanYeuCau() != null) {
                        System.out.println(cd.getCongDoanYeuCau().getMaCD());
                        cbcCongDoanYC.removeAllItems();
                        cbcCongDoanYC.addItem(congDoan_dao.TimKiemMa(cd.getCongDoanYeuCau().getMaCD()).getTenCongDoan());
                    } else cbcCongDoanYC.removeAllItems();
                }
            }
        });

        lblMa.setPreferredSize(lblTen.getPreferredSize());
        lblTenSP.setPreferredSize(lblTen.getPreferredSize());
        lblTenCD.setPreferredSize(lblTen.getPreferredSize());
        lblSLCanLam.setPreferredSize(lblTen.getPreferredSize());


        JButton btnThem, btnXoa, btnSua, btnThoat, btnXoaRong;
        pnCenC.add(btnThem = new JButton("Thêm Phân Công"));
        btnThem.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnThem.setBackground(Color.decode("#4caf50"));
        btnThem.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnXoa = new JButton("Xóa Phân Công"));
        btnXoa.setIcon(new ImageIcon(getClass().getResource("/icons/delete_icon.png")));
        btnXoa.setBackground(Color.decode("#f44336"));
        btnXoa.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnSua = new JButton("Sửa Phân Công"));
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

        JPanel pnCenW = new JPanel();
        java.util.List<SanPham> sanPhamList = new ArrayList<>();
        SanPham_Dao sanPham_dao = new SanPham_Dao();
        SanPham_Table sanpham_model = new SanPham_Table(sanPham_dao.getLS());
        JTable tableSanPham = new JTable();
        tableSanPham.setModel(sanpham_model);
        tableSanPham.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = tableSanPham.getSelectedRow();
                if (r != -1) {
                    txtTenSP.setText(tableSanPham.getValueAt(r, 1).toString());
                    List<CongDoan> list = congDoan_dao.TimKiemMaSP(tableSanPham.getValueAt(r, 0).toString());
                    if (list != null) {
                        cbcTenCD.removeAllItems();
                        ;
                        for (CongDoan cd : list) {
                            cbcTenCD.addItem(cd.getTenCongDoan());
                        }
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
        JScrollPane scSanPham = new JScrollPane(tableSanPham, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scSanPham.setPreferredSize(new Dimension(600, 220));
        pnCenW.add(scSanPham);
        pnCenW.setBorder(new TitledBorder("Danh Sách Sản Phẩm"));

        pnCenN.setLayout(new BorderLayout());

        pnCenN.add(b, BorderLayout.WEST);
        pnCenN.add(pnCenW, BorderLayout.EAST);
        pnCenN.setBorder(new TitledBorder("Thông tin phân công"));


        pnCenter.add(pnCenN, BorderLayout.NORTH);
        pnCenter.add(pnCenC, BorderLayout.CENTER);

        //pnSouth
        pnSouth = new JPanel();
        List<PhanCong> ls = new ArrayList<>();
        PhanCong_Dao phanCong_dao = new PhanCong_Dao();
        PhanCong_Table model = new PhanCong_Table(phanCong_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if (r != -1) {
                    txtMa.setText(table.getValueAt(r,0).toString());
                    cbcTenCongNhan.setSelectedItem(table.getValueAt(r,1).toString());
                    txtTenSP.setText(table.getValueAt(r,2).toString());
                    cbcTenCD.addItem(table.getValueAt(r,3).toString());
                    txtSLCanLam.setText(table.getValueAt(r,4).toString());
                    CongDoan cd = congDoan_dao.TimKiemTen(table.getValueAt(r,3).toString());
                    if (cd.getCongDoanYeuCau() != null) {
                        System.out.println(cd.getCongDoanYeuCau().getMaCD());
                        cbcCongDoanYC.removeAllItems();
                        cbcCongDoanYC.addItem(congDoan_dao.TimKiemMa(cd.getCongDoanYeuCau().getMaCD()).getTenCongDoan());
                    } else cbcCongDoanYC.removeAllItems();
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
        pnSouth.setBorder(new TitledBorder("Danh Sách Phân Công"));

        //Sự Kiện Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableSanPham.getSelectedRow() != -1) {
                    PhanCong phanCong = new PhanCong(txtMa.getText().trim(),
                            Integer.parseInt(txtSLCanLam.getText()));
                    phanCong.setCongNhan(congNhan_dao.TimKiemTen(cbcTenCongNhan.getSelectedItem().toString()));
                    phanCong.setCongDoan(congDoan_dao.TimKiemTen(cbcTenCD.getSelectedItem().toString()));
                    if (phanCong_dao.addPhanCong(phanCong)) {
                        try {
                            table.setModel(new PhanCong_Table(phanCong_dao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                    clearTextField();
                    table.setModel(new PhanCong_Table(phanCong_dao.getLS()));
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
                        String maX = txtMa.getText().trim();
                        if (phanCong_dao.deletePhanCong(maX)) {
                            try {
                                table.setModel(new PhanCong_Table(phanCong_dao.getLS()));
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
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = table.getSelectedRow();
                txtMa.setEnabled(false);
                if (r != -1) {
                    String maS = txtMa.getText().trim();
                    PhanCong phanCong = new PhanCong(maS,
                            Integer.parseInt(txtSLCanLam.getText()));
                    phanCong.setCongNhan(congNhan_dao.TimKiemTen(cbcTenCongNhan.getSelectedItem().toString()));
                    phanCong.setCongDoan(congDoan_dao.TimKiemTen(cbcTenCD.getSelectedItem().toString()));
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?", "option", JOptionPane.YES_NO_OPTION);
                    if (lc == JOptionPane.YES_OPTION) {
                        if (phanCong_dao.updatePhanCong(phanCong)) {
                            try {
                                table.setModel(new PhanCong_Table(phanCong_dao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    clearTextField();
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng cần sửa!");
                }
            }
        });
        //Sự Kiện Xóa Rỗng
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
        txtTenSP.setText("");
        txtSLCanLam.setText("");
        cbcTenCD.removeAllItems();
        cbcCongDoanYC.removeAllItems();
        txtSLCanLam.requestFocus();
    }
}
