/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

/**
 *
 * @author 84386
 */
public class HoaDonDao {

    public int maHoaDon() {
        String sql = "SELECT Max(MaHD) FROM HoaDon";
        return Integer.parseInt(XJdbc.value(sql).toString());
    }

    public void themHoaDon(HoaDon model) {
        String sql = "INSERT INTO HoaDon (MaUser, ngayLap) VALUES (?, ?)";
        XJdbc.update(sql,
                model.getMaUser(),
                model.getNgayLap());
    }

    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    HoaDon entity = new HoaDon();
                    entity.setMaHD(rs.getInt("MaHD"));
                    entity.setMaUser(rs.getString("MaUser"));
                    entity.setNgayLap(rs.getDate("NgayLap"));
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
