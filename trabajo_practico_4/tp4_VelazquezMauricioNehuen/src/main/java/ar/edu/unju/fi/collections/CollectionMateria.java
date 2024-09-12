package ar.edu.unju.fi.collections;

import ar.edu.unju.fi.model.Materia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CollectionMateria {

    private static List<Materia> materias = new ArrayList<>();

    /**
     * Devuelve un arrayList de objetos de la clase Materia
     *
     * @return Objeto materia
     */
    public static List<Materia> getMaterias() {
        return materias;
    }

    /**
     * Agrega un objeto de la clase Materia al arrayList
     *
     * @param materia Objeto de la clase Materia
     */
    public static boolean agregarMateria(Materia materia) {
        materias.add(materia);
        return true;
    }

    /**
     * Elimina un objeto de la clase Materia en el arrayList
     *
     * @param id Objeto de la clase Materia con atributos modificados
     */
    public static void eliminarMateria(Long id) {
        Iterator<Materia> iterator = materias.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getIdMateria().equals(id)) {
                iterator.remove();
            }
        }
    }

    /**
     * Modifica un objeto de la clase Materia en el arrayList
     *
     * @param materia Objeto de la clase Materia
     */
    public static void modificarMateria(Materia materia) {
        for (Materia m : materias) {
            if (m.getCodigo().equals(materia.getCodigo())) {
                m.setNombre(materia.getNombre());
                m.setCurso(materia.getCurso());
                m.setCantidadHoras(materia.getCantidadHoras());
                m.setModalidad(materia.getModalidad());
                m.setDocente(materia.getDocente());
                m.setCarrera(materia.getCarrera());
            }
        }
    }

    /**
     * Busca un objeto de la clase Materia en el arrayList
     *
     * @param id Objeto de la clase Materia con atributos modificados
     * @return Objeto de la clase Materia
     */
    public static Materia buscarMateria(Long id) {
        Predicate<Materia> filterCodigo = m -> m.getIdMateria().equals(id);
        Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
        return materia.orElse(null);
    }


}
