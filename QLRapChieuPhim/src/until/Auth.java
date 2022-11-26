/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package until;

import dao.NhanVienDAO;
import entity.HoaDon;
import entity.NhanVien;
import entity.PhongChieu;


public class Auth {
    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static NhanVien user = new NhanVienDAO().selectById("US001");
    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }
     public static HoaDon HoaDonGiaoDich = null;

    public static boolean themSua;
    
    public static PhongChieu pc = null;
    
}
