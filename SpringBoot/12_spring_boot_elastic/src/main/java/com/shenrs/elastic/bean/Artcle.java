package com.shenrs.elastic.bean;

import io.searchbox.annotations.JestId;

/**
 * @author shenrs
 * @Description
 * @create 2019-04-25 15:00
 **/
public class Artcle {

    @JestId/*标识这是一个主键*/
    private Integer id;

    private String author;

    private String title;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
