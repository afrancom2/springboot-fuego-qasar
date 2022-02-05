package springbootfuegoqasar.reto1.services;

import java.util.List;
import java.util.Optional;

import springbootfuegoqasar.model.dtos.SatelliteDTO;

public interface IModelSatelliteService {

	public List<SatelliteDTO> findAll();

	public Optional<SatelliteDTO> findById(String id);

	public SatelliteDTO save(SatelliteDTO satelite);
	
	public SatelliteDTO findByName(String name);
	
	
}
