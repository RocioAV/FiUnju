package ar.edu.unju.fi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carreras")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long idCarrera;

    @NotBlank(message = "El código es obligatorio")
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres")
    @Column(name = "car_codigo")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 100, message = "El nombre debe tener mas de 5 caracteres")
    @Column(name = "car_nombre")
    private String nombre;

    @NotNull(message = "La cantidad de años es obligatoria")
    @Min(value = 1, message = "La cantidad de años debe ser mayor que 0")
    @Max(value = 8, message = "La cantidad de años debe ser menor que 8")
    @Column(name = "car_duracion")
    private Byte cantidadAnios;

    @Column(name = "car_estado")
    private Boolean estado;

    @OneToMany(mappedBy = "carrera")
    List<Alumno> alumnos = new ArrayList<Alumno>();

    @OneToMany(mappedBy = "carrera")
    List<Materia> materias = new ArrayList<Materia>();
    
    
    @Override
    public String toString() {
        return "Carrera{" +
               "idCarrera=" + idCarrera +
               ", codigo='" + codigo + '\'' +
               ", nombre='" + nombre + '\'' +
               ", cantidadAnios=" + cantidadAnios +
               ", estado=" + estado +
               // No incluir la relación alumnos y materias directamente en toString()
               '}';
    }

}
