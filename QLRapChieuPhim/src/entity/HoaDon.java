/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

public class HoaDon {
    private int MaHD;
    private String MaNV;
    private Date ngayLap;

    public HoaDon() {
    }

    public HoaDon(int MaHD, String MaNV, Date ngayLap) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.ngayLap = ngayLap;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    
}
