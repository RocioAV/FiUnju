package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocenteMapper {

	DocenteDTO toDocenteDTO(Docente docente);

	Docente toDocente(DocenteDTO docenteDTO);

    List<DocenteDTO> toDocenteDTOList (List<Docente> docentes);

    List<Docente> toDocenteList (List<DocenteDTO> docentesDTO);
}