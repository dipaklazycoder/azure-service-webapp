package com.ds.azure.assignment1;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "imagePath", nullable = true)
    private String imagePath;
    @Column(name = "thumbnailPath", nullable = true)
    private String thumbnailPath;
}
