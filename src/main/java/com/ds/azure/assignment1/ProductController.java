package com.ds.azure.assignment1;

import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping(value = "")
    public Collection<Product> products() {
        return productService.getProducts();
    }

    @PostMapping(value = "",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public String create(@RequestPart("image") @ApiParam(value="File", required=true) MultipartFile image,
                                      @RequestPart("name") String name,
                                      @RequestPart("description") String description) {
        log.info("products details: " + image);
        productService.createProduct(ProductDTO.builder().name(name)
                .description(description).image(image).build());
        // Add your processing logic here
        return "success";
    }
    @PatchMapping(value = "{id}")
    public String updateThumbnail(@PathVariable("id") long id,
                         @RequestParam("thumbnailPath") String thumbnailPath) {
        log.info("products details: " + thumbnailPath);
        productService.updateProductThumbnail(id, thumbnailPath);
        // Add your processing logic here
        return "success";
    }
}