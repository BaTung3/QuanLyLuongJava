package application;

import entity.TaiKhoan;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import ui.Form_DangNhap;

public class RunApplication {
    
    public static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
    
    public static DateTimeFormatter Date = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public static DateTimeFormatter Time = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    public static DateTimeFormatter Weekday = DateTimeFormatter.ofPattern("EEEE");
    
   public static DateTimeFormatter Weekdaytime = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy");
    
   public static LocalTime start = LocalTime.of( 7 , 00,00 ) ;
   
   public static LocalTime end = LocalTime.of( 21 , 00,00 ) ;
   
   public static float heSoBH = 0.105f;
   public static float luongKhongThue = 11000000f;
   
      public static float TinhLuong(double lcb , double heso)
        {
            return (float) ((float) lcb * heso);
        }
      
      public static float TinhBaoHiem(double lcb , double heso)
        {
            return TinhLuong(lcb,heso) * heSoBH;
        }
      
       public static float GetThueThuNhapCaNhanThang(double lcb , double heso)
        {
            float sauBaoHiem = TinhLuong(lcb,heso) - TinhBaoHiem(lcb,heso) -luongKhongThue;


            if (sauBaoHiem < 0) return 0;
             else if (sauBaoHiem <= 5000000) return sauBaoHiem * 0.05f;
            else if (sauBaoHiem >5000000 && sauBaoHiem <= 10000000) return (sauBaoHiem * 0.1f)-250000;
            else if (sauBaoHiem < 10000000 && sauBaoHiem <= 18000000) return (sauBaoHiem * 0.15f) - 750000;
           else if (sauBaoHiem < 18000000 && sauBaoHiem <= 32000000) return (sauBaoHiem * 0.2f) - 1650000;
           else if (sauBaoHiem < 32000000 && sauBaoHiem <= 52000000) return (sauBaoHiem * 0.25f) - 3250000;
           else if (sauBaoHiem < 52000000 && sauBaoHiem <= 80000000) return (sauBaoHiem * 0.3f) - 5850000;
            else return (sauBaoHiem * 0.35f) - 9850000;



        }
       
        public static double LuongPhaiTra(double lcb , double heso,float tienphat, float phucap)
        {
            System.out.print("TinhLuong "+TinhLuong(lcb,heso)+" BH: "+TinhBaoHiem(lcb,heso)+ " TTNCN "+GetThueThuNhapCaNhanThang(lcb,heso)+ " Tienphat "+tienphat +" Phu cap " + phucap);
            return (double) (TinhLuong(lcb,heso) - TinhBaoHiem(lcb,heso) - GetThueThuNhapCaNhanThang(lcb,heso) - tienphat) + phucap;
        }
   
    public static boolean IsAdmin(TaiKhoan taikhoan){
       
       if(taikhoan.getTenDN().equalsIgnoreCase("admin"))return true;
       else return false;
   }
   
   public static boolean CheckTrongGioLam(){
       
       if(LocalTime.now().isBefore(start))return false;
       else if (LocalTime.now().isAfter(end))return false;
       else return true;
   }
   
    public static String GetTimeNow(String type){
        LocalDateTime localDateTime = LocalDateTime.now();
        String result =  localDateTime.format(RunApplication.CUSTOM_FORMATTER);
        if(type.equalsIgnoreCase("date"))
        {
            result = localDateTime.format(Date);
        }
        
         if(type.equalsIgnoreCase("time"))
        {
            result = localDateTime.format(Time);
        }
          if(type.equalsIgnoreCase("weekdaytime"))
        {
            result = localDateTime.format(Weekdaytime);
        }
          
            if(type.equalsIgnoreCase("weekday"))
        {
            String result1 = localDateTime.format(Weekday);
            
//            if(result1.equalsIgnoreCase("Monday"))result = "Thứ Hai";
//            if(result1.equalsIgnoreCase("Tuesday"))result = "Thứ Ba";
//            if(result1.equalsIgnoreCase("Wednesday"))result = "Thứ Tư";
//            if(result1.equalsIgnoreCase("Thursday"))result = "Thứ Năm";
//            if(result1.equalsIgnoreCase("Friday"))result = "Thứ Sáu";
//            if(result1.equalsIgnoreCase("Saturday"))result = "Thứ Bảy";
//            if(result1.equalsIgnoreCase("Sunday"))result = "Chủ Nhật";
           
            
            switch(result1) {
                        case "Monday":
                          result = "Thứ Hai";
                          break;
                        case "Tuesday":
                          result = "Thứ Ba";
                        case "Wednesday":
                          result = "Thứ Tư";
                          break;
                          
                          case "Thursday":
                          result = "Thứ Năm";
                          break;
                          
                          case "Friday":
                          result = "Thứ Sáu";
                          break;
                          
                          case "Saturday":
                          result = "Thứ Bảy";
                          break;
                          
                          case "Sunday":
                          result = "Chủ Nhật";
                          break;
                      }
        } 
        return result;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
    
    public static void main(String[] args) {
        Form_DangNhap form_dangNhap = new Form_DangNhap();
        form_dangNhap.doShow();
        form_dangNhap.setVisible(true);
    }
}
