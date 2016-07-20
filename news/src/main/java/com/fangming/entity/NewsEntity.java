package com.fangming.entity;

import java.io.Serializable;
import java.util.List;

public class NewsEntity implements Serializable {
    /**
     * 新闻 ID
     */
    private String hashId;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻概要
     */
    private String profil;
    /**
     * 新闻url
     */
    private String newurl;
    /**
     * 新闻内容
     */
    private String content;
    /**
     * 新闻时间
     */
    private String updatetime;
    /**
     * 是否有3个小图
     */
    private Boolean isthreepng;
    /**
     * 新闻来源
     */
    private String newssource;
    /**
     * 评论数量
     */
    private String commentnumbers;
    /**
     * 专题名称
     */
    private String isbooktip;
    /**
     * 右边图片url
     */
    private String rightpictureurl;

    public String getRightpictureurl() {
        return rightpictureurl;
    }

    public void setRightpictureurl(String rightpictureurl) {
        this.rightpictureurl = rightpictureurl;
    }

    public String getIsbooktip() {
        return isbooktip;
    }

    public void setIsbooktip(String isbooktip) {
        this.isbooktip = isbooktip;
    }

    public String getCommentnumbers() {
        return commentnumbers;
    }

    public void setCommentnumbers(String commentnumbers) {
        this.commentnumbers = commentnumbers;
    }

    public String getNewssource() {
        return newssource;
    }

    public void setNewssource(String newssource) {
        this.newssource = newssource;
    }

    public Boolean getIsthreepng() {
        return isthreepng;
    }

    public void setIsthreepng(Boolean isthreepng) {
        this.isthreepng = isthreepng;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getNewurl() {
        return newurl;
    }

    public void setNewurl(String newurl) {
        this.newurl = newurl;
    }

    public NewsEntity() {
    }

    public NewsEntity(String title) {
        this.title = title;
    }

    public NewsEntity(String title, String profil, String newurl) {
        this.title = title;
        this.profil = profil;
        this.newurl = newurl;
    }
}
