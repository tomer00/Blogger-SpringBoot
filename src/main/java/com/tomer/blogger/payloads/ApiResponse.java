package com.tomer.blogger.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Integer code;
}
