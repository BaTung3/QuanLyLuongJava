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
   
   //Ca Lam//
   
   public static LocalTime start1 = LocalTime.of( 7 , 00,00 ) ;
   
   public static LocalTime end1 = LocalTime.of( 9 , 00,00 ) ;
   
   public static LocalTime start2 = LocalTime.of( 9 , 30,00 ) ;
   
   public static LocalTime end2 = LocalTime.of( 12 , 30,00 ) ;
   
   public static LocalTime start3 = LocalTime.of( 13 , 00,00 ) ;
   
   public static LocalTime end3 = LocalTime.of( 15 , 00,00 ) ;
   
   
   public static LocalTime start4 = LocalTime.of( 15 , 30,00 ) ;
   
   public static LocalTime end4 = LocalTime.of( 17 , 30,00 ) ;
   
   
   public static LocalTime start5 = LocalTime.of( 18 , 00,00 ) ;
   
   public static LocalTime end5 = LocalTime.of( 21 , 00,00 ) ;
   
   //End CaLam//
   
   public static float heSoBH = 0.105f;
   public static float luongKhongThue = 11000000f;
   
   public static Double ChucvuToHesoluong(String hsl){

                        if(hsl.equalsIgnoreCase("Nhân viên bán hàng"))
                        {return 1*1.0;
                        }
                        else if (hsl.equalsIgnoreCase("Nhân viên kho"))
                        {return 1.5;
                        } 
                        else if (hsl.equalsIgnoreCase("Kế toán"))
                        {return 2*1.0;
                        } 
                         else if (hsl.equalsIgnoreCase("Trưởng phòng"))
                        {return 2.5*1.0;
                        } 
                          else if (hsl.equalsIgnoreCase("Quản lý"))
                        {return 3*1.0;
                        } 
                          else return 3.5;
   }
   
    public static String ChucvuToMaHesoluong(String hsl){

                        if(hsl.equalsIgnoreCase("Nhân viên bán hàng"))
                        {return "HSL001";
                        }
                        else if (hsl.equalsIgnoreCase("Nhân viên kho"))
                        {return "HSL002";
                        } 
                        else if (hsl.equalsIgnoreCase("Kế toán"))
                        {return "HSL003";
                        } 
                         else if (hsl.equalsIgnoreCase("Trưởng phòng"))
                        {return "HSL004";
                        } 
                          else if (hsl.equalsIgnoreCase("Quản lý"))
                        {return "HSL005";
                        } 
                          else return "HSL006";
   }
   
   public static String HesoluongToChucvu(double hsl){

                        if(hsl ==1)
                        {return "Nhân viên bán hàng";
                        }
                        else if (hsl ==1.5) return "Nhân viên kho";
                        else if (hsl ==2)return "Kế toán";
                         else if (hsl ==2.5)return "Trưởng phòng";
                          else if (hsl ==3)return "Quản lý";
                          else return "Giám Đốc";
   }
   
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
   
   public static int CheckCaLam(){
       LocalTime now = LocalTime.now();
       
      if(now.isBefore(end1)&& now.isAfter(start1))
                        {return 1;
                        }
                        else if (now.isBefore(end2)&& now.isAfter(start2)) return 2;
                        else if (now.isBefore(end3)&& now.isAfter(start3)) return 3;
                        else if (now.isBefore(end4)&& now.isAfter(start4)) return 4;
                        else if (now.isBefore(end5)&& now.isAfter(start5)) return 5;
                        else if (now.isBefore(start)|| now.isAfter(end)) return 6;
                                                  else return 0;
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
