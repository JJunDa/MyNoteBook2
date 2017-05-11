package com.example.administrator.mynotebook.ui.date;

/**
 * Created by Administrator on 2017/5/9.
 */

public class Note {

    private boolean isDel;
    private int id;
    private int notifiId;
    private int starId;
    private String title;
    private String content;
    private String createDate;
    private String modifyDate;
    public Note(){

    }

    public Note(boolean isDel, int id,int notifiId, int starId, String title, String content, String createDate, String modifyDate) {
        this.isDel = isDel;
        this.id = id;
        this.notifiId = notifiId;
        this.starId = starId;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getNotifiId() {
        return notifiId;
    }

    public void setNotifiId(int notifiId) {
        this.notifiId = notifiId;
    }

    public int getStarId() {
        return starId;
    }

    public void setStarId(int starId) {
        this.starId = starId;
    }

}



