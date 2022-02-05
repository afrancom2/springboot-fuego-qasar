package springbootfuegoqasar.reto1.services;

import org.springframework.data.repository.CrudRepository;

import springbootfuegoqasar.model.dtos.SatelliteDTO;

public interface IModelSatelliteService extends CrudRepository <SatelliteDTO, Long>{
	
	public SatelliteDTO findByName(String name);
	
}
