package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.collections.CollectionAlumno;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.IMateriaService;

@Service("materiaServiceCollection")
public class MateriaServiceImpl implements IMateriaService {

	@Autowired
	private MateriaMapper materiaMapper;
	
	@Override
	public List<MateriaDTO> findAll() {
		List<MateriaDTO> materiaDTO = materiaMapper.toMateriaDTOList(CollectionMateria.getMaterias());
		return materiaDTO;
	}

	@Override
	public MateriaDTO findById(Long id) {
		MateriaDTO materiaDTO = materiaMapper.toMateriaDTO(CollectionMateria.buscarMateria(id));
		return materiaDTO;
	}

	@Override
	public Materia save(MateriaDTO materiaDTO) {
		CollectionMateria.agregarMateria(materiaMapper.toMateria(materiaDTO));
		return CollectionMateria.buscarMateria(materiaDTO.getIdMateria());
	}

	@Override
	public void deleteById(Long id) {
		CollectionMateria.eliminarMateria(id);
	}

	@Override
	public void edit(MateriaDTO materiaDTO) {
		CollectionMateria.modificarMateria(materiaMapper.toMateria(materiaDTO));
	}

	@Override
	public List<MateriaDTO> findByAlumno(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MateriaDTO> findByEstado(boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

}
