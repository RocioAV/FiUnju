package ar.edu.unju.fi.collections;


import ar.edu.unju.fi.model.Alumno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class CollectionAlumno {

    private static List<Alumno> alumnos = new ArrayList<>();

    /**
     * Devuelve un arrayList de objetos de la clase Alumno
     *
     * @return Objeto alumno
     */
    public static List<Alumno> getAlumnos() {
        if (alumnos.isEmpty()) {
            alumnos.add(new Alumno((long) 1,"40565353", "Mauricio", "Velazquez", "mauricio@correo.com", "388412345",
                    LocalDate.of(1996, 10, 29), "Av. San Martin 123", "APU4853",true, null,  null));
            alumnos.add(new Alumno((long)2,"40564234", "Cristian", "Alvarez", "cristian@correo.com", "388412345",
                    LocalDate.of(2001, 5, 13), "Av. Perez 123", "APU8363",true, null, null));
            alumnos.add(new Alumno((long)3,"40347047", "Patricio", "Rey", "patricio@correo.com", "388412345",
                    LocalDate.of(1998, 3, 15), "Av. Fascio 666", "APU1234",true, null,  null));
        }
        return alumnos;
    }

    /**
     * Agrega un objeto de la clase Alumno al arrayList
     *
     * @param alumno Objeto de la clase Alumno
     */
    public static boolean agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
        	return true;
    }

    /**
     * Elimina un objeto de la clase Alumno en el arrayList
     *
     * @param id Objeto de la clase Alumno con atributos modificados
     */
    public static void eliminarAlumno(Long id) {
        alumnos.removeIf(alumno -> alumno.getIdAlumno().equals(id));
    }

    /**
     * Modifica un objeto de la clase Alumno en el arrayList
     *
     * @param alumno Objeto de la clase Alumno
     */
    public static void modificarAlumno(Alumno alumno) {
        for (Alumno a : alumnos) {
            if (a.getDni().equals(alumno.getDni())) {
                a.setNombre(alumno.getNombre());
                a.setApellido(alumno.getApellido());
                a.setEmail(alumno.getEmail());
                a.setTelefono(alumno.getTelefono());
                a.setFechaNacimiento(alumno.getFechaNacimiento());
                a.setDomicilio(alumno.getDomicilio());
                a.setLu(alumno.getLu());
            }
        }
    }

    /**
     * Busca un objeto de la clase Alumno en el arrayList
     *
     * @param id Objeto de la clase Alumno
     * @return Objeto de la clase Alumno
     */
    public static Alumno buscarAlumno(Long id) {
        Predicate<Alumno> filterCodigo = alumno -> alumno.getIdAlumno().equals(id);
        Optional<Alumno> alumno = alumnos.stream().filter(filterCodigo).findFirst();
        return alumno.orElse(null);
    }
}
