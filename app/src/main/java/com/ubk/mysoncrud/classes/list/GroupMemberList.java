package com.ubk.mysoncrud.classes.list;

/**
 * Created by USER on 21/02/2018.
 */

public class GroupMemberList {
    private String groupid, groupname, teacherid, plpid;

    public GroupMemberList(String groupid, String groupname, String teacherid, String plpid) {
        this.groupid = groupid;
        this.groupname = groupname;
        this.teacherid = teacherid;
        this.plpid = plpid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getPlpid() {
        return plpid;
    }

    public void setPlpid(String plpid) {
        this.plpid = plpid;
    }
}
