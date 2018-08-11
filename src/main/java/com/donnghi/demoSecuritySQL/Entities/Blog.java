package com.donnghi.demoSecuritySQL.Entities;

import javax.persistence.*;

@Entity
@Table(name = "blog")
public class Blog
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    public Blog() {}

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Blog(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title=\'" + title + "\'" +
                ", content=\'" + content + "\'" +
                "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
