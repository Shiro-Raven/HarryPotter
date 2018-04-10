package harrypotter.model.character;

import java.io.IOException;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.world.Direction;

public class SlytherinWizard extends Wizard implements Champion {
	private Direction traitDirection;

	public SlytherinWizard(String name) {
		super(name, 850, 550);
	}

	public void useTrait() throws IOException, InCooldownException,
			OutOfBordersException, InvalidTargetCellException {

		if (getListener() != null)
			getListener().onSlytherinTrait(traitDirection);

	}

	public Direction getTraitDirection() {
		return traitDirection;
	}

	public void setTraitDirection(Direction traitDirection) {
		this.traitDirection = traitDirection;
	}

}
