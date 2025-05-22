/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLY_NHANSU;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vubin
 */
public class DocGhiFile {

    public List<NhanVien> docDanhSachNhanVienTuFile(String fileName) {
        List<NhanVien> danhSach = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Dòng đọc được: " + line); // Debugging line
                String[] data = line.split(",");

                // Kiểm tra số lượng trường quan trọng
                if (data.length < 8) { // Ít nhất 10 trường là cần thiết (MSNV, tên, email, ...)
                    System.out.println("Dữ liệu không đầy đủ: " + line);
                    continue; // Bỏ qua dòng nếu không đủ dữ liệu
                }

                String MSNV = data[0].trim();
                String ten = data[1].trim();
                String email = data[2].trim();
                String diachi = data[3].trim();
                String ngaysinh = data[4].trim();
                String gioitinh = data[5].trim();
                String sdt = data[6].trim();
                String loaiNV = data[7].trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateOfBirth = LocalDate.parse(ngaysinh, formatter);

                // Xử lý các loại nhân viên khác nhau
                if (loaiNV.equalsIgnoreCase("Fulltime")) {
                    if (data.length >= 10) { // Kiểm tra đủ 12 trường cho Fulltime
                        double luongCoBan = Integer.parseInt(data[8].trim());
                        double phuCap = Integer.parseInt(data[9].trim());

                        FullTime nvFulltime = new FullTime(MSNV, ten, email, diachi, ngaysinh, gioitinh,
                                sdt, luongCoBan, phuCap);
                        danhSach.add(nvFulltime);
                        System.out.println("Thêm nhân viên FullTime thành công: " + MSNV);
                    } else {
                        System.out.println("Dữ liệu không đầy đủ cho nhân viên Fulltime: " + line);
                    }
                } else if (loaiNV.equalsIgnoreCase("PartTime")) {
                    if (data.length >= 9) {
                        double luongtheogio = Double.parseDouble(data[8].trim());
                        PartTime nvParttime = new PartTime(MSNV, ten, email, diachi,
                                ngaysinh, gioitinh, sdt, luongtheogio);
                        danhSach.add(nvParttime);
                        System.out.println("Thêm nhân viên Parttime thành công: " + MSNV);
                    } else {
                        System.out.println("Dữ liệu không đầy đủ cho nhân viên PartTime: " + line);
                    }
                } else if (loaiNV.equalsIgnoreCase("TruongPhong")) {
                    if (data.length >= 11) {
                        double luongCoBan = Integer.parseInt(data[8].trim());
                        double phuCap = Integer.parseInt(data[9].trim());

                        int soNamKinhNghiem = Integer.parseInt(data[10].trim());
                        TruongPhong truongPhong = new TruongPhong(MSNV, ten, email, diachi, ngaysinh, gioitinh, sdt,
                                luongCoBan, phuCap, soNamKinhNghiem);
                        danhSach.add(truongPhong);
                        System.out.println("Thêm nhân viên TruongPhong thành công: " + MSNV);
                    } else {
                        System.out.println("Dữ liệu không đầy đủ cho nhân viên TruongPhong: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return danhSach;
    }

    public void xuatDanhSachNhanVienRaFile(String tenFile, List<NhanVien> dsnv) {
        if (tenFile == null || tenFile.trim().isEmpty()) {
            System.out.println("Tên file không hợp lệ.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (NhanVien nv : dsnv) {
                writer.write(nv.hienthichitiet());
                writer.newLine();
            }
            System.out.println("Xuất danh sách nhân viên ra file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }

    }

    // **************************** DANH SÁCH PHÒNG BAN ************************************
//    public List<PhongBan> docDanhSachPhongBanTuFile(String tenFile) {
//    List<PhongBan> danhSachPhongBan = new ArrayList<>();
//    if (tenFile == null || tenFile.trim().isEmpty()) {
//        System.out.println("Tên file không hợp lệ.");
//        return danhSachPhongBan;
//    }
//
//    try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
//        String line;
//        PhongBan phongBan = null;
//        while ((line = br.readLine()) != null) {
//            line = line.trim();  // Loại bỏ khoảng trắng thừa ở đầu và cuối dòng
//            if (line.startsWith("Phòng ban:")) {  // Dòng bắt đầu với "Phòng ban:" nghĩa là bắt đầu đọc một phòng ban mới
//                String maPhongTenPhong = line.replace("Phòng ban: ", "").trim();
//                String[] parts = maPhongTenPhong.split(","); // Tách mã phòng và tên phòng
//                if (parts.length >= 2) {
//                    String maPhong = parts[0].trim();
//                    String tenPhong = parts[1].trim();
//                    phongBan = new PhongBan(maPhong, tenPhong);  // Tạo đối tượng PhongBan với mã phòng và tên phòng
//                }
//            } else if (line.startsWith("Danh sách nhân viên:") && phongBan != null) {
//                // Bỏ qua dòng "Danh sách nhân viên:"
//                continue;
//            } else if (!line.isEmpty() && phongBan != null) {
//                // Đọc thông tin nhân viên và thêm vào danh sách nhân viên của phòng ban
//                String thongTinNhanVien = line.trim();
//                // Giả sử thông tin nhân viên là một chuỗi, bạn có thể tạo đối tượng NhanVien từ đó
//                NhanVien nhanVien = new NhanVien(thongTinNhanVien);  // Ví dụ tạo nhân viên từ chuỗi
//                phongBan.addNhanVien(nhanVien);  // Thêm nhân viên vào danh sách nhân viên của phòng ban
//            } else if (phongBan != null) {
//                // Sau khi hoàn thành một phòng ban, thêm phòng ban vào danh sách
//                danhSachPhongBan.add(phongBan);
//                phongBan = null;  // Đặt lại phongBan để xử lý phòng ban tiếp theo
//            }
//        }
//        // Kiểm tra xem phòng ban cuối cùng có bị thiếu không
//        if (phongBan != null) {
//            danhSachPhongBan.add(phongBan);
//        }
//        System.out.println("Đã đọc thành công " + danhSachPhongBan.size() + " phòng ban từ file.");
//    } catch (IOException e) {
//        System.out.println("Lỗi khi đọc file: " + e.getMessage());
//    }
//
//    return danhSachPhongBan;
//}
    public List<PhongBan> nhapDanhSachPhongBanTuFile(String tenFile) {
        List<PhongBan> danhSachPhongBan = new ArrayList<>();
        if (tenFile == null || tenFile.trim().isEmpty()) {
            System.out.println("Tên file không hợp lệ.");
            return danhSachPhongBan;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String maPhongPart = parts[0].trim();
                    String tenPhongPart = parts[1].trim();

                    String maPhong = maPhongPart.replace("Mã:", "").trim();
                    // Lấy tên phòng từ chuỗi "Tên: KE TOAN"
                    String tenPhong = tenPhongPart.replace("Tên:", "").trim();

                    danhSachPhongBan.add(new PhongBan(maPhong, tenPhong));

                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }

        return danhSachPhongBan;
    }

    public void xuatDanhSachPhongBanRaFile(String tenFile, List<PhongBan> dspb) {
        if (tenFile == null || tenFile.trim().isEmpty()) {
            System.out.println("Tên file không hợp lệ.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (PhongBan pb : dspb) {
                writer.write(pb.toString());
                writer.newLine();
                writer.write(pb.getDanhSachNhanVienAsString());
                writer.newLine();
            }
            System.out.println("Xuất danh sách phòng ban ra file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // **************************** DANH SÁCH DỰ ÁN ************************************
    public List<DuAn> nhapDanhSachDuAnTuFile(String tenFile) {
        List<DuAn> danhsachDuAn = new ArrayList<>();
        if (tenFile == null || tenFile.trim().isEmpty()) {
            System.out.println("Tên file không hợp lệ.");
            return danhsachDuAn;
        }

        // Định dạng ngày
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Loại bỏ khoảng trắng thừa và bỏ qua các dòng trống
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // Bỏ qua dòng trống
                }

                // Chia dữ liệu theo dấu phẩy
                String[] data = line.split(",");

                // Nếu dữ liệu không đủ 4 thuộc tính, bỏ qua dòng
                if (data.length >= 4) {
                    String maDuAn = data[0].trim();
                    String tenDuAn = data[1].trim();

                    // Kiểm tra và chuyển đổi ngày tháng từ chuỗi
                    LocalDate ngayBatDau = null;
                    LocalDate ngayKetThuc = null;
                    try {
                        ngayBatDau = LocalDate.parse(data[2].trim(), dateFormat);
                        ngayKetThuc = LocalDate.parse(data[3].trim(), dateFormat);
                    } catch (Exception e) {
                        System.out.println("Lỗi khi phân tích ngày trong dòng: " + line);
                        continue;
                    }

                    // Kiểm tra các thông tin có hợp lệ hay không
                    if (maDuAn.isEmpty() || tenDuAn.isEmpty() || ngayBatDau == null || ngayKetThuc == null) {
                        System.out.println("Mã, tên dự án hoặc ngày không hợp lệ trong dòng: " + line);
                        continue;
                    }

                    // Tạo đối tượng DuAn và thêm vào danh sách
                    DuAn da = new DuAn(maDuAn, tenDuAn, ngayBatDau, ngayKetThuc);
                    danhsachDuAn.add(da);

                    // Kiểm tra lại thông tin vừa nhập
                    System.out.println("Mã dự án: " + maDuAn + ", Tên dự án: " + tenDuAn + ", Ngày Bắt Đầu: " + ngayBatDau
                            + ", Ngày Kết Thúc: " + ngayKetThuc);
                } else {
                    System.out.println("Dòng không hợp lệ (thiếu dữ liệu): " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
        return danhsachDuAn;
    }

    // Ghi danh sách dự án vào file
    public void xuatDanhSachDuAnRaFile(String tenFile, List<DuAn> dsda) {
        if (tenFile == null || tenFile.trim().isEmpty()) {
            System.out.println("Tên file không hợp lệ.");
            return;
        }

        // Định dạng ngày
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (DuAn da : dsda) {
                // Ghi thông tin dự án vào file theo định dạng chuẩn
                writer.write(da.getMaDA() + "," + da.getTenDA() + "," + da.getNgayBatDau().format(dateFormat) + ","
                        + da.getNgayKetThuc().format(dateFormat) + ",");
//                writer.newLine();
                writer.write(da.getDanhSachNhanVienAsString());
                writer.newLine();
            }
            System.out.println("Xuất danh sách dự án ra file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    public void xuatDanhSachTinhLuongRaFile(String tenFile, List<TinhLuong> dstl) {
        if (tenFile == null || tenFile.trim().isEmpty()) {
            System.out.println("Tên file không hợp lệ.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (TinhLuong tl : dstl) {
                writer.write(tl.toString());
                writer.newLine();
            }
            System.out.println("Xuất danh sách tính lương ra file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}
