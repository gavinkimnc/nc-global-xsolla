package com.project.otholla.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {

    @JsonProperty("project_id")
    public Integer projectId;
    @JsonProperty("merchant_id")
    public Integer merchantId;


}