/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.LichChieu;
import entity.Phim;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

public class LichChieuDao {
        public void insert(LichChieu model){
        String sql="INSERT INTO LichChieu (MaLichChieu, MaPhim, GioChieu, NgayChieu, MaPhong) VALUES (?, ?, ?, ?, ?)";
            XJdbc.update(sql, 
                model.getMaLichChieu(),
                model.getMaPhim(),
                model.getGioChieu(),
                model.getNgayChieu(),
                model.getMaPhong());
    }
    
    public void update(LichChieu model){
        String sql="UPDATE LichChieu SET MaPhim=?, GioChieu=?, NgayChieu=?, MaPhong=? WHERE MaLichChieu=?";
        XJdbc.update(sql,
                model.getMaPhim(),
                model.getGioChieu(),
                model.getNgayChieu(),
                model.getMaPhong(),
                model.getMaLichChieu());
    }
    
    public void delete(String MaLichChieu){
        String sql="DELETE FROM LichChieu WHERE MaLichChieu=?";
        XJdbc.update(sql, MaLichChieu);
    }
    
    public List<LichChieu> selectAll(){
        String sql="SELECT * FROM LichChieu";
        return this.selectBySql(sql);
    }
    
    public LichChieu selectMaPhim(String maphim) {
        String sql = "SELECT MaLichChieu, GioChieu, NgayChieu, MaPhong FROM LichChieu WHERE MaPhim='?'";
        List<LichChieu> list = this.selectBySql(sql, maphim);
        return list.size() > 0 ? list.get(0) : null;
    }    
    
    public LichChieu selectById(String malichchieu){
        String sql="SELECT * FROM LichChieu WHERE MaLichChieu=?";
        List<LichChieu> list = this.selectBySql(sql, malichchieu);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<LichChieu> selectBySql(String sql, Object...args){
        List<LichChieu> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    LichChieu entity=new LichChieu();
                    entity.setMaLichChieu(rs.getInt("MaLichChieu"));
                    entity.setMaPhim(rs.getString("MaPhim"));
                    entity.setGioChieu(rs.getInt("GioChieu"));
                    entity.setNgayChieu(rs.getDate("NgayChieu"));
                    entity.setMaPhong(rs.getString("MaPhong"));
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
}
