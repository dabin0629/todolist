package com.healist.repository;

import com.healist.model.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Long> {
    // 기본 CRUD 메서드 제공 (save, findAll 등)
}
