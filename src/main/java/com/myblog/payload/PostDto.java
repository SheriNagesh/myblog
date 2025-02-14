package com.myblog.payload;


import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
@Data
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min =2,message="Post title should be at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min =4,message="Post title should be at least 4 characters")
    private String description;

    @NotEmpty
    @Size(min =5,message="Post title should be at least 5 characters")
    private String content;
}
