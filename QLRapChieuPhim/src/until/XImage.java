/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package until;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author quoct
 */
public class XImage {
    /**
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     * @return 
     */
    public static Image getAppIcon(){
        String file = "/ui/img/MainLogoSignIn.png";
        return new ImageIcon(XImage.class.getResource(file)).getImage();
    }
    
    /**
     * Sao chép file logo chuyên đề vào thư mục logo
     * @param src là đối tượng file ảnh
     */   
    public static void save(File src){
        File dst = new File("logos", src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } 
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Đọc hình ảnh logo chuyên đề
     * @param fileName  là tên file logo
     * @return ảnh đọc được
     */   
    public static ImageIcon read(String fileName){
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    public static ImageIcon resizeImg(ImageIcon img, int x, int y) {
        ImageIcon image = img;
        int ix = image.getIconWidth(), iy = image.getIconHeight();
        int dx = 0, dy = 0;
        if (x / y > ix / iy) {
            dy = y;
            dx = dy * ix / iy;
        } else {
            dx = x;
            dy = dx * iy / ix;
        }
        return new ImageIcon(image.getImage().getScaledInstance(dx, dy, Image.SCALE_SMOOTH));
    }
}
