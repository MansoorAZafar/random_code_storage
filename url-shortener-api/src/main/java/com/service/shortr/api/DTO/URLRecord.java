package com.service.shortr.api.DTO;
import jakarta.validation.constraints.NotEmpty;

public record URLRecord(
    @NotEmpty(message = "URL is mandatory") String url,
    String alias
) {}
