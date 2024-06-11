package com.alura.literalura.repository;

import com.alura.literalura.model.CodigoIdioma;
import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository extends JpaRepository<Libro, Long>
{
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdioma(CodigoIdioma idioma);
}
