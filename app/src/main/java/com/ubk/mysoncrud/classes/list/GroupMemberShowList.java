package com.ubk.mysoncrud.classes.list;

/**
 * Created by USER on 22/02/2018.
 */

public class GroupMemberShowList {
    private String url,sc,plp;

    public GroupMemberShowList(String url, String sc, String plp) {
        this.url = url;
        this.sc = sc;
        this.plp = plp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getPlp() {
        return plp;
    }

    public void setPlp(String plp) {
        this.plp = plp;
    }
}
