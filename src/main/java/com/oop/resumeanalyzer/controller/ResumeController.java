package com.oop.resumeanalyzer.controller;

import com.oop.resumeanalyzer.dto.AnalysisRequest;
import com.oop.resumeanalyzer.dto.AnalysisResponse;
import com.oop.resumeanalyzer.dto.JobRoleDto;
import com.oop.resumeanalyzer.model.JobRole;
import com.oop.resumeanalyzer.model.Report;
import com.oop.resumeanalyzer.service.ResumeAnalyzerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ResumeController {

    private final ResumeAnalyzerService service;

    public ResumeController(ResumeAnalyzerService service) {
        this.service = service;
    }

    @GetMapping("/roles")
    public List<JobRoleDto> getRoles() {
        return service.listRoles().stream()
                .map(r -> new JobRoleDto(r.getRoleId(), r.getRoleName(), r.getRoleCategory(), r.getRequiredSkills()))
                .collect(Collectors.toList());
    }

    @PostMapping("/analyze")
    public AnalysisResponse analyze(@Valid @RequestBody AnalysisRequest request) {
        return service.analyze(request);
    }

    @GetMapping("/reports")
    public List<Report> getReports() {
        return service.recentReports();
    }
}
