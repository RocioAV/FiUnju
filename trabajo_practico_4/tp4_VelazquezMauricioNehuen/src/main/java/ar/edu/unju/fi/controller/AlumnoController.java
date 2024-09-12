package ar.edu.unju.fi.controller;


import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoDTO alumnoDTO;
    
    @Autowired
    private AlumnoMapper alumnoMapper;
    
    @Qualifier("alumnoServiceMySql")
    @Autowired
    private IAlumnoService alumnoService;
    
    @Qualifier("carreraServiceMySql")
    @Autowired
    private ICarreraService carreraService; // inyecto carreraService para poder utilizarlo
    
    @Autowired
    private CarreraMapper carreraMapper; // Inyecta el mapper de Carrera
    
    @Qualifier("materiaServiceMySql")
    @Autowired
    private IMateriaService materiaService;
    /**
     * Metodo que permite mostrar la pagina de alumnos
     *
     * @param model objeto que permite agregar atributos y enviarlos a la vista
     * @return la vista alumnos.html
     */
    @GetMapping("/listado")
    public String getAlumnosPage(Model model) {
        model.addAttribute("titulo", "Alumnos");
        model.addAttribute("alumnos", alumnoService.findByEstado(true));
        return "alumnos";
    }

    /**
     * Metodo que permite mostrar la pagina de nuevo alumno
     *
     * @param model objeto que permite agregar atributos y enviarlos a la vista
     * @return la vista alumno-form.html
     */
    @GetMapping("/nuevo-alumno")
    public String getNuevoAlumnoPage(Model model) {
        boolean edicion = false;
        model.addAttribute("titulo", "Nuevo Alumno");
        model.addAttribute("edicion", edicion);
        
        //model.addAttribute("alumno", alumnoDTO);
        model.addAttribute("alumno", new AlumnoDTO());
        
        model.addAttribute("carreras", carreraService.findByEstado(true)); // Añadir carreras para el formulario
        
        return "alumno-form";
    }

    /**
     * Metodo que permite guardar un nuevo alumno
     *
     * @param alumnoDTO objeto que representa un alumno
     * @return la vista alumnos.html
     */
    
    //se agrega modificacion en la generacion de id de alumno
    
    @PostMapping("/guardar-alumno")
    public ModelAndView guardarAlumno(@Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO, BindingResult result) {
    	
    	CarreraDTO carreraDTO = carreraService.findById(alumnoDTO.getCarrera().getIdCarrera());
    	
    	if (result.hasErrors()) {
        	ModelAndView modelView = new ModelAndView("alumno-form");
            modelView.addObject("alumno", alumnoDTO);
            modelView.addObject("edicion", false);
            modelView.addObject("carreras",  carreraService.findByEstado(true));
            modelView.addObject("titulo","Nuevo Alumno");
            return modelView;
    	}else {
    		ModelAndView modelView = new ModelAndView("alumnos");
            
            alumnoDTO.setCarrera(carreraMapper.toCarrera(carreraDTO));
            
            alumnoDTO.setEstado(true); // Establecer estado por defecto
            alumnoService.save(alumnoDTO); // Guardar alumno
            
            modelView.addObject("alumnos", alumnoService.findByEstado(true));
            modelView.addObject("titulo", "Alumnos");
            modelView.addObject("isAdded", true);
            return modelView;
    	}
    	
        
    }

    /**
     * Metodo que permite mostrar la pagina editar un alumno
     *
     * @param model objeto que permite agregar atributos y enviarlos a la vista
     * @param id   atributo que representa el dni del alumno
     * @return la vista alumno-form.html
     */
    @GetMapping("/editar-alumno/{id}")
    public String getEditarAlumnoPage(Model model, @PathVariable(value = "id") Long id) {
    	System.out.println(id);
        boolean edicion = true;
        AlumnoDTO alumnoEncontrado = new AlumnoDTO();
        alumnoEncontrado = alumnoService.findById(id);
        
        model.addAttribute("titulo", "Alumnos");
        model.addAttribute("edicion", edicion);
        model.addAttribute("alumno", alumnoEncontrado );
        model.addAttribute("carreras",  carreraService.findByEstado(true)); // Añadir carreras para el formulario
        
        
        return "alumno-form";
    }

    /**
     * Metodo que permite modificar un alumno
     *
     * @param alumnoDTO             objeto que representa un alumno
     * @param redirectAttributes objeto que permite enviar mensajes a la vista
     * @return la vista alumnos.html
     */
    @PostMapping("modificar-alumno")
    public String editarAlumno(Model model, @Valid @ModelAttribute("alumno") AlumnoDTO alumnoDTO,BindingResult result, RedirectAttributes redirectAttributes) {
        
    	CarreraDTO carrera = carreraService.findById(alumnoDTO.getCarrera().getIdCarrera());
    	
    	if (result.hasErrors()) {
    		boolean edicion = true;
    		model.addAttribute("titulo", "Editar Alumno");
            model.addAttribute("edicion", edicion);
            model.addAttribute("alumno", alumnoDTO );
            model.addAttribute("carreras",  carreraService.findByEstado(true));
            return "alumno-form";
        }
    	
    	alumnoDTO.setCarrera(carreraMapper.toCarrera(carrera));
    	//logica para evitar perder las inscripciones si el alumno tenia previamente
    	if(materiaService.findByAlumno(alumnoDTO.getIdAlumno()).isEmpty()) {
        	alumnoService.edit(alumnoDTO);
        }else {
        	alumnoDTO.setMaterias(materiaService.findByAlumno(alumnoDTO.getIdAlumno()));
        	alumnoService.edit(alumnoDTO);
        }
    	
        redirectAttributes.addFlashAttribute("isUpdated", true);
        return "redirect:/alumnos/listado";
    }

    /**
     * Metodo que permite eliminar un alumno
     *
     * @param id atributo que representa el dni del alumno
     * @return la vista alumnos.html
     */
    @GetMapping("/eliminar-alumno/{id}")
    public String eliminarAlumno(@PathVariable(value = "id") Long id) {
        alumnoService.deleteById(id);
        return "redirect:/alumnos/listado";
    }
}
