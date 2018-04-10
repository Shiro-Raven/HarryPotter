package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;

public class RavenclawWizard extends Wizard implements Champion {

	public RavenclawWizard(String name) {
		super(name, 750, 700);
	}

	public void useTrait() throws InCooldownException {

		if (getListener() != null)
			getListener().onRavenclawTrait();

	}
}
