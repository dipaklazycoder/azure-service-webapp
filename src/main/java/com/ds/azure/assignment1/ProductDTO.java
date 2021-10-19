package com.ds.azure.assignment1;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProductDTO {
    private String name;
    private String description;
    private MultipartFile image;
}
