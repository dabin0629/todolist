package com.healist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "todolist")
public class Todolist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "할 일(work)은 비어 있을 수 없습니다.")
    @Size(max = 100, message = "할 일(work)은 최대 100자까지 가능합니다.")
    @Column(name = "work", nullable = false)
    private String work;

    @Column(name = "progress", nullable = false)
    private String progress;

    // 기본 생성자
    public Todolist() {
        this.progress = "0%"; // progress의 기본값을 명시적으로 설정
    }

    // 생성자 (progress 값을 명시적으로 설정)
    public Todolist(String work, String progress) {
        this.work = work;
        this.progress = progress != null ? progress : "0%";
    }

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
