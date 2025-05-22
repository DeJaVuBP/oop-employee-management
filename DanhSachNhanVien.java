/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLY_NHANSU;

/**
 *
 * @author vubin
 */
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DanhSachNhanVien implements Ichucnang<NhanVien> {

    private List<NhanVien> danhSachNhanVien;  // Danh sách nhân viên
    Scanner scanner = new Scanner(System.in);
    boolean quayLaiMenuChinh = false;

    // Constructor
    public DanhSachNhanVien() {
        this.danhSachNhanVien = new ArrayList<>();

    }

   ///////////// Thêm nhân viên ///////////////
    @Override
    public void them(NhanVien nv) {
        for (NhanVien existingNV : danhSachNhanVien) {

            if (existingNV.getMSNV().equals(nv.getMSNV())) {
                System.out.println("Mã số nhân viên đã tồn tại! Vui lòng nhập mã khác.");
                return;
            }
        }
        danhSachNhanVien.add(nv);
        System.out.println("Thêm nhân viên thành công!");
    }

    ////////////// Xóa nhân viên theo mã số nhân viên ////////////
    @Override
    public void xoa(String ma) {
        NhanVien nvCanXoa = null;
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getMSNV().equals(ma)) {
                nvCanXoa = nv;
                break;
            }
        }
        if (nvCanXoa != null) {
            danhSachNhanVien.remove(nvCanXoa);
            System.out.println("Đã xóa nhân viên có mã số: " + ma);
        } else {
            System.out.println("Không tìm thấy nhân viên với mã số: " + ma);
        }
    }

    /////////////// Sửa thông tin nhân viên theo mã số nhân viên //////////////
    public void sua(String MSNV) {
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getMSNV().equals(MSNV)) {
                System.out.println("Thông tin nhân viên:");
                nv.hienThiThongTin();
                if (nv instanceof TruongPhong) {
                    suaTruongPhong((TruongPhong) nv);
                } else if (nv instanceof FullTime) {
                    suaNhanVienFullTime((FullTime) nv);
                } else if (nv instanceof PartTime) {
                    suaNhanVienPartTime((PartTime) nv);
                } else {
                    System.out.println("Loại nhân viên không xác định.");
                }
                return;
            }
        }
        System.out.println("Không tìm thấy nhân viên với mã số: " + MSNV);
    }

    ////////////////// SỬA THÔNG TIN TRƯỞNG PHÒNG ////////////////// 
    
    private void suaTruongPhong(TruongPhong truongPhong) {
        System.out.println("Chỉnh sửa thông tin Trưởng Phòng:");

        System.out.println("1. MSNV");
        System.out.println("2. Tên");
        System.out.println("3. Email");
        System.out.println("4. Địa chỉ");
        System.out.println("5. Ngày Sinh");
        System.out.println("6. Giới tính");
        System.out.println("7. Số điện thoại");
        System.out.println("8. Lương cơ bản");
        System.out.println("9. Phụ cấp");
        System.out.println("10. Số năm kinh nghiệm");
        System.out.print("Lựa chọn của bạn: ");
        int tpChoice = scanner.nextInt();
        scanner.nextLine();

        switch (tpChoice) {
            case 1:
            	System.out.print("Nhập mã số nhân viên mới: ");
                String newMSNV = scanner.nextLine().trim().toUpperCase();
                ;
                validateMSNV(newMSNV);
                truongPhong.setMSNV(newMSNV);
                System.out.println("Đã cập nhật mã số nhân viên.");
                break;
            case 2:
                System.out.print("Nhập tên mới: ");
                String newTen = scanner.nextLine().trim().toUpperCase();
                validateTen(newTen);
                truongPhong.setTen(newTen);
                System.out.println("Đã cập nhật tên.");
                break;
            case 3:
                System.out.print("Nhập email mới: ");
                String newEmail = scanner.nextLine();
                validateEmail(newEmail);
                truongPhong.setEmail(newEmail);
                System.out.println("Đã cập nhật email.");
                break;
            case 4:
                System.out.print("Nhập địa chỉ mới: ");
                String newDiaChi = scanner.nextLine();
                validateDiaChi(newDiaChi);
                truongPhong.setDiachi(newDiaChi);
                System.out.println("Đã cập nhật địa chỉ.");
                break;
            case 5:
                System.out.print("Nhập ngày sinh mới: ");
                String newNgaySinh = scanner.nextLine();
                validateNgaySinh(newNgaySinh);
                truongPhong.setNgaysinh(newNgaySinh);
                System.out.println("Đã cập nhật ngày sinh.");
                break;
            case 6:
                System.out.print("Nhập giới tính mới: ");
                String newGioiTinh = scanner.nextLine();
                validateGioiTinh(newGioiTinh);
                truongPhong.setGioitinh(newGioiTinh);
                System.out.println("Đã cập nhật giới tính.");
                break;
            case 7:
                System.out.print("Nhập số điện thoại mới: ");
                String newSDT = scanner.nextLine();
                validateSdt(newSDT);
                truongPhong.setSdt(newSDT);
                System.out.println("Đã cập nhật số điện thoại.");
                break;
            case 8:
            	//luong co ban
            case 9:
            	//phu cap
            case 10:
                System.out.print("Nhập số năm kinh nghiệm: ");
                int newKinhNghiem = scanner.nextInt();
                truongPhong.setSoNamKinhNghiem(newKinhNghiem);
                validateSoNamKinhNghiem(newKinhNghiem);
                System.out.println("Đã cập nhật số năm kinh nghiệm.");
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ.");

        }
    }

    //////// Phương thức sửa thông tin cho Nhân viên Full-Time ///////////
    private void suaNhanVienFullTime(FullTime fullTime) {
        System.out.println("Chỉnh sửa thông tin Nhân viên Full-Time:");
        System.out.println("1. MSNV");
        System.out.println("2. Tên");
        System.out.println("3. Email");
        System.out.println("4. Địa chỉ");
        System.out.println("5. Ngày Sinh");
        System.out.println("6. Giới tính");
        System.out.println("7. Số điện thoại");
        System.out.println("8. Lương cơ bản");
        System.out.println("9. Phụ cấp");
        System.out.print("Lựa chọn của bạn: ");
        int ftChoice = scanner.nextInt();
        scanner.nextLine();
        int newLuongCB = 0; // Khai báo ngoài phạm vi switch để có thể sử dụng ở case 2

        switch (ftChoice) {
	        case 1:
	            System.out.print("Nhập mã số nhân viên mới: ");
	            String newMSNV = scanner.nextLine().trim().toUpperCase();
	            validateMSNV(newMSNV);
	            fullTime.setMSNV(newMSNV);
	            System.out.println("Đã cập nhật mã số nhân viên.");
	            break;
        	case 2:
	            System.out.print("Nhập tên mới: ");
	            String newTen = scanner.nextLine().trim().toUpperCase();
	            validateTen(newTen);
	            fullTime.setTen(newTen);
	            System.out.println("Đã cập nhật tên.");
	            break;        
            case 3:
                System.out.print("Nhập email mới: ");
                String newEmail = scanner.nextLine();
                validateEmail(newEmail);
                fullTime.setEmail(newEmail);
                System.out.println("Đã cập nhật email.");
                break;
            case 4:
                System.out.print("Nhập địa chỉ mới: ");
                String newDiaChi = scanner.nextLine();
                validateDiaChi(newDiaChi);
                fullTime.setDiachi(newDiaChi);
                System.out.println("Đã cập nhật địa chỉ.");
                break;
            case 5:
                System.out.print("Nhập ngày sinh mới: ");
                String newNgaySinh = scanner.nextLine();
                validateNgaySinh(newNgaySinh);
                fullTime.setNgaysinh(newNgaySinh);
                System.out.println("Đã cập nhật ngày sinh.");
                break;
            case 6:
                System.out.print("Nhập giới tính mới: ");
                String newGioiTinh = scanner.nextLine();
                validateGioiTinh(newGioiTinh);
                fullTime.setGioitinh(newGioiTinh);
                System.out.println("Đã cập nhật giới tính.");
                break;
            case 7:
                System.out.print("Nhập số điện thoại mới: ");
                String newSDT = scanner.nextLine();
                validateSdt(newSDT);
                fullTime.setSdt(newSDT);
                System.out.println("Đã cập nhật số điện thoại.");
                break;
            case 8:
                System.out.print("Nhập lương cơ bản mới: ");
                newLuongCB = scanner.nextInt();
                validateLuongCoBan(newLuongCB);
                fullTime.setLuongCoBan(newLuongCB);
                System.out.println("Đã cập nhật lương cơ bản.");
                break;
            case 9:
                // Kiểm tra xem newLuongCB đã được nhập ở case 1 chưa
                if (newLuongCB == 0) {
                    System.out.println("Vui lòng nhập lương cơ bản trước khi cập nhật phụ cấp.");
                } else {
                    System.out.print("Nhập các khoản phụ cấp mới: ");
                    int newPhuCap = scanner.nextInt();
                    validatePhuCap(newPhuCap, newLuongCB); // Truyền newLuongCB vào validate
                    fullTime.setPhuCap(newPhuCap);
                    System.out.println("Đã cập nhật các khoản phụ cấp.");
                }
                break;

        }

    }

    ////////////////// SỬA NHÂN VIÊN PART TIME ////////////////
    
    private void suaNhanVienPartTime(PartTime partTime) {
        System.out.println("Chỉnh sửa thông tin Nhân viên Part-Time:");
        System.out.println("1. MSNV");
        System.out.println("2. Tên");
        System.out.println("3. Email");
        System.out.println("4. Địa chỉ");
        System.out.println("5. Ngày Sinh");
        System.out.println("6. Giới tính");
        System.out.println("7. Số điện thoại");
        System.out.println("8. Lương một giờ");
        System.out.print("Lựa chọn của bạn: ");
        int ptChoice = scanner.nextInt();
        scanner.nextLine(); // Đọc dấu Enter còn sót lại

        switch (ptChoice) {

            case 1:
                System.out.print("Nhập mã số nhân viên mới: ");
                String newMSNV = scanner.nextLine().trim().toUpperCase();
                try {
                    validateMSNV(newMSNV); // Kiểm tra mã nhân viên
                    partTime.setMSNV(newMSNV);
                    System.out.println("Đã cập nhật mã số nhân viên.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                System.out.print("Nhập tên mới: ");
                String newTen = scanner.nextLine().trim().toUpperCase();
                try {
                    validateTen(newTen); // Kiểm tra tên
                    partTime.setTen(newTen);
                    System.out.println("Đã cập nhật tên.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                System.out.print("Nhập email mới: ");
                String newEmail = scanner.nextLine();
                try {
                    validateEmail(newEmail); // Kiểm tra email
                    partTime.setEmail(newEmail);
                    System.out.println("Đã cập nhật email.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 4:
                System.out.print("Nhập địa chỉ mới: ");
                String newDiachi = scanner.nextLine();
                try {
                    validateDiaChi(newDiachi); // Kiểm tra địa chỉ
                    partTime.setDiachi(newDiachi);
                    System.out.println("Đã cập nhật địa chỉ.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 5:
                System.out.print("Nhập ngày sinh mới (dd/MM/yyyy): ");
                String newNgaysinh = scanner.nextLine();
                try {
                    validateNgaySinh(newNgaysinh); // Kiểm tra ngày sinh
                    partTime.setNgaysinh(newNgaysinh);
                    System.out.println("Đã cập nhật ngày sinh.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 6:
                System.out.print("Nhập giới tính mới (Nam/Nữ): ");
                String newGioitinh = scanner.nextLine();
                try {
                    validateGioiTinh(newGioitinh); // Kiểm tra giới tính
                    partTime.setGioitinh(newGioitinh);
                    System.out.println("Đã cập nhật giới tính.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 7:
                System.out.print("Nhập số điện thoại mới: ");
                String newSdt = scanner.nextLine();
                try {
                    validateSdt(newSdt); // Kiểm tra số điện thoại
                    partTime.setSdt(newSdt);
                    System.out.println("Đã cập nhật số điện thoại.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 8:

            default:
                System.out.println("Lựa chọn không hợp lệ.");
                break;
        }
    }

    ///////////// Tìm kiếm nhân viên theo mã số nhân viên //////////////
    @Override
    public void timKiem(String ma) {
        for (NhanVien nv : danhSachNhanVien) {
            if (nv.getMSNV().equals(ma)) {
                nv.hienThiThongTin();
                return;
            }
        }
        System.out.print("Không tìm thấy nhân viên mã:" + ma);
    }

    /////////////// Hiển thị tất cả thông tin của nhân viên /////////////
    @Override
    public void hienThi() {
        if (danhSachNhanVien.isEmpty()) {
            System.out.println("Danh sách nhân viên trống!");
        } else {
            for (NhanVien nv : danhSachNhanVien) {
                System.out.println(nv.toString());
            }
        }
    }

    ///////////// TÌM NHÂN VIÊN /////////////
    
    public NhanVien timNhanVien(String maNhanVien) {
        // Duyệt qua danh sách nhân viên để tìm nhân viên với mã số tương ứng
        for (NhanVien nv : this.danhSachNhanVien) {
            if (nv.getMSNV().equals(maNhanVien)) {
                return nv; // Nếu tìm thấy nhân viên có mã tương ứng
            }
        }
        return null; // Nếu không tìm thấy nhân viên
    }

    // Phương thức để lấy danh sách nhân viên
    public List<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    ////////////// VALIDATE //////////////
    
    private static void validateMSNV(String MSNV) {
        if (MSNV.isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống.");
        }
    }

    private static void validateTen(String ten) {
        if (ten.isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống.");
        }
    }

    private static void validateEmail(String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email phải chứa ký tự '@'.");
        }
    }

    private static void validateDiaChi(String diachi) {
        if (diachi.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống.");
        }
    }

    private static void validateNgaySinh(String ngaysinh) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsedDate = LocalDate.parse(ngaysinh, formatter);
            int age = Period.between(parsedDate, LocalDate.now()).getYears();
            if (age < 18) {
                throw new IllegalArgumentException("Nhân viên phải đủ 18 tuổi.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Ngày sinh không hợp lệ. Định dạng phải là dd/MM/yyyy.");
        }
    }

    private static void validateLuongCoBan(double luongCoBan) {
        if (luongCoBan <= 0) {
            throw new IllegalArgumentException("Lương cơ bản phải lớn hơn 0.");
        }
    }

    private static void validatePhuCap(double phucap, double luongCoBan) {
        if (phucap < 0) {
            throw new IllegalArgumentException("phụ cấp không thể âm");
        } else if (phucap > luongCoBan) {
            throw new IllegalArgumentException("phải nhỏ hơn lương cơ bản");
        }
    }

    private static void validateGioiTinh(String gioitinh) {
        if (!gioitinh.equalsIgnoreCase("Nam") && !gioitinh.equalsIgnoreCase("Nữ")) {
            throw new IllegalArgumentException("Giới tính không hợp lệ. Phải là 'Nam' hoặc 'Nữ'.");
        }
    }

    public static boolean validateSoGioLamViec(double newGioLam) {
        if (newGioLam < 0) {
            System.out.println("Số giờ làm việc phải là số nguyên dương. Vui lòng nhập lại.");
            return false; // Trả về false nếu không hợp lệ
        }
        return true; // Trả về true nếu hợp lệ
    }

    private static boolean validatePhuCap(double phuCap) {
        if (phuCap < 0) {
            System.out.println("Phụ cấp phải là một số dương. Vui lòng nhập lại.");
            return false; // Trả về false nếu không hợp lệ
        }
        return true; // Trả về true nếu hợp lệ
    }

    private static boolean validateSoNamKinhNghiem(int soNamKinhNghiem) {
        if (soNamKinhNghiem < 0) {
            System.out.println("Số năm kinh nghiệm phải là một số nguyên dương. Vui lòng nhập lại.");
            return false; // Trả về false nếu không hợp lệ
        }
        return true; // Trả về true nếu hợp lệ
    }

    private static void validateSdt(String sdt) {
        if (!sdt.matches("0\\d{9,10}")) {
            throw new IllegalArgumentException("SĐT không đúng định dạng.");
        }
    }

}
