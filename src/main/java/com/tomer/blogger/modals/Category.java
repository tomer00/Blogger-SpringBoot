package com.tomer.blogger.modals;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column(name = "title" ,length = 120,nullable = false)
    private String categoryTitle;
    @Column(name = "des",columnDefinition = "TEXT")
    private String categoryDes;
}
