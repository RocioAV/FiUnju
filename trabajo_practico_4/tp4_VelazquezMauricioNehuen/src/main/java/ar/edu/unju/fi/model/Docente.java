package ar.edu.unju.fi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "docentes")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long idDocente;

    @NotBlank(message = "El legajo no puede estar vacío")
    @Size(min = 4, max = 20, message = "El legajo debe tener entre 4 y 20 caracteres")
    @Column(name = "doc_legajo")
    private String legajo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min=2, message="El nombre debe tener mas de 2 caracteres")
    @Size(max = 40, message = "El nombre no puede tener más de 40 caracteres")
    @Column(name = "doc_nombre")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min=2, message="El apellido debe tener mas de 2 caracteres")
    @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
    @Column(name = "doc_apellido")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    @Column(name = "doc_email")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min=10, max=15, message = "El teléfono debe tener entre 10 y 15 dígitos")
    @Column(name = "doc_telefono")
    private String telefono;

    @Column(name = "doc_estado")
    private boolean estado;

    @OneToOne(mappedBy = "docente")
    private Materia materia;
    

    
}
