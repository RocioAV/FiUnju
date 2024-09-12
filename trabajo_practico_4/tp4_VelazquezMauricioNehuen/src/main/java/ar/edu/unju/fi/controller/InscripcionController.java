package ar.edu.unju.fi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.IMateriaService;

@Controller
@RequestMapping("/inscripcion")
public class InscripcionController {
	
	@Autowired
    private AlumnoDTO alumnoDTO;
	//CONSULTAR LOGICAAAAA
	@Qualifier("materiaServiceMySql")
    @Autowired
    private IMateriaService materiaService;
	
	@Qualifier("alumnoServiceMySql")
	@Autowired
	private IAlumnoService alumnoService;
	
	@Autowired
	private MateriaMapper materiaMapper;
	
	@GetMapping("/carreras")
    public String formInscripciones(Model model) {
		boolean getMat = false;
		model.addAttribute("getMat", getMat);
		model.addAttribute("materias", materiaService.findAll());
        model.addAttribute("titulo", "Inscripciones");
        model.addAttribute("alumno", alumnoDTO);
        return "inscripcionAlumno";
    }
	
	@PostMapping("/validar")
	public String validarAlumno(@ModelAttribute("alumno") AlumnoDTO alumnoDTO, Model model,RedirectAttributes redirectAttributes) {
		//boolean getMat = true;
		List<AlumnoDTO> alumnos = alumnoService.findAll();
		//List<Materia> materias = new ArrayList<>();
		for (AlumnoDTO a : alumnos) {
            if (a.getLu().equals(alumnoDTO.getLu())) {
            	alumnoDTO = a;
            	redirectAttributes.addFlashAttribute("encontrado", true);
            	redirectAttributes.addFlashAttribute("isUpdated", false);
                redirectAttributes.addFlashAttribute("isNotUpdated", false);
            	return "redirect:/inscripcion/select_carrera/"+ alumnoDTO.getIdAlumno();
            }else {
            redirectAttributes.addFlashAttribute("isUpdated", false);
            redirectAttributes.addFlashAttribute("isNotUpdated", true);
            }
		}
		return "redirect:/inscripcion/carreras";
	}
	
	
	  @GetMapping("/select_carrera/{id}") 
	  public String selectCarrera(Model model, @PathVariable(value = "id") Long id) {
		  boolean getMat = true;
		  AlumnoDTO alumnoEncontrado = alumnoService.findById(id);
		  List<MateriaDTO> materiasDisponibles = materiaMapper.toMateriaDTOList(alumnoEncontrado.getCarrera().getMaterias());
//		  System.out.println("MATERIAS CARERAAAA");
//		  for (MateriaDTO MATCAR : materiasDisponibles) {
//	            System.out.println(MATCAR.toString());
//	        }
		  List<MateriaDTO> materiasAlumno = alumnoEncontrado.getMaterias();
//		  for (MateriaDTO matAlu : materiasAlumno) {
//	            System.out.println(matAlu.toString());
//	        }
		  List<MateriaDTO> materiasCarrera = materiaMapper.toMateriaDTOList(alumnoEncontrado.getCarrera().getMaterias());
		  for (MateriaDTO materiaCarr : materiasCarrera) {
			  for (MateriaDTO materiaAlu : materiasAlumno) {
				  if (materiaCarr.getIdMateria().equals(materiaAlu.getIdMateria())) {
					  materiasDisponibles.remove(materiaCarr);
					  break;
				  }
			  }
		  }
//		  for (MateriaDTO MATCAR : materiasDisponibles) {
//	            System.out.println(MATCAR.toString());
//	        }
		 // model.addAttribute("materias", materiasAlumno);
		  model.addAttribute("materias", materiasDisponibles);
		  model.addAttribute("getMat", getMat);
		  model.addAttribute("titulo", "Inscripciones");
		  model.addAttribute("alumno", alumnoEncontrado); 
		  return "inscripcionAlumno"; 
	}
	 
//	  @GetMapping("/inscripcion-materia/{id}")
//	    public String getInscripcionAlumnoPage(Model model, @PathVariable(value = "id") Long id) {
//	    	
//	    	AlumnoDTO alumnoEncontrado = alumnoService.findById(id); 
//	     // Obtener la carrera del alumno
//	        CarreraDTO carreraAlumno = alumnoEncontrado.getCarrera();
//
//	        // Filtrar las materias disponibles para la carrera del alumno que aún no ha cursado
//	        List<MateriaDTO> materiasDisponibles = materiaService.findByEstado(true).stream().filter(materia -> materia.getCarrera().getIdCarrera().equals(carreraAlumno.getIdCarrera())).collect(Collectors.toList());
//	        List<MateriaDTO> materiasInscriptas = materiaService.findByAlumno(id);
//	        
//	        materiasDisponibles.removeAll(materiasInscriptas);
//	        model.addAttribute("titulo", "Inscripcion");
//	        model.addAttribute("alumno", alumnoEncontrado );
//	        model.addAttribute("carreras", carreraService.findByEstado(true));
//	        model.addAttribute("materias", materiasDisponibles);
//	      
//	    	return "inscripcion-AM-form";
//	    	
//	    }
	  
	@PostMapping("/inscribir")
	public String editarAlumno(@ModelAttribute("alumno") AlumnoDTO alumnoDTO, @RequestParam("idAlumno") Long alumnoId, @RequestParam("MateriaID") Long materiaId, RedirectAttributes redirectAttributes) {
			MateriaDTO materiaDTO = materiaService.findById(materiaId);
			AlumnoDTO alumnoEncontrado = alumnoService.findById(alumnoId);
            if(alumnoEncontrado.getMaterias().isEmpty()) {
            	List<MateriaDTO> materiasDTO = new ArrayList<>();
            	materiasDTO.add(materiaDTO);
          		alumnoEncontrado.setMaterias(materiasDTO);
            }else {
            	alumnoEncontrado.getMaterias().add(materiaDTO);
            }
            //System.out.println(alumnoEncontrado.toString());
            alumnoService.edit(alumnoEncontrado);
            redirectAttributes.addFlashAttribute("isUpdated", true);
            redirectAttributes.addFlashAttribute("isNotUpdated", false);
	    return "redirect:/inscripcion/carreras";
	}
	
}
