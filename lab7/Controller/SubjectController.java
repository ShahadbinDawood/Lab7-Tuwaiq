package com.example.lab7.Controller;

import com.example.lab7.Api.ApiResponds;
import com.example.lab7.Model.Subject;
import com.example.lab7.Service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject")
@RequiredArgsConstructor
public class SubjectController {
    private  final SubjectService subjectService;

    @GetMapping("/get")
    public ResponseEntity<?> getSubject() {
        return ResponseEntity.status(200).body(subjectService.getSubjects());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSubject(@RequestBody @Valid Subject subject, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdded = subjectService.addSubjects(subject);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponds("subject added successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponds("ID dose exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable String id, @RequestBody @Valid Subject subject, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = subjectService.updateSubjects(id, subject);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponds("subject Updated successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponds("subject is not found "));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable String id) {
        boolean isDeleted = subjectService.deleteSubjects(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponds("subject deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponds("subject is not found "));

    }
    @GetMapping("/subjects-author/{author}")
    public ResponseEntity<?> listSubjectsByAuthor(@PathVariable String author){
        if (subjectService.listSubjectsByAuthor(author).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponds("no subject with this author are  found"));

        }
        return ResponseEntity.status(200).body(subjectService.listSubjectsByAuthor(author)) ;
    }
    @GetMapping("/subjects-teacher/{teacher}")
    public ResponseEntity<?> searchByTeacher(@PathVariable String teacher){
        if (subjectService.searchByTeacher(teacher).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponds("no subject with this teacher are  found"));

        }
        return ResponseEntity.status(200).body(subjectService.searchByTeacher(teacher)) ;
    }
    @GetMapping("/subjects-category/{category}")
    public ResponseEntity<?> listSubjectsByCategory(@PathVariable String category){
        if (subjectService.listSubjectsByCategory(category).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponds("no subject with this category are  found"));

        }
        return ResponseEntity.status(200).body(subjectService.listSubjectsByCategory(category)) ;
    }

}