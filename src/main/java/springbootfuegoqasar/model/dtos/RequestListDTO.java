package springbootfuegoqasar.model.dtos;

import java.util.List;

public class RequestListDTO {
	
	private List<MessageInputDTO> satellites;

	public List<MessageInputDTO> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<MessageInputDTO> satellites) {
		this.satellites = satellites;
	}
	
}
