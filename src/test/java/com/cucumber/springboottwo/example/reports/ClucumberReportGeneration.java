package com.cucumber.springboottwo.example.reports;

import com.trivago.cluecumber.core.CluecumberCore;
import com.trivago.cluecumber.engine.exceptions.CluecumberException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClucumberReportGeneration {

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("No arguments found");
            }
            String jsonDirectory = args[0];
            String reportDirectory = args[1];

            log.info("Entered source json directory: {}", jsonDirectory);
            log.info("Entered output report directory: {}", reportDirectory);

            new CluecumberCore.Builder()
                    .setCustomPageTitle("My Spring Boot Cucumber Reports")
                    .setExpandAttachments(true)
                    .setExpandBeforeAfterHooks(true)
                    .build().generateReports(jsonDirectory, reportDirectory);
        } catch (CluecumberException e) {
            log.error("Unable to generate cluecumber report", e.fillInStackTrace());
        }
    }

}
