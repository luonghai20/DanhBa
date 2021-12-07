package com.haixl.danhba;

public class DanhBa {
    int idAnh;
    String name;
    String phone;

    public DanhBa(int idAnh, String name, String phone) {
        this.idAnh = idAnh;
        this.name = name;
        this.phone = phone;
    }

    public int getIdAnh() {
        return idAnh;
    }

    public void setIdAnh(int idAnh) {
        this.idAnh = idAnh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
