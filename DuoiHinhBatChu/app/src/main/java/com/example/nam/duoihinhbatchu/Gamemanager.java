package com.example.nam.duoihinhbatchu;

import android.media.Image;
import android.widget.Button;

import java.util.Random;

/**
 * Created by Nam on 5/29/2016.
 */
public class Gamemanager {

    private static final String cauTl[] = new String[]{"AOMUA", "BAOCAO", "CANTHIEP", "CATTUONG", "CHIEUTRE", "DANHLUA", "DANONG", "GIANDIEP", "GIANGMAI", "HOIDONG", "HONGTAM", "KHOAILANG",
            "KIEMCHUYEN", "LANCAN", "MASAT", "NAMBANCAU", "OTO", "QUYHANG", "SONGSONG", "THATTINH", "THOTHE", "TICHPHAN", "TOHOAI", "TOTIEN", "TRANHTHU", "VUAPHALUOI", "VUONBACHTHU", "XAKEP",
            "XAPHONG", "XEDAPDIEN"};
    ;
    private static final String bangChuCai[] = new String[]{"A", "B", "C", "D", "E", "G", "H", "I", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y"};
    ;
    private String image;
    private StringBuilder luaChon;
    Random chuCai = new Random();
    Random chen = new Random();
    private String cauTlDung = "";

    public String getCauTlDung() {
        return cauTlDung;
    }

    public void setCauTlDung(String cauTlDung) {
        this.cauTlDung = cauTlDung;
    }

    public StringBuilder getLuaChon() {
        return luaChon;
    }

    public String getImage() {
        return image;
    }

    public Gamemanager() {
        cauTlDung = "";
    }

    public void randomImage() {
        Random rd = new Random();
        int number = rd.nextInt(29);
        image = "image_" + number;
        cauTlDung = cauTl[number].toString();
    }

    public void displaySelect() {
        luaChon = new StringBuilder(cauTlDung);
        while (luaChon.length() < 16) {
            int vtChuCai = chuCai.nextInt(21);
            int vtChen = chen.nextInt(cauTlDung.length());
            luaChon.insert(vtChen, bangChuCai[vtChuCai]);

        }
    }


}
