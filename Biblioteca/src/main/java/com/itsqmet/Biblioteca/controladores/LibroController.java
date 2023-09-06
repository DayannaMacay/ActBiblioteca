package com.itsqmet.Biblioteca.controladores;

import com.itsqmet.Biblioteca.entidades.Autor;
import com.itsqmet.Biblioteca.entidades.Editorial;
import com.itsqmet.Biblioteca.entidades.Libro;
import com.itsqmet.Biblioteca.repositorios.AutorRepository;
import com.itsqmet.Biblioteca.repositorios.EditorialRepository;
import com.itsqmet.Biblioteca.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class LibroController {

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    EditorialRepository editorialRepository;

    @GetMapping("/libros")
    public String listarLibros(Model model) {
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros);
        return "libro/libro";
    }

    //CREAR
    @GetMapping("/libro/form")
    public String formulario(Model model){
        model.addAttribute("libro", new Libro());
        //Traer elementos que estan en autor (relacion uno a muchos)
        List<Autor> autores = autorRepository.findAll();
        model.addAttribute("autores", autores);
        //Traer elementos que estan en editorial (relacion uno a uno)
        List<Editorial> editoriales = editorialRepository.findAll();
        model.addAttribute("editoriales", editoriales);
        return "libro/formulario";
    }

    @PostMapping("/libro/form")
    public String crear(Libro libro){
        libroRepository.save(libro);
        return "redirect:/libros";
    }


    //EDITAR LIBROS
    @GetMapping("/libro/editar/{id}")
    public String editarLibro(@PathVariable int id, Model model) {
        Optional<Libro> libroOptional = libroRepository.findById(id);
        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            // Cargar autores y editoriales para usar en el formulario de edición
            List<Autor> autores = autorRepository.findAll();
            List<Editorial> editoriales = editorialRepository.findAll();
            model.addAttribute("libro", libro);
            model.addAttribute("autores", autores);
            model.addAttribute("editoriales", editoriales);
            return "libro/formulario";
        } else {
            // Manejo de error si el libro no se encuentra
            return "redirect:/libros";
        }
    }

    @PostMapping("/libro/editar/{id}")
    public String guardarLibro(@PathVariable int id, Libro libro) {
        // Lógica para guardar los cambios en el libro
        libroRepository.save(libro);
        return "redirect:/libros";
    }



    //ELIMINAR
    @GetMapping("/libro/eliminar/{id}")
    public String eliminar(@PathVariable int id){
        libroRepository.deleteById(id);
        return "redirect:/libros";
    }

}
