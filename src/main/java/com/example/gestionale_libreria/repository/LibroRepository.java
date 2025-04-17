package com.example.gestionale_libreria.repository;

import com.example.gestionale_libreria.entity.Libro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Page<Libro> findByAutore(String autore, Pageable pageable);

    List<Libro> findByAnnoPubblicazioneGreaterThan (Integer annoPubblicazione);

    //  Trovare libri disponibili di un determinato genere
    List<Libro> findByGenere (String genere);

    // Trovare libri con prezzo inferiore a un valore specificato
    List<Libro> findByPrezzoLessThan (Integer prezzo);

    @Query("SELECT l LIBRO l WHERE l.id = ?1")
    List<Libro> findlibroById (Long id);
}
