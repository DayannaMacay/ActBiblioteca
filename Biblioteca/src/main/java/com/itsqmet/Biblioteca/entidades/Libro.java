package com.itsqmet.Biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private LocalDate publicacion;

    @ManyToOne //decimos que lo siguiente va a ser la llave foranea de la relacion
    @JoinColumn(name="autor_id") //le damos nombre a la llave foranea
    private Autor autor;


    @OneToOne
    @JoinColumn(name="editorial_id")
    private Editorial editorial;
}
