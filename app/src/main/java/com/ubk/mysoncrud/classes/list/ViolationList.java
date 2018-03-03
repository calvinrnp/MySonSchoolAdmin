package com.ubk.mysoncrud.classes.list;

/**
 * Created by USER on 27/02/2018.
 */

public class ViolationList {
    private String violationid, violationname, violationpoint, violationdate;

    public ViolationList(String violationid, String violationname, String violationpoint, String violationdate) {
        this.violationid = violationid;
        this.violationname = violationname;
        this.violationpoint = violationpoint;
        this.violationdate = violationdate;
    }

    public String getViolationid() {
        return violationid;
    }

    public void setViolationid(String violationid) {
        this.violationid = violationid;
    }

    public String getViolationname() {
        return violationname;
    }

    public void setViolationname(String violationname) {
        this.violationname = violationname;
    }

    public String getViolationpoint() {
        return violationpoint;
    }

    public void setViolationpoint(String violationpoint) {
        this.violationpoint = violationpoint;
    }

    public String getViolationdate() {
        return violationdate;
    }

    public void setViolationdate(String violationdate) {
        this.violationdate = violationdate;
    }
}
