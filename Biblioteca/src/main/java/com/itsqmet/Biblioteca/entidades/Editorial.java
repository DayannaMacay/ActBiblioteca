package com.itsqmet.Biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
}
