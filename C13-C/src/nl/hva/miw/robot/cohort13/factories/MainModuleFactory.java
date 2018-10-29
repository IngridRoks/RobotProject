package nl.hva.miw.robot.cohort13.factories;

import behaviour.modules.BehaviourModule;
import behaviour.modules.ClearConsoleModule;
import behaviour.modules.DelayModule;
import behaviour.modules.WaitForEnterKeyModule;
import behaviour.modules.logic.InverterModule;
import behaviour.modules.logic.LeafModule;
import behaviour.modules.logic.LoopModule;
import behaviour.modules.logic.SequenceModule;
import behaviour.modules.logic.SequenceUntilFailModule;
import behaviour.modules.logic.StateConditionModule;
import behaviour.modules.logic.SucceederModule;
import behaviour.modules.procedures.console.ConsoleModule;
import behaviour.modules.procedures.exit.GoodbyeModule;
import behaviour.modules.procedures.keuze_opdracht.KeuzeOpdrachtModule;
import behaviour.modules.procedures.parcour.ParcoursModule;
import behaviour.modules.procedures.parcour.ParcoursModuleRGB;
import behaviour.modules.procedures.parcour.ParcoursModuleRGB2;
import behaviour.modules.procedures.testing.ColorSensorTesterModule;
import behaviour.modules.procedures.testing.CubeRecognizerTestModule;
import behaviour.modules.procedures.testing.ColorRecognizerTestModule;
import behaviour.modules.procedures.testing.DriveForwardTesterModule;
import behaviour.modules.procedures.testing.EndTestMessageModule;
import behaviour.modules.procedures.testing.ProximitySensorTesterModule;
import behaviour.modules.procedures.testing.StartTestMessageModule;
import behaviour.modules.procedures.uitbreiding.UitbreidingOpdrachtModule;
import behaviour.modules.procedures.welcome.WelcomeModule;
import behaviour.modules.sound.BeepModule;
import behaviour.modules.sound.BeepSequenceModule;
import behaviour.modules.sound.PlaySampleModule;
import nl.hva.miw.robot.cohort13.Marvin;
import nl.hva.miw.robot.cohort13.MarvinState;
import nl.hva.miw.robot.cohort13.resources.Sounds;
import nl.hva.miw.robot.cohort13.resources.TestingProcedureNames;

public class MainModuleFactory extends ModuleFactory {

