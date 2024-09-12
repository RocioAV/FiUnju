package ar.edu.unju.fi.dto;


import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Materia;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DocenteDTO {
	
	private Long idDocente;
	
	@NotBlank(message = "El legajo no puede estar vacío")
	@Size(min = 4, max = 20, message = "El legajo debe tener entre 4 y 20 caracteres")
	private String legajo;
	
	@NotBlank(message = "El nombre no puede estar vacío")
    @Size(min=2, message="El nombre debe tener mas de 2 caracteres")
    @Size(max = 40, message = "El nombre no puede tener más de 40 caracteres")
    private String nombre;
	
	@NotBlank(message = "El apellido no puede estar vacío")
    @Size(min=2, message="El apellido debe tener mas de 2 caracteres")
    @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
    private String apellido;
	
	@NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;
	
	@NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min=10, max=15, message = "El teléfono debe tener entre 10 y 15 dígitos")
    private String telefono;
	
    private boolean estado;
    
    private Materia materia;
}
