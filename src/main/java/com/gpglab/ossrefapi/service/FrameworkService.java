package com.gpglab.ossrefapi.service;

import com.gpglab.ossrefapi.domain.Framework;
import com.gpglab.ossrefapi.dto.FrameworkDetailResponseDto;
import com.gpglab.ossrefapi.dto.FrameworkResponseDto;
import com.gpglab.ossrefapi.repository.FrameworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FrameworkService {

    private final FrameworkRepository frameworkRepository;

    /**
     * 전체 프레임워크 목록 조회 (프로젝트 수 기준 정렬)
     */
    public List<FrameworkResponseDto> findAll() {
        return frameworkRepository.findAllOrderByProjectCount().stream()
                .map(FrameworkResponseDto::from)
                .toList();
    }

    /**
     * 프레임워크 단건 조회 (연관 프로젝트 포함)
     */
    public FrameworkDetailResponseDto findById(Long id) {
        Framework framework = frameworkRepository.findByIdWithProjects(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "프레임워크를 찾을 수 없습니다. id=" + id));
        return FrameworkDetailResponseDto.from(framework);
    }

    /**
     * 프레임워크 이름으로 검색
     */
    public List<FrameworkResponseDto> searchByName(String keyword) {
        return frameworkRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(FrameworkResponseDto::from)
                .toList();
    }

}
