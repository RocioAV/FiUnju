package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.IAlumnoService;

@Service ("alumnoServiceMySql")
public class AlumnoServiceImp implements IAlumnoService {
	
	private static Logger logger = LoggerFactory.getLogger(AlumnoServiceImp.class);
	
	 @Autowired
	 private AlumnoRepository alumnoRepository;
	 @Autowired 
	 private AlumnoMapper alumnoMapper;

	@Override
	public List<AlumnoDTO> findAll() {
		return alumnoMapper.toAlumnoDTOList(alumnoRepository.findAll());
		
	}

	@Override
	public AlumnoDTO findById(Long id) {
		AlumnoDTO alumno= alumnoMapper.toAlumnoDTO(alumnoRepository.findById(id).get());
		if(alumno != null) {
			logger.info("Alumno encontrado con exito");
		}else {
			logger.error("¡Alumno NO encontrado!");
		}
		return alumno;
	}

	@Override
	public Alumno save(AlumnoDTO alumnoDTO) {
		Alumno alumno=alumnoRepository.save(alumnoMapper.toAlumno(alumnoDTO));
		if(alumno != null) {
			logger.info("¡Alumno guardado exitosamente!");
		}else {
			logger.error("¡Alumno NO se pudo guardar!");
		}
		return alumno;
	}

	@Override
	public void deleteById(Long id) {
		Alumno alumno = alumnoRepository.findById(id).get();
		if(alumno != null) {
			alumno.setEstado(false);
			alumnoRepository.save(alumno);
			logger.warn("Alumno ELIMINADO");
		}else {
			logger.error("Alumno NO se pudo eliminar");
		}
		
		
	}

	@Override
	public void edit(AlumnoDTO alumnoDTO) {
		logger.info("¡Alumno modificado!");
		alumnoRepository.save(alumnoMapper.toAlumno(alumnoDTO));
		
	}

	@Override
	public List<AlumnoDTO> findByCarrera(Long id) {
		List<AlumnoDTO> alumnos=alumnoMapper.toAlumnoDTOList(alumnoRepository.findByCarrera(id));
		if(alumnos.isEmpty()) {
			logger.warn("No existen alumnos inscriptos en esta carrera");
		}else {
			logger.info("Alumnos inscriptos en esta carrera recuperados con exito");
		}
		return alumnos;
	}

	@Override
	public List<AlumnoDTO> findByMateria(Long id) {
		List<AlumnoDTO> alumnos=alumnoMapper.toAlumnoDTOList(alumnoRepository.findByMateria(id));
		if(alumnos.isEmpty()) {
			logger.warn("No existen alumnos inscriptos en esta materia");
		}else {
			logger.info("Alumnos inscriptos en esta materia recuperados con exito");
		}
		return alumnos;
	}

	@Override
	public List<AlumnoDTO> findByEstado(boolean estado) {
		List<AlumnoDTO> alumnos= alumnoMapper.toAlumnoDTOList(alumnoRepository.findByEstado(estado));
		if(alumnos.isEmpty()) {
			logger.warn("No existen alumnos dados de alta");
		}else {
			logger.info("Alumnos activos recuperados con exito");
		}
		return alumnos;
	}
}