	@Override
	public BehaviourModule createModule(Marvin marvin) {		
		LeafModule leafModule = new LeafModule(marvin);
		SequenceModule sequenceModuleA = new SequenceModule(marvin);
		LoopModule loopModuleB = new LoopModule(marvin);
		SequenceModule sequenceModuleB = new SequenceModule(marvin);
		SucceederModule succeederModuleA = new SucceederModule(marvin);
		SucceederModule succeederModuleB = new SucceederModule(marvin);
		SucceederModule succeederModuleC = new SucceederModule(marvin);
		SucceederModule succeederModuleD = new SucceederModule(marvin);
		InverterModule inverterModule = new InverterModule(marvin);
		
		SequenceUntilFailModule sequenceUntilFailModule_A = new SequenceUntilFailModule(marvin);
		SequenceUntilFailModule sequenceUntilFailModule_B = new SequenceUntilFailModule(marvin);	
		SequenceUntilFailModule sequenceUntilFailModule_C = new SequenceUntilFailModule(marvin);
		SequenceUntilFailModule sequenceUntilFailModule_E = new SequenceUntilFailModule(marvin);
		
		GoodbyeModule goodbyeModule = new GoodbyeModule(marvin);
		SequenceModule sequenceModule_Testing = new SequenceModule(marvin);
		
		leafModule.addModule(sequenceModuleA);
			//Welcome
			sequenceModuleA.addModule(new WelcomeModule(marvin));
			sequenceModuleA.addModule(new WaitForEnterKeyModule(marvin));
			//Main Loop
			sequenceModuleA.addModule(loopModuleB);
				//Sequence
				loopModuleB.addModule(sequenceModuleB);
					//Console
					sequenceModuleB.addModule(new BeepSequenceModule(marvin));
					sequenceModuleB.addModule(new ConsoleModule(marvin));
					
					//Testing
					sequenceModuleB.addModule(succeederModuleA);		
						succeederModuleA.addModule(sequenceUntilFailModule_A);
							sequenceUntilFailModule_A.addModule(new StateConditionModule(marvin, MarvinState.TESTING));
							sequenceUntilFailModule_A.addModule(new WaitForEnterKeyModule(marvin));
							sequenceUntilFailModule_A.addModule(sequenceModule_Testing);
								//Console Module
							//ParcoursModuleRGB2					
								//sequenceModule_Testing.addModule(new StartTestMessageModule(marvin, TestingProcedureNames.consoleName));
								////sequenceModule_Testing.addModule(new WaitForEnterKeyModule(marvin));
								//sequenceModule_Testing.addModule(new EndTestMessageModule(marvin, TestingProcedureNames.consoleName));
								//Proximity
								//sequenceModule_Testing.addModule(new StartTestMessageModule(marvin, TestingProcedureNames.proximityAndSoundTesterModule));
								//sequenceModule_Testing.addModule(new PlaySampleModule(marvin, Sounds.woopwoop));
								//sequenceModule_Testing.addModule(new DelayModule(marvin, 1000));
								//sequenceModule_Testing.addModule(new PlaySampleModule(marvin, Sounds.weAreTheRobots));
								//sequenceModule_Testing.addModule(new EndTestMessageModule(marvin, TestingProcedureNames.proximityAndSoundTesterModule));
								//Sound
								//sequenceModule_Testing.addModule(new StartTestMessageModule(marvin, TestingProcedureNames.soundName));
								//sequenceModule_Testing.addModule(new SoundTesterModule(marvin, soundName));
								//sequenceModule_Testing.addModule(new PlaySampleModule(marvin, "woopwoop.wav"));
								//sequenceModule_Testing.addModule(new SinWaveModule(marvin));
								//sequenceModule_Testing.addModule(new BeepModule(marvin));
								//sequenceModule_Testing.addModule(new BeepSequenceModule(marvin));
								//sequenceModule_Testing.addModule(new BeepSequenceUpModule(marvin));
								//sequenceModule_Testing.addModule(new BuzzModule(marvin));
								//sequenceModule_Testing.addModule(new SystemSoundModule(marvin, 0));
								//sequenceModule_Testing.addModule(new SystemSoundModule(marvin, 1));
								//sequenceModule_Testing.addModule(new SystemSoundModule(marvin, 2));
								//sequenceModule_Testing.addModule(new SystemSoundModule(marvin, 3));
								//sequenceModule_Testing.addModule(new SystemSoundModule(marvin, 4));
								//sequenceModule_Testing.addModule(new EndTestMessageModule(marvin, TestingProcedureNames.soundName));
								//Color Sensor
//								sequenceModule_Testing.addModule(new StartTestMessageModule(marvin, TestingProcedureNames.colorSensorName));
								sequenceModule_Testing.addModule(new ColorSensorTesterModule(marvin, 
										marvin.getColorSensorControlA().getColorSensor(), TestingProcedureNames.colorSensorName + "_A"));
								
//								sequenceModule_Testing.addModule(new ColorSensorTesterModule(marvin, 
//								marvin.getColorSensorControlB().getColorSensor(), "_B"));
								//marvin.colorSensorB, TestingProcedureNames.colorSensorName + "_B");
								//sequenceModule_Testing.addModule(new ColorRecognizerTestModule(marvin, 
								//		marvin.colorSensorB, TestingProcedureNames.colorSensorName));
							///	sequenceModule_Testing.addModule(new CubeRecognizerTestModule(marvin, 
										//marvin.getColorSensorControlB(), TestingProcedureNames.colorSensorName));			
								
								
								
								sequenceModule_Testing.addModule(new EndTestMessageModule(marvin, TestingProcedureNames.colorSensorName));
								
								//Drive Forward Test
								//sequenceModule_Testing.addModule(new StartTestMessageModule(marvin, TestingProcedureNames.drivingName));
								//sequenceModule_Testing.addModule(new DriveForwardTesterModule(marvin, TestingProcedureNames.drivingName));
								//sequenceModule_Testing.addModule(new EndTestMessageModule(marvin, TestingProcedureNames.drivingName));
								//Proximity Sensor Tester
								//sequenceModule_Testing.addModule(new StartTestMessageModule(marvin, TestingProcedureNames.proximitySensorName));
								//sequenceModule_Testing.addModule(new ProximitySensorTesterModule(marvin, TestingProcedureNames.proximitySensorName));
								//sequenceModule_Testing.addModule(new EndTestMessageModule(marvin, TestingProcedureNames.proximitySensorName));
								//Touch Sensor Tester
								//sequenceModule_Testing.addModule(new StartTestMessageModule(marvin, touchSensorName));
								//sequenceModule_Testing.addModule(new TouchSensorTesterModule(marvin, touchSensorName));
								//sequenceModule_Testing.addModule(new EndTestMessageModule(marvin, touchSensorName));
							sequenceUntilFailModule_A.addModule(new DelayModule(marvin, 500));	
							sequenceUntilFailModule_A.addModule(new ClearConsoleModule(marvin));
					//Parcours
					sequenceModuleB.addModule(succeederModuleB);	
						succeederModuleB.addModule(sequenceUntilFailModule_B);
							sequenceUntilFailModule_B.addModule(new StateConditionModule(marvin, MarvinState.PARCOUR));
							sequenceUntilFailModule_B.addModule(new WaitForEnterKeyModule(marvin));
							sequenceUntilFailModule_B.addModule(new ParcoursModuleRGB(marvin));
							sequenceUntilFailModule_B.addModule(new BeepModule(marvin));
							sequenceUntilFailModule_B.addModule(new DelayModule(marvin, 500));
							sequenceUntilFailModule_B.addModule(new ClearConsoleModule(marvin));
					//Keuze Opdracht
					sequenceModuleB.addModule(succeederModuleC);	
						succeederModuleC.addModule(sequenceUntilFailModule_C);
							sequenceUntilFailModule_C.addModule(new StateConditionModule(marvin, MarvinState.KEUZE_OPDRACHT));
							sequenceUntilFailModule_C.addModule(new WaitForEnterKeyModule(marvin));
							sequenceUntilFailModule_C.addModule(new KeuzeOpdrachtModule(marvin));
							sequenceUntilFailModule_C.addModule(new DelayModule(marvin, 500));
							sequenceUntilFailModule_C.addModule(new ClearConsoleModule(marvin));
					//Show
					sequenceModuleB.addModule(succeederModuleD);
						succeederModuleD.addModule(sequenceUntilFailModule_E);
							sequenceUntilFailModule_E.addModule(new StateConditionModule(marvin, MarvinState.SHOW));
							sequenceUntilFailModule_E.addModule(new WaitForEnterKeyModule(marvin));	
							sequenceUntilFailModule_E.addModule(new UitbreidingOpdrachtModule(marvin));				
							sequenceUntilFailModule_E.addModule(new DelayModule(marvin, 500));
							sequenceUntilFailModule_E.addModule(new ClearConsoleModule(marvin));
					//Exit
					sequenceModuleB.addModule(inverterModule);
						inverterModule.addModule(new StateConditionModule(marvin, MarvinState.EXIT));	
			//GoodBye
			sequenceModuleA.addModule(goodbyeModule);
			
		return leafModule;
	}
	
}