package com.exam.vit.taskManager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String errorText;
    private Integer errorCode;
    public Response(String errorText, Integer errorCode) {
        this.errorCode = errorCode;
        this.errorText = errorText;
    }
}
