package com.project.shopapp.controllers;

import com.project.shopapp.dtos.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
//    Hien thi tat ca category
    @GetMapping("")
    public ResponseEntity<String> getAllCategory(@RequestParam("page") int page,
                                                 @RequestParam("limit") int limit
                                                 ){
        return ResponseEntity.ok(String.format("get ok, page=%d limit=%d", page, limit));    }

    @PostMapping("")
    public ResponseEntity<?> insertCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                 BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok("insert ok");    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id){
        return ResponseEntity.ok("update ok");    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok("delete ok");    }
}
