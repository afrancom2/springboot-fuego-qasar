package springbootfuegoqasar.model.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

public class SatelliteDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(length = 256, nullable = false)
	private String name;
	
	private PositionDTO position;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PositionDTO getPosition() {
		return position;
	}
	public void setPosition(PositionDTO position) {
		this.position = position;
	}
	
}
