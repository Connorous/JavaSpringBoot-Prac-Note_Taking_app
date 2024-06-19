package com.example.note.taking.application.datasources;

import com.example.note.taking.application.entities.Note;
import com.example.note.taking.application.entities.NoteSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteSetRepository extends JpaRepository<NoteSet, Integer> {
    @Query("SELECT note FROM Note note WHERE note.noteSetId = ?1")
    List<Note> findNoteBySetId(Integer noteSetId);

    @Query("SELECT noteSet FROM NoteSet noteSet WHERE noteSet.noteSetName = ?1")
    Optional<NoteSet> findByNoteSetName(String noteSetName);
}
