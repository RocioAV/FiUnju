package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.mapper.DocenteMapper;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IDocenteService;

@Service("docenteServiceMySql")
public class DocenteServiceImp implements IDocenteService {
	
	private static Logger logger = LoggerFactory.getLogger(DocenteServiceImp.class);
	
	 @Autowired
	 private DocenteRepository docenteRepository;
	 @Autowired 
	 private DocenteMapper docenteMapper;
	 
	 //se inyects materia
	 
	 @Autowired
	    private MateriaRepository materiaRepository;

	@Override
	public List<DocenteDTO> findAll() {
		return docenteMapper.toDocenteDTOList(docenteRepository.findAll());
		
	}

	@Override
	public DocenteDTO findById(Long id) {
		DocenteDTO docenteDTO =docenteMapper.toDocenteDTO(docenteRepository.findById(id).get());
		if (docenteDTO != null) {
			logger.info("Docente encontrado con éxito: " + docenteDTO.getNombre());
		} else {
			logger.error("¡Docente NO encontrado!");
		}
		return docenteDTO;
	}

	@Override
	public Docente save(DocenteDTO docenteDTO) {
		Docente docente = docenteRepository.save(docenteMapper.toDocente(docenteDTO));
		if (docente != null) {
			logger.info("¡Docente guardado exitosamente!");
		} else {
			logger.error("¡Docente NO se pudo guardar!");
		}
		return docente;
	}

	@Override
	public void deleteById(Long id) {
		Docente docente = docenteRepository.findById(id).get();
		if (docente != null) {
			docente.setEstado(false);
			docenteRepository.save(docente);
			logger.warn("Docente ELIMINADO (desactivado) con éxito.");
		} else {
			logger.error("¡Docente NO se pudo eliminar!");
		}
		
		
	}

	@Override
	public void edit(DocenteDTO docenteDTO) {
		Docente docente=docenteRepository.save(docenteMapper.toDocente(docenteDTO));
		if (docente != null) {
			logger.info("¡Docente modificado con éxito!");
		} else {
			logger.error("¡Docente NO se pudo modificar!");
		}
	}
	
	

	@Override
	public List<DocenteDTO> findDocentesSinMateria() {
		List<DocenteDTO> docentesDtos = docenteMapper.toDocenteDTOList(docenteRepository.findDocentesSinMateria());
		if (docentesDtos.isEmpty()) {
			logger.warn("No existen docentes sin materias asignadas.");
		} else {
			logger.info("Docentes sin materias asignadas recuperados con éxito.");
		}
		return docentesDtos;
	}

	@Override
	public List<DocenteDTO> findByEstado(boolean estado) {
		List<DocenteDTO> docentesDtos = docenteMapper.toDocenteDTOList(docenteRepository.findByEstado(estado));
		if (docentesDtos.isEmpty()) {
			logger.warn("No existen docentes con el estado especificado.");
		} else {
			logger.info("Docentes con estado " + estado + " recuperados con éxito.");
		}
		return docentesDtos;
	}

}
