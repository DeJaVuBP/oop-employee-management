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

        // Kiểm tra nếu danh sách phòng ban trống
//        if (pb.isEmpty()) {
//            System.out.println("Danh sách phòng ban trống, không thể đọc dữ liệu nhân viên.");
//            return danhSach; // Trả về danh sách trống nếu phòng ban không có
//        }

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
                        int luongCoBan = Integer.parseInt(data[8].trim());
                        int phuCap = Integer.parseInt(data[9].trim());

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
                        int luongCoBan = Integer.parseInt(data[8].trim());
                        int phuCap = Integer.parseInt(data[9].trim());

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
                writer.write(nv.toString());
                writer.newLine();
            }
            System.out.println("Xuất danh sách nhân viên ra file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }

    }

    // **************************** DANH SÁCH PHÒNG BAN
    // ************************************
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
                if (parts.length == 2) {
                    String maPhong = parts[0].trim();
                    String tenPhong = parts[1].trim();
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
            }
            System.out.println("Xuất danh sách phòng ban ra file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // **************************** DANH SÁCH DỰ ÁN
    // ************************************
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
                // Loại bỏ các khoảng trắng thừa và chia dữ liệu theo dấu phẩy
                String[] data = line.split(",");
                if (data.length != 4) { // Kiểm tra số lượng phần tử phải là 4
                    System.out.println("Dữ liệu không đủ cho dòng: " + line);
                    continue;
                }

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

                if (maDuAn.isEmpty() || tenDuAn.isEmpty() || ngayBatDau == null || ngayKetThuc == null) {
                    System.out.println("Mã hoặc tên dự án hoặc ngày không hợp lệ trong dòng: " + line);
                    continue;
                }

                // Tạo đối tượng DuAn và thêm vào danh sách
                DuAn da = new DuAn(maDuAn, tenDuAn, ngayBatDau, ngayKetThuc);
                danhsachDuAn.add(da);

                // Kiểm tra lại thông tin vừa nhập
                System.out.println("Mã dự án: " + maDuAn + ", Tên dự án: " + tenDuAn + ", Ngày Bắt Đầu: " + ngayBatDau
                        + ", Ngày Kết Thúc: " + ngayKetThuc);
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
                        + da.getNgayKetThuc().format(dateFormat));
                writer.newLine();
            }
            System.out.println("Xuất danh sách dự án ra file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
}
