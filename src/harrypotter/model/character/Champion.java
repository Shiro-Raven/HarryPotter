package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;

import java.io.IOException;

public interface Champion {

	public void useTrait() throws IOException, InCooldownException,
			OutOfBordersException, InvalidTargetCellException;

}
