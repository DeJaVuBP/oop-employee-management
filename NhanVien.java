/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLY_NHANSU;

/**
 *
 * @author vubin
 */

public abstract class NhanVien implements Ithongtin {

    protected String MSNV;          // Mã số nhân viên
    protected String ten;           // Tên nhân viên
    protected String email;         // Email
    protected String diachi;        // Địa chỉ
    protected String ngaysinh;   // Ngày sinh
    protected String gioitinh;      // Giới tính
    protected String sdt;           // Số điện thoại
    
    // Constructor tối thiểu
    public NhanVien(String MSNV, String ten) {
        this.MSNV = MSNV;
        this.ten = ten;   
    }
    
        // Constructor đầy đủ
    public NhanVien(String MSNV, String ten, String email, String diachi,
            String ngaysinh, String gioitinh, String sdt) {
        this.MSNV = MSNV;
        this.ten = ten;
        this.email = email;
        this.diachi = diachi;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
    }

    // Getters và Setters
    public String getMSNV() {
        return MSNV;
    }

    public void setMSNV(String MSNV) {
        this.MSNV = MSNV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    // Phương thức trừu tượng để lấy kiểu nhân viên
    public abstract String getLoaiNhanVien();

    
    @Override
    public String toString() {
        return "Mã NV: " + MSNV + ", Họ tên: " + ten + ", Vị Trí: " + getLoaiNhanVien();
    }
    public abstract String hienthichitiet();
}
