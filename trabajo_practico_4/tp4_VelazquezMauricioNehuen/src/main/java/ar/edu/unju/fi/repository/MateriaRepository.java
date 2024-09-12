package ar.edu.unju.fi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

//	 @Query(value = "SELECT m.* FROM materias m JOIN alumnos_materias am ON m.mat_id = am.mat_id WHERE am.alu_id = ?1", nativeQuery = true)
//	    List<Materia> findByAlumnoId(Long alumnoId);
	 @Query(value = "SELECT m.* FROM materias m JOIN alumnos_materias am ON m.mat_id = am.mat_id WHERE am.alu_id = ?1 AND m.mat_estado = true", nativeQuery = true)
	 List<Materia> findByAlumnoId(Long alumnoId);

	 
	 @Query(value = "SELECT * FROM materias WHERE mat_estado = ?1", nativeQuery = true)
	    List<Materia> findByEstado(boolean estado);
}
