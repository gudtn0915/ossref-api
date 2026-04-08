package com.gpglab.ossrefapi.dto;


import com.gpglab.ossrefapi.domain.Framework;
import com.gpglab.ossrefapi.domain.ProjectFramework;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FrameworkDetailResponseDto {

    private Long id;
    private String name;
    private long projectCount;
    private List<ProjectSummaryDto> projects;

    public static FrameworkDetailResponseDto from(Framework framework) {
        List<ProjectSummaryDto> projectSummaries = framework.getProjectFrameworks().stream()
                .map(ProjectSummaryDto::from)
                .toList();

        return FrameworkDetailResponseDto.builder()
                .id(framework.getId())
                .name(framework.getName())
                .projectCount(projectSummaries.size())
                .projects(projectSummaries)
                .build();
    }

    @Getter
    @Builder
    public static class ProjectSummaryDto {
        private Long projectId;
        private String projectName;
        private String owner;
        private Integer stars;
        private String detectedFrom;

        public static ProjectSummaryDto from(ProjectFramework pf) {
            return ProjectSummaryDto.builder()
                    .projectId(pf.getProject().getId())
                    .projectName(pf.getProject().getName())
                    .owner(pf.getProject().getOwner())
                    .stars(pf.getProject().getStars())
                    .detectedFrom(pf.getDetectedFrom())
                    .build();
        }
    }
}
