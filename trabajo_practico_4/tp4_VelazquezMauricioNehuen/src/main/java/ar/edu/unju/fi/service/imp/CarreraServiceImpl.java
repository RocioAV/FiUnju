package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.ICarreraService;


@Service("carreraServiceCollection")
public class CarreraServiceImpl implements ICarreraService {
	
	@Autowired
	private CarreraMapper carreraMapper;

	@Override
	public List<CarreraDTO> findAll() {
		// TODO Auto-generated method stub
		
		List<CarreraDTO> carrerasDtos = carreraMapper.toCarreraDTOList(CollectionCarrera.getCarreras());
		
		return carrerasDtos;
	}

	@Override
	public CarreraDTO findById(Long id ) {
		CarreraDTO carreraDTO = carreraMapper.toCarreraDTO(CollectionCarrera.buscarCarrera(id));
		
		return carreraDTO;
	}

	@Override
	public Carrera save(CarreraDTO carreraDTO) {
		CollectionCarrera.agregarCarrera(carreraMapper.toCarrera(carreraDTO));
		return CollectionCarrera.buscarCarrera(carreraDTO.getIdCarrera());
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		CollectionCarrera.eliminarCarerra(id);

	}

	@Override
	public void edit(CarreraDTO carreraDTO) {
		CollectionCarrera.modificarCarrera(carreraMapper.toCarrera(carreraDTO));
	}

	@Override
	public List<CarreraDTO> findByEstado(boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}



}
