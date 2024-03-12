package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200, message = "Giua 3 va 200")
    private String name;
    @Min(value = 0, message = "Khong be hon 0")
    @Max(value = 1000000000, message="khong qua 10000000")
    private Float price;
    private String thumbnail;
    private String description;
    @JsonProperty("category_id")
    private String categoryId;
    private List<MultipartFile> files;
}
