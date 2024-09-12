package ar.edu.unju.fi.alumno;

import java.time.LocalDate;

import ar.edu.unju.fi.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;

@SpringBootTest
class EditAlumno {

	@Autowired
    private AlumnoMapper alumnoMapper;

    @Autowired
    private AlumnoRepository alumnoRepository;
	
	@Test
	void editAlumno() {
		Alumno alumno= new Alumno(null,"40565353", "Nehuen", "Velazquezzzzzzzzzzz", "mauricio@correo.com", "388412345",
                LocalDate.of(1996, 10, 29), "Av. San Martin 123", "APU4853",true, null,  null);
		alumno.setIdAlumno(25L);

		alumnoRepository.save(alumno);
	}

}
