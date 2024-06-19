package com.example.note.taking.application.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;
    private String note;
    private String dateCreated;
    private Date dateObject;
    private Integer noteSetId;

    public Note(Integer id, String note, String dateCreated, Date dateObject, Integer noteSetId) {
        Id = id;
        this.note = note;
        this.dateCreated = dateCreated;
        this.dateObject = dateObject;
        this.noteSetId = noteSetId;
    }

    public Note(String note, String dateCreated, Date dateObject, Integer noteSetId) {
        this.note = note;
        this.dateCreated = dateCreated;
        this.dateObject = dateObject;
        this.noteSetId = noteSetId;
    }

    public Note() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateObject() {
        return dateObject;
    }

    public void setDateObject(Date dateObject) {
        this.dateObject = dateObject;
    }

    public Integer getNoteSetId() {
        return noteSetId;
    }

    public void setNoteSetId(Integer noteSetId) {
        this.noteSetId = noteSetId;
    }
}
