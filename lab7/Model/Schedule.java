package com.example.lab7.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Schedule {

    @NotEmpty(message = "Day is required")
    @Pattern(regexp = "^(Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday)$+", message = "Invalid day. Please enter a valid day of the week.")
    private String day;
    @NotEmpty(message = "Class list cannot be empty")
    private List<Classes> classes;
    @NotNull(message = "Start time is required")
    private String startTime;
}
