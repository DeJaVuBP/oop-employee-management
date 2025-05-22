/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLY_NHANSU;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vubin
 */
public class DanhSachDuAn implements Ichucnang<DuAn> {

    private List<DuAn> dsda;
    private Scanner scanner;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DanhSachDuAn() {
        this.dsda = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void hienThi() {
        if (dsda.isEmpty()) {
            System.out.println("Danh sách dự án rỗng.");
            return;
        }
        System.out.println("Danh sách dự án:");
        for (DuAn da : dsda) {
            da.hienThiThongTin();
        }
    }

    // Phương thức mới để trả về danh sách dự án
    public List<DuAn> getDanhSachDuAn() {
        return dsda;
    }

//////////////////////THÊM/////////////////////////
    
    @Override
    public void them(DuAn da) {
        for (DuAn existingDA : dsda) {
            if (existingDA.getMaDA().equals(da.getMaDA())) {
                System.out.println("Lỗi: Mã dự án " + da.getMaDA() + " đã tồn tại. Không thể thêm.");
                return;
            }
        }
        dsda.add(da);
        System.out.println("Đã thêm dự án mới.");
    }
    // Thêm dự án với validate
    public void themDuAn() {
        DuAn da = new DuAn();

        // Nhập và validate mã dự án
        boolean maHopLe;
        do {
            da.nhapMaDA();
            String maDA = da.getMaDA().trim().toUpperCase();

            if (!validateMaDA(maDA)) {
                System.out.println("Lỗi: Mã dự án không đúng định dạng (DAxxx). Vui lòng nhập lại.");
                maHopLe = false;
            } else if (isMaDATrung(maDA)) {
                System.out.println("Lỗi: Mã dự án " + maDA + " đã tồn tại. Vui lòng nhập mã khác.");
                maHopLe = false;
            } else {
                maHopLe = true;
                da.setMaDA(maDA);
            }
        } while (!maHopLe);

        // Nhập và validate tên dự án
        boolean tenHopLe;
        do {
            da.nhapTenDA();
            String tenDA = da.getTenDA().trim();

            if (!validateTenDA(tenDA)) {
                System.out.println("Lỗi: Tên dự án không được chứa ký tự đặc biệt. Vui lòng nhập lại.");
                tenHopLe = false;
            } else {
                tenHopLe = true;
                da.setTenDA(tenDA);
            }
        } while (!tenHopLe);

        // Nhập và validate ngày bắt đầu, ngày kết thúc
        boolean ngayHopLe;
        do {
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            String ngayBatDau = scanner.nextLine();
            System.out.print("Nhập ngày kết thúc (dd/MM/yyyy): ");
            String ngayKetThuc = scanner.nextLine();

            if (!validateNgay(ngayBatDau, ngayKetThuc)) {
                System.out.println("Lỗi: Ngày không hợp lệ hoặc ngày bắt đầu sau ngày kết thúc. Vui lòng nhập lại.");
                ngayHopLe = false;
            } else {
                ngayHopLe = true;
                da.setNgayBatDau(LocalDate.parse(ngayBatDau, dateFormat));
                da.setNgayKetThuc(LocalDate.parse(ngayKetThuc, dateFormat));
            }
        } while (!ngayHopLe);

        // Thêm dự án vào danh sách
        dsda.add(da);
        System.out.println("Đã thêm dự án mới thành công.");

    }
    
//////////////////////SỬA/////////////////////////
    
    public void suaDuAn(String maDA) {
        for (DuAn da : dsda) {
            if (da.getMaDA().equals(maDA)) {
                System.out.println("Thông tin dự án hiện tại:");
                da.hienThiThongTin();

                System.out.println("Bạn muốn chỉnh sửa thuộc tính nào?");
                System.out.println("1. Mã dự án");
                System.out.println("2. Tên dự án");
                System.out.println("3. Ngày bắt đầu & Ngày kết thúc");
                System.out.println("4. Sửa tất cả các thuộc tính");
                System.out.print("Lựa chọn của bạn: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng còn lại

                switch (choice) {
                    case 1:
                        System.out.print("Nhập mã dự án mới: ");
                        String newMaDA = scanner.nextLine();
                        validateMaDA(newMaDA);
                        da.setMaDA(newMaDA);
                        System.out.println("Đã cập nhật mã dự án.");
                        break;
                    case 2:
                        System.out.print("Nhập tên dự án mới: ");
                        String newTenDA = scanner.nextLine();
                        validateTenDA(newTenDA);
                        da.setTenDA(newTenDA);
                        System.out.println("Đã cập nhật tên dự án.");
                        break;
                    case 3:
                        boolean ngayHopLe;
                        do {
                            System.out.print("Nhập ngày bắt đầu mới (dd/MM/yyyy): ");
                            String newNgayBatDau = scanner.nextLine();

                            System.out.print("Nhập ngày kết thúc mới (dd/MM/yyyy): ");
                            String newNgayKetThuc = scanner.nextLine();

                            // Validate cả hai ngày
                            if (!validateNgay(newNgayBatDau, newNgayKetThuc)) {
                                System.out.println("Lỗi: Ngày không hợp lệ hoặc ngày bắt đầu sau ngày kết thúc. Vui lòng nhập lại.");
                                ngayHopLe = false;
                            } else {
                                ngayHopLe = true;
                                da.setNgayBatDau(LocalDate.parse(newNgayBatDau, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                da.setNgayKetThuc(LocalDate.parse(newNgayKetThuc, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                System.out.println("Đã cập nhật ngày bắt đầu và ngày kết thúc.");
                            }
                        } while (!ngayHopLe);
                        break;

                    case 4:
                        suaTatCaThuocTinh(da);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
                return;
            }
        }
        System.out.println("Không tìm thấy dự án có mã: " + maDA);
    }

//////////////////////SỬA TẤT CẢ THUỘC TÍNH/////////////////////////
    
    private void suaTatCaThuocTinh(DuAn da) {
        // Nhập và validate mã dự án mới
        boolean maHopLe;
        do {
            System.out.print("Nhập mã dự án mới: ");
            String newMaDAAll = scanner.nextLine();
            if (!validateMaDA(newMaDAAll)) {
                System.out.println("Lỗi: Mã dự án không đúng định dạng (DAxxx). Vui lòng nhập lại.");
                maHopLe = false;
            } else {
                da.setMaDA(newMaDAAll);
                maHopLe = true;
            }
        } while (!maHopLe);

        // Nhập và validate tên dự án mới
        boolean tenHopLe;
        do {
            System.out.print("Nhập tên dự án mới: ");
            String newTenDAAll = scanner.nextLine();
            if (!validateTenDA(newTenDAAll)) {
                System.out.println("Lỗi: Tên dự án không được chứa ký tự đặc biệt. Vui lòng nhập lại.");
                tenHopLe = false;
            } else {
                da.setTenDA(newTenDAAll);
                tenHopLe = true;
            }
        } while (!tenHopLe);

        // Nhập và validate ngày bắt đầu và ngày kết thúc
        boolean ngayHopLe;
        do {
            System.out.print("Nhập ngày bắt đầu mới (dd/MM/yyyy): ");
            String newNgayBatDauAll = scanner.nextLine();

            System.out.print("Nhập ngày kết thúc mới (dd/MM/yyyy): ");
            String newNgayKetThucAll = scanner.nextLine();

            if (!validateNgay(newNgayBatDauAll, newNgayKetThucAll)) {
                System.out.println("Lỗi: Ngày không hợp lệ hoặc ngày bắt đầu sau ngày kết thúc. Vui lòng nhập lại.");
                ngayHopLe = false;
            } else {
                da.setNgayBatDau(LocalDate.parse(newNgayBatDauAll, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                da.setNgayKetThuc(LocalDate.parse(newNgayKetThucAll, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                ngayHopLe = true;
            }
        } while (!ngayHopLe);

        System.out.println("Đã cập nhật tất cả các thuộc tính của dự án.");
    }

//////////////////////XÓA/////////////////////////
    
    public void xoa(String maDA) {
        boolean removed = dsda.removeIf(da -> da.getMaDA().equals(maDA));
        if (removed) {
            System.out.println("Đã xóa dự án có mã: " + maDA);
        } else {
            System.out.println("Không tìm thấy dự án có mã: " + maDA);
        }
    }

//////////////////////TÌM KIẾM/////////////////////////
    
    public void timKiem(String maDA) {
        for (DuAn da : dsda) {
            if (da.getMaDA().equals(maDA)) {
                System.out.println("Thông tin dự án tìm thấy:");
                da.hienThiThongTin();
                return;
            }
        }
        System.out.println("Không tìm thấy dự án có mã: " + maDA);
    }
    
    public DuAn timKiemDuAn(String maDA) {
        for (DuAn da : dsda) {
            if (da.getMaDA().equals(maDA)) {
                return da;  
            }
        }
        return null;  
    }

    public DuAn timDuAnCuaNhanVien(String maNV) {
    for (DuAn da : dsda) {
        if (da.kiemTraNhanVien(maNV)) {
            return da; // Trả về phòng ban mà nhân viên đang thuộc
        }
    }
    return null; // Không tìm thấy nhân viên trong bất kỳ phòng ban nào
}
//////////////////////THÊM NHÂN VIÊN VÀO D.A/////////////////////////
    
//    public void themNhanVienVaoDuAn(String maDA, NhanVien nv) {
//         boolean found = false;

//        // Duyệt qua danh sách phòng ban để tìm phòng ban với mã trùng khớp
//        for (DuAn da : dsda) {
//            if (da.getMaDA().equals(maDA)) {
//                found = true;
//                da.themNhanVien(nv); 
//                System.out.println("Đã thêm nhân viên " + nv.getTen() + " vào dự án " + da.getTenDA());
//                break;
//            }
//        }
//
//        if (!found) {
//            System.out.println("Không tìm thấy dự án với mã " + maDA);
//        }
//    }

//    // Xóa nhân viên khỏi dự án theo mã dự án
//    public void xoaNhanVienKhoiDuAn(String maDA, String msnv) {
//        for (DuAn da : dsda) {
//            if (da.getMaDA().equals(maDA)) {
//                da.xoaNhanVien(msnv);
//                return;
//            }
//        }
//        System.out.println("Không tìm thấy dự án có mã: " + maDA);
//    }

//    // Hiển thị danh sách nhân viên tham gia của một dự án
//    public void hienThiNhanVienDuAn(String maDA) {
//        for (DuAn da : dsda) {
//            if (da.getMaDA().equals(maDA)) {
//                System.out.println("Danh sách nhân viên tham gia dự án " + maDA + ": " + da.getDanhSachNhanVien());
//                return;
//            }
//        }
//        System.out.println("Không tìm thấy dự án có mã: " + maDA);
//    }

    // Kiểm tra sự tham gia của một nhân viên trong một dự án
    public void kiemTraNhanVienTrongDuAn(String maDA, String msnv) {
        for (DuAn da : dsda) {
            if (da.getMaDA().equals(maDA)) {
                if (da.kiemTraNhanVien(msnv)) {
                    System.out.println("Nhân viên " + msnv + " có tham gia dự án " + maDA);
                } else {
                    System.out.println("Nhân viên " + msnv + " không tham gia dự án " + maDA);
                }
                return;
            }
        }
        System.out.println("Không tìm thấy dự án có mã: " + maDA);
    }
    
//////////////////////VALIDATE/////////////////////////
    
    public boolean validateMaDA(String maDA) {
        return maDA.matches("^DA\\d{3}$");
    }

    // Validate tên dự án
    public boolean validateTenDA(String tenDA) {
        return tenDA.matches("^[a-zA-Z0-9 ]+$");
    }

    // Validate ngày
    public boolean validateNgay(String ngayBatDau, String ngayKetThuc) {
        try {
            // Nếu chỉ có một ngày, kiểm tra định dạng
            if (ngayKetThuc == null || ngayKetThuc.isEmpty()) {
                LocalDate.parse(ngayBatDau, dateFormat);
                return true;
            }

            // Nếu có cả hai ngày, kiểm tra định dạng và logic
            LocalDate batDau = LocalDate.parse(ngayBatDau, dateFormat);
            LocalDate ketThuc = LocalDate.parse(ngayKetThuc, dateFormat);

            return !batDau.isAfter(ketThuc); // Ngày bắt đầu phải trước hoặc bằng ngày kết thúc
        } catch (DateTimeParseException e) {
            return false; // Trả về false nếu định dạng không hợp lệ
        }
    }



    // Kiểm tra mã dự án không bị trùng
    public boolean isMaDATrung(String maDA) {
        for (DuAn existingDA : dsda) {
            if (existingDA.getMaDA().equalsIgnoreCase(maDA)) {
                return true;
            }
        }
        return false;
    }
}
