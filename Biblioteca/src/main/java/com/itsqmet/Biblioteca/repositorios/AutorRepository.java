package com.itsqmet.Biblioteca.repositorios;

import com.itsqmet.Biblioteca.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository <Autor, Integer> {
}
