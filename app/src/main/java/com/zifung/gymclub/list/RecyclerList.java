package com.zifung.gymclub.list;

public class RecyclerList {
    private String Name;  // course name
    private String Time;  // course time
    private String TelNumber;

    public RecyclerList(){ }

    public RecyclerList(String name, String time, String tel_number){
        this.Name=name;
        this.Time=time;
        this.TelNumber = tel_number;
    }
    public String getName(){ return Name; }
    public String getTime(){ return Time; }
    public String getTelNumber() { return TelNumber; }
}
