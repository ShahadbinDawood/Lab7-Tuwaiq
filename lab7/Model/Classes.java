package com.example.lab7.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Classes {
    @NotEmpty(message = "ID should not be Empty")
    private String ID ;
    @NotEmpty(message = "name should not be Empty")
    private String name ;
    @NotNull(message = "subjects should not be Empty ")
    private ArrayList<Subject> subjects;
    @NotNull(message = "student should not be Empty ")
    private String[] student ;

}
