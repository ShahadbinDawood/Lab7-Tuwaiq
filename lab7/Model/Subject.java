package com.example.lab7.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subject {
    @NotEmpty(message = "ID should not be Empty")
    private String ID ;
    @NotEmpty(message = "title should not be Empty")
    private String title ;
    @NotEmpty(message = "teacher should not be Empty")
    private String teacher ;
    @NotEmpty(message = "author should not be Empty")
    private String author ;
    @NotEmpty(message = "category should not be Empty")
    private String category;

}
