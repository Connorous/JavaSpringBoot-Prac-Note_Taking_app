package com.example.note.taking.application.config;

import com.example.note.taking.application.datasources.NoteRepository;
import com.example.note.taking.application.datasources.NoteSetRepository;
import com.example.note.taking.application.entities.Note;
import com.example.note.taking.application.entities.NoteSet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Configuration
public class ApplicationConfig {
    @Bean
    CommandLineRunner commandLineRunner (NoteRepository noteRepository, NoteSetRepository noteSetRepository) {
        return args -> {

        };
    }
}
