package com.ds.azure.assignment1;

import com.ctc.wstx.shaded.msv_core.util.Uri;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Collection;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BlobStorageService blobStorageService;
    @Autowired
    private QueueService queueService;
    @Value("${blob.base-uri}")
    String blobBaseUri;

    public void createProduct(ProductDTO productDTO) {
        try {
        var blobId = saveInBlob(productDTO);
        var product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImagePath(blobId);
        productRepository.save(product);
        pushInQueue(Message.builder().id(product.getId())
                .imageUrl(new URI(String.join("/", blobBaseUri,  blobId))).build());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void updateProductThumbnail(long id, String thumbnailPath) {
       var product =  productRepository.findById(id);
       if(product.isPresent()) {
           var prd = product.get();
           prd.setThumbnailPath(thumbnailPath);
           productRepository.save(prd);

       }
    }
    public Collection<Product> getProducts() {
        var products = productRepository.findAll();
        products.forEach(p-> {
                p.setImagePath(String.join("/", blobBaseUri,  p.getImagePath()));
                if(p.getThumbnailPath()!= null) {
                    p.setThumbnailPath(String.join("/", blobBaseUri,  p.getThumbnailPath()));
                }
        });
        return products;
    }

    private String saveInBlob(ProductDTO productDTO) {
        return blobStorageService.upload(productDTO.getImage(), "");
    }

    private void pushInQueue(Message message) throws JsonProcessingException {
        var msg = JacksonUtil.marshal(message);
        String encodedString = Base64.getEncoder().encodeToString(msg.getBytes());
        queueService.insert(encodedString);
    }
}
