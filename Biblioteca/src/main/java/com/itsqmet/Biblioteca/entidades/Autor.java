package com.itsqmet.Biblioteca.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;

}
