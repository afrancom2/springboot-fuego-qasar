package springbootfuegoqasar.reto1.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import springbootfuegoqasar.model.dtos.MessageInputDTO;
import springbootfuegoqasar.model.dtos.PositionDTO;
import springbootfuegoqasar.model.dtos.SatelliteDTO;
import springbootfuegoqasar.model.dtos.SatelliteServiceDTO;
import springbootfuegoqasar.reto1.services.IModelServiceLocation;

@Service
public class ModelServiceLocationImpl implements IModelServiceLocation{

	@Override
	public PositionDTO getLocation(List<SatelliteServiceDTO> details) {
		SatelliteDTO satAux2 = details.get(1).getSatellite();
		SatelliteDTO satAux3 = details.get(2).getSatellite();

		MessageInputDTO msgAux1 = details.get(0).getMessageInputDTO();
		MessageInputDTO msgAux2 = details.get(1).getMessageInputDTO();
		MessageInputDTO msgAux3 = details.get(2).getMessageInputDTO();

		PositionDTO positionDTO = new PositionDTO();
		
		try {
			Double xFinal = ((Math.pow(msgAux1.getDistance(), 2)) - (Math.pow(msgAux2.getDistance(), 2))
					+ (Math.pow(satAux2.getPosition().getPosx(), 2))) / (2 * satAux2.getPosition().getPosx());

			Double yFinal = ((Math.pow(msgAux1.getDistance(), 2) - Math.pow(msgAux3.getDistance(), 2)
					+ Math.pow(satAux3.getPosition().getPosx(), 2) + Math.pow(satAux3.getPosition().getPosy(), 2))
					/ (2 * satAux3.getPosition().getPosy())
					- (satAux3.getPosition().getPosx() / satAux3.getPosition().getPosy()) * xFinal);

			positionDTO.setPosx(xFinal);
			positionDTO.setPosy(yFinal);
			return positionDTO;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getMessage(List<SatelliteServiceDTO> details) {
		List<String> msgAux = new ArrayList<>();
		StringBuilder msgFinal = new StringBuilder();
		msgAux.addAll(details.get(0).getMessageInputDTO().getMessage());

		if (msgAux.size() != details.get(1).getMessageInputDTO().getMessage().size()
				&& msgAux.size() != details.get(2).getMessageInputDTO().getMessage().size())
			return null;

		for (int i = 0; i < msgAux.size(); i++) {
			if (!msgAux.get(i).isEmpty()) {
				msgFinal.append(msgAux.get(i));
			} else if (!details.get(1).getMessageInputDTO().getMessage().get(i).isEmpty()) {
				msgFinal.append(details.get(1).getMessageInputDTO().getMessage().get(i));
			} else if (!details.get(2).getMessageInputDTO().getMessage().get(i).isEmpty()) {
				msgFinal.append(details.get(2).getMessageInputDTO().getMessage().get(i));
			} else {
				return null;
			}

			msgFinal.append(" ");
		}

		return msgFinal.toString().trim();
	}

}
