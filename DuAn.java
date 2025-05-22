package QLY_NHANSU;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class DuAn implements Ithongtin {

    private String maDA;
    private String tenDA;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private List<NhanVien> danhSachNhanVien;
    private Scanner scanner;
    private DateTimeFormatter dateFormat;

    public DuAn() {
        this.maDA = "";
        this.tenDA = "";
        this.ngayBatDau = null;
        this.ngayKetThuc = null;
        this.danhSachNhanVien = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public DuAn(String maDA, String tenDA) {
        this.maDA = maDA;
        this.tenDA = tenDA;
        this.ngayBatDau = null;
        this.ngayKetThuc = null;
        this.danhSachNhanVien = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public DuAn(String maDA, String tenDA, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.maDA = maDA;
        this.tenDA = tenDA;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.danhSachNhanVien = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public void nhapMaDA() {
        System.out.print("Nhập mã dự án: ");
        maDA = scanner.nextLine();
    }

    public void nhapTenDA() {
        System.out.print("Nhập tên dự án: ");
        tenDA = scanner.nextLine();
    }

    public void nhapNgayBatDau() {
        System.out.print("Nhập ngày bắt đầu (định dạng dd/MM/yyyy): ");
        String input = scanner.nextLine();
        ngayBatDau = LocalDate.parse(input, dateFormat); // Assume dateFormat is a shared formatter
    }

    public void nhapNgayKetThuc() {
        System.out.print("Nhập ngày kết thúc (định dạng dd/MM/yyyy): ");
        String input = scanner.nextLine();
        ngayKetThuc = LocalDate.parse(input, dateFormat);
    }

    @Override
    public String toString() {
        return "Mã dự án: " + maDA + ", Tên dự án: " + tenDA
                + ", Ngày bắt đầu: " + (ngayBatDau != null ? ngayBatDau.format(dateFormat) : "chưa nhập")
                + ", Ngày kết thúc: " + (ngayKetThuc != null ? ngayKetThuc.format(dateFormat) : "chưa nhập")
                + ", Số lượng nhân viên tham gia: " + danhSachNhanVien.size();
    }

    @Override
    public void hienThiThongTin() {
        System.out.println(this.toString());
    }

    // Getter và Setter cho các thuộc tính
    public String getMaDA() {
        return maDA;
    }

    public void setMaDA(String maDA) {
        this.maDA = maDA;
    }

    public String getTenDA() {
        return tenDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public List<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public void setDanhSachNhanVien(List<NhanVien> danhSachNhanVien) {
        this.danhSachNhanVien = danhSachNhanVien;
    }

    public boolean kiemTraNhanVien(String msnv) {
    for (NhanVien nv : danhSachNhanVien) {
        if (nv.getMSNV().equals(msnv)) {
            return true; // Tìm thấy nhân viên có mã số trùng khớp
        }
    }
    return false; // Không tìm thấy nhân viên
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

    public void hienThiDanhSachNhanVien() {
        System.out.println("Danh sách nhân viên trong dự án: " + tenDA);
        if (danhSachNhanVien.isEmpty()) {
            System.out.println("Không có nhân viên nào trong dự án này.");
        } else {
            for (NhanVien nv : danhSachNhanVien) {
                System.out.println(nv);
            }
        }
    }

    public String getDanhSachNhanVienAsString() {
        StringBuilder sb = new StringBuilder();
        if (danhSachNhanVien.isEmpty()) {
            sb.append("Không có nhân viên nào trong dự án này.\n");
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
