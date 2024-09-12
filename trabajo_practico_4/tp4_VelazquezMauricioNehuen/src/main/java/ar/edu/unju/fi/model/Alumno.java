package ar.edu.unju.fi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alu_id")
    private Long idAlumno;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(min=7, max=8, message = "El DNI debe tener entre 7 y 8 dígitos")
    @Column(name = "alu_dni")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener mas de 2 caracteres")
    @Column(name = "alu_nombre")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener mas de 2 caracteres")
    @Column(name = "alu_apellido")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Column(name = "alu_email")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min=10, message = "El teléfono debe tener entre 10 y 15 dígitos")
    @Column(name = "alu_telefono")
    private String telefono;

    @Past(message="La fecha debe ser anterior a la fecha actual")
    @NotNull(message="La fecha de nacimiento es obligatoria")
    @Column(name = "alu_fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El domicilio es obligatorio")
    @Size(min = 5, max = 100, message = "El domicilio debe tener mas de 5 caracteres")
    @Column(name = "alu_domicilio")
    private String domicilio;

    @NotBlank(message = "El LU no puede estar vacío")
	@Size(min = 4, max = 20, message = "El LU debe tener mas de 4 caracteres")
    @Column(name = "alu_lu")
    private String lu;

    @Column(name = "alu_estado")
    private boolean estado;

    @ManyToMany
    @JoinTable(
            name = "alumnos_materias",
            joinColumns = @JoinColumn(name = "alu_id"),
            inverseJoinColumns = @JoinColumn(name = "mat_id")
    )
    List<Materia> materias = new ArrayList<Materia>();

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Carrera carrera;
    
    
    
    @Override
    public String toString() {
        return "Alumno{" +
               "idAlumno=" + idAlumno +
               ", dni='" + dni + '\'' +
               ", nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", email='" + email + '\'' +
               ", telefono='" + telefono + '\'' +
               ", fechaNacimiento=" + fechaNacimiento +
               ", domicilio='" + domicilio + '\'' +
               ", lu='" + lu + '\'' +
               ", estado=" + estado +
               // No incluir la relación carrera directamente en toString()
               '}';
    }
    
    
}
