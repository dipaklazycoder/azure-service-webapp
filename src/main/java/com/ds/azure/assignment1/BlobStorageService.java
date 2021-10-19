package com.ds.azure.assignment1;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.models.BlobProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;

@Service
public class BlobStorageService {
    @Autowired
    private BlobClientBuilder client;

    public String upload(MultipartFile file, String prefixName) {
        if(file != null && file.getSize() > 0) {
            try {
                //implement your own file name logic.
                String fileName = prefixName + UUID.randomUUID().toString() + file.getOriginalFilename();
                var blobClient = client.blobName(fileName).buildClient();
                blobClient.upload(file.getInputStream(),file.getSize());
                blobClient.setMetadata(Collections.singletonMap("productCategory", "images"));
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public byte[] getFile(String name) {
        try {
            File temp = new File("/temp/"+name);
            BlobProperties properties = client.blobName(name).buildClient().downloadToFile(temp.getPath());
            byte[] content = Files.readAllBytes(Paths.get(temp.getPath()));
            temp.delete();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
