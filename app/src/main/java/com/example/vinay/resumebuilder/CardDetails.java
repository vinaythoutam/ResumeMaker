package com.example.vinay.resumebuilder;

/**
 * Created by ammu on 12/18/2016.
 */
public class CardDetails {

    String cname,cdate,ctype;
    int cid;

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setDate(String cdate) {
        this.cdate = cdate;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public String getDate() {
        return cdate;
    }

    public int getCid() {
        return cid;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }
}
