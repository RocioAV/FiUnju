package ar.edu.unju.fi.alumno;

import ar.edu.unju.fi.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.mapper.AlumnoMapper;

@SpringBootTest
class FindById {

	@Autowired
    private AlumnoMapper alumnoMapper;

    @Autowired
    private AlumnoRepository alumnoRepository;
	
	@Test
	void findAlumnos() {
		System.out.println(alumnoRepository.findById(25L));
	}

}
