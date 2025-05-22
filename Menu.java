/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLY_NHANSU;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author vubin
 */
public class Menu {

    public static void main(String[] args) {
        DanhSachNhanVien danhSach = new DanhSachNhanVien();
        DocGhiFile nhapTuFile = new DocGhiFile();
        DanhSachDuAn dsda = new DanhSachDuAn(); // Sử dụng đúng tên lớp
        DanhSachPhongBan dspb = new DanhSachPhongBan();
        DanhSachTinhLuong dstl = new DanhSachTinhLuong();
        Scanner scanner = new Scanner(System.in);
        int luaChon;
        boolean quayLaiMenuChinh = false; // Cờ kiểm tra xem có quay lại menu chính không

        do {
            System.out.println("----- MENU -----");
            System.out.println("1. Quản lý Nhân Viên");
            System.out.println("2. Quản lý Phòng Ban");
            System.out.println("3. Quản lý Dự Án");
            System.out.println("4. Quản lý Lương");
            System.out.println("0. Quay Lại");
            System.out.println("----- ----- -----");
            System.out.print("Chọn chức năng: ");
            luaChon = scanner.nextInt();
            scanner.nextLine();

            switch (luaChon) {
                case 1:
                    int luaChonNhanVien;
                    do {
                        System.out.println("----- MENU QUẢN LÝ NHÂN VIÊN -----");
                        System.out.println("1. Thêm nhân viên");
                        System.out.println("2. Xóa nhân viên");
                        System.out.println("3. Sửa nhân viên");
                        System.out.println("4. Tìm kiếm nhân viên");
                        System.out.println("5. Hiển thị danh sách nhân viên");
                        System.out.println("6. Đọc danh sách nhân viên từ file");
                        System.out.println("7. Ghi danh sách nhân viên vào file");
                        System.out.println("0. Thoát");
                        System.out.println("----- ----- ----- ----- ----- -----");
                        System.out.print("Chọn chức năng: ");
                        luaChonNhanVien = scanner.nextInt();
                        scanner.nextLine();
                        switch (luaChonNhanVien) {
                            case 1:
                                int loaiNV;
                                do {
                                    System.out.println("----- ----- -----");
                                    System.out.println("Chọn loại nhân viên:");
                                    System.out.println("1. Trưởng phòng");
                                    System.out.println("2. FullTime");
                                    System.out.println("3. PartTime");
                                    System.out.println("0. Quay Lại");
                                    System.out.println("----- ----- -----");
                                    System.out.print("Chọn chức năng: ");
                                    loaiNV = scanner.nextInt();
                                    scanner.nextLine();
                                    if (loaiNV < 1 || loaiNV > 3) {
                                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                                    }
                                } while (loaiNV < 1 || loaiNV > 3);

                                String MSNV = "",
                                 ten = "",
                                 email = "",
                                 diachi = "",
                                 ngaysinh = "",
                                 gioitinh = "",
                                 sdt = "";

                                while (true) {
                                    try {
                                        System.out.print("Nhập mã nhân viên: ");
                                        MSNV = scanner.nextLine().trim().toUpperCase();
                                        validateMSNV(MSNV);
                                        break;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Lỗi: " + e.getMessage());
                                    }
                                }

                                while (true) {
                                    try {
                                        System.out.print("Nhập tên: ");
                                        ten = scanner.nextLine().trim().toUpperCase();
                                        validateTen(ten);
                                        break;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Lỗi: " + e.getMessage());
                                    }
                                }

                                while (true) {
                                    try {
                                        System.out.print("Nhập email: ");
                                        email = scanner.nextLine();
                                        validateEmail(email);
                                        break;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Lỗi: " + e.getMessage());
                                    }
                                }

                                while (true) {
                                    try {
                                        System.out.print("Nhập địa chỉ: ");
                                        diachi = scanner.nextLine();
                                        validateDiaChi(diachi);
                                        break;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Lỗi: " + e.getMessage());
                                    }
                                }

                                while (true) {
                                    try {
                                        System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
                                        ngaysinh = scanner.nextLine();
                                        validateNgaySinh(ngaysinh);
                                        break;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Lỗi: " + e.getMessage());
                                    }
                                }

                                while (true) {
                                    try {
                                        System.out.print("Nhập giới tính (Nam/Nữ): ");
                                        gioitinh = scanner.nextLine();
                                        validateGioiTinh(gioitinh);
                                        break;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Lỗi: " + e.getMessage());
                                    }
                                }

                                while (true) {
                                    try {
                                        System.out.print("Nhập SĐT: ");
                                        sdt = scanner.nextLine();
                                        validateSdt(sdt);
                                        break;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Lỗi: " + e.getMessage());
                                    }
                                }
                                if (quayLaiMenuChinh) {
                                    break;
                                }
                                switch (loaiNV) {
                                    case 1:
                                        int luongCoBan = 0;
                                        int phuCap = 0;
                                        int soNamKinhNghiem;
                                        while (true) {
                                            try {
                                                System.out.print("Nhập Lương cơ bản: ");
                                                luongCoBan = scanner.nextInt();
                                                System.out.print("Nhập phụ cấp: ");
                                                phuCap = scanner.nextInt();
                                                validatePhuCap(phuCap);
                                                System.out.print("Nhập số năm kinh nghiệm: ");
                                                soNamKinhNghiem = scanner.nextInt();
                                                validateSoNamKinhNghiem(soNamKinhNghiem);
                                                scanner.nextLine();

                                                break;
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Lỗi: " + e.getMessage());
                                            }
                                        }
                                        TruongPhong truongPhong = new TruongPhong(MSNV, ten, email, diachi, ngaysinh, gioitinh, sdt,
                                                luongCoBan, phuCap, soNamKinhNghiem);
                                        danhSach.them(truongPhong);
                                        break;

                                    case 2: // Fulltime
                                        while (true) {
                                            try {
                                                System.out.print("Nhập lương cơ bản: ");
                                                luongCoBan = scanner.nextInt();
                                                validateLuongCoBan(luongCoBan);
                                                break;
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Lỗi: " + e.getMessage());
                                            }
                                        }

                                        while (true) {
                                            try {
                                                System.out.print("Phụ cấp: ");
                                                phuCap = scanner.nextInt();
                                                validatePhuCap(phuCap, luongCoBan);
                                                break;
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Lỗi: " + e.getMessage());
                                            }
                                        }

                                        FullTime fullTime = new FullTime(MSNV, ten, email, diachi, ngaysinh, gioitinh,
                                                sdt, luongCoBan, phuCap);
                                        danhSach.them(fullTime);
                                        break;

                                    case 3: // Parttime
                                        double luongTheoGio = 0;
                                        while (true) {

                                            try {
                                                System.out.print("Nhập lương theo giờ: ");
                                                luongTheoGio = scanner.nextDouble();
                                                validateSoLuongTheoGio(luongTheoGio);
                                                scanner.nextLine();
                                                PartTime partTime = new PartTime(MSNV, ten, email, diachi,
                                                        ngaysinh, gioitinh, sdt, luongTheoGio);

                                                danhSach.them(partTime);
                                                break;
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("Lỗi: " + e.getMessage());
                                            }
                                        }

                                    case 0:
                                        System.out.println("Thoát chương trình.");
                                        break;

                                    default:
                                        System.out.println("Lựa chọn không hợp lệ.");
                                        break;

                                }
                                break;
                            case 2:
                                System.out.println("Xóa nhân viên: ");
                                MSNV = scanner.nextLine();
                                danhSach.xoa(MSNV);
                                break;
                            case 3:
                                System.out.println("Nhập mã nhân viên:");
                                MSNV = scanner.nextLine().trim().toUpperCase();
                                danhSach.sua(MSNV);
                                break;
                            case 4:
                                System.out.println("Nhập mã nhân viên:");
                                MSNV = scanner.nextLine().trim().toUpperCase();
                                danhSach.timKiem(MSNV);
                                break;

                            case 5:
                                danhSach.hienThi();
                                break;

                            case 6:
                                System.out.print("Nhập tên file để đọc danh sách: ");
                                String tenFileDoc = scanner.nextLine();
                                if (tenFileDoc.trim().isEmpty()) {
                                    System.out.println("Tên file không được để trống.");
                                } else {
                                    // Đọc danh sách phòng ban từ file và thêm vào DanhSachPB
                                    List<NhanVien> nhanvien = nhapTuFile.docDanhSachNhanVienTuFile(tenFileDoc);
                                for (NhanVien nv : nhanvien) {
                                    danhSach.them(nv);
                                }
                                    System.out.println("Đã nhập danh sách phòng ban từ file.");
                                }
//                               
                                break;
                            case 7:
                                System.out.print("Nhập tên file để xuất danh sách: ");
                                String tenFile = scanner.nextLine();
                                nhapTuFile.xuatDanhSachNhanVienRaFile(tenFile, danhSach.getDanhSachNhanVien());
                                System.out.println("Đã lưu danh sách nhân viên vào file.");
                                break;

                            case 0:
                                System.out.println("Thoát chương trình.");
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                                break;
                        }
                        break;
                    } while (luaChon != 0);
                    break;
                case 2:
                    int luaChonPhongBan;

                    do {
                        System.out.println("----- MENU QUẢN LÝ PHÒNG BAN -----");
                        System.out.println("1. Thêm phòng ban");
                        System.out.println("2. Xóa phòng ban");
                        System.out.println("3. Sửa thông tin phòng ban");
                        System.out.println("4. Tìm kiếm phòng ban");
                        System.out.println("5. Hiển thị danh sách phòng ban");
                        System.out.println("6. Đọc danh sách phòng ban từ file");
                        System.out.println("7. Ghi danh sách phòng ban vào file");
                        System.out.println("8. Thêm nhân viên vào phòng ban");
                        System.out.println("9. Xóa nhân viên vào phòng ban");
                        System.out.println("10. Hiển thị nhân viên của phòng ban");
                        System.out.println("0. Quay lại");
                        System.out.println("----- ----- ----- ----- ----- -----");
                        System.out.print("Chọn chức năng: ");

                        // Kiểm tra hợp lệ cho lựa chọn menu
                        while (!scanner.hasNextInt()) {
                            System.out.println("Vui lòng chọn một trong các chức năng trên.");
                            scanner.next(); // Xóa đầu vào không hợp lệ
                        }
                        luaChonPhongBan = scanner.nextInt();
                        scanner.nextLine(); // Đọc bỏ dòng còn lại

                        switch (luaChonPhongBan) {

                            case 1:
                                dspb.themPhongBan(); // Thêm phòng ban
                                break;
                            case 2:
                                System.out.print("Nhập mã phòng ban cần xóa: ");
                                String maXoa = scanner.nextLine().trim().toUpperCase();
                                ;
                                dspb.xoa(maXoa); // Xóa phòng ban
                                break;
                            case 3:
                                System.out.print("Nhập mã phòng ban cần sửa: ");
                                String maSua = scanner.nextLine().trim().toUpperCase();
                                dspb.suaPhongBan(maSua); // Sửa thông tin phòng ban
                                break;
                            case 4:
                                System.out.print("Nhập mã phòng ban cần tìm: ");
                                String maTim = scanner.nextLine().trim().toUpperCase();
                                dspb.timKiem(maTim); // Tìm kiếm phòng ban
                                break;
                            case 5:
                                dspb.hienThi(); // Hiển thị danh sách phòng ban
                                break;
                            case 6:
                                System.out.print("Nhập tên file để đọc danh sách: ");
                                String tenFileDoc = scanner.nextLine();
                                if (tenFileDoc.trim().isEmpty()) {
                                    System.out.println("Tên file không được để trống.");
                                } else {
                                    // Đọc danh sách phòng ban từ file và thêm vào DanhSachPB
                                    List<PhongBan> danhSachPhongBan = nhapTuFile.nhapDanhSachPhongBanTuFile(tenFileDoc);
                                    for (PhongBan pb : danhSachPhongBan) {
                                        dspb.them(pb);
                                    }
                                    System.out.println("Đã nhập danh sách phòng ban từ file.");
                                }
                                break;
                            case 7:
                                System.out.print("Nhập tên file để lưu danh sách: ");
                                String tenFileLuu = scanner.nextLine();
                                if (tenFileLuu.trim().isEmpty()) {
                                    System.out.println("Tên file không được để trống.");
                                } else {
                                    nhapTuFile.xuatDanhSachPhongBanRaFile(tenFileLuu, dspb.getDanhSachPhongBan());
                                    System.out.println("Đã lưu danh sách phòng ban vào file.");
                                }
                                break;
                            case 8:
                                System.out.print("Nhập mã nhân viên cần thêm: ");
                                String maNV = scanner.nextLine().trim().toUpperCase();
                                NhanVien nhanVienThem = danhSach.timNhanVien(maNV); // Tìm nhân viên trong các phòng ban

                                if (nhanVienThem != null) {
                                    System.out.print("Nhập mã phòng ban để thêm nhân viên vào: ");
                                    String maPB = scanner.nextLine().trim().toUpperCase();
                                    PhongBan phongBanThem = dspb.timKiemPhongBan(maPB);

                                    if (phongBanThem != null) {
                                        phongBanThem.themNhanVien(nhanVienThem);
                                        System.out.println("Đã thêm nhân viên " + nhanVienThem.getTen() + " vào phòng ban " + phongBanThem.getTenPB());
                                    } else {
                                        System.out.println("Phòng ban không tồn tại.");
                                    }
                                } else {
                                    System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
                                }
                                break;

                            case 9:

                            case 10:
                                System.out.print("Nhập mã phòng ban để xem danh sách nhân viên: ");
                                String maPBHienThi = scanner.nextLine().trim().toUpperCase();
                                PhongBan phongBanHienThi = dspb.timKiemPhongBan(maPBHienThi);

                                if (phongBanHienThi != null) {
                                    phongBanHienThi.hienThiDanhSachNhanVien();
                                } else {
                                    System.out.println("Không tìm thấy phòng ban với mã: " + maPBHienThi);
                                }
                                break;

                        }
                    } while (luaChonPhongBan != 0);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
                case 3:
                    int luaChonDA;
                    do {
                        System.out.println("----- MENU QUẢN LÝ DỰ ÁN -----");
                        System.out.println("1. Thêm dự án");
                        System.out.println("2. Xóa dự án");
                        System.out.println("3. Sửa thông tin dự án");
                        System.out.println("4. Tìm kiếm dự án");
                        System.out.println("5. Hiển thị danh sách dự án");
                        System.out.println("6. Đọc danh sách dự án từ file");
                        System.out.println("7. Ghi danh sách dự án vào file");
                        System.out.println("8. Thêm nhân viên vào dự án");
                        System.out.println("9. Xóa nhân viên vào dự án");
                        System.out.println("10. Hiển thị nhân viên của dự án");
                        System.out.println("0. Thoát");
                        System.out.println("----- ----- ----- ----- ------");
                        System.out.print("Chọn chức năng: ");

                        // Kiểm tra hợp lệ cho lựa chọn menu
                        while (!scanner.hasNextInt()) {
                            System.out.println("Vui lòng chọn một trong các chức năng trên.");
                            scanner.next(); // Xóa đầu vào không hợp lệ
                        }
                        luaChonDA = scanner.nextInt();
                        scanner.nextLine(); // Đọc bỏ dòng còn lại

                        switch (luaChonDA) {
                            case 1:
                                dsda.themDuAn(); // Thêm dự án
                                break;
                            case 2:
                                System.out.print("Nhập mã dự án cần xóa: ");
                                String maXoa = scanner.nextLine().trim().toUpperCase();
                                dsda.xoa(maXoa); // Xóa dự án
                                break;
                            case 3:
                                System.out.print("Nhập mã dự án cần sửa: ");
                                String maSua = scanner.nextLine().trim().toUpperCase();
                                dsda.suaDuAn(maSua); // Sửa thông tin dự án
                                break;

                            case 4:
                                System.out.print("Nhập mã dự án cần tìm: ");
                                String maTim = scanner.nextLine().trim().toUpperCase();
                                dsda.timKiem(maTim); // Tìm kiếm dự án
                                break;
                            case 5:
                                dsda.hienThi(); // Xuất danh sách dự án
                                break;
                            case 6:
                                System.out.print("Nhập tên file để đọc danh sách: ");
                                String tenFileDoc = scanner.nextLine();
                                if (tenFileDoc.trim().isEmpty()) {
                                    System.out.println("Tên file không được để trống.");
                                } else {
                                    List<DuAn> danhSachDuAn = nhapTuFile.nhapDanhSachDuAnTuFile(tenFileDoc);
                                    for (DuAn da : danhSachDuAn) {
                                        dsda.them(da);
                                    }
                                    System.out.println("Đã nhập danh sách dự án từ file.");
                                }
                                break;
                            case 7:
                                System.out.print("Nhập tên file để lưu danh sách: ");
                                String tenFileLuu = scanner.nextLine();
                                if (tenFileLuu.trim().isEmpty()) {
                                    System.out.println("Tên file không được để trống.");
                                } else {
                                    nhapTuFile.xuatDanhSachDuAnRaFile(tenFileLuu, dsda.getDanhSachDuAn());
                                    System.out.println("Đã lưu danh sách dự án vào file.");
                                }
                                break;

                            case 8:
                                System.out.print("Nhập mã nhân viên cần thêm: ");
                                String maNV = scanner.nextLine().trim().toUpperCase();
                                NhanVien nhanVienThem = danhSach.timNhanVien(maNV); // Tìm nhân viên trong các phòng ban

                                if (nhanVienThem != null) {
                                    System.out.print("Nhập mã dự án để thêm nhân viên vào: ");
                                    String maDA = scanner.nextLine().trim().toUpperCase();
                                    DuAn duAnThem = dsda.timKiemDuAn(maDA);

                                    if (duAnThem != null) {
                                        duAnThem.themNhanVien(nhanVienThem);
                                        System.out.println("Đã thêm nhân viên " + nhanVienThem.getTen() + " vào dự án " + duAnThem.getTenDA());
                                    } else {
                                        System.out.println("Dự án không tồn tại.");
                                    }
                                } else {
                                    System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
                                }
                                break;

                            case 9:

                            case 10:
                                System.out.print("Nhập mã dự án để xem danh sách nhân viên: ");
                                String maDAHienThi = scanner.nextLine().trim().toUpperCase();
                                DuAn duAnHienThi = dsda.timKiemDuAn(maDAHienThi);

                                if (duAnHienThi != null) {
                                    duAnHienThi.hienThiDanhSachNhanVien();
                                } else {
                                    System.out.println("Không tìm thấy phòng ban với mã: " + maDAHienThi);
                                }
                                break;
                            case 0:
                                System.out.println("Thoát chương trình.");
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                                break;
                        }

                    } while (luaChonDA != 0);
                    break;

                case 4:
                    int luaChonTL;
                    do {
                        System.out.println("----- MENU QUẢN LÝ lƯƠNG -----");
                        System.out.println("1. Thêm bảng lương");
                        System.out.println("2. Xóa bảng lương");
                        System.out.println("3. Sửa thông tin dự án");
                        System.out.println("4. Tìm kiếm bảng lương");
                        System.out.println("5. Hiển thị danh sách bảng ");
//                        System.out.println("6. Đọc danh sách dự án từ file");
//                        System.out.println("7. Ghi danh sách dự án vào file");
//                        System.out.println("8. Thêm nhân viên vào dự án");
//                        System.out.println("9. Hiển thị nhân viên của dự án");
                        System.out.println("0. Thoát");
                        System.out.println("----- ----- ----- ----- ------");
                        System.out.print("Chọn chức năng: ");

                        // Kiểm tra hợp lệ cho lựa chọn menu
                        while (!scanner.hasNextInt()) {
                            System.out.println("Vui lòng chọn một trong các chức năng trên.");
                            scanner.next(); // Xóa đầu vào không hợp lệ
                        }
                        luaChonTL = scanner.nextInt();
                        scanner.nextLine(); // Đọc bỏ dòng còn lại

                        switch (luaChonTL) {
                            case 1:
                                System.out.println("Thêm bảng lương:");

                                // Hiển thị danh sách nhân viên để người dùng chọn
                                System.out.println("Danh sách nhân viên hiện có:");
                                danhSach.hienThi(); // Giả sử bạn có phương thức này trong danh sách nhân viên

                                System.out.print("Nhập mã nhân viên cần thêm bảng lương: ");
                                String maNhanVien = scanner.nextLine().trim().toUpperCase();

                                // Tìm nhân viên trong danh sách
                                NhanVien nhanVien = danhSach.timNhanVien(maNhanVien); // Phương thức tìm kiếm theo mã
                                if (nhanVien == null) {
                                    System.out.println("Không tìm thấy nhân viên với mã: " + maNhanVien);
                                    break;
                                }

                                System.out.print("Nhập tháng: ");
                                int thang = scanner.nextInt();
                                scanner.nextLine(); // Đọc bỏ dòng còn lại

                                // Kiểm tra loại nhân viên và nhập thông tin phù hợp
                                if (nhanVien instanceof FullTime) {
                                    System.out.print("Nhập số ngày làm: ");
                                    int soNgayLam = scanner.nextInt();
                                    scanner.nextLine(); // Đọc bỏ dòng còn lại
                                    TinhLuong tinhLuong = new TinhLuong(thang, nhanVien, soNgayLam);
                                    dstl.them(tinhLuong);
                                } else if (nhanVien instanceof PartTime) {
                                    System.out.print("Nhập số giờ làm: ");
                                    int soGioLam = scanner.nextInt();
                                    scanner.nextLine(); // Đọc bỏ dòng còn lại
                                    TinhLuong tinhLuong = new TinhLuong(thang, nhanVien, soGioLam, true);
                                    dstl.them(tinhLuong);
                                } else if (nhanVien instanceof TruongPhong) {
                                    System.out.print("Nhập số ngày làm: ");
                                    int soNgayLam = scanner.nextInt();
                                    System.out.print("Nhập hệ số lương: ");
                                    double heSoLuong = scanner.nextDouble();
                                    scanner.nextLine(); // Đọc bỏ dòng còn lại
                                    TinhLuong tinhLuong = new TinhLuong(thang, nhanVien, soNgayLam, heSoLuong);
                                    dstl.them(tinhLuong);
                                }

                                System.out.println("Thêm bảng lương thành công!");
                                break;

                            case 2:

                            case 3:

                            case 4:

                            case 5:
                                dstl.hienThi();
                                break;

                            case 0:
                                System.out.println("Thoát chương trình.");
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                                break;
                        }

                    } while (luaChonTL != 0);

            }

        } while (luaChon != 0);

        scanner.close();
    }

