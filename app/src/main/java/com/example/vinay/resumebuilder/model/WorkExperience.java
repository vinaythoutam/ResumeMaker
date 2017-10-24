package com.example.vinay.resumebuilder.model;

/**
 * Created by raskolnikov on 15/10/17.
 */

public class WorkExperience {

    String exStartdate,exEnddate;
    int exCid;
    String exJobtitle,exJobdescription,exCompanyname;

    public int getexCid() {
        return exCid;
    }

    public void setexCid(int exCid) {
        this.exCid = exCid;
    }

    public String getexStartdate() {
        return exStartdate;
    }

    public void setexStartdate(String exStartdate) {
        this.exStartdate = exStartdate;
    }

    public String getexEnddate() {
        return exEnddate;
    }

    public void setexEnddate(String exEnddate) {
        this.exEnddate = exEnddate;
    }

    public String getexJobtitle() {
        return exJobtitle;
    }

    public void setexJobtitle(String exJobtitle) {
        this.exJobtitle = exJobtitle;
    }

    public String getexJobdescription() {
        return exJobdescription;
    }

    public void setexJobdescription(String exJobdescription) {
        this.exJobdescription = exJobdescription;
    }

    public String getexCompanyname() {
        return exCompanyname;
    }

    public void setexCompanyname(String exCompanyname) {
        this.exCompanyname = exCompanyname;
    }
}
