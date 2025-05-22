package QLY_NHANSU;

import java.util.Scanner;

public class PartTime extends NhanVien {

    private double luongtheogio;   // Lương theo giờ làm việc

    // Constructor đầy đủ
    public PartTime(String MSNV, String ten, String email, String diachi,
            String ngaysinh, String gioitinh, String sdt, double luongtheogio) {
        super(MSNV, ten, email, diachi, ngaysinh, gioitinh, sdt);
        this.luongtheogio = luongtheogio;
    }

    // Constructor với các giá trị mặc định
    public PartTime(String MSNV, String ten) {
        super(MSNV, ten, "", "", "", "", "");  // Khởi tạo với giá trị mặc định
        this.luongtheogio = 0.0;   // Giá trị mặc định cho lương theo giờ
    }

    // Getter và Setter cho các thuộc tính
    public double getLuongtheogio() {
        return luongtheogio;
    }

    public void setLuongtheogio(double luongtheogio) {
        this.luongtheogio = luongtheogio;
    }

    // Phương thức hiển thị thông tin nhân viên
    @Override
    public void hienThiThongTin() {
        System.out.println(this.toString());
    }

    @Override
    public String getLoaiNhanVien() {
        return "PartTime";
    }

}
