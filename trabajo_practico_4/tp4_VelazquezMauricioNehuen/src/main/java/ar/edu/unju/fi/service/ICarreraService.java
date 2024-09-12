package ar.edu.unju.fi.service;

import java.util.List;
import java.util.UUID;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

public interface ICarreraService {
	
	List<CarreraDTO> findAll();
	
	CarreraDTO findById(Long id);
	
	Carrera save(CarreraDTO carreraDTO);
	
	void deleteById(Long id);
	 
	void edit(CarreraDTO carreraDTO);
	
	List<CarreraDTO> findByEstado(boolean estado);

}
