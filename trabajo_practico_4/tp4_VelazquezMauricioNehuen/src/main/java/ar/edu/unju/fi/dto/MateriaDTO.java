package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MateriaDTO {
	
	private Long idMateria;
	
	@NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 100, message = "El nombre debe tener mas de 5 caracteres")
	private String nombre;
	
	@NotBlank(message = "El código es obligatorio")
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres")
    private String codigo;
	
	@NotBlank(message = "El curso es obligatorio")
    private String curso;
	
	@NotNull(message = "La cantidad de horas es obligatoria")
	@Min(value = 1, message = "La cantidad de horas debe ser mayor que 0")
	@Max(value = 12, message = "La cantidad de horas no puede ser mayor a 12")
    private Byte cantidadHoras;
	
	@NotBlank(message = "La modalidad es obligatoria")
    private String modalidad;
	
    private boolean estado;
    
    private Docente docente;
    
    private Carrera carrera;
}
