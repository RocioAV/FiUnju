package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.ICarreraService;

@Service("carreraServiceMySql")
public class CarreraServiceImp implements ICarreraService {

	private static Logger logger = LoggerFactory.getLogger(CarreraServiceImp.class);
	
	@Autowired
	private CarreraMapper carreraMapper;
	
	@Autowired
	private CarreraRepository carreraRepository;

	@Override
	public List<CarreraDTO> findAll() {
		List<CarreraDTO> carrerasDtos = carreraMapper.toCarreraDTOList(carreraRepository.findAll());
		
		return carrerasDtos;
	}

	@Override
	public CarreraDTO findById(Long id) {
		CarreraDTO carreraDTO = carreraMapper.toCarreraDTO(carreraRepository.findById(id).get());
		if (carreraDTO != null) {
			logger.info("Carrera encontrada con éxito: " + carreraDTO.getNombre());
		} else {
			logger.error("¡Carrera NO encontrada!");
		}
		return carreraDTO;
	}
	

	@Override
	public Carrera save(CarreraDTO carreraDTO) {
		Carrera carrera = carreraRepository.save(carreraMapper.toCarrera(carreraDTO));
		if (carrera != null) {
			logger.info("¡Carrera guardada exitosamente!");
		} else {
			logger.error("¡Carrera NO se pudo guardar!");
		}
		return carrera;
	}


	
	@Override
	public void edit(CarreraDTO carreraDTO) {
		Carrera carrera =carreraRepository.save(carreraMapper.toCarrera(carreraDTO));
		if (carrera != null) {
			logger.info("¡Carrera modificada con éxito!");
		} else {
			logger.error("¡Carrera NO se pudo modificar!");
		}
	}
	

	@Override
	public void deleteById(Long id) {
		Carrera carrera = carreraRepository.findById(id).get();
		if (carrera != null) {
			carrera.setEstado(false);
			carreraRepository.save(carrera);
			logger.warn("Carrera ELIMINADA (desactivada) con éxito.");
		} else {
			logger.error("¡Carrera NO se pudo eliminar!");
		}
		
	}
	
	@Override
	public List<CarreraDTO> findByEstado(boolean estado) {
		List<CarreraDTO> carrerasDtos = carreraMapper.toCarreraDTOList(carreraRepository.findByEstado(estado));
		if (carrerasDtos.isEmpty()) {
			logger.warn("No existen carreras con el estado especificado.");
		} else {
			logger.info("Carreras con estado " + estado + " recuperadas con éxito.");
		}
		return carrerasDtos;
	}
	
}
