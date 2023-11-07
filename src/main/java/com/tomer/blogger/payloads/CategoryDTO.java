package com.tomer.blogger.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {

    private int categoryId;
    @NotEmpty
    @Size(min = 4, message = "Name must be 4 chars minimum")
    private String categoryTitle;
    private String categoryDes;
}
