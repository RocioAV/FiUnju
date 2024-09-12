package ar.edu.unju.fi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.model.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
	@Query(value = "SELECT * FROM carreras WHERE car_estado = ?1", nativeQuery = true)
    List<Carrera> findByEstado(boolean estado);
}
