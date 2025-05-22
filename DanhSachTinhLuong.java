package QLY_NHANSU;

import java.util.ArrayList;


public class DanhSachTinhLuong {

    private ArrayList<TinhLuong> danhSach;

    public DanhSachTinhLuong() {
        danhSach = new ArrayList<>();
    }

    // Thêm tính lương
    public void them(TinhLuong tinhLuong) {
        danhSach.add(tinhLuong);
        System.out.println("Thêm thành công!");
    }

    // Xóa tính lương theo mã nhân viên và tháng
    public void xoa(String maNhanVien, int thang) {
        boolean found = false;
        for (int i = 0; i < danhSach.size(); i++) {
            TinhLuong tl = danhSach.get(i);
            if (tl.getNhanVien().getMSNV().equals(maNhanVien) && tl.getThang() == thang) {
                danhSach.remove(i);
                found = true;
                System.out.println("Xóa thành công!");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy thông tin cần xóa!");
        }
    }

    // Sửa thông tin lương theo mã nhân viên và tháng
    public void sua(String maNhanVien, int thang, int soNgayLam, int soGioLam, double heSoLuong) {
        boolean found = false;
        for (TinhLuong tl : danhSach) {
            if (tl.getNhanVien().getMSNV().equals(maNhanVien) && tl.getThang() == thang) {
                if (tl.getNhanVien() instanceof FullTime) {
                    tl.setSoNgayLam(soNgayLam);
                } else if (tl.getNhanVien() instanceof PartTime) {
                    tl.setSoGioLam(soGioLam);
                }  else if (tl.getNhanVien() instanceof TruongPhong) {
                    tl.setSoNgayLam(soNgayLam);
                    tl.setHeSoLuong(heSoLuong);
                }
                tl.capNhatLuong(); // Cập nhật lại lương
                found = true;
                System.out.println("Sửa thành công!");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy thông tin cần sửa!");
        }
    }

    // Hiển thị toàn bộ danh sách
    public void hienThi() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sách trống.");
        } else {
            for (TinhLuong tl : danhSach) {
                System.out.println(tl);
            }
        }
    }

    // Tìm kiếm theo tháng
    public void timKiem(int thang) {
        boolean found = false;
        for (TinhLuong tl : danhSach) {
            if (tl.getThang() == thang) {
                System.out.println(tl);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy thông tin trong tháng " + thang);
        }
    }

}
