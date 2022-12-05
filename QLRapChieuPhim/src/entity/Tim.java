/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

public class Tim {

    private String uname;
    private String ufullname;
    private String upass;
    private int rol;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUfullname() {
        return ufullname;
    }

    public void setUfullname(String ufullname) {
        this.ufullname = ufullname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Tim() {
    }

    public Tim(String uname, String ufullname, String upass, int rol) {
        this.uname = uname;
        this.ufullname = ufullname;
        this.upass = upass;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "- ++++++" + ufullname;
    }
}
