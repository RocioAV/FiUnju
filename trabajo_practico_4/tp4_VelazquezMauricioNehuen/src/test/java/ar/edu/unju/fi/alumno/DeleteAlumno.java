package ar.edu.unju.fi.alumno;


import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.mapper.AlumnoMapper;

@SpringBootTest
class DeleteAlumno {

	@Autowired
    private AlumnoMapper alumnoMapper;

    @Autowired
    private AlumnoRepository alumnoRepository;
	
	@Test
	void deleteAlumno() {
		Alumno alumno = alumnoRepository.findById(3L).get();
		alumno.setEstado(false);
		alumnoRepository.save(alumno);
	}

}
