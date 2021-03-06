package behaviour.modules.procedures.testing;

import behaviour.modules.BehaviourModule;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;
import nl.hva.miw.robot.cohort13.Marvin;


/**
 * 
 * voert test procedure aan voor het testen van de color sensor
 *
 */
public class ColorSensorTesterModule extends BehaviourModule {
	
	private EV3ColorSensor colorSensor;
	
	public ColorSensorTesterModule(Marvin marvin, EV3ColorSensor colorSensor) {
		super(marvin);
		this.colorSensor = colorSensor;
	}

	@Override
	public boolean execute() {
		TextLCD textLCD = getMarvin().getBrick().getTextLCD();
		int testMode = 0;
		int testCount = 0;
			
	    textLCD.setAutoRefresh(false);
	    
	    SensorMode sensorModeRGB = colorSensor.getRGBMode();
	    SensorMode sensorModeRed = colorSensor.getRedMode();
	    SensorMode sensorModeAmbient = colorSensor.getAmbientMode();
	    
	    float[] sampleRGB = new float[sensorModeRGB.sampleSize()];
	    float[] sampleRed = new float[sensorModeRed.sampleSize()];
	    float[] sampleAmbient = new float[sensorModeAmbient.sampleSize()];

	    while (testMode == 0) {
	        textLCD.setAutoRefresh(false);
	        textLCD.refresh();
	        textLCD.clear();

	        if (testMode == 0) {
	        	if (colorSensor.getFloodlight() != Color.WHITE) {
	        		colorSensor.setFloodlight(Color.WHITE);
	        		colorSensor.setCurrentMode(sensorModeRGB.getName());		
	        	}
	        	
	        	sensorModeRGB.fetchSample(sampleRGB, 0);
		    
		        float r = sampleRGB[0];
		        float g = sampleRGB[1];
		        float b = sampleRGB[2];
		        
		        String sR = String.format("R: %.2f", r *  10);
		        String sG = String.format("G: %.2f", g * 10);
		        String sB = String.format("B: %.2f ", b * 10);
		        
			    textLCD.drawString("RGB mode: " + testCount, 1, 1);
		        textLCD.drawString(sR, 1, 2);
		        textLCD.drawString(sG, 1, 3);
		        textLCD.drawString(sB, 1, 4);  
		        
		     
        	} else if (testMode == 1) {	
	    		//Red Mode
	        	if (colorSensor.getFloodlight() != Color.RED) {
	        		colorSensor.setFloodlight(Color.RED);
	        		colorSensor.setCurrentMode(sensorModeRed.getName());		
	        	}
        		
        		sensorModeRed.fetchSample(sampleRed, 0);
        		
		        float r = sampleRed[0];
		        
		        String sR = String.format("R: %.2f", r);
		        
			    textLCD.drawString("Red mode: " + testCount, 2, 1);
		        textLCD.drawString(sR, 1, 2);
        	} else if (testMode == 2) {	
        		//Ambient Mode
	        	if (colorSensor.getFloodlight() != Color.NONE) {
	        		colorSensor.setFloodlight(false);
	        		colorSensor.setCurrentMode(sensorModeAmbient.getName());
	        	}
        		
        		sensorModeAmbient.fetchSample(sampleAmbient, 0);
        		
		        float r = sampleAmbient[0];
		        //float g = sampleAmbient[1];
		        //float b = sampleAmbient[2];
		        
		        String sR = String.format("R: %.2f", r);
		        
		        //String sG = String.format("G: %.2f", g);
		        //String sB = String.format("B: %.2f ", b);
		        
			    textLCD.drawString("Ambient mode: " + testCount, 2, 1);
		        textLCD.drawString(sR, 1, 2);
		        textLCD.drawString("" + sampleAmbient.length, 1, 3);
		        //textLCD.drawString(sG, 1, 3);
		        //textLCD.drawString(sB, 1, 4);  
        	}
        	
        	testCount++;
        	
        	Delay.msDelay(250);
	        
        	/*
	        if (testCount > 30) {
	        	testCount = 0;
	        	testMode++;
	        	//Button.LEDPattern(4);    // flash green led and
	        	Sound.beep();
	        }
	        
	        if (testMode > 2) {
	        	break;
	        }
	        */
	    }  

	    return true;
	}
	
}
