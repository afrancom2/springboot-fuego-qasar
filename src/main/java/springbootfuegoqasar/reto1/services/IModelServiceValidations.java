package springbootfuegoqasar.reto1.services;

import org.springframework.http.ResponseEntity;

import springbootfuegoqasar.model.dtos.MessageInputDTO;
import springbootfuegoqasar.model.dtos.MessageOutputDTO;
import springbootfuegoqasar.model.dtos.RequestListDTO;

public interface IModelServiceValidations {

	public ResponseEntity<MessageOutputDTO> topSecret(RequestListDTO request);
	
	public ResponseEntity<MessageOutputDTO> topSecretSplit(String satelliteName, MessageInputDTO request);
	
	public ResponseEntity<MessageOutputDTO> getTopSecret();
	
}
