/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLY_NHANSU;

import java.util.List;

/**
 *
 * @author vubin
 */
public class NewClass {

    private List<NhanVien> dsnv;

    public void timKiem(String msnv) {
        for (NhanVien nv : dsnv) {
            if (nv.getMSNV().equals(msnv)) {
                System.out.print("đã tìm thấy nhân viên");
            }
        }
    }
}
