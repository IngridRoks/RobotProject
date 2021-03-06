package behaviour.modules.general;

import behaviour.modules.BehaviourModule;
import lejos.hardware.lcd.TextLCD;
import nl.hva.miw.robot.cohort13.Marvin;

public class ClearConsoleModule extends BehaviourModule {

	public ClearConsoleModule(Marvin marvin) {
		super(marvin);
	}

	/**
	 * clears the console
	 * @return true
	 */
	@Override
	public boolean execute() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		textLCD.setAutoRefresh(false);
		textLCD.clear();
		textLCD.refresh();
		return true;
	}
}
