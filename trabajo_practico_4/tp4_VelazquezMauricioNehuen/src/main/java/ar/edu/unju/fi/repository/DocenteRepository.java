package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.model.Docente;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

	@Query(value = "SELECT * FROM docentes WHERE doc_estado = ?1", nativeQuery = true)
    List<Docente> findByEstado(boolean estado);
	
	@Query("SELECT d FROM Docente d WHERE d.idDocente NOT IN (SELECT m.docente.idDocente FROM Materia m) AND d.estado = true")
    List<Docente> findDocentesSinMateria();
}
