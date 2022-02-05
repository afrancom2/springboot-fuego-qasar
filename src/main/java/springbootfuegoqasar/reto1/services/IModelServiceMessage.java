package springbootfuegoqasar.reto1.services;

import java.util.List;
import java.util.Optional;

import springbootfuegoqasar.model.dtos.MessageInputDTO;

public interface IModelServiceMessage {

	public List<MessageInputDTO> findAll();
	
	public Optional<MessageInputDTO> findById(String name);
	
	public MessageInputDTO save(MessageInputDTO messageRequest);
	
	public MessageInputDTO findByName(String name);
	
	public void delete(MessageInputDTO messageRequest);
	
	public void deleteAll();
	
}
