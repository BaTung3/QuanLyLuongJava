package ui;

import dao.CongDoan_Dao;
import dao.SanPham_Dao;
import entity.CongDoan;
import entity.SanPham;
import table_model.CongDoan_Table;
import table_model.SanPham_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Form_CongDoan extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth,pnCenter,pnSouth;
    JLabel lblMa, lblMaSP,lblTen, lblTenSP, lblGiaCD, lblSoLuong;
    JTextField txtMa, txtMaSP,txtTenSP,txtGiaCD, txtTen;
    JComboBox<String> cbcGT;
    JCheckBox cbCongDoanYC;
    JComboBox cbcCongDoanYC;
    JSpinner spinnerSoLuong;
    public Form_CongDoan(){
        doShow();
    }
    public void doShow(){
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("CÔNG ĐOẠN SẢN XUẤT SẢN PHẨM");
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
        b.add(Box.createVerticalStrut(50));
        b.setPreferredSize(new Dimension(680,140));

        b.add(b1 = Box.createHorizontalBox());
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblMa = new JLabel("Mã Công Đoạn:"));
        b1.add(txtMa = new JTextField(20));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Công Đoạn:"));
        b1.add(txtTen = new JTextField(20));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblMaSP = new JLabel("Mã Sản Phẩm:"));
        b2.add(txtMaSP = new JTextField(20));
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblTenSP = new JLabel("Tên Sản Phẩm:"));
        b2.add(txtTenSP = new JTextField(20));
        b.add(Box.createVerticalStrut(10));

        b.add(b3 = Box.createHorizontalBox());
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblGiaCD = new JLabel("Giá Công Đoạn: "));
        b3.add(txtGiaCD = new JTextField(20));
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblSoLuong = new JLabel("Số Lượng:"));
        b3.add(spinnerSoLuong = new JSpinner());
        spinnerSoLuong.setPreferredSize(new Dimension(100, 20));
        b.add(Box.createVerticalStrut(10));

        b.add(b4 = Box.createHorizontalBox());
        b4.add(Box.createHorizontalStrut(80));
        b4.add(cbCongDoanYC = new JCheckBox("Công Đoạn Yêu Cầu"));
        b4.add(cbcCongDoanYC = new JComboBox<>());
        cbcCongDoanYC.addItem("");
        CongDoan_Dao congDoan_dao = new CongDoan_Dao();
        cbcCongDoanYC.setPreferredSize(new Dimension(100, 20));
        b4.add(Box.createHorizontalStrut(80));
        b.add(Box.createVerticalStrut(50));


        lblMa.setPreferredSize(lblGiaCD.getPreferredSize());
        lblMaSP.setPreferredSize(lblGiaCD.getPreferredSize());
        lblTenSP.setPreferredSize(lblGiaCD.getPreferredSize());
        lblTen.setPreferredSize(lblGiaCD.getPreferredSize());
        lblSoLuong.setPreferredSize(lblGiaCD.getPreferredSize());


        JButton btnThem,btnXoa,btnSua,btnThoat,btnXoaRong;
        pnCenC.add(btnThem = new JButton("Thêm Công Đoạn"));
        btnThem.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnThem.setBackground(Color.decode("#4caf50"));
        btnThem.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnXoa = new JButton("Xóa Công Đoạn"));
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

        txtMaSP.setEditable(false);
        txtTenSP.setEditable(false);
        cbcCongDoanYC.setEnabled(false);

        cbCongDoanYC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cbCongDoanYC.isSelected()) {
                    cbcCongDoanYC.setEnabled(true);
                } else
                    cbcCongDoanYC.setEnabled(false);
            }
        });

        JPanel pnCenW = new JPanel();
        List<SanPham> sanPhamList = new ArrayList<>();
        SanPham_Dao sanPham_dao = new SanPham_Dao();
        SanPham_Table sanpham_model = new SanPham_Table(sanPham_dao.getLS());
        JTable tableSanPham = new JTable();
        tableSanPham.setModel(sanpham_model);
        tableSanPham.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = tableSanPham.getSelectedRow();
                if(r != -1) {
                    txtMaSP.setText(tableSanPham.getValueAt(r, 0).toString());
                    txtTenSP.setText(tableSanPham.getValueAt(r, 1).toString());
                    List<CongDoan> list;
                    list = congDoan_dao.TimKiemMaSP(tableSanPham.getValueAt(tableSanPham.getSelectedRow(),0).toString());
                    if (list != null) {
                        for (CongDoan cd : list) {
                            cbcCongDoanYC.addItem(cd.getTenCongDoan());
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
        JScrollPane scSanPham = new JScrollPane(tableSanPham,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scSanPham.setPreferredSize(new Dimension(600,220));
        pnCenW.add(scSanPham);
        pnCenW.setBorder(new TitledBorder("Danh Sách Sản Phẩm"));
        
        pnCenN.setLayout(new BorderLayout());

        pnCenN.add(b, BorderLayout.WEST);
        pnCenN.add(pnCenW, BorderLayout.EAST);
        pnCenN.setBorder(new TitledBorder("Thông tin công đoạn"));
        
        
        pnCenter.add(pnCenN,BorderLayout.NORTH);
        pnCenter.add(pnCenC,BorderLayout.CENTER);

        //pnSouth
        pnSouth = new JPanel();
        List<CongDoan> ls = new ArrayList<>();
        CongDoan_Table model = new CongDoan_Table(congDoan_dao.getLS());
        JTable table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = table.getSelectedRow();
                if(r != -1) {
                    txtMa.setText(table.getValueAt(r, 0).toString());
                    txtTen.setText(table.getValueAt(r, 1).toString());
                    txtMaSP.setText(table.getValueAt(r,3).toString());
                    txtTenSP.setText(table.getValueAt(r,4).toString());
                    txtGiaCD.setText(table.getValueAt(r,2).toString());
                    spinnerSoLuong.setValue(Integer.parseInt(table.getValueAt(r, 5).toString()));
                    cbcCongDoanYC.setSelectedItem(table.getValueAt(r,6).toString());
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

        txtMa.setEditable(false);
        pnSouth.add(sc);
        pnSouth.setBorder(new TitledBorder("Danh Sách Công Đoạn"));

        //Sự Kiện Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableSanPham.getSelectedRow() != -1) {
                    if (txtTen.getText().trim().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Tên công đoạn không được rỗng");
                        txtTen.requestFocus();
                    } else if (txtGiaCD.getText().trim().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Giá không được rỗng");
                        txtGiaCD.requestFocus();
                    } else if (Integer.parseInt(spinnerSoLuong.getValue().toString()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Số Lượng Phải > 0");
                        spinnerSoLuong.requestFocus();
                    } else {
                        CongDoan congDoan = new CongDoan(txtMa.getText().trim(), txtTen.getText().trim(),
                                Double.parseDouble(txtGiaCD.getText().trim()),
                                Integer.parseInt(spinnerSoLuong.getValue().toString()));
                        SanPham sanPham = sanPham_dao.TimKiemMa(txtMaSP.getText().trim());
                        congDoan.setSanPham(sanPham);
                        if (cbCongDoanYC.isSelected()) {
                            CongDoan congDoanYeuCau = congDoan_dao.TimKiemTen(cbcCongDoanYC.getSelectedItem().toString());
                            if (congDoanYeuCau != null)
                                congDoan.setCongDoanYeuCau(congDoanYeuCau);
                        }
                        if (congDoan_dao.addCongDoan(congDoan)) {
                            try {
                                table.setModel(new CongDoan_Table(congDoan_dao.getLS()));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else
                            JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                        clearTextField();
                        table.setModel(new CongDoan_Table(congDoan_dao.getLS()));
                    }
                }  else {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm!");
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
                        if (congDoan_dao.deleteCongDoan(maX)) {
                            try {
                                table.setModel(new CongDoan_Table(congDoan_dao.getLS()));
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
                    CongDoan congDoan = new CongDoan(maS, txtTen.getText().trim(),
                            Double.parseDouble(txtGiaCD.getText().trim()),
                            Integer.parseInt(spinnerSoLuong.getValue().toString()));
                    SanPham sanPham = sanPham_dao.TimKiemMa(txtMaSP.getText().trim());
                    congDoan.setSanPham(sanPham);
                    if (cbCongDoanYC.isSelected()) {
                        CongDoan congDoanYeuCau = congDoan_dao.TimKiemTen(cbcCongDoanYC.getSelectedItem().toString());
                        if (congDoanYeuCau != null)
                            congDoan.setCongDoanYeuCau(congDoanYeuCau);
                    }
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?", "option", JOptionPane.YES_NO_OPTION);
                    if (lc == JOptionPane.YES_OPTION) {
                        if (congDoan_dao.updateCongDoan(congDoan)) {
                            try {
                                table.setModel(new CongDoan_Table(congDoan_dao.getLS()));
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
        this.add(pnNorth,BorderLayout.NORTH);
        this.add(pnCenter,BorderLayout.CENTER);
        this.add(pnSouth,BorderLayout.SOUTH);

    }
    public void clearTextField(){
        txtMa.setText("");
        txtTen.setText("");
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtGiaCD.setText("");
        spinnerSoLuong.setValue(0);
        cbcCongDoanYC.setSelectedIndex(0);
        cbCongDoanYC.setSelected(false);
        txtTen.requestFocus();
    }
}
