package springbootfuegoqasar.model.dtos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class PositionDTO implements Serializable{

	/**
	 * Value Default
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 10, nullable = false)
	private Double posx;
	
	@Column(length = 10, nullable = false)
	private Double posy;
	
	
	public Double getPosx() {
		return posx;
	}

	public void setPosx(Double posx) {
		this.posx = posx;
	}

	public Double getPosy() {
		return posy;
	}

	public void setPosy(Double posy) {
		this.posy = posy;
	}
	
}
