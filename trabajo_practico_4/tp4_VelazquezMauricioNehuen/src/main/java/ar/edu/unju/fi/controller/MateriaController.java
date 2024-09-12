package ar.edu.unju.fi.controller;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/materias")
public class MateriaController {
    @Autowired
    private MateriaDTO materiaDTO;

    @Autowired
    private DocenteDTO docenteDTO;

    @Autowired
    private CarreraDTO carreraDTO;
    
    @Autowired
    private DocenteMapper docenteMapper;
    @Autowired
    private CarreraMapper carreraMapper;
    
    // @Qualifier("docenteServiceCollection")
    @Qualifier("docenteServiceMySql")
    @Autowired
    private IDocenteService docenteService;
    
    // @Qualifier("carreraServiceCollection")
    @Qualifier("carreraServiceMySql")
    @Autowired
    private ICarreraService carreraService;
    
    // @Qualifier("materiaServiceCollection")
    @Qualifier("materiaServiceMySql")
    @Autowired
    private IMateriaService materiaService;

  
    
    
    /**
     * Metodo que permite mostrar la pagina de materias
     *
     * @param model objeto que permite agregar atributos y enviarlos a la vista
     * @return la vista materias.html
     */
    @GetMapping("/listado")
    public String getMateriasPage(Model model) {
        model.addAttribute("titulo", "Materias");
        model.addAttribute("materias", materiaService.findByEstado(true));
        return "materias";
    }

    /**
     * Metodo que permite mostrar la pagina de nueva materia
     *
     * @param model objeto que permite agregar atributos y enviarlos a la vista
     * @return la vista materia-form.html
     */
    @GetMapping("/nueva-materia")
    public String getNuevaMateriaPage(Model model) {
        boolean edicion = false;
        model.addAttribute("titulo", "Nueva Materia");
        model.addAttribute("edicion", edicion);
        model.addAttribute("materia", materiaDTO);
        model.addAttribute("carreras", carreraService.findByEstado(true));
        model.addAttribute("docentes", docenteService.findDocentesSinMateria());
        return "materia-form";
    }

    /**
     * Metodo que permite guardar una nueva materia
     *
     * @param materiaDTO objeto que representa una materia
     * @return la vista materias.html
     */    
    @PostMapping("/guardar-materia")
    public ModelAndView guardarMateria(@Valid @ModelAttribute("materia") MateriaDTO materiaDTO, BindingResult result) {
    	if(result.hasErrors()) {

    		ModelAndView modelView = new ModelAndView("materia-form");
            modelView.addObject("titulo", "Nueva Materia");
            modelView.addObject("carreras", carreraService.findByEstado(true));
            modelView.addObject("docentes", docenteService.findDocentesSinMateria());
            modelView.addObject("edicion", false);
            modelView.addObject("materia", materiaDTO);
            return modelView;
    	}
    	
    	ModelAndView modelView = new ModelAndView("materias");
        materiaDTO.setEstado(true);
      //buscamos el docente y la carrera seleccionada
        docenteDTO = docenteService.findById(materiaDTO.getDocente().getIdDocente());
        carreraDTO = carreraService.findById(materiaDTO.getCarrera().getIdCarrera());
        //seteamos el Objeto a materiaDTO
        materiaDTO.setDocente(docenteMapper.toDocente(docenteDTO));  
        materiaDTO.setCarrera(carreraMapper.toCarrera(carreraDTO));
      //guardamos materia en la BD
        materiaService.save(materiaDTO);
      //agregamos los atributos necesarios para la vista
        modelView.addObject("materias",materiaService.findByEstado(true));
        modelView.addObject("titulo", "Materias");
        modelView.addObject("isAdded", true);
        return modelView;
    }

    /**
     * Metodo que permite mostrar la pagina de editar materia
     *
     * @param model  objeto que permite agregar atributos y enviarlos a la vista
     * @param id codigo de la materia a editar
     * @return la vista materia-form.html
     */
    // directamente asigno el resultado de la busqueda a materiaEncontrada
    
    @GetMapping("/editar-materia/{id}")
    public String getEditarMateriaPage(Model model, @PathVariable(value = "id") Long id) {
        boolean edicion = true;
        MateriaDTO materiaEncontrada = materiaService.findById(id);
        model.addAttribute("titulo", "Materias");
        model.addAttribute("edicion", edicion);
        model.addAttribute("materia", materiaEncontrada);
        model.addAttribute("carreras", carreraService.findByEstado(true));
        model.addAttribute("docentes", docenteService.findByEstado(true));
        return "materia-form";
    }

    /**
     * Metodo que permite modificar una materia
     *
     * @param materiaDTO objeto que representa una materia a modificar con sus nuevos datos
     * @return la vista materias.html
     */
    @PostMapping("modificar-materia")
    public String modificarMateria(Model model, @Valid @ModelAttribute("materia") MateriaDTO materiaDTO, BindingResult result,RedirectAttributes redirectAttributes) {
    	
    	if(result.hasErrors()) {
    		
    		model.addAttribute("titulo", "Editar Materia");
            model.addAttribute("edicion", true);
            model.addAttribute("materia", materiaDTO);
            model.addAttribute("carreras", carreraService.findByEstado(true));
            model.addAttribute("docentes", docenteService.findByEstado(true));
            
            return "materia-form";
    	}
    	//obtenemos los objetos de carrera y docene seleccionados y los seteamos a materiaDTO
    	docenteDTO = docenteService.findById(materiaDTO.getDocente().getIdDocente());
        carreraDTO = carreraService.findById(materiaDTO.getCarrera().getIdCarrera());
        materiaDTO.setDocente(docenteMapper.toDocente(docenteDTO));  
        materiaDTO.setCarrera(carreraMapper.toCarrera(carreraDTO));
        //guardamos en la base de datos
        materiaService.edit(materiaDTO);
        
        redirectAttributes.addFlashAttribute("isUpdated", true);
        
        return "redirect:/materias/listado";
    }

    /**
     * Metodo que permite eliminar una materia
     *
     * @param id codigo de la materia a eliminar
     * @return la vista materias.html
     */
    @GetMapping("/eliminar-materia/{id}")
    public String eliminarMateria(@PathVariable(value = "id") Long id) {
        materiaService.deleteById(id);
        return "redirect:/materias/listado";
    }

}
