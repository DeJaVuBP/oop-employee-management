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
public class DanhSachPhongBan implements Ichucnang<PhongBan> {

    private List<PhongBan> dspb;
    private Scanner scanner;

    public DanhSachPhongBan() {
        this.dspb = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public List<PhongBan> getDanhSachPhongBan() {
        return dspb;
    }

////////////////////////Thêm////////////////////////
    @Override
    public void them(PhongBan pb) {
        dspb.add(pb);
        System.out.println("Đã thêm phòng ban mới.");
    }

    public void themPhongBan() {
        System.out.print("Nhập mã phòng ban: ");
        String maPB = scanner.nextLine().trim().toUpperCase();;
        System.out.print("Nhập tên phòng ban: ");
        String tenPB = scanner.nextLine().trim().toUpperCase();;

        try {
            validateMAPB(maPB);
            validateTenPB(tenPB);
            PhongBan pb = new PhongBan(maPB, tenPB);
            dspb.add(pb);
            System.out.println("Đã thêm phòng ban mới.");
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

////////////////////////SỬA////////////////////////
    public void suaPhongBan(String maPB) {
        for (PhongBan pb : dspb) {
            if (pb.getMaPB().equals(maPB)) {
                System.out.println("Thông tin phòng ban hiện tại:");
                pb.hienThiThongTin();

                System.out.println("Bạn muốn chỉnh sửa thuộc tính nào?");
                System.out.println("1. Mã phòng ban");
                System.out.println("2. Tên phòng ban");
                System.out.println("3. Sửa tất cả các thuộc tính (Mã và Tên)");
                System.out.print("Lựa chọn của bạn: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Nhập mã phòng ban mới: ");
                            String newMaPB = scanner.nextLine().trim().toUpperCase();;
                            validateMAPB(newMaPB);
                            pb.setMaPB(newMaPB);
                            System.out.println("Đã cập nhật mã phòng ban.");
                            break;
                        case 2:
                            System.out.print("Nhập tên phòng ban mới: ");
                            String newTenPB = scanner.nextLine().trim().toUpperCase();;
                            validateTenPB(newTenPB);
                            pb.setTenPB(newTenPB);
                            System.out.println("Đã cập nhật tên phòng ban.");
                            break;
                        case 3:
                            System.out.print("Nhập mã phòng ban mới: ");
                            newMaPB = scanner.nextLine().trim().toUpperCase();;
                            validateMAPB(newMaPB);
                            pb.setMaPB(newMaPB);

                            System.out.print("Nhập tên phòng ban mới: ");
                            newTenPB = scanner.nextLine().trim().toUpperCase();;
                            validateTenPB(newTenPB);
                            pb.setTenPB(newTenPB);

                            System.out.println("Đã cập nhật tất cả các thuộc tính của phòng ban.");
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Lỗi: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Không tìm thấy phòng ban có mã: " + maPB);
    }

////////////////////////Xóa Phòng Ban////////////////////////
    @Override
    public void xoa(String maPB) {
        boolean removed = dspb.removeIf(pb -> pb.getMaPB().equals(maPB));
        if (removed) {
            System.out.println("Đã xóa phòng ban có mã: " + maPB);
        } else {
            System.out.println("Không tìm thấy phòng ban có mã: " + maPB);
        }
    }

////////////////////////Tìm Kiếm////////////////////////
    @Override
    public void timKiem(String maPB) {
        for (PhongBan pb : dspb) {
            if (pb.getMaPB().equals(maPB)) {
                System.out.println("Thông tin phòng ban tìm thấy:");
                pb.hienThiThongTin();
                return;
            }
        }
        System.out.println("Không tìm thấy phòng ban có mã: " + maPB);
    }

    public PhongBan timKiemPhongBan(String maPB) {
        for (PhongBan pb : dspb) {
            if (pb.getMaPB().equals(maPB)) {
                return pb;  // Trả về phòng ban tìm thấy
            }
        }
        return null;  // Không tìm thấy phòng ban
    }

    

////////////////////////Hiển Thị////////////////////////
    public void hienThi() {
        if (dspb.isEmpty()) {
            System.out.println("Danh sách phòng ban rỗng.");
            return;
        }
        System.out.println("Danh sách phòng ban:");
        for (PhongBan pb : dspb) {
            pb.hienThiThongTin();
        }
    }

//    public void hienThiDanhSachPhongBan() {
//        for (PhongBan pb : dspb) {
//            pb.hienThiDanhSachNhanVien();
//        }
//    }
    private static void validateTenPB(String tenPB) {
        if (tenPB.isEmpty()) {
            throw new IllegalArgumentException("Tên phòng ban không được để trống.");
        }
        if (tenPB.length() < 3) {
            throw new IllegalArgumentException("Tên phòng ban phải chứa ít nhất 3 ký tự.");
        }
    }

    private static void validateMAPB(String maPB) {
        if (maPB.isEmpty()) {
            throw new IllegalArgumentException("Mã phòng ban không được để trống.");
        }
        if (maPB.length() < 2 || maPB.length() > 5) {
            throw new IllegalArgumentException("Mã phòng ban phải có độ dài từ 2 đến 5 ký tự.");
        }
        if (!maPB.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Mã phòng ban chỉ được chứa chữ cái và số.");
        }
    }

}
