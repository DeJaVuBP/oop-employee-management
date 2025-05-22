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
    public Scanner scanner;

    // Static: Số lượng phòng ban
    private static int soLuongPhongBan = 0;

    // Constructor mặc định
    public PhongBan() {
        this.tenPB = "";
        this.maPB = "";
        this.danhSachNhanVien = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        soLuongPhongBan++;
    }

    // Constructor không có danh sách nhân viên
    public PhongBan(String maPB, String tenPB) {
        this.tenPB = tenPB;
        this.maPB = maPB;
        this.danhSachNhanVien = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        soLuongPhongBan++;
    }

    // Constructor đầy đủ
    public PhongBan(String maPB, String tenPB, List<NhanVien> danhSachNhanVien) {
        this.tenPB = tenPB;
        this.maPB = maPB;
        this.danhSachNhanVien = danhSachNhanVien != null ? danhSachNhanVien : new ArrayList<>();
        this.scanner = new Scanner(System.in);
        soLuongPhongBan++;
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

    // Getter cho số lượng phòng ban
    public static int getSoLuongPhongBan() {
        return soLuongPhongBan;
    }


    // Hiển thị thông tin của phòng ban
    @Override
    public String toString() {
        return "Mã: " + maPB + ", Tên: " + tenPB + ", Số lượng nhân viên: " + danhSachNhanVien.size();
    }

    // Hiển thị thông tin chi tiết của phòng ban và danh sách nhân viên
    @Override
    public void hienThiThongTin() {
        System.out.println(this.toString());
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
    public boolean xoaNhanVien(NhanVien nhanVien) {
        return danhSachNhanVien.remove(nhanVien);
    }

    public boolean kiemTraNhanVien(String msnv) {
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getMSNV().equals(msnv)) {
                return true; // Tìm thấy nhân viên có mã số trùng khớp
            }
        }
        return false; // Không tìm thấy nhân viên
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

    public String getDanhSachNhanVienAsString() {
        StringBuilder sb = new StringBuilder();
        if (danhSachNhanVien.isEmpty()) {
            sb.append("Không có nhân viên nào trong phòng ban này.\n");
        } else {
            sb.append(String.format("%-10s %-20s\n", "Mã NV", "Tên"));
            sb.append("-------------------------------------------------------------\n");
            for (NhanVien nv : danhSachNhanVien) {
                sb.append(String.format("%-10s %-20s\n",
                        nv.getMSNV(),
                        nv.getTen()));
            }
        }
        return sb.toString();
    }
}
