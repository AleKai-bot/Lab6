package com.example.familiagoogle;

import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Sucursal implements Serializable {

    private String localidad;
    private Double x;
    private Double y;
    private String farmacia;
    private int num;

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(String farmacia) {
        this.farmacia = farmacia;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Sucursal(String localidad, Double x, Double y, String farmacia, int num) {
        this.localidad = localidad;
        this.x = x;
        this.y = y;
        this.farmacia = farmacia;
        this.num = num;
    }
}
