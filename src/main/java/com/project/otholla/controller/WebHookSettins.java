package com.project.otholla.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookSettins {

    @JsonProperty(value = "project_id")
    private String projectId;
    @JsonProperty(value = "merchant_id")
    private String merchantId;
}
