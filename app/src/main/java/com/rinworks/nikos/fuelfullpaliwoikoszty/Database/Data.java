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

    @ColumnInfo(name = "zatankowano_zaplacono_notatka")
    private String zatankowano_zaplacono_tytulNotatki;

    @ColumnInfo(name = "zaplacono_naprawiono")
    private String zaplacono_naprawiono_notatka;

    @ColumnInfo(name = "przejechano")
    private String przejechano;

    @ColumnInfo(name = "spalanie")
    private String spalanie;

    public Data(int type, String zatankowano_zaplacono_tytulNotatki, String zaplacono_naprawiono_notatka,
                String przejechano, String spalanie) {
        this.type = type;
        this.zatankowano_zaplacono_tytulNotatki = zatankowano_zaplacono_tytulNotatki;
        this.zaplacono_naprawiono_notatka = zaplacono_naprawiono_notatka;
        this.przejechano = przejechano;
        this.spalanie = spalanie;
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

    public String getZatankowano_zaplacono_tytulNotatki() {
        return zatankowano_zaplacono_tytulNotatki;
    }

    public void setZatankowano_zaplacono_tytulNotatki(String zatankowano_zaplacono_tytulNotatki) {
        this.zatankowano_zaplacono_tytulNotatki = zatankowano_zaplacono_tytulNotatki;
    }

    public String getZaplacono_naprawiono_notatka() {
        return zaplacono_naprawiono_notatka;
    }

    public void setZaplacono_naprawiono_notatka(String zaplacono_naprawiono_notatka) {
        this.zaplacono_naprawiono_notatka = zaplacono_naprawiono_notatka;
    }

    public String getPrzejechano() {
        return przejechano;
    }

    public void setPrzejechano(String przejechano) {
        this.przejechano = przejechano;
    }

    public String getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(String spalanie) {
        this.spalanie = spalanie;
    }
}


