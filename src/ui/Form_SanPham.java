package ui;

import com.toedter.calendar.JDateChooser;
import dao.CongNhan_Dao;
import dao.SanPham_Dao;
import entity.CongNhan;
import entity.Phongban;
import entity.SanPham;
import table_model.CongNhan_Table;
import table_model.SanPham_Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Form_SanPham extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel pnNorth, pnCenter, pnSouth;
    JLabel lblMa, lblGioiTinh, lblNamSinh, lblKieuDang, lblChatLieu, lblSoLuong, lblPhongBan, lblTen, lblHeSoLuong, lblTayNghe, lblDiaChi;
    JTextField txtMa, txtKieuDang, txtChatLieu, txtCM, txtPhuCap, txtTen, txtDiaChi;
    JComboBox<String> cbcGT, cbcPhongBan, cbcTayNghe;
    JDateChooser namSinh, NgayVao;
    private JSpinner spinner;

    public Form_SanPham() {
        doShow();
    }

    public void doShow() {
        //pnNorth
        pnNorth = new JPanel();
        JPanel pnTieuDe = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        JLabel lblTieuDe = new JLabel("SẢN PHẨM");
        lblTieuDe.setFont(new Font("arial", Font.BOLD, 20));
        lblTieuDe.setForeground(Color.RED);
        pnTieuDe.add(lblTieuDe);
        pnNorth.add(pnTieuDe);

        //pnCenter
        pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        Box b, b1, b2;
        JPanel pnCenN = new JPanel();
        JPanel pnCenC = new JPanel();
        b = Box.createVerticalBox();
        b.setPreferredSize(new Dimension(840, 160));


        b.add(Box.createVerticalStrut(20));
        b.add(b1 = Box.createHorizontalBox());
        b1.add(lblMa = new JLabel("Mã Sản Phẩm: "));
        b1.add(txtMa = new JTextField(30));
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lblTen = new JLabel("Tên Sản Phẩm:    "));
        b1.add(txtTen = new JTextField(20));
        b.add(Box.createVerticalStrut(10));

        b.add(b2 = Box.createHorizontalBox());
        b2.add(lblKieuDang = new JLabel("Kiểu Dáng: "));
        b2.add(txtKieuDang = new JTextField(30));
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblChatLieu = new JLabel("Chất Liệu:    "));
        b2.add(txtChatLieu = new JTextField(20));
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblSoLuong = new JLabel("Số Lượng: "));
        b2.add(spinner = new JSpinner());
        spinner.setPreferredSize(new Dimension(100, 20));
        b.add(Box.createVerticalStrut(60));


        lblMa.setPreferredSize(lblTen.getPreferredSize());
        lblKieuDang.setPreferredSize(lblTen.getPreferredSize());
        lblChatLieu.setPreferredSize(lblTen.getPreferredSize());
        lblSoLuong.setPreferredSize(lblTen.getPreferredSize());


        JButton btnThem, btnXoa, btnSua, btnThoat, btnXoaRong;
        pnCenC.add(btnThem = new JButton("Thêm Sản Phẩm"));
        btnThem.setIcon(new ImageIcon(getClass().getResource("/icons/add_icon.png")));
        btnThem.setBackground(Color.decode("#4caf50"));
        btnThem.setForeground(Color.decode("#FFFFFF"));
        pnCenC.add(btnXoa = new JButton("Xóa Sản Phẩm"));
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
        pnCenN.setBorder(new TitledBorder("Thông tin sản phẩm"));
        pnCenter.add(pnCenN, BorderLayout.NORTH);
        pnCenter.add(pnCenC, BorderLayout.CENTER);

        //pnSouth
        pnSouth = new JPanel();
        List<SanPham> ls = new ArrayList<>();
        SanPham_Dao sanPham_dao = new SanPham_Dao();
        SanPham_Table model = new SanPham_Table(sanPham_dao.getLS());
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
                    txtChatLieu.setText(table.getValueAt(r, 3).toString());
                    txtKieuDang.setText(table.getValueAt(r, 2).toString());
                    spinner.setValue(Integer.parseInt(table.getValueAt(r, 4).toString()));
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
        pnSouth.setBorder(new TitledBorder("Danh Sách Sản Phẩm"));

        //Sự Kiện Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTen.getText().trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Tên sản phẩm không được rỗng");
                } else if (txtKieuDang.getText().trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Kiểu dáng không được rỗng");
                } else if (txtChatLieu.getText().trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Chất liệu không được rỗng");
                } else {
                    SanPham sanPham = new SanPham(txtMa.getText().trim(), txtTen.getText().trim(),
                            txtKieuDang.getText().trim(),
                            txtChatLieu.getText().trim(),
                            Integer.parseInt(spinner.getValue().toString()));
                    if (sanPham_dao.addSanPham(sanPham)) {
                        try {
                            table.setModel(new SanPham_Table(sanPham_dao.getLS()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập thông tin !");
                    clearTextField();
                    table.setModel(new SanPham_Table(sanPham_dao.getLS()));
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
                        if (sanPham_dao.deleteSanPham(maX)) {
                            try {
                                table.setModel(new SanPham_Table(sanPham_dao.getLS()));
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
                    SanPham sanPham = new SanPham(maS, txtTen.getText().trim(),
                            txtKieuDang.getText().trim(),
                            txtChatLieu.getText().trim(),
                            Integer.parseInt(spinner.getValue().toString()));
                    int lc = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin không ?", "option", JOptionPane.YES_NO_OPTION);
                    if (lc == JOptionPane.YES_OPTION) {
                        if (sanPham_dao.updateSanPham(maS, sanPham)) {
                            try {
                                table.setModel(new SanPham_Table(sanPham_dao.getLS()));
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
        txtTen.setText("");
        txtKieuDang.setText("");
        txtChatLieu.setText("");
        txtKieuDang.setText("");
        spinner.setValue(0);
        txtTen.requestFocus();
    }
}