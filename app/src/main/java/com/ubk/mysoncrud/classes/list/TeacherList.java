package com.ubk.mysoncrud.classes.list;

/**
 * Created by USER on 22/02/2018.
 */

public class TeacherList {
    private String teacherid, teachername, teacheremail, teachermobile;

    public TeacherList(String teacherid, String teachername, String teacheremail, String teachermobile) {
        this.teacherid = teacherid;
        this.teachername = teachername;
        this.teacheremail = teacheremail;
        this.teachermobile = teachermobile;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTeacheremail() {
        return teacheremail;
    }

    public void setTeacheremail(String teacheremail) {
        this.teacheremail = teacheremail;
    }

    public String getTeachermobile() {
        return teachermobile;
    }

    public void setTeachermobile(String teachermobile) {
        this.teachermobile = teachermobile;
    }
}
