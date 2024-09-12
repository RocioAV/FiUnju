package ar.edu.unju.fi.mapper;

import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapper {

    CarreraDTO toCarreraDTO(Carrera carrera);

    Carrera toCarrera(CarreraDTO carreraDTO);

    List<CarreraDTO> toCarreraDTOList (List<Carrera> carreras);

    List<Carrera> toCarreraList (List<CarreraDTO> carrerasDTO);
}
