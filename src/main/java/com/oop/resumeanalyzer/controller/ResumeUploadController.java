package com.oop.resumeanalyzer.controller;

import com.oop.resumeanalyzer.model.JobRole;
import com.oop.resumeanalyzer.service.ResumeAnalyzerService;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Reads an uploaded resume (PDF or TXT) and finds the skills inside it.
 * It only looks for skills that the job roles in our database require.
 * The file itself is NOT saved - only read.
 */
@RestController
@RequestMapping("/api")
public class ResumeUploadController {

    // other ways people write the same skill
    private static final Map<String, List<String>> ALIASES = Map.of(
            "oop", List.of("object oriented", "object-oriented"),
            "data structures", List.of("dsa", "data structure")
    );

    private final ResumeAnalyzerService service;

    public ResumeUploadController(ResumeAnalyzerService service) {
        this.service = service;
    }

    @PostMapping("/extract-skills")
    public ResponseEntity<?> extractSkills(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return problem("The file is empty.");
        }

        String originalName = file.getOriginalFilename() == null ? "resume" : file.getOriginalFilename();
        String lowerName = originalName.toLowerCase();
        String text;

        try {
            if (lowerName.endsWith(".pdf")) {
                try (PDDocument document = Loader.loadPDF(file.getBytes())) {
                    text = new PDFTextStripper().getText(document);
                }
            } else if (lowerName.endsWith(".txt")) {
                text = new String(file.getBytes(), StandardCharsets.UTF_8);
            } else {
                return problem("Please upload a PDF or a .txt file.");
            }
        } catch (IOException e) {
            return problem("Could not read this file. Is it a real PDF?");
        }

        // make line breaks and extra spaces into single spaces
        text = text.replaceAll("\\s+", " ").trim();

        if (text.isEmpty()) {
            return problem("No text found. Scanned PDFs (photos of paper) cannot be read.");
        }

        // collect every skill that any role needs
        Set<String> allSkills = new LinkedHashSet<>();
        for (JobRole role : service.listRoles()) {
            allSkills.addAll(role.getRequiredSkills());
        }

        List<String> found = new ArrayList<>();
        for (String skill : allSkills) {
            if (mentions(text, skill)) {
                found.add(skill);
            }
        }

        return ResponseEntity.ok(Map.of("skills", found, "fileName", originalName));
    }

    // true if the text contains the skill as a whole word (Java is not found inside JavaScript)
    private boolean mentions(String text, String skill) {
        List<String> terms = new ArrayList<>();
        terms.add(skill);
        terms.addAll(ALIASES.getOrDefault(skill.toLowerCase(), List.of()));

        for (String term : terms) {
            Pattern pattern = Pattern.compile(
                    "(?<![A-Za-z0-9+#])" + Pattern.quote(term) + "(?![A-Za-z0-9+#])",
                    Pattern.CASE_INSENSITIVE);
            if (pattern.matcher(text).find()) {
                return true;
            }
        }
        return false;
    }

    private ResponseEntity<?> problem(String message) {
        return ResponseEntity.badRequest().body(Map.of("message", message));
    }
}
