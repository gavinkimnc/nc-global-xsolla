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
public class WebhookReq {
    @JsonProperty(value = "notification_type")
    private String notificationType;

    @JsonProperty(value = "user")
    private WebHookUser users;

    @JsonProperty(value = "settings")
    private WebHookSettins settings;
}
