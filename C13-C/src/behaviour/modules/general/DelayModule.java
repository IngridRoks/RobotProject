package behaviour.modules.general;

import behaviour.modules.BehaviourModule;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class DelayModule extends BehaviourModule {
	private int delayMS;
	
	public DelayModule(Marvin marvin, int delayMS) {
		super(marvin);
		this.delayMS = delayMS;
	}

	/**
	 * Delay for certain amount of ms
	 * @return true
	 */
	@Override
	public boolean execute() {
		Delay.msDelay(delayMS);
		return true;
	}
}
