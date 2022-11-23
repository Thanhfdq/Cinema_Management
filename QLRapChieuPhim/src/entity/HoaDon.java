/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import java.util.TreeMap;

public class HoaDon {
    private Integer MaHD;
    private String MaNV;
    private Date ngayLap;
    private TreeMap<ThucDon,Integer> DSSP;

    public HoaDon() {
          this.DSSP = new TreeMap<>();
    }

    public HoaDon(Integer MaHD, String MaNV, Date ngayLap, TreeMap<ThucDon, Integer> DSSP) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.ngayLap = ngayLap;
        this.DSSP = DSSP;
    }
    
    public Integer getMaHD() {
        return MaHD;
    }

    public void setMaHD(Integer MaHD) {
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

    public TreeMap<ThucDon, Integer> getDSSP() {
        return DSSP;
    }

    public void setDSSP(TreeMap<ThucDon, Integer> DSSP) {
        this.DSSP = DSSP;
    }
    
    

    
    public void themSP(ThucDon TD, Integer SoLuong){
        boolean chkTonTai = DSSP.containsKey(TD);
        if(chkTonTai){
            int SoLuongTruoc = DSSP.get(TD);
            SoLuong += SoLuongTruoc;
            DSSP.put(TD, SoLuong);
        }
        else{
            DSSP.put(TD, SoLuong);
        }
    }
    public void truSP(ThucDon TD, Integer SoLuong){
        boolean chkTonTai = DSSP.containsKey(TD);
        if(chkTonTai){
            int SoLuongTruoc = DSSP.get(TD);
            SoLuong = SoLuongTruoc - SoLuong;
            /*
             * Nếu số lượng sau khi trừ xong <= 0 thì xoá sản phẩm khỏi danh sách
             * Nếu số lượng sau khi trừ xong > 0 thì xoá sản phẩm 
                   và cập nhật lại sản phẩm cùng số lượng mới
             */
            if(SoLuong <= 0){
                DSSP.remove(TD);    
            }
            else {
                DSSP.remove(TD);
                DSSP.put(TD, SoLuong);
            }
        }
    }
    
    public void xoaSP(ThucDon TD){
        boolean kiemtra = DSSP.containsKey(TD);
        if(kiemtra){
            DSSP.remove(TD);
        }
    }

}
