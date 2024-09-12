package ar.edu.unju.fi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
	
	@Query(value = "SELECT * FROM alumnos WHERE alu_estado = ?1", nativeQuery = true)
    List<Alumno> findByEstado(boolean estado);
	
	@Query("SELECT a FROM Alumno a WHERE a.carrera.idCarrera = :id AND a.estado = true")
	List<Alumno> findByCarrera(Long id);

	@Query(value = "SELECT a.* FROM alumnos a JOIN alumnos_materias am ON a.alu_id = am.alu_id WHERE am.mat_id = :id AND a.alu_estado = true", nativeQuery = true)
	List<Alumno> findByMateria(Long id);

}
