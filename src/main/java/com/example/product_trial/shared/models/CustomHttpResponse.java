package com.example.product_trial.shared.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomHttpResponse {
    private boolean success;
    private String message;
    private Object data;
}
