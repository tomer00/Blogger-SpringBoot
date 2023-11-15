package com.tomer.blogger.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CommentDTO {

    private int ID;
    @NotEmpty
    private String content;
}
