package springbootfuegoqasar.reto1.services;

import org.springframework.data.repository.CrudRepository;

import springbootfuegoqasar.model.dtos.MessageInputDTO;

public interface IModelServiceMessage extends CrudRepository <MessageInputDTO, Long>{
	
	public MessageInputDTO findByName(String name);
	
}
