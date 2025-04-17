package com.example.gestionale_libreria.service;

import com.example.gestionale_libreria.entity.Libro;
import com.example.gestionale_libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    public List<Libro> getAllBooks() {
        return libroRepository.findAll();
    }

    public Page<Libro> getByAutorePageable (String autore, int pagina, int dimensione) {
        Pageable paginazione = PageRequest.of(pagina, dimensione);

        return libroRepository.findByAutore(autore, paginazione);
    }

    public List<Libro> getAnnoPubblicazioneGreaterThan (Integer annoPubblicazione) {
        return libroRepository.findByAnnoPubblicazioneGreaterThan(annoPubblicazione);
    }

    //  Trovare libri disponibili di un determinato genere
    public List<Libro> getFindByGenere (String genere) {
        return libroRepository.findByGenere(genere);
    }

    // Trovare libri con prezzo inferiore a un valore specificato
    public List<Libro> getPrezzoLessThan (Integer prezzo) {
        return libroRepository.findByPrezzoLessThan(prezzo);
    }

    public Optional<Libro> addBook(Libro libro) {
        if (libro.getTitolo() == null ||
                libro.getAutore() == null ||
                libro.getGenere() == null) {
            return Optional.empty();
        }

        return Optional.of(libroRepository.save(libro));
    }
}
