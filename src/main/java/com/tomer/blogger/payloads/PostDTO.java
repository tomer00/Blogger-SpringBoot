package com.tomer.blogger.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {

    private int ID;

    @NotEmpty
    @Size(min = 4)
    private String title;

    private String image;

    @NotEmpty
    private String content;

    private Date addedDate;

    private String categoryTitle;
    private String userName;

}
