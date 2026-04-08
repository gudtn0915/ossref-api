package com.gpglab.ossrefapi.controller;


import com.gpglab.ossrefapi.dto.FrameworkDetailResponseDto;
import com.gpglab.ossrefapi.dto.FrameworkResponseDto;
import com.gpglab.ossrefapi.service.FrameworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frameworks")
@RequiredArgsConstructor
public class FrameworkController {

    private final FrameworkService frameworkService;

    /**
     * 전체 프레임워크 목록 조회
     * GET /api/frameworks
     */
    @GetMapping
    public ResponseEntity<List<FrameworkResponseDto>> findAll() {
        return ResponseEntity.ok(frameworkService.findAll());
    }

    /**
     * 프레임워크 단건 조회 (연관 프로젝트 포함)
     * GET /api/frameworks/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<FrameworkDetailResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(frameworkService.findById(id));
    }

    /**
     * 프레임워크 이름 검색
     * GET /api/frameworks/search?keyword=spring
     */
    @GetMapping("/search")
    public ResponseEntity<List<FrameworkResponseDto>> searchByName(
            @RequestParam String keyword) {
        return ResponseEntity.ok(frameworkService.searchByName(keyword));
    }
}
