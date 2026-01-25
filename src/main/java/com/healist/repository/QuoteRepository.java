package com.healist.repository; // 패키지 경로 확인

import com.healist.model.Quote; // 모델 경로와 연결
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    // 랜덤으로 하나의 명언 가져오기
    @Query(value = "SELECT * FROM saying ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Quote> findRandomQuote();

    // 커스텀: 모든 할 일을 가져오기 (기본 제공됨으로 사실 추가 안해도 됩니다)
    @Query("SELECT q FROM Quote q")
    List<Quote> findAllQuotes();

    // 커스텀: 특정 키워드가 포함된 할 일만 가져오기
    @Query("SELECT q FROM Quote q WHERE q.task LIKE %:keyword%")
    List<Quote> findQuotesByKeyword(String keyword);
}
