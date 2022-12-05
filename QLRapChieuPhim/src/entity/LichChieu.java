/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

public class LichChieu {
    private int maLichChieu;
    private String maPhim;
    private int gioChieu;
    private Date ngayChieu;
    private String maPhong;

    public LichChieu() {
    }

    public LichChieu(int maLichChieu, String maPhim, int gioChieu, Date ngayChieu, String maPhong) {
        this.maLichChieu = maLichChieu;
        this.maPhim = maPhim;
        this.gioChieu = gioChieu;
        this.ngayChieu = ngayChieu;
        this.maPhong = maPhong;
    }

    public int getMaLichChieu() {
        return maLichChieu;
    }

    public void setMaLichChieu(int maLichChieu) {
        this.maLichChieu = maLichChieu;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public int getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(int gioChieu) {
        this.gioChieu = gioChieu;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

//    public LichChieu(int maLichChieu, int gioChieu, Date ngayChieu, String maPhong) {
//        this.maLichChieu = maLichChieu;
//        this.gioChieu = gioChieu;
//        this.ngayChieu = ngayChieu;
//        this.maPhong = maPhong;
//    }

    
}
