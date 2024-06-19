package com.example.note.taking.application.controllers;

import com.example.note.taking.application.entities.Note;
import com.example.note.taking.application.entities.NoteSet;
import com.example.note.taking.application.services.NoteService;
import com.example.note.taking.application.services.NoteSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static java.time.Month.FEBRUARY;

@RestController
public class ApplicationController {

    private final NoteService noteService;
    private final NoteSetService noteSetService;
    private List<Note> notes;
    private List<NoteSet> noteSets;
    private Integer currentNoteSetId;
    private ModelAndView modelAndView;

    @Autowired
    public ApplicationController(NoteService noteService, NoteSetService noteSetService) {
        this.noteService = noteService;
        this.noteSetService = noteSetService;
    }


    @GetMapping("/")
    public ModelAndView showNotes(Model model) {
        currentNoteSetId = null;
        noteSets = noteSetService.getNoteSets();
        model.addAttribute("noteSets", noteSets);
        modelAndView = new ModelAndView();
        modelAndView.setViewName("note-taking-app");
        return modelAndView;
    }

    @GetMapping("/get-note-set")
    public ModelAndView showNotesById(Model model, @RequestParam(value = "id", required = false) Integer id) {
        List<Note> notes = noteSetService.getNotes(id);
        currentNoteSetId = id;
        List<NoteSet> noteSets = noteSetService.getNoteSets();
        if (currentNoteSetId == null && noteSets.size() == 0) {

            return showNotes(model);
        }
        notes.sort(Comparator.comparing(Note::getDateObject));
        Collections.reverse(notes);
        model.addAttribute("notes", notes);
        model.addAttribute("noteSets", noteSets);
        for (NoteSet noteSet : noteSets) {
            if (noteSet.getId() == id) {
                model.addAttribute("noteSetName", noteSet.getNoteSetName());
                break;
            }
        }
        modelAndView = new ModelAndView();
        modelAndView.setViewName("note-taking-app");
        return modelAndView;
    }


    @PostMapping(path = "/add-note-set")
    public RedirectView addNoteSet(@RequestParam(value = "noteSetName", required = false) String notSetName) {
        NoteSet newNoteSet = new NoteSet(notSetName);
        noteSetService.addNewNoteSet(newNoteSet);
        return new RedirectView("/");
    }


    @PostMapping(path = "/add-note")
    public RedirectView addNote(@RequestParam(value = "noteText", required = false) String noteText) {
        if (currentNoteSetId == null) {
            return new RedirectView("/");
        }
        Note note = new Note(noteText, new SimpleDateFormat("MMMM dd, yyyy - hh : mm a", Locale.getDefault()).format(new Date()), new Date(), currentNoteSetId);
        noteService.addNewNote(note);
        return new RedirectView("/get-note-set?id=" + currentNoteSetId);
    }


    @PostMapping(path = "/save-note")
    public RedirectView saveNote(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "noteText", required = false) String noteText) {
        noteService.updateNote(id, noteText);
        return new RedirectView("/get-note-set?id=" + currentNoteSetId);
    }


    @PostMapping(path = "/delete-note-set")
    public RedirectView deleteNoteSet(@RequestParam(value = "id", required = false) Integer id) {
        noteSetService.deleteNoteSet(id);
        return new RedirectView("/");
    }

    @PostMapping(path = "/delete-note")
    public RedirectView deleteNote(@RequestParam(value = "id", required = false) Integer id) {
        noteService.deleteNote(id);
        return new RedirectView("/get-note-set?id=" + currentNoteSetId);
    }
}
