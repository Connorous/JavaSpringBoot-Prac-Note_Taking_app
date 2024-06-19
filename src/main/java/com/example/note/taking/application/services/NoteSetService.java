package com.example.note.taking.application.services;

import com.example.note.taking.application.datasources.NoteRepository;
import com.example.note.taking.application.datasources.NoteSetRepository;
import com.example.note.taking.application.entities.Note;
import com.example.note.taking.application.entities.NoteSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteSetService {
    private final NoteSetRepository repository;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteSetService(NoteSetRepository repository, NoteRepository noteRepository) {
        this.repository = repository;
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotes(Integer id) {
        return repository.findNoteBySetId(id);
    }

    public List<NoteSet> getNoteSets() {
        return repository.findAll();
    }

    public void addNewNoteSet(NoteSet noteSet) {
        Optional<NoteSet> noteSetByName = repository.findByNoteSetName(noteSet.getNoteSetName());
        if (noteSetByName.isPresent()) {
            System.out.println("note set already exists");
            return;
        }
        repository.save(noteSet);
    }

    public void deleteNoteSet(Integer noteSetId) {
        if (!repository.existsById(noteSetId)) {
            System.out.println("note set with id " + noteSetId + " does not exist");
            return;
        }
        List<Note> notesInSet = getNotes(noteSetId);
        if (notesInSet.size() > 0) {
            for (Note note : notesInSet) {
                noteRepository.deleteById(note.getId());
            }
        }
        repository.deleteById(noteSetId);
    }

    public NoteSet getLastNote() {
        List<NoteSet> noteSets = getNoteSets();
        if (noteSets.isEmpty()) {
            return null;
        }
        return noteSets.get(-1);
    }
}
