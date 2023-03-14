package cn.itcast.domain;

import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.TimeZone;



public class Record {

    private int auto_id;
    private  String id;
    private  String part_a;
    private  String part_b;
    private  Date start_date;
    private  Date expiry_date;
    private  String doc_name;
    private  boolean if_recent_expire;
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }



    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public boolean isIf_recent_expire() {
        return if_recent_expire;
    }

    public void setIf_recent_expire(boolean if_recent_expire) {
        this.if_recent_expire = if_recent_expire;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPart_a() {
        return part_a;
    }

    public void setPart_a(String part_a) {
        this.part_a = part_a;
    }

    public String getPart_b() {
        return part_b;
    }

    public void setPart_b(String part_b) {
        this.part_b = part_b;
    }

    @Override
    public String toString() {
        return "Record{" +
                "auto_id=" + auto_id +
                ", id='" + id + '\'' +
                ", part_a='" + part_a + '\'' +
                ", part_b='" + part_b + '\'' +
                ", start_date=" + start_date +
                ", expiry_date=" + expiry_date +
                ", doc_name='" + doc_name + '\'' +
                ", if_recent_expire=" + if_recent_expire +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
