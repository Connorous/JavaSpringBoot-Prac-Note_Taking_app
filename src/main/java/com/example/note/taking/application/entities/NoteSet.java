package com.example.note.taking.application.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class NoteSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;
    private String noteSetName;

    public NoteSet(Integer id, String noteSetName) {
        Id = id;
        this.noteSetName = noteSetName;
    }

    public NoteSet() {
    }

    public NoteSet(String noteSetName) {
        this.noteSetName = noteSetName;
    }

    public int getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNoteSetName() {
        return noteSetName;
    }

    public void setNoteSetName(String noteSetName) {
        this.noteSetName = noteSetName;
    }
}
