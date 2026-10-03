package com.oop.resumeanalyzer.service;

import com.oop.resumeanalyzer.model.JobRole;
import com.oop.resumeanalyzer.model.ManagerialRole;
import com.oop.resumeanalyzer.model.TechnicalRole;
import org.springframework.stereotype.Component;

/**
 * Picks the correct ReportGenerator subclass for a given JobRole.
 * The caller only ever talks to the abstract ReportGenerator type -
 * the actual method that runs depends on the object's real class
 * at runtime (runtime POLYMORPHISM / dynamic dispatch).
 */
@Component
public class ReportGeneratorFactory {

    public ReportGenerator getGenerator(JobRole role) {
        if (role instanceof TechnicalRole) {
            return new TechnicalReportGenerator();
        } else if (role instanceof ManagerialRole) {
            return new ManagerialReportGenerator();
        }
        throw new IllegalArgumentException("Unknown role type: " + role.getClass());
    }
}
