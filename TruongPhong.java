package QLY_NHANSU;

import java.util.Scanner;

public class TruongPhong extends NhanVien {

    private double luongCoBan;
    private double phuCap;
    private double soNamKinhNghiem;

    // Constructor đầy đủ
    public TruongPhong(String MSNV, String ten, String email, String diachi, String ngaysinh,
            String gioitinh, String sdt, double luongCoBan, double phuCap, double soNamKinhNghiem) {
        super(MSNV, ten, email, diachi, ngaysinh, gioitinh, sdt);
        this.soNamKinhNghiem = soNamKinhNghiem;
        this.phuCap = phuCap;
        this.luongCoBan = luongCoBan;
    }

    // Constructor mặc định
    public TruongPhong(String MSNV, String ten) {
        super(MSNV, ten, "", "", "", "", ""); // Khởi tạo với giá trị mặc định
        this.luongCoBan = 0; // Giá trị mặc định cho lương cơ bản
        this.phuCap = 0; // Giá trị mặc định cho phụ cấp
        this.soNamKinhNghiem = 0;
    }

    // Getter và Setter cho các thuộc tính
    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public double getSoNamKinhNghiem() {
        return soNamKinhNghiem;
    }

    public void setSoNamKinhNghiem(int soNamKinhNghiem) {
        if (soNamKinhNghiem < 2) {
            throw new IllegalArgumentException("Số năm kinh nghiệm phải lớn hơn hoặc bằng 2.");
        }
        this.soNamKinhNghiem = soNamKinhNghiem;
    }

    // Phương thức toString để mô tả thông tin nhân viên
    // Phương thức hiển thị thông tin nhân viên
    @Override
    public void hienThiThongTin() {
        System.out.println(this.toString());
    }

    @Override
    public String getLoaiNhanVien() {
        return "Trưởng Phòng";
    }
}
