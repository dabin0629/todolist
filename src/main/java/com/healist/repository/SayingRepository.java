package com.healist.repository;

import com.healist.model.Saying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 이 인터페이스는 데이터베이스와 상호작용하기 위한 JPA 레포지토리입니다.
@Repository
public interface SayingRepository extends JpaRepository<Saying, Long> {
}
