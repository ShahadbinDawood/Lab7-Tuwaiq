package com.example.lab7.Controller;

import com.example.lab7.Api.ApiResponds;
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
        return ResponseEntity.status(200).body(new ApiResponds("Schedule Added Successfully"));
    }


    @PutMapping("/update/{day}")
    public ResponseEntity<?> updateSchedule(@PathVariable String day, @RequestBody @Valid Schedule schedule) {
        boolean isUpdated = scheduleService.updateSchedule(day, schedule);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponds("Schedule Updated Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponds("Schedule Not Found for this Day"));
    }


    @DeleteMapping("/delete/{day}")
    public ResponseEntity<?> deleteSchedule(@PathVariable String day) {
        boolean isDeleted = scheduleService.deleteSchedule(day);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponds("Schedule Deleted Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponds("Schedule Not Found"));
    }


    @GetMapping("/search-day/{day}")
    public ResponseEntity<?> searchByDay(@PathVariable String day) {
        ArrayList<Schedule> schedule = scheduleService.searchByDay(day);
        if (schedule == null) {
            return ResponseEntity.status(404).body(new ApiResponds("No schedule found for this day"));
        }
        return ResponseEntity.status(200).body(schedule);}


    @GetMapping("/search-time/{startTime}")
    public ResponseEntity<?> searchByTime(@PathVariable String startTime) {
        ArrayList<Schedule> result = scheduleService.searchByTime(startTime);
        if (result.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponds("No schedules found for this start time"));
        }
        return ResponseEntity.status(200).body(result);
    }


    @GetMapping("/search-class/{classId}")
    public ResponseEntity<?> searchByClassID(@PathVariable String classId) {
        ArrayList<Schedule> result = scheduleService.searchByClassID(classId);
        if(result.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponds("no schedule with this class are found"));
        }
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/weekly-ordered")
    public ResponseEntity<?> getOrderedWeeklySchedule() {
        return ResponseEntity.status(200).body(scheduleService.getOrderedWeeklySchedule());
    }
}
