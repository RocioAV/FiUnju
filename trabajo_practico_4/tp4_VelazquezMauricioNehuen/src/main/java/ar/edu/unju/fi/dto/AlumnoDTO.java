package ar.edu.unju.fi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {

	private Long idAlumno;
	
	@NotBlank(message = "El DNI es obligatorio")
    @Size(min=7, max=8, message = "El DNI debe tener entre 7 y 8 dígitos")
    private String dni;
	
	@NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener mas de 2 caracteres")
    private String nombre;
	
	@NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener mas de 2 caracteres")
    private String apellido;
	
	@NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;
	
	@NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min=10, message = "El teléfono debe tener entre 10 y 15 dígitos")
    private String telefono;
	
	@NotBlank(message="La fecha de nacimiento es obligatoria")
    private String fechaNacimiento;
	
	@NotBlank(message = "El domicilio es obligatorio")
    @Size(min = 5, max = 100, message = "El domicilio debe tener mas de 5 caracteres")
    private String domicilio;
	
	@NotBlank(message = "El LU no puede estar vacío")
	@Size(min = 4, max = 20, message = "El LU debe tener mas de 4 caracteres")
    private String lu;
	
    private boolean estado;
    
    @NotNull(message="La carrera no puede ser null")
    private Carrera carrera; // Nuevo campo para almacenar la entidad de la carrera asociada
    
    private List<MateriaDTO> materias;
}
