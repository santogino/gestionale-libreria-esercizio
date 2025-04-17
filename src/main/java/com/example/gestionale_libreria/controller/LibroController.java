package com.example.gestionale_libreria.controller;

import com.example.gestionale_libreria.entity.Libro;
import com.example.gestionale_libreria.service.LibroService;
import jakarta.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/all")
    public List<Libro> getAll() {
        return libroService.getAllBooks();
    }

    @GetMapping("/by-autore")
    public ResponseEntity<Page<Libro>> getByAutore(@RequestParam String autore,
                                                   @RequestParam(defaultValue = "0") int pagina,
                                                   @RequestParam(defaultValue = "10") int dimensione) {
        Page<Libro> paginaLibri = libroService.getByAutorePageable(autore,pagina,dimensione);

        return ResponseEntity.ok(paginaLibri);

    }

    @GetMapping("/pubblicazione")
    public List<Libro> getAnnoPubblicazioneGreaterThan (@Nullable @RequestParam Integer annoPubblicazione) {
        return libroService.getAnnoPubblicazioneGreaterThan(annoPubblicazione);
    }

    @PostMapping
    public Optional<Libro> addLibro (@RequestBody Libro libro) {
        return libroService.addBook(libro);
    }
}
