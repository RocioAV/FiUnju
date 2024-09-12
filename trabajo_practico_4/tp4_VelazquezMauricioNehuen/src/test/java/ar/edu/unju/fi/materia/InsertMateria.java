package ar.edu.unju.fi.materia;

import ar.edu.unju.fi.mapper.AlumnoMapper;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class InsertMateria {

    @Autowired
    private MateriaRepository materiaRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Test
	void guardarMateria() {
		Materia materia = new Materia();
		materia.setNombre("Matematica");
		materia.setCodigo("MAT123");
		materia.setCurso("Primero");
		materia.setCantidadHoras((byte)5);
		materia.setModalidad("Presencial");
		materia.setEstado(true);

		materia.setDocente(null);
		materia.setCarrera(null);

		List<Alumno> alumnos = new ArrayList<>();
		Alumno aluEncontrado = alumnoRepository.findById(1L).get();
		alumnos.add(aluEncontrado);

		materia.setAlumnos(alumnos);

		materiaRepository.save(materia);

	}

}
