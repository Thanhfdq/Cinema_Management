/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.HoaDonCT;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

/**
 *
 * @author 84386
 */
public class HoaDonCTDao {

    public void themHDCT(HoaDonCT model) {
        String sql = "INSERT INTO HoaDonCT(MaHD, MaMon, SoLuong) VALUES (?, ?, ?)";
        XJdbc.update(sql,
                model.getMaHD(),
                model.getMaMon(),
                model.getSoLuong());

    }

    protected List<HoaDonCT> selectBySql(String sql, Object... args) {
        List<HoaDonCT> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    HoaDonCT entity = new HoaDonCT();
                    entity.setMaHD(rs.getInt("MaHD"));
                    entity.setMaMon(rs.getInt("MaMon"));
                    entity.setSoLuong(rs.getInt("SoLuong"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
