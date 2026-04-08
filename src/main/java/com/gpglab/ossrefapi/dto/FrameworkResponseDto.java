package com.gpglab.ossrefapi.dto;


import com.gpglab.ossrefapi.domain.Framework;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FrameworkResponseDto {

    private Long id;
    private String name;
    private long projectCount;

    public static FrameworkResponseDto from(Framework framework) {
        return FrameworkResponseDto.builder()
                .id(framework.getId())
                .name(framework.getName())
                .projectCount(framework.getProjectFrameworks().size())
                .build();
    }
}
