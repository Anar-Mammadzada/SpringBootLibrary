package com.company.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private  String description;
    private int copies;

    @Column(name = "copies_available")
    private int copiesAvailable;
    private String category;
    private String img;
}
