package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;

@Service("materiaServiceMySql")
public class MateriaServiceImp implements IMateriaService {
	
	private static Logger logger = LoggerFactory.getLogger(MateriaServiceImp.class);

	@Autowired
	 private MateriaRepository materiaRepository;
	 @Autowired 
	 private MateriaMapper materiaMapper;
	
	@Override
	public List<MateriaDTO> findAll() {
		return materiaMapper.toMateriaDTOList(materiaRepository.findAll());
	}

	@Override
	public MateriaDTO findById(Long id) {
		MateriaDTO materiaDTO =materiaMapper.toMateriaDTO(materiaRepository.findById(id).get());
		if (materiaDTO != null) {
			logger.info("Materia encontrada con éxito: " + materiaDTO.getNombre());
		} else {
			logger.error("¡Materia NO encontrada!");
		}
		return materiaDTO;
	}

	@Override
	public Materia save(MateriaDTO materiaDTO) {
		Materia materia = materiaRepository.save(materiaMapper.toMateria(materiaDTO));
		if (materia != null) {
			logger.info("¡Materia guardada exitosamente!");
		} else {
			logger.error("¡Materia NO se pudo guardar!");
		}
		return materia;
	}

	@Override
	public void deleteById(Long id) {
		Materia materia = materiaRepository.findById(id).get();
		if (materia != null) {
			materia.setEstado(false);
			materiaRepository.save(materia);
			logger.warn("Materia ELIMINADA (desactivada) con éxito.");
		} else {
			logger.error("¡Materia NO se pudo eliminar!");
		}
		
	}

	@Override
	public void edit(MateriaDTO materiaDTO) {
		Materia materia =materiaRepository.save(materiaMapper.toMateria(materiaDTO));
		if (materia != null) {
			logger.info("¡Materia modificada con éxito!");
		} else {
			logger.error("¡Materia NO se pudo modificar!");
		}
	}

	@Override
	public List<MateriaDTO> findByAlumno(Long id) {
		List<MateriaDTO> materiasDtos = materiaMapper.toMateriaDTOList(materiaRepository.findByAlumnoId(id));
		if (materiasDtos.isEmpty()) {
			logger.warn("No existen materias asignadas al alumno con ID: " + id);
		} else {
			logger.info("Materias asignadas al alumno con ID " + id + " recuperadas con éxito.");
		}
		return materiasDtos;
	}

	@Override
	public List<MateriaDTO> findByEstado(boolean estado) {
		List<MateriaDTO> materiasDtos =materiaMapper.toMateriaDTOList(materiaRepository.findByEstado(estado));
		if (materiasDtos.isEmpty()) {
			logger.warn("No existen materias con el estado especificado.");
		} else {
			logger.info("Materias con estado " + estado + " recuperadas con éxito.");
		}
		return materiasDtos;
		
	}
}
