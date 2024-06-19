package com.example.note.taking.application.services;

import com.example.note.taking.application.datasources.NoteRepository;
import com.example.note.taking.application.entities.Note;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository repository;

    @Autowired
    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public void addNewNote(Note note) {
        repository.save(note);
    }

    public void deleteNote(Integer noteId) {
        if (!repository.existsById(noteId)) {
            System.out.println("a note with id " + noteId + " does not exist");
            return;
        }
        repository.deleteById(noteId);
    }

    @Transactional
    public void updateNote(Integer noteId, String newNote) {
        if (!repository.existsById(noteId)) {
            System.out.println("note with id " + noteId + " does not exist");
            return;
        }
        Optional<Note> noteRef = repository.findById(noteId);
        Note noteToUpdate = noteRef.get();

        if (newNote != null && !newNote.equals("") && !Objects.equals(noteToUpdate.getNote(), newNote)) {
            noteToUpdate.setNote(newNote);
        }

        repository.save(noteToUpdate);
    }
}
