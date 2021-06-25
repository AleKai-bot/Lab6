package com.example.familiagoogle;

import java.io.Serializable;
import java.util.ArrayList;

public class Sucursales implements Serializable {

    private ArrayList<Sucursal> suc;

    public Sucursales() {
        ArrayList<Sucursal> suc = new ArrayList<>();
    }

    public Sucursales(ArrayList<Sucursal> suc) {
        this.suc = suc;
    }

    public ArrayList<Sucursal> getSuc() {
        return suc;
    }

    public void setSuc(ArrayList<Sucursal> suc) {
        this.suc = suc;
    }

    public void addSucursal(Sucursal x){
        suc.add(x);
    }

    public ArrayList<Sucursal> getSucursales(){
        return suc;
    }

}
