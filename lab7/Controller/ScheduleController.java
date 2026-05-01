package com.example.lab7.Controller;

import com.example.lab7.Model.Schedule;
import com.example.lab7.Service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    @GetMapping("/get")
    public ResponseEntity<?> getAllSchedules() {
        return ResponseEntity.status(200).body(scheduleService.getAllSchedules());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addSchedule(@RequestBody @Valid Schedule schedule) {
        scheduleService.addSchedule(schedule);
        return ResponseEntity.status(201).body("Schedule Added Successfully");
    }


    @PutMapping("/update/{day}")
    public ResponseEntity<?> updateSchedule(@PathVariable String day, @RequestBody @Valid Schedule schedule) {
        boolean isUpdated = scheduleService.updateSchedule(day, schedule);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Schedule Updated Successfully");
        }
        return ResponseEntity.status(404).body("Schedule Not Found for this Day");
    }


    @DeleteMapping("/delete/{day}")
    public ResponseEntity<?> deleteSchedule(@PathVariable String day) {
        boolean isDeleted = scheduleService.deleteSchedule(day);
        if (isDeleted) {
            return ResponseEntity.status(200).body("Schedule Deleted Successfully");
        }
        return ResponseEntity.status(404).body("Schedule Not Found");
    }


    @GetMapping("/search-day/{day}")
    public ResponseEntity<?> searchByDay(@PathVariable String day) {

        return ResponseEntity.status(200).body(scheduleService.searchByDay(day));
    }


    @GetMapping("/search-time/{startTime}")
    public ResponseEntity<?> searchByTime(@PathVariable String startTime) {
        ArrayList<Schedule> result = scheduleService.searchByTime(startTime);
        return ResponseEntity.status(200).body(result);
    }


    @GetMapping("/search-class/{classId}")
    public ResponseEntity<?> searchByClassID(@PathVariable String classId) {
        ArrayList<Schedule> result = scheduleService.searchByClassID(classId);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/weekly-ordered")
    public ResponseEntity<?> getOrderedWeeklySchedule() {
        return ResponseEntity.status(200).body(scheduleService.getOrderedWeeklySchedule());
    }
}
