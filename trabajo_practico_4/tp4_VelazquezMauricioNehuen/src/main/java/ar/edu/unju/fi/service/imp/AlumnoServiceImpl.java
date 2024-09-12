package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.collections.CollectionAlumno;
import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.IAlumnoService;

@Service ("alumnoServiceCollection")
public class AlumnoServiceImpl implements IAlumnoService {

	@Autowired
	private AlumnoMapper alumnoMapper;
	
	@Override
	public List<AlumnoDTO> findAll() {
		List<AlumnoDTO> alumnosDTO = alumnoMapper.toAlumnoDTOList(CollectionAlumno.getAlumnos());
		return alumnosDTO;
	}

	@Override
	public AlumnoDTO findById(Long id) {
		AlumnoDTO alumnoDTO = alumnoMapper.toAlumnoDTO(CollectionAlumno.buscarAlumno(id));
		return alumnoDTO;
	}

	@Override
	public Alumno save(AlumnoDTO alumnoDTO) {
		CollectionAlumno.agregarAlumno(alumnoMapper.toAlumno(alumnoDTO));
		return CollectionAlumno.buscarAlumno(alumnoDTO.getIdAlumno());
	}

	@Override
	public void deleteById(Long id) {
		CollectionAlumno.eliminarAlumno(id);
		
	}

	@Override
	public void edit(AlumnoDTO alumnoDTO) {
		CollectionAlumno.modificarAlumno(alumnoMapper.toAlumno(alumnoDTO));
		
	}

	@Override
	public List<AlumnoDTO> findByCarrera(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlumnoDTO> findByMateria(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlumnoDTO> findByEstado(boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

}
