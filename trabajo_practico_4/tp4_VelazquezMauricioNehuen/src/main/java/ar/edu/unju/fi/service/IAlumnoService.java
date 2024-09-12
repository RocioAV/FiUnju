package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

public interface IAlumnoService {

	List<AlumnoDTO> findAll();
	
	AlumnoDTO findById(Long id);
	
	// Posible cambio a boolean
	Alumno save(AlumnoDTO alumnoDTO);
	
	void deleteById(Long id);
	
	void edit(AlumnoDTO alumnoDTO);
	
	List<AlumnoDTO> findByCarrera(Long id);

	List<AlumnoDTO> findByMateria(Long id);
	
	List<AlumnoDTO> findByEstado(boolean estado);
}
