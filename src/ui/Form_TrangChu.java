package ui;

import application.RunApplication;
import entity.TaiKhoan;
import javax.swing.*;
import java.awt.*;

public class Form_TrangChu extends JPanel {
    
     TaiKhoan taiKhoan;
    boolean isAdmin = false;
    
    public Form_TrangChu(TaiKhoan taiKhoan){
        this.taiKhoan = taiKhoan;
        //this.isAdmin = isAdmin;
        System.out.println("TenDN"+taiKhoan.getTenDN());
         
//         if(taiKhoan.getTenDN().equalsIgnoreCase("admin")){
//            isAdmin = true;
//         }  
        
         //TimeUnit.SECONDS.sleep(1);
         
        doShow();
    }
    
    public Form_TrangChu() {
        doShow();
    }
    public void doShow() {
        JPanel pnCenterN = new JPanel();
        JPanel pnCenterC = new JPanel();
        JLabel lblTieuDe = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ LƯƠNG SẢN PHẨM");
        lblTieuDe.setFont(new Font("arial",Font.BOLD,24));
        lblTieuDe.setForeground(Color.RED);
        pnCenterN.setPreferredSize(new Dimension(1000,80));

        JLabel lbImage = new JLabel();
        
         lbImage.setText("Chào Mừng");
       lbImage.setFont(new Font("arial",Font.BOLD,50));
        lbImage.setForeground(Color.GREEN);
       lbImage.setPreferredSize(new Dimension(1000,80));
        
        JLabel tenNV = new JLabel();
         tenNV.setText("Nhân viên: " + taiKhoan.getTenDN() );
       tenNV.setFont(new Font("arial",Font.BOLD,25));
        tenNV.setForeground(Color.GREEN);
        tenNV.setPreferredSize(new Dimension(1000,40));
        
        //LocalDateTime localDateTime = LocalDateTime.now();

        
         JLabel time = new JLabel();
         time.setText("Thời gian: " + RunApplication.GetTimeNow("") );
       time.setFont(new Font("arial",Font.BOLD,25));
        time.setForeground(Color.RED);
        time.setPreferredSize(new Dimension(1000,40));
        
        JLabel diemDanh = new JLabel();
         diemDanh.setText("Đã Điểm Danh lúc : " );
       diemDanh.setFont(new Font("arial",Font.BOLD,25));
        diemDanh.setForeground(Color.GREEN);
        diemDanh.setPreferredSize(new Dimension(1000,40));
        //lbImage.setIcon(new ImageIcon(getClass().getResource("/icons/background.jpg")));

        pnCenterN.add(lblTieuDe);
        pnCenterC.add(lbImage);
         pnCenterC.add(tenNV);
        pnCenterC.add(diemDanh);
        pnCenterC.add(time);

        this.setLayout(new BorderLayout());
        this.add(pnCenterN, BorderLayout.NORTH);
        this.add(pnCenterC, BorderLayout.CENTER);
    }
}
