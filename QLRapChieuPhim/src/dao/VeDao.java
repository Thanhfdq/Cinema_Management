/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Ve;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

public class VeDao {
    public void insert(Ve model){
        String sql="INSERT INTO Ve (MaUser, NgayLap, MaGhe, MaPhong, MaLichChieu) VALUES (?, ?, ?, ?, ?)";
            XJdbc.update(sql, 
                model.getMaNV(),
                model.getNgayLap(),
                model.getMaGhe(),
                model.getMaPhong(),
                model.getMaLichChieu());
    }
    
    public void update(Ve model){
        String sql="UPDATE Ve SET MaUser, NgayLap, MaGhe, MaPhong, MaLichChieu WHERE MaVe=?";
        XJdbc.update(sql,
                model.getMaNV(),
                model.getNgayLap(),
                model.getMaGhe(),
                model.getMaPhong(),
                model.getMaLichChieu(),
                model.getMaVe());
    }
    
    public void delete(String MaVe){
        String sql="DELETE FROM Ve WHERE MaVe=?";
        XJdbc.update(sql, MaVe);
    }
    
    public List<Ve> selectAll(){
        String sql="SELECT * FROM Ve";
        return this.selectBySql(sql);
    }
    
    public Ve selectById(String mave){
        String sql="SELECT * FROM Ve WHERE MaVe=?";
        List<Ve> list = this.selectBySql(sql, mave);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<Ve> selectBySql(String sql, Object...args){
        List<Ve> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    Ve entity=new Ve();
                    entity.setMaVe(rs.getString("MaVe"));
                    entity.setMaNV(rs.getString("MaUser"));
                    entity.setNgayLap(rs.getDate("NgayLap"));
                    entity.setMaGhe(rs.getString("MaGhe"));
                    entity.setMaPhong(rs.getString("MaPhong"));
                    entity.setMaLichChieu(rs.getInt("MaLichChieu"));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getDsPhimChieuTrongNgay(String ngay) {
        String sql = "{CALL sp_dsphimtheongay (?)}";
        String[] cols = {"Hinh", "TenPhim", "GioChieu", "MaPhong", "MaLichChieu"};
        return this.getListOfArray(sql, cols, ngay);
    }

    public List<Object[]> getDsGheDaDatTrongNgay(String ngay, String phong, int gio) {
        String sql = "{CALL sp_dsghedadattheolichchieu (?, ?, ?)}";
        String[] cols = {"MaGhe"};
        return this.getListOfArray(sql, cols, ngay, phong, gio);
    }

    public String getGiaGhe(String soGhe, String soPhong) {
        String sql = "select Gia from Ghe where MaGhe = ? and MaPhong = ?";
        Object gia = XJdbc.value(sql, soGhe, soPhong);
        System.out.println(soGhe+" "+soPhong+" "+gia);
        return gia.toString();
    }
    
    public String getMaVe(Object soGhe, Object soPhong, Object maLichChieu){
        String sql = "select MaVe from Ve where MaGhe = ? and MaPhong = ? and MaLichChieu = ?";
        Object mave = XJdbc.value(sql, soGhe, soPhong, maLichChieu);
        return mave.toString();
    }
}