    public boolean validateNhanVienTrongPhongBan(PhongBan phongBan, NhanVien nhanVien) {
        if (phongBan.getDanhSachNhanVien().contains(nhanVien)) {
            System.out.println("Nhân viên " + nhanVien.getTen() + " đã tồn tại trong phòng ban " + phongBan.getTenPB() + ".");
            return true;
        }
        return false;
    }

    // Phương thức tìm kiếm nhân viên theo mã trong tất cả các phòng ban
    public static NhanVien timNhanVien(String maNV, DanhSachNhanVien dsnv) {
        // Duyệt qua tất cả các nhân viên trong danh sách
        for (NhanVien nv : dsnv.getDanhSachNhanVien()) {
            // Kiểm tra nếu mã nhân viên trùng với mã nhập vào
            if (nv.getMSNV().equalsIgnoreCase(maNV)) {
                return nv; // Nếu tìm thấy nhân viên, trả về đối tượng nhân viên
            }
        }
        return null; // Trả về null nếu không tìm thấy nhân viên
    }

    private static void validateMSNV(String MSNV) {
        if (MSNV.isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống.");
        }
        if (MSNV.length() > 5) {
            throw new IllegalArgumentException("Mã nhân viên không được vượt quá 4 ký tự.");
        }
    }

    private static void validateTen(String ten) {
        if (ten.isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống.");
        }

        // Chia tên thành các từ bằng cách sử dụng dấu cách làm phân tách
        String[] words = ten.trim().split("\\s+");

        // Kiểm tra số lượng từ trong tên
        if (words.length < 2 || words.length > 4) {
            throw new IllegalArgumentException("Tên phải có ít nhất 2 chữ và tối đa 4 chữ.");
        }
    }

