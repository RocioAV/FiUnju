package ar.edu.unju.fi.carrera;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.mapper.CarreraMapper;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;


@SpringBootTest
class InsertCarrera {
	
	@Autowired
	private CarreraMapper carreraMapper;
	
	@Autowired
	private CarreraRepository carreraRepository;

	@Test
	void guardarCarrera() {
		
		// Crear el objeto CarreraDTO con los datos a insertar
        CarreraDTO carreraDTO = new CarreraDTO(null, "CS101", "Ciencia de Datos", (byte) 4, true);

        // Convertir CarreraDTO a Carrera utilizando el mapper
        Carrera carrera = carreraMapper.toCarrera(carreraDTO);

        // Guardar el objeto Carrera en la base de datos
        carreraRepository.save(carrera);
		
	}

}
