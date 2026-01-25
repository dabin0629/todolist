package com.healist.model;

import javax.persistence.*;

@Entity
@Table(name = "saying") // 테이블 이름이 `saying`이므로 이 이름으로 매핑합니다.
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quote")
    private String quote;

    @Column(name = "author")
    private String author;

    @Column(name = "task")
    private String task;

    // 기본 생성자 (JPA 사용을 위해 필요)
    public Quote() {}

    // 파라미터를 받는 생성자
    public Quote(String quote, String author, String task) {
        this.quote = quote;
        this.author = author;
        this.task = task;
    }

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
