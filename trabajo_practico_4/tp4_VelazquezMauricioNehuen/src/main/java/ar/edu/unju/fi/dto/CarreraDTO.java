package ar.edu.unju.fi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CarreraDTO {
	
	private Long idCarrera;
	
	@NotBlank(message = "El código es obligatorio")
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres")
    private String codigo;
	
	@NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 100, message = "El nombre debe tener mas de 5 caracteres")
    private String nombre;
	
	@NotNull(message = "La cantidad de años es obligatoria")
    @Min(value = 1, message = "La cantidad de años debe ser mayor que 0")
	@Max(value = 8, message = "La cantidad de años debe ser menor que 8")
    private Byte cantidadAnios;
	
    private Boolean estado;
}
