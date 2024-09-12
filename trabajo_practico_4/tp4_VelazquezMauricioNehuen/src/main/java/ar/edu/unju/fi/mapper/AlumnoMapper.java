package ar.edu.unju.fi.mapper;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;


import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;


@Mapper( componentModel = MappingConstants.ComponentModel.SPRING )
public interface AlumnoMapper {

    //Dejo fechaNacimiento porque en AlumnoDTO es un String y en Alumno es un LocalDate

    @Mappings({
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento", dateFormat = "yyyy-MM-dd")
    })
    AlumnoDTO toAlumnoDTO(Alumno alumno);

    @InheritInverseConfiguration
    Alumno toAlumno(AlumnoDTO alumnoDTO);

    List<AlumnoDTO> toAlumnoDTOList (List<Alumno> alumnos);

    List<Alumno> toAlumnoList (List<AlumnoDTO> alumnosDTO);


}
