package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MateriaMapper {

	MateriaDTO toMateriaDTO(Materia materia);

	Materia toMateria(MateriaDTO materiaaDTO);

    List<MateriaDTO> toMateriaDTOList (List<Materia> materias);

    List<Materia> toMateriaList (List<MateriaDTO> materiasDTO);
}
