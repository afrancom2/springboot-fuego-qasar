package springbootfuegoqasar.model.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class MessageOutputDTO {

	private PositionDTO position;
	
	@Column(length = 256, nullable = false)
	private String message;
	

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
