package com.healist.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id") // 여기는 데이터베이스의 `id` 칼럼을 매핑합니다.
    private String id; // 로그인 시 입력받는 사용자 ID와 매칭

    @Column(name = "pw") // 여기는 데이터베이스의 `pw` 칼럼을 매핑합니다.
    private String pw; // 로그인 시 입력받는 사용자 비밀번호와 매칭

    // 추가적인 필드가 있을 경우 정의합니다.
    @Column(name = "name")
    private String name;

    // Getter 및 Setter
    public String getId() {
        return id; // Getter 이름을 id와 매칭되게 수정
    }

    public void setId(String id) {
        this.id = id; // 파라미터 id를 필드에 설정
    }

    public String getPw() {
        return pw; // Getter 이름을 pw와 매칭되게 수정
    }

    public void setPw(String pw) {
        this.pw = pw; // 파라미터 pw를 필드에 설정
    }

    public String getName() {
        return name; // 이름을 가져오는 Getter
    }

    public void setName(String name) {
        this.name = name; // 이름을 설정하는 Setter
    }
}
