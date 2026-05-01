package com.example.lab7.Service;

import com.example.lab7.Model.Classes;
import com.example.lab7.Model.Schedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private final ArrayList<Schedule> schedules = new ArrayList<>();
    public List<Schedule> getAllSchedules() {
        return schedules;
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    public boolean updateSchedule(String day,Schedule schedule) {
        for (int i = 0; i < schedules.size(); i++) {
            if (schedules.get(i).getDay().equals(day)) {
                schedules.set(i, schedule);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSchedule(String day) {
        for (Schedule s : schedules) {
            if (s.getDay().equals(day)) {
                schedules.remove(s);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Schedule> searchByDay(String day) {
        ArrayList<Schedule> foundSchedules = new ArrayList<>();
        for (Schedule s : schedules) {
            if (s.getDay().equalsIgnoreCase(day)) {
                foundSchedules.add(s);
            }
        }
        return foundSchedules;
    }

    public ArrayList<Schedule> searchByTime(String startTime) {
        ArrayList<Schedule> foundSchedules = new ArrayList<>();
        for (Schedule s : schedules) {
            if (s.getStartTime().equalsIgnoreCase(startTime)) {
                foundSchedules.add(s);
            }
        }
        return foundSchedules;
    }


    public ArrayList<Schedule> searchByClassID(String classId) {
        ArrayList<Schedule> foundSchedules = new ArrayList<>();
        for (Schedule s : schedules) {
            for (Classes c : s.getClasses()) {
                if (c.getID().equals(classId)) {
                    foundSchedules.add(s);
                    break;
                }
            }
        }
        return foundSchedules;
    }

    public ArrayList<Schedule> getOrderedWeeklySchedule() {
        ArrayList<Schedule> ordered = new ArrayList<>();
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        for (String day : weekDays) {
            for (Schedule s : schedules) {
                if (s.getDay().equalsIgnoreCase(day)) {
                    ordered.add(s);
                }
            }
        }
        return ordered;
    }
}
