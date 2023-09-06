package com.itsqmet.Biblioteca.repositorios;

import com.itsqmet.Biblioteca.entidades.Autor;
import com.itsqmet.Biblioteca.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository <Libro, Integer> {
    List<Libro> findByEditorialId(Integer editorialId);
    List<Libro> findByAutor(Autor autor);
}
