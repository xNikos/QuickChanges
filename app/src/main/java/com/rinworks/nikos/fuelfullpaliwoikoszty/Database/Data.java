package com.rinworks.nikos.fuelfullpaliwoikoszty.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private int type;

    @ColumnInfo(name = "spalanie")
    private float spalanie;

    @ColumnInfo(name = "zatankowano")
    private float zatankowano;

    @ColumnInfo(name = "zaplacono")
    private float zaplacono;

    @ColumnInfo(name = "przejechano")
    private float przejechano;

    @ColumnInfo(name = "notatka_naprawiono")
    private String notatka_naprawiono;

    @ColumnInfo(name = "tytulNotatki")
    private String tytulNotatki;

    public Data(int type, float zatankowano, float zaplacono, float przejechano, String
            notatka_naprawiono, String tytulNotatki) {
        this.type = type;
        this.zatankowano = zatankowano;
        this.zaplacono = zaplacono;
        this.przejechano = przejechano;
        this.notatka_naprawiono = notatka_naprawiono;
        this.tytulNotatki = tytulNotatki;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(float spalanie) {
        this.spalanie = spalanie;
    }

    public float getZatankowano() {
        return zatankowano;
    }

    public void setZatankowano(float zatankowano) {
        this.zatankowano = zatankowano;
    }

    public float getZaplacono() {
        return zaplacono;
    }

    public void setZaplacono(float zaplacono) {
        this.zaplacono = zaplacono;
    }

    public float getPrzejechano() {
        return przejechano;
    }

    public void setPrzejechano(float przejechano) {
        this.przejechano = przejechano;
    }

    public String getNotatka_naprawiono() {
        return notatka_naprawiono;
    }

    public void setNotatka_naprawiono(String notatka_naprawiono) {
        this.notatka_naprawiono = notatka_naprawiono;
    }

    public String getTytulNotatki() {
        return tytulNotatki;
    }

    public void setTytulNotatki(String tytulNotatki) {
        this.tytulNotatki = tytulNotatki;
    }
}


