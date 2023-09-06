package com.itsqmet.Biblioteca.controladores;

import com.itsqmet.Biblioteca.entidades.Autor;
import com.itsqmet.Biblioteca.entidades.Editorial;
import com.itsqmet.Biblioteca.entidades.Libro;
import com.itsqmet.Biblioteca.repositorios.AutorRepository;
import com.itsqmet.Biblioteca.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LibroRepository libroRepository;

    //READ
    @GetMapping("/autores")
    public String autor(Model model){
        List<Autor> autores = autorRepository.findAll();
        model.addAttribute("autores", autores);
        return "autor/autor";
    }

    @GetMapping ("/autor/form")
    public String formulario(Model model){
        model.addAttribute("autor", new Autor()); // Se crea un nuevo empleado
        return "/autor/formulario";
    }


    //EDITAR AUTOR A PESAR DE LA RELACION
    @GetMapping("/autor/editar/{id}")
    public String editarAutor(@PathVariable("id") Integer id, Model model) {
        Optional<Autor> autorOptional = autorRepository.findById(id);
        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            model.addAttribute("autor", autor);
            return "autor/formulario";
        }
        return "redirect:/autores";
    }

    @PostMapping("/autor/form")
    public String guardarAutor(@ModelAttribute("autor") Autor autor) {
        if (autor.getId() != null) {
            Autor autorExistente = autorRepository.findById(autor.getId()).orElse(null);
            if (autorExistente != null) {
                autorExistente.setNombre(autor.getNombre());
                autorExistente.setApellido(autor.getApellido());
                List<Libro> librosDelAutor = libroRepository.findByAutor(autorExistente);
                for (Libro libro : librosDelAutor) {
                    libro.setAutor(autorExistente);
                    libroRepository.save(libro);
                }
                autorRepository.save(autorExistente);
            }
        } else {
            autorRepository.save(autor);
        }
        return "redirect:/autores";
    }



    //ELIMINAR AUTOR PONIENDO EN LA LISTA DE USUARIOS "SIN AUTOR" EN VEZ DE ELIMINAR EL LIBRO
    @GetMapping("/autor/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        Optional<Autor> autorOptional = autorRepository.findById(id);
        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            List<Libro> libros = libroRepository.findByAutor(autor);
            for (Libro libro : libros) {
                libro.setAutor(null);
                libroRepository.save(libro);
            }
            autorRepository.delete(autor);
        }
        return "redirect:/autores";
    }


}
