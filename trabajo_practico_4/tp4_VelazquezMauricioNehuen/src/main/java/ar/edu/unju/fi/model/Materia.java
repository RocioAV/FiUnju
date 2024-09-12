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
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mat_id")
    private Long idMateria;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 100, message = "El nombre debe tener mas de 5 caracteres")
    @Column(name = "mat_nombre")
    private String nombre;

    @NotBlank(message = "El código es obligatorio")
    @Size(min = 3, max = 10, message = "El código debe tener entre 3 y 10 caracteres")
    @Column(name = "mat_codigo")
    private String codigo;

    @NotBlank(message = "El curso es obligatorio")
    @Column(name = "mat_curso")
    private String curso;

    @NotNull(message = "La cantidad de horas es obligatoria")
    @Min(value = 1, message = "La cantidad de horas debe ser mayor que 0")
    @Max(value = 12, message = "La cantidad de horas no puede ser mayor a 12")
    @Column(name = "mat_horas")
    private Byte cantidadHoras;

    @NotBlank(message = "La modalidad es obligatoria")
    @Column(name = "mat_modalidad")
    private String modalidad;

    @Column(name = "mat_estado")
    private boolean estado;

    @OneToOne
    @JoinColumn(name = "doc_id")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Carrera carrera;

    @ManyToMany(mappedBy = "materias")
    List<Alumno> alumnos = new ArrayList<Alumno>();
}
