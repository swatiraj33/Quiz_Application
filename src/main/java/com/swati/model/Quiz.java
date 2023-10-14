package com.swati.model;

import java.util.List;

import org.hibernate.annotations.ManyToAny;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id ;
    private String title;
    @ManyToAny
    private List<Question> question;

}
