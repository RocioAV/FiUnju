package ar.edu.unju.fi.service;

import java.util.List;
import java.util.UUID;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

public interface IMateriaService {
	List<MateriaDTO> findAll();
	
	MateriaDTO findById(Long id);
	
	Materia save(MateriaDTO materiaDTO);
	
	void deleteById(Long id);
	
	void edit(MateriaDTO materiaDTO);
	
	List<MateriaDTO> findByAlumno(Long id);
	
	List<MateriaDTO> findByEstado(boolean estado);
}
