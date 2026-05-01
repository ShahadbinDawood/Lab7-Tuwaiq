package com.example.lab7.Controller;

import com.example.lab7.Api.ApiResponds;
import com.example.lab7.Model.Classes;
import com.example.lab7.Model.Subject;
import com.example.lab7.Service.ClassesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/class")
@RequiredArgsConstructor
public class ClassesController {
    private  final ClassesService classesService;

    @GetMapping("/get")
    public ResponseEntity<?> getClasses() {
        return ResponseEntity.status(200).body(classesService.getClasses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addClasses(@RequestBody @Valid Classes classes, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdded = classesService.addClasses(classes);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponds("class  added successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponds("ID dose exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClasses(@PathVariable String id, @RequestBody @Valid Classes classes, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = classesService.updateClasses(id, classes);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponds("class Updated successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponds("class is not found "));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClasses(@PathVariable String id) {
        boolean isDeleted = classesService.deleteClasses(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponds("class deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponds("class is not found "));

    }
    @GetMapping("/add-subject/{id}")
    public ResponseEntity<?> addSubject(@PathVariable String id , @RequestBody @Valid Subject subject , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isadded= classesService.addSubject(id ,subject);
        if (isadded) {
            return ResponseEntity.status(200).body(new ApiResponds("subject added to class successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponds("class is not found "));

    }
    @GetMapping("/search-id/{id}")
    public ResponseEntity<?> getClassByID(@PathVariable String id) {
        Classes foundClass = classesService.getClassByID(id);

        if (foundClass == null) {
            return ResponseEntity.status(400).body("Class is not found.");
        }

        return ResponseEntity.status(200).body(foundClass);
    }
    @GetMapping("/search-category/{category}")
    public ResponseEntity<?> searchWithCategory(@PathVariable String category) {
        Classes foundClass = classesService.searchWithCategory(category);

        if (foundClass == null) {
            return ResponseEntity.status(400).body("No class found containing subjects with this  category: " );
        }

        return ResponseEntity.status(200).body(foundClass);
    }

}
