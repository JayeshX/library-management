package com.example.attendance;

public class dataholder {
    private String at,dt,name;

    public dataholder(String At,String Dt,String Name){
        at = At;
        dt = Dt;
        name =Name;

    }
    public String getName() {
        return name;
    }

    public void setName(String Name) {
        name = Name;
    }
    public String getAt() {
        return at;
    }

    public void setAt(String At) {
        at = At;
    }
    public String getDt() {
        return dt;
    }

    public void setDt(String Dt) {
        dt = Dt;
    }
}
