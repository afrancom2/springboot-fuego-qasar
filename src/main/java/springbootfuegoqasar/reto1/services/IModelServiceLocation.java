package springbootfuegoqasar.reto1.services;

import java.util.List;

import springbootfuegoqasar.model.dtos.PositionDTO;
import springbootfuegoqasar.model.dtos.SatelliteServiceDTO;

public interface IModelServiceLocation {

	public PositionDTO getLocation(List<SatelliteServiceDTO> details);
	
	public String getMessage(List<SatelliteServiceDTO> details);
	
}
