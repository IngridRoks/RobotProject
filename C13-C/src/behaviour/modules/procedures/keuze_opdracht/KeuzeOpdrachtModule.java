package behaviour.modules.procedures.keuze_opdracht;

import behaviour.modules.BehaviourModule;
import behaviour.modules.procedures.testing.ProximitySensorTesterModule;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;

public class KeuzeOpdrachtModule extends BehaviourModule {
	private final long runTime = 30000;

	public KeuzeOpdrachtModule(Marvin marvin) {
		super(marvin);
	}
	ProximitySensorTesterModule test1;
	
	@Override
	public boolean execute() {
       	
			TextLCD textLCD = getMarvin().getBrick().getTextLCD();
			EV3IRSensor afstandTester = getMarvin().proximitySensor;
			final SampleProvider sp = afstandTester.getDistanceMode();
			long startTime = System.currentTimeMillis();
			long currentTime = System.currentTimeMillis();
			
			startDriveForward();
			while(currentTime - startTime < 20000) {
				currentTime = System.currentTimeMillis();
				float [] sample = new float[sp.sampleSize()];
			    sp.fetchSample(sample, 0);	
			    int distanceValue = (int)sample[0];
			  
			    textLCD.refresh();
		        textLCD.clear();
		        textLCD.drawString("Distance:", 2, 1);
		        textLCD.drawString("" + distanceValue, 1, 2);
		        Delay.msDelay(500);	
		       
		        if(distanceValue < 35) {
		        	stopRobot();
		        	rotate180();
		        	startDriveBackward();
			        stopRobot();
			        grabIt();
			//        stopRobot();
			//        startDriveForward ();			        
			//        Delay.msDelay(3000);
			        stopRobot();
			        letLoose();
			        stopRobot();
		        }
			}return true;
		}  
	        
	        

//	
	
	public void startDriveForward () {
		marvin.groteMotorLinks.forward();
		marvin.groteMotorRechts.forward();
		marvin.groteMotorLinks.setSpeed(300);
		marvin.groteMotorRechts.setSpeed(300);
//		}
	}
	
	public void startDriveBackward() {
		long startTime180 = System.currentTimeMillis();
		long lastTime180 = System.currentTimeMillis();
		
		while (lastTime180 - startTime180 <1000) {
			lastTime180 = System.currentTimeMillis();
			marvin.groteMotorLinks.backward();
			marvin.groteMotorRechts.backward();
			marvin.groteMotorLinks.setSpeed(300);
			marvin.groteMotorRechts.setSpeed(300);
		}
	}
	
	public void stopRobot() {
		marvin.groteMotorLinks.stop();
		marvin.groteMotorRechts.stop();
	}	
	
	public void rotate180 () {
		long startTime180 = System.currentTimeMillis();
		long lastTime180 = System.currentTimeMillis();
		
		while (lastTime180 - startTime180 <3500) {
			lastTime180 = System.currentTimeMillis();
			marvin.groteMotorLinks.forward();
			marvin.groteMotorLinks.setSpeed(300);
			marvin.groteMotorRechts.backward();
			marvin.groteMotorRechts.setSpeed(300);
		}
	}
	
	public void grabIt() {
		marvin.kleineMotorArm.backward();
		marvin.kleineMotorArm.setSpeed(150);
		Delay.msDelay(1900);
		marvin.kleineMotorArm.stop();
	}
	
	public void letLoose() {
		marvin.kleineMotorArm.forward();
		marvin.kleineMotorArm.setSpeed(150);
		Delay.msDelay(1900);
		marvin.kleineMotorArm.stop();
	}
	
	
	
	
}
