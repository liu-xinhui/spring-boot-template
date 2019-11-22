package com.step.template.main.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("project")
public class ProjectParam {
    private String demoParam;
}
