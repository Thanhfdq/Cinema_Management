/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import until.XJdbc;

/**
 *
 * @author quoct
 */
public class ThongKeDAO {

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

    //thong ke doanh thu
    public List<Object[]> doanhSoTheoPhim(String ngayDau, String ngayCuoi) {
        String sql = "{CALL sp_thongketheophim (?, ?)}";
        String[] cols = {"MaPhim", "TenPhim", "Sobuoichieu", "Sovebanra", "Doanhthu"};
        return getListOfArray(sql, cols, ngayDau, ngayCuoi);
    }

    //dung cho soat ve
    public List<Object[]> getListPhimChieuTrongNgay(String date) {
        String sql = "select Phim.TenPhim, LichChieu.MaLichChieu\n"
                + "from Phim join LichChieu on Phim.MaPhim = LichChieu.MaPhim\n"
                + "where LichChieu.NgayChieu = ?";
        String[] cols = {"TenPhim", "maLichChieu"};
        return getListOfArray(sql, cols, date);
    }

    public List<Object[]> getListVeTheoLichChieu(String maLC) {
        String sql = "select MaVe from ve where MaLichChieu = ?";
        String cols[] = {"MaVe"};
        return getListOfArray(sql, cols, maLC);
    }

    //thong ke nhan vien
    public List<Object[]> soNhanvienTheoChucVu() {
        String sql = "select Role.VaiTro, COUNT(Users.MaRole) Soluong\n"
                + "from Users join Role on Users.MaRole = Role.MaRole\n"
                + "where Users.MaRole <> 6\n"
                + "group by Role.MaRole, Role.VaiTro";
        String cols[] = {"VaiTro", "SoLuong"};
        return getListOfArray(sql, cols);
    }

    //thong ke phong chieu
    public List<Object[]> soPhongTheoSoGhe() {
        String sql = "select SoluongGhe, COUNT(MaPhong) SlPhong\n"
                + "from PhongChieu\n"
                + "group by SoluongGhe";
        String[] cols = {"SoLuongGhe", "SlPhong"};
        return getListOfArray(sql, cols);
    }
}
