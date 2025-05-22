/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLY_NHANSU;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vubin
 */
public class PhongBan implements Ithongtin {

    private String tenPB;                  // Tên phòng ban
    private String maPB;                   // Mã phòng ban
    private List<NhanVien> danhSachNhanVien; // Danh sách nhân viên trong phòng ban
    private Scanner scanner;

    // Constructor mặc định
    public PhongBan() {
        this.tenPB = "";
        this.maPB = "";
        this.danhSachNhanVien = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Constructor không có danh sách nhân viên
    public PhongBan(String maPB, String tenPB) {
        this.tenPB = tenPB;
        this.maPB = maPB;
        this.danhSachNhanVien = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Constructor đầy đủ
    public PhongBan(String maPB, String tenPB, List<NhanVien> danhSachNhanVien) {
        this.tenPB = tenPB;
        this.maPB = maPB;
        this.danhSachNhanVien = danhSachNhanVien != null ? danhSachNhanVien : new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Getters và Setters
    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public List<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(List<NhanVien> danhSachNhanVien) {
        this.danhSachNhanVien = danhSachNhanVien != null ? danhSachNhanVien : new ArrayList<>();
    }

    // Thêm nhân viên vào phòng ban
    public void themNhanVien(NhanVien nv) {
        if (nv != null) {
            danhSachNhanVien.add(nv);
        } else {
            System.out.println("Nhân viên không hợp lệ!");
        }
    }

    // Xóa nhân viên khỏi phòng ban
    public void xoaNhanVien(NhanVien nv) {
        if (danhSachNhanVien.remove(nv)) {
            System.out.println("Đã xóa nhân viên: " + nv.getTen());
        } else {
            System.out.println("Nhân viên không tồn tại trong danh sách.");
        }
    }
//    // Tìm kiếm nhân viên theo mã nhân viên
//    public NhanVien timNhanVien(String maNV) {
//        for (NhanVien nv : danhSachNhanVien) {
//            if (nv.getMSNV().equalsIgnoreCase(maNV)) {
//                return nv;
//            }
//        }
//        System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
//        return null;
//    }
    // Hiển thị thông tin của phòng ban
    @Override
    public String toString() {
        return "Phòng Ban [Mã: " + maPB + ", Tên: " + tenPB + ", Số lượng nhân viên: " + danhSachNhanVien.size() + "]";
    }

    // Hiển thị thông tin chi tiết của phòng ban và danh sách nhân viên
    @Override
    public void hienThiThongTin() {
        System.out.println(this.toString());
    }

    // Hiển thị danh sách nhân viên trong phòng ban
    public void hienThiDanhSachNhanVien() {
        System.out.println("Danh sách nhân viên trong phòng ban: " + tenPB);
        if (danhSachNhanVien.isEmpty()) {
            System.out.println("Không có nhân viên nào trong phòng ban này.");
        } else {
            for (NhanVien nv : danhSachNhanVien) {
                System.out.println(nv);
            }
        }
    }

//    private static void validateTenPB(String tenPB) {
//        // Kiểm tra nếu tên phòng ban trống
//        if (tenPB.isEmpty()) {
//            throw new IllegalArgumentException("Tên phòng ban không được để trống.");
//        }
//        // Kiểm tra nếu tên phòng ban có ít hơn 3 ký tự
//        if (tenPB.length() < 3) {
//            throw new IllegalArgumentException("Tên phòng ban phải chứa ít nhất 3 ký tự.");
//        }
//        
//    }
//
//    private static void validateMAPB(String maPB) {
//        if (maPB.isEmpty()) {
//            throw new IllegalArgumentException("Mã phòng ban không được để trống.");
//        }
//        if (maPB.length() < 2 || maPB.length() > 5) {
//            throw new IllegalArgumentException("Mã phòng ban phải có độ dài từ 2 đến 5 ký tự.");
//        }
//        if (!maPB.matches("[a-zA-Z0-9]+")) {
//            throw new IllegalArgumentException("Mã phòng ban chỉ được chứa chữ cái và số.");
//        }
//        // Kiểm tra nếu tên phòng ban không bắt đầu bằng "PB"
//        if (!maPB.startsWith("PB")) {
//            throw new IllegalArgumentException("Mã phòng ban phải bắt đầu bằng 'PB'.");
//        }
//    }
}
