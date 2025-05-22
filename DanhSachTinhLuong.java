package QLY_NHANSU;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DanhSachTinhLuong {

    private List<TinhLuong> dstl;

    public DanhSachTinhLuong() {
        dstl = new ArrayList<>();
    }

    public List<TinhLuong> getDanhSachTinhLuong() {
        return dstl;
    }

    // Thêm tính lương
    public void them(TinhLuong tinhLuong) {
        dstl.add(tinhLuong);
        System.out.println("Thêm thành công!");
    }

    public void xoa(String maNhanVien, int thang) {
        boolean found = false;
        Iterator<TinhLuong> iterator = dstl.iterator();

        while (iterator.hasNext()) {
            TinhLuong tl = iterator.next();
            if (tl.getNhanVien().getMSNV().equals(maNhanVien) && tl.getThang() == thang) {
                iterator.remove(); // Sử dụng iterator để xóa phần tử an toàn
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
        for (TinhLuong tl : dstl) {
            if (tl.getNhanVien().getMSNV().equals(maNhanVien) && tl.getThang() == thang) {
                if (tl.getNhanVien() instanceof FullTime) {
                    tl.setSoNgayLam(soNgayLam);
                } else if (tl.getNhanVien() instanceof PartTime) {
                    tl.setSoGioLam(soGioLam);
                } else if (tl.getNhanVien() instanceof TruongPhong) {
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
        if (dstl.isEmpty()) {
            System.out.println("Danh sách trống.");
        } else {
            for (TinhLuong tl : dstl) {
                System.out.println(tl);
            }
        }
    }

    // Tìm kiếm theo tháng
    public void timKiem(int thang) {
        boolean found = false;
        for (TinhLuong tl : dstl) {
            if (tl.getThang() == thang) {
                System.out.println(tl);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy thông tin trong tháng " + thang);
        }
    }

    public TinhLuong timKiem(String maNhanVien, int thang) {
        for (TinhLuong tl : dstl) { // danhSach là danh sách các bảng lương
            if (tl.getNhanVien().getMSNV().equals(maNhanVien) && tl.getThang() == thang) {
                return tl; // Trả về bảng lương tìm thấy
            }
        }
        System.out.println("Không tìm thấy bảng lương cho mã nhân viên " + maNhanVien + " trong tháng " + thang + ".");
        return null; // Không tìm thấy
    }

}
