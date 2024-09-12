package ar.edu.unju.fi.alumno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;

@SpringBootTest
class InsertAlumno {

	@Autowired
    private AlumnoMapper alumnoMapper;

    @Autowired
    private AlumnoRepository alumnoService;
    @Autowired
    private MateriaRepository materiaRepository;

	@Test
	void guardarAlumno() {
		Alumno alumno= new Alumno(null,"40565353", "Nazarena", "Velazquez", "mauricio@correo.com", "388412345",
                LocalDate.of(1996, 10, 29), "Av. San Martin 123", "APU4853",true, null, null);

		List<Materia> materias = new ArrayList<>();

		//Materia materiaEcontrada = materiaRepository.findById(1L).get();
		//materias.add(materiaEcontrada);

		alumno.setMaterias(materias);

		alumnoService.save(alumno);
	}

}
