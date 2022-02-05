package springbootfuegoqasar.model.dtos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;


public class SatelliteServiceDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private SatelliteDTO satellite;
	
	private MessageInputDTO messageInputDTO;

	public SatelliteServiceDTO(SatelliteDTO satellite, MessageInputDTO messageInputDTO) {
		super();
		this.satellite = satellite;
		this.messageInputDTO = messageInputDTO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SatelliteDTO getSatellite() {
		return satellite;
	}

	public void setSatellite(SatelliteDTO satellite) {
		this.satellite = satellite;
	}

	public MessageInputDTO getMessageInputDTO() {
		return messageInputDTO;
	}

	public void setMessageInputDTO(MessageInputDTO messageInputDTO) {
		this.messageInputDTO = messageInputDTO;
	}
	
}
