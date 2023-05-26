package com.exam.vit.taskManager.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessDTOResponse {
    public SuccessDTOResponse(Boolean result) {
        this.result = result;
    }
    private Boolean result = true;
}
