package com.itsqmet.Biblioteca.controladores;

import com.itsqmet.Biblioteca.entidades.Autor;
import com.itsqmet.Biblioteca.entidades.Editorial;
import com.itsqmet.Biblioteca.entidades.Libro;
import com.itsqmet.Biblioteca.repositorios.EditorialRepository;
import com.itsqmet.Biblioteca.repositorios.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EditorialController {

    @Autowired
    EditorialRepository editorialRepository;

    @Autowired
    LibroRepository libroRepository;

    @GetMapping("/editoriales")
    public String editorial(Model model){
        List<Editorial> editorials = editorialRepository.findAll();
        model.addAttribute("editoriales",editorials);
        return "editorial/editorial";
    }

    @GetMapping ("/editorial/form")
    public String formulario(Model model){
        model.addAttribute("editorial", new Editorial());
        return "/editorial/formulario";
    }

    @PostMapping("/editorial/form")
    public String crear(Editorial editorial){
        editorialRepository.save(editorial);
        return "redirect:/editoriales";
    }

    //EDITAR EDITORIAL
    @GetMapping("/editorial/editar/{id}")
    public String editarEditorial(@PathVariable int id, Model model) {
        Optional<Editorial> editorialOptional = editorialRepository.findById(id);
        if (editorialOptional.isPresent()) {
            Editorial editorial = editorialOptional.get();
            model.addAttribute("editorial", editorial);
            return "editorial/formulario";
        } else {
            // Manejo de error si la editorial no se encuentra
            return "redirect:/editoriales";
        }
    }



    //ELIMINAR EDITORIAL SIN ELIMINAR LIBRO RELACIONADO
    @GetMapping("/editorial/eliminar/{id}")
    public String eliminarEditorial(@PathVariable Integer id){
        Editorial editorial = editorialRepository.findById(id).orElse(null);
        if (editorial != null) {
            List<Libro> libros = libroRepository.findByEditorialId(id);
            for (Libro libro : libros) {
                libro.setEditorial(null);
                libroRepository.save(libro);
            }
            editorialRepository.delete(editorial);
        }
        return "redirect:/editoriales";
    }


}