    private static void validateEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email phải chứa ký tự '@'.");
        }
//        if (email.length() < 10) {
//            throw new IllegalArgumentException("Email phải có ít nhất 10 ký tự.");
//        }
    }

    private static void validateDiaChi(String diachi) {
        if (diachi.isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống.");
        }

//        // Kiểm tra có chữ và số
//        if (!diachi.matches(".*[a-zA-Z].*") || !diachi.matches(".*\\d.*")) {
//            throw new IllegalArgumentException("Địa chỉ phải chứa cả chữ và số.");
//        }
//
//        // Kiểm tra số chữ số trong địa chỉ
//        int countDigits = 0;
//        for (char c : diachi.toCharArray()) {
//            if (Character.isDigit(c)) {
//                countDigits++;
//            }
//        }
//
//        // Kiểm tra số lượng chữ số có từ 3 đến 5
//        if (countDigits < 3 || countDigits > 5) {
//            throw new IllegalArgumentException("Địa chỉ phải có ít nhất 3 chữ số và tối đa 5 chữ số.");
//        }
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

    private static void validateTenPB(String TenPB) {
        if (TenPB.isEmpty()) {
            throw new IllegalArgumentException("Tên phòng ban không được để trống.");
        }
        if (TenPB.length() < 3) {
            throw new IllegalArgumentException("Tên phòng ban phải chứa ít nhất 3 ký tự.");
        }
    }

    private static void validateMAPB(String MAPB) {
        if (MAPB.isEmpty()) {
            throw new IllegalArgumentException("Mã phòng ban không được để trống.");
        }
        if (MAPB.length() < 2 || MAPB.length() > 4) {
            throw new IllegalArgumentException("Mã phòng ban phải có độ dài từ 2 đến 5 ký tự.");
        }
        if (!MAPB.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Mã phòng ban chỉ được chứa chữ cái và số.");
        }
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

    public static boolean validateSoLuongTheoGio(double luongTheoGio) {
        if (luongTheoGio < 0) {
            System.out.println("Số lương theo giờ phải là số nguyên dương. Vui lòng nhập lại.");
            return false; // Trả về false nếu không hợp lệ
        }
        return true; // Trả về true nếu hợp lệ
    }

    private static void validateSdt(String sdt) {
        if (!sdt.matches("0\\d{9,10}")) {
            throw new IllegalArgumentException("SĐT không đúng định dạng.");
        }
    }

    public static String chuyenThanhInHoa(String str) {
        if (str == null) {
            return null; // Trả về null nếu chuỗi đầu vào là null
        }
        return str.toUpperCase(); // Sử dụng phương thức toUpperCase() để chuyển đổi
    }
}
