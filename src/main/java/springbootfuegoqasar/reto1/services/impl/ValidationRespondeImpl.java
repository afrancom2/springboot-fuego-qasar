package springbootfuegoqasar.reto1.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import springbootfuegoqasar.model.dtos.MessageInputDTO;
import springbootfuegoqasar.model.dtos.MessageOutputDTO;
import springbootfuegoqasar.model.dtos.PositionDTO;
import springbootfuegoqasar.model.dtos.RequestListDTO;
import springbootfuegoqasar.model.dtos.SatelliteDTO;
import springbootfuegoqasar.model.dtos.SatelliteServiceDTO;
import springbootfuegoqasar.reto1.services.IModelSatelliteService;
import springbootfuegoqasar.reto1.services.IModelServiceMessage;
import springbootfuegoqasar.reto1.services.IModelServiceValidations;

@Service
public class ValidationRespondeImpl implements IModelServiceValidations{
	
	@Autowired
	private IModelSatelliteService iSatelliteService; // Servicio de control de satelites
	@Autowired
	private IModelServiceMessage modelServiceMessage; // Servicio de control de mensajes
	@Autowired
	private ModelServiceLocationImpl modelServiceLocationImpl;
	
	@Override
	public ResponseEntity<MessageOutputDTO> topSecret(RequestListDTO request) {

		List<MessageInputDTO> detalleSatelite = request.getSatellites();
		List<SatelliteServiceDTO> sateliteXdetalle = new ArrayList<>(); // Relacion entre satelites y mensajes

		detalleSatelite.forEach((response) -> {
			validSaveMessageRequest(response);
			SatelliteDTO satellite = iSatelliteService.findByName(response.getName());
			MessageInputDTO messageInputDTO = new MessageInputDTO(response.getName(), response.getDistance(), response.getMessage());
			SatelliteServiceDTO satelliteServiceDTO = new SatelliteServiceDTO(satellite, messageInputDTO);
			sateliteXdetalle.add(satelliteServiceDTO);
		});

		if (sateliteXdetalle.size() != 3) {
			return ResponseEntity.notFound().build(); // No hay suficiente informacion para el calculo 404
		}

		return validationResponseRequest(sateliteXdetalle);
	}
	
	@Override
	public ResponseEntity<MessageOutputDTO> topSecretSplit(@PathVariable String satelliteName,
			@RequestBody MessageInputDTO request) {

		if (iSatelliteService.findByName(satelliteName.toLowerCase()) == null) {
			return ResponseEntity.notFound().build(); // Satelite no existe 404
		}

		MessageOutputDTO messageOutputDTO = new MessageOutputDTO();
		request.setName(satelliteName.toLowerCase());
		validSaveMessageRequest(request);
		
		messageOutputDTO.setMessage("Registrado correctamente");

		return ResponseEntity.ok().body(messageOutputDTO);
	}
	
	public ResponseEntity<MessageOutputDTO> getTopSecret() {

		if (((List<MessageInputDTO>) modelServiceMessage.findAll()).size() != 3) {
			return ResponseEntity.notFound().build();
		}

		List<MessageInputDTO> detalleSatelite = (List<MessageInputDTO>) modelServiceMessage.findAll();
		List<SatelliteServiceDTO> sateliteXdetalle = new ArrayList<>();

		detalleSatelite.forEach((response) -> {
			SatelliteDTO satellite = iSatelliteService.findByName(response.getName());
			MessageInputDTO messageInputDTO = new MessageInputDTO(response.getName(), response.getDistance(), response.getMessage());
			SatelliteServiceDTO satelliteServiceDTO = new SatelliteServiceDTO(satellite, messageInputDTO);
			sateliteXdetalle.add(satelliteServiceDTO);
		});

		return validationResponseRequest(sateliteXdetalle);
	}
	
	/**
	 * 
	 * @param messageRequest
	 * Recibe un mensaje recibido por algun satelite, valida si el mensaje ya existe en la DB y lo reemplaza por el nuevo
	 */
	private void validSaveMessageRequest(MessageInputDTO messageRequest) {
		if (modelServiceMessage.findByName(messageRequest.getName().toLowerCase()) != null) {
			modelServiceMessage.delete(modelServiceMessage.findByName(messageRequest.getName().toLowerCase()));
		}
		modelServiceMessage.save(messageRequest);
	}
	
	/**
	 * 
	 * @param sateliteXdetalle
	 * @return ResponseEntity
	 * 
	 * Metodo que realiza el llamado a los Casos de uso para obtener la informacion procesada de acuerdo a la necesidad
	 * En caso de no obtener alguno de los dos valores, retorna un 404
	 */
	private ResponseEntity<MessageOutputDTO> validationResponseRequest(List<SatelliteServiceDTO> sateliteDetalle) {
		PositionDTO posFinal = modelServiceLocationImpl.getLocation(sateliteDetalle);
		String msgFinal = modelServiceLocationImpl.getMessage(sateliteDetalle);

		if (msgFinal == null || posFinal == null) {
			return ResponseEntity.notFound().build(); // No es posible determinar la pos o el msg - 404
		}
		
		MessageOutputDTO messageOutputDTO = new MessageOutputDTO();
		messageOutputDTO.setPosition(posFinal);
		messageOutputDTO.setMessage(msgFinal);
		return ResponseEntity.ok().body(messageOutputDTO);
	}

}
