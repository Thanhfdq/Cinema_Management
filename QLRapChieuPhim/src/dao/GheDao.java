/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Ghe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

public class GheDao {
    public void insert(Ghe model){
        String sql="INSERT INTO Ghe (MaPhong, MaGhe, Gia VALUES (?, ?, ?)";
            XJdbc.update(sql, 
                model.getMaPhong(),
                model.getMaGhe(),
                model.getGia());
    }
    
    public void update(Ghe model){
        String sql="UPDATE Ghe SET MaPhong=?, Gia=? WHERE MaGhe=?";
        XJdbc.update(sql, 
                model.getMaPhong(),
                model.getGia(),
                model.getMaGhe());
    }
    
    public void delete(String MaGhe){
        String sql="DELETE FROM Ghe WHERE MaGhe=?";
        XJdbc.update(sql, MaGhe);
    }
    
    public List<Ghe> selectAll(){
        String sql="SELECT * FROM Ghe";
        return this.selectBySql(sql);
    }
    
    public Ghe selectById(String maghe){
        String sql="SELECT * FROM Ghe WHERE MaGhe=?";
        List<Ghe> list = this.selectBySql(sql, maghe);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<Ghe> selectBySql(String sql, Object...args){
        List<Ghe> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    Ghe entity=new Ghe();
                    entity.setMaPhong(rs.getString("MaPhong"));
                    entity.setMaGhe(rs.getString("MaGhe"));
                    entity.setGia(rs.getFloat("Gia"));
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
