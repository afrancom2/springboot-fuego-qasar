package springbootfuegoqasar.reto2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootfuegoqasar.model.dtos.MessageInputDTO;
import springbootfuegoqasar.model.dtos.MessageOutputDTO;
import springbootfuegoqasar.model.dtos.RequestListDTO;
import springbootfuegoqasar.reto1.services.IModelServiceValidations;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class SatelliteController {

	private IModelServiceValidations modelServiceValidations;

	@Autowired
	public SatelliteController(IModelServiceValidations modelServiceValidations) {
		this.modelServiceValidations = modelServiceValidations;
	}
	
	@PostMapping("/topsecret/")
	public ResponseEntity<MessageOutputDTO> topSecret(
			@RequestBody RequestListDTO request) 
	{
		return this.modelServiceValidations.topSecret(request);
	}
	
	@PostMapping("/topsecret_split/{satellite_name}")
	public ResponseEntity<MessageOutputDTO> topSecretSplit(
			@PathVariable String satelliteName,
			@RequestBody MessageInputDTO messageInputDTO) 
	{
		return this.modelServiceValidations.topSecretSplit(satelliteName, messageInputDTO);
	}
	
	@GetMapping("/topsecret_split/")
	public ResponseEntity<MessageOutputDTO> getTopSecret() {
		return this.modelServiceValidations.getTopSecret();
	}
	
}
