package nl.hva.miw.robot.cohort13;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;

public interface HardwareInterface {

	public static final Port SENSOR_PORT_1 = LocalEV3.get().getPort("S1");
	public static final Port SENSOR_PORT_2 = LocalEV3.get().getPort("S2");
	public static final Port SENSOR_PORT_3 = LocalEV3.get().getPort("S3");
	public static final Port SENSOR_PORT_4 = LocalEV3.get().getPort("S4");

	public static final RegulatedMotor GROTE_MOTOR_LINKS = Motor.A;
	public static final RegulatedMotor GROTE_MOTOR_RECHTS = Motor.B;
	public static final RegulatedMotor GROTE_MOTOR_3 = Motor.C;
	public static final RegulatedMotor GROTE_MOTOR_4 = Motor.D;

	// TODO CHECKEN OF DIT WERKT	
	public static final EV3ColorSensor COLOR_SENSOR = new EV3ColorSensor(SENSOR_PORT_1); 
	// TODO CHECKEN OF DIT WERKT	
	public static final EV3TouchSensor TOUCH_SENSOR = new EV3TouchSensor(SENSOR_PORT_3);


	
}
