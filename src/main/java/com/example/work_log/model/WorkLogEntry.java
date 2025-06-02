package com.example.work_log.model;

import jakarta.persistence.*;
import org.hibernate.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkLogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String title;
    public LocalDate date;

    @Column(length = 2000)
    private String description;

    @ElementCollection
    private List<String> tags = new ArrayList<String>();

    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public List<String> getTags(){
        return this.tags;
    }

    public void addTag(String tag){
        tags.add(tag);
    }


}
