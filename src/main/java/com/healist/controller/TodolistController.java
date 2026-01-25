package com.healist.controller;

import com.healist.model.Todolist;
import com.healist.repository.TodolistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/todolist")
public class TodolistController {

    @Autowired
    private TodolistRepository todolistRepository;

    // 모든 할 일을 가져오는 엔드포인트
    @GetMapping
    public List<Todolist> getAllTasks() {
        return todolistRepository.findAll();
    }

    // 새 할 일을 추가하는 엔드포인트
    @PostMapping("/add")
    public ResponseEntity<Todolist> addTask(@RequestBody Todolist newTask) {
        if (newTask.getProgress() == null || newTask.getProgress().isEmpty()) {
            newTask.setProgress("0%"); // progress가 null일 경우 기본값 설정
        }
        Todolist savedTask = todolistRepository.save(newTask);
        return ResponseEntity.ok(savedTask);
    }

    // 진행률 업데이트 엔드포인트
    @PutMapping("/progress/{id}")
    public ResponseEntity<String> updateProgress(@PathVariable Long id, @RequestParam String progress) {
        return todolistRepository.findById(id)
                .map(task -> {
                    // 진행률 유효성 검사 추가
                    if (!progress.matches("(20%|50%|70%|100%)")) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 진행률 값입니다.");
                    }

                    task.setProgress(progress);
                    todolistRepository.save(task);

                    // 진행률에 따른 메시지 반환
                    String message;
                    switch (progress) {
                        case "20%":
                            message = "잘하고 있어요! 계속하세요!";
                            break;
                        case "50%":
                            message = "벌써 반이나 왔어요!";
                            break;
                        case "70%":
                            message = "거의 다 왔어요!";
                            break;
                        case "100%":
                            message = "대단해요!";
                            break;
                        default:
                            message = "진행률이 업데이트되었습니다.";
                            break;
                    }
                    return ResponseEntity.ok(message);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("할 일을 찾을 수 없습니다."));
    }

    // 할 일 수정하기
    @PutMapping("/edit/{id}")
    public ResponseEntity<Todolist> editTask(@PathVariable Long id, @RequestBody Todolist updatedTask) {
        return todolistRepository.findById(id)
                .map(task -> {
                    task.setWork(updatedTask.getWork());

                    // Null 값 확인 및 기본값 설정
                    String updatedProgress = updatedTask.getProgress() != null ? updatedTask.getProgress() : "0%";
                    task.setProgress(updatedProgress);

                    Todolist savedTask = todolistRepository.save(task);
                    return ResponseEntity.ok(savedTask);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 할 일 삭제하기
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        return todolistRepository.findById(id)
                .map(task -> {
                    todolistRepository.delete(task);
                    return ResponseEntity.ok("할 일이 성공적으로 삭제되었습니다.");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("할 일을 찾을 수 없습니다."));
    }
}
