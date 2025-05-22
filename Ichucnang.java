/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package QLY_NHANSU;

/**
 *
 * @author vubin
 */
public interface Ichucnang<T> {

    void them(T obj);        // Thêm một đối tượng (nhân viên, phòng ban, dự án)

    void xoa(String ma);          // Xóa đối tượng theo mã

//    void sua(String ma, Object obj); // Sửa thông tin đối tượng theo mã
    void timKiem(String ma);    // Tìm kiếm đối tượng theo mã, trả về đối tượng tìm thấy

    void hienThi();
}
