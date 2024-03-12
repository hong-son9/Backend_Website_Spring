package com.project.shopapp.controllers;

import com.project.shopapp.dtos.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertProduct(@Valid @ModelAttribute ProductDTO productDTO,
//                                           @RequestPart("file") MultipartFile file,
                                           BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            List<MultipartFile> files = productDTO.getFiles();
//            de rong
            files = files == null ? new ArrayList<MultipartFile>() : files;
            for(MultipartFile file : files){
                if(file.getSize() == 0){
                    continue;
                }
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("max 10mb");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("/image")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }
//        luu va cap nhat
                String filename = storeFile(file);
//                luu vao product trong db
            }
            return ResponseEntity.ok("insert ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
private String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//        Them UUID vao truoc ten file de dam bao ten file day du
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
//        Duong dan den thu muc ma ban muon luu file
        java.nio.file.Path uploadDir = Paths.get("uploads");
//        Kiem tra va tao thu muc neu khong ton tai
        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
//        Duong dan day du ten file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
//        Sao chep file vao thu muc dich
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
}

    @GetMapping("")
    public ResponseEntity<String> getAllCategory(@RequestParam("page") int page,
                                                 @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("get ok");
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getProductsById(@PathVariable("id") String producId) {
        return ResponseEntity.ok("get ok id" + producId);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body("delete ok");
    }
}
