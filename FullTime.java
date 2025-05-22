package QLY_NHANSU;

public class FullTime extends NhanVien {

    private double luongCoBan;
    private double phuCap;

    // Constructor có tham số cho cả luongCoBan và phuCap
    public FullTime(String MSNV, String ten, String email, String diachi,
            String ngaysinh, String gioitinh, String sdt, double luongCoBan, double phuCap) {
        super(MSNV, ten, email, diachi, ngaysinh, gioitinh, sdt);
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
    }

    // Constructor không tham số hoặc khởi tạo với giá trị mặc định
    public FullTime(String MSNV, String ten) {
        super(MSNV, ten, "", "", "", "", ""); // Khởi tạo với giá trị mặc định
        this.luongCoBan = 0; // Giá trị mặc định cho lương cơ bản
        this.phuCap = 0; // Giá trị mặc định cho phụ cấp
    }

    // Getter và Setter
    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    // Hiển thị thông tin nhân viên
    @Override
    public void hienThiThongTin() {
        System.out.println(toString());
    }

    @Override
    public String getLoaiNhanVien() {
        return "FullTime";
    }

}
