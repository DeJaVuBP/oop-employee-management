/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLY_NHANSU;

/**
 *
 * @author vubin
 */
class TinhLuong {

    private int thang;
    private NhanVien nhanVien;
    private double luongThucNhan;
    private int soNgayLam; // Dành cho FullTime
    private double soGioLam;  // Dành cho PartTime
    private double heSoLuong; // Dành cho Trưởng phòng

    // Constructor cho FullTime
    public TinhLuong(int thang, NhanVien nhanVien, int soNgayLam) {
        this.thang = thang;
        this.nhanVien = nhanVien;
        this.soNgayLam = soNgayLam;
        this.soGioLam = -1; // Không áp dụng
        this.luongThucNhan = tinhLuong();
    }

    public TinhLuong(int thang, NhanVien nhanVien, int soNgayLam, double heSoLuong) {
        this.thang = thang;
        this.nhanVien = nhanVien;
        this.soNgayLam = soNgayLam;
        this.soGioLam = -1; // Không áp dụng
        this.luongThucNhan = tinhLuong();
        this.heSoLuong = heSoLuong;
    }

    // Constructor cho PartTime
    public TinhLuong(int thang, NhanVien nhanVien, int soGioLam, boolean isPartTime) {
        this.thang = thang;
        this.nhanVien = nhanVien;
        this.soGioLam = soGioLam;
        this.soNgayLam = -1; // Không áp dụng
        this.luongThucNhan = tinhLuong();
    }

    private double tinhLuong() {
        if (nhanVien instanceof FullTime) {
            FullTime ft = (FullTime) nhanVien;
            return ((ft.getLuongCoBan()/26) * soNgayLam ) + (ft.getPhuCap()); // Giả định tháng có 26 ngày làm
        } else if (nhanVien instanceof PartTime) {
            PartTime pt = (PartTime) nhanVien;
            return pt.getLuongtheogio() * soGioLam;
        } else if (nhanVien instanceof TruongPhong) {
            TruongPhong tp = (TruongPhong) nhanVien;
            return (((tp.getLuongCoBan()/26)*2) * soNgayLam ) + (tp.getPhuCap());
        }

        return 0;
    }

    // Getters và setters
    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getSoNgayLam() {
        return soNgayLam;
    }

    public void setSoNgayLam(int soNgayLam) {
        this.soNgayLam = soNgayLam;
    }

    public double getSoGioLam() {
        return soGioLam;
    }

    public void setSoGioLam(double soGioLam) {
        this.soGioLam = soGioLam;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void capNhatLuong() {
        this.luongThucNhan = tinhLuong();
    }

    @Override
    public String toString() {
        String chiTiet = (soNgayLam != -1) ? ("Số ngày làm: " + soNgayLam) : ("Số giờ làm: " + soGioLam);
        return "Tháng: " + thang + ", " + nhanVien.toString() + ", " + chiTiet + ", Lương: " + luongThucNhan;
    }
}
