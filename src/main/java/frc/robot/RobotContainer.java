package frc.robot;


import edu.wpi.first.wpilibj.PS4Controller;

import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ElevatorCom;
import frc.robot.commands.TeleopDriveCommand;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.commands.*;

public class RobotContainer {
	/*
	** Drive Train +
	** Intake + 
	** Shooter +

	** Climber
	** Climber Angle
	** Addressable Led
	*/
	private DriveTrain driveTrain = new DriveTrain();

	public PS4Controller driverJoystick = new PS4Controller(Constants.JoystickConstants.driverPort);
	private ElevatorSub elevatorSub= new ElevatorSub();
	}

	private void configureButtonBindings() {

		//new JoystickButton(driverJoystick, 3).whileHeld(new ShooterCom(shooter)); 
		//new JoystickButton(driverJoystick, 12).whileHeld(new ShooterSolenoidCom(shooter)); 
		//new POVButton(driverJoystick, 180).whileHeld(new IndexCom(shooter));
        new JoystickButton(driverJoystick, 10).onTrue(Commands.runOnce(() -> driveTrain.changeSlowMode(),driveTrain));
		//new JoystickButton(driverJoystick, 10).onTrue(Commands.runOnce(() -> driveTrain.changeSlowMode(),driveTrain));
		//new JoystickButton(driverJoystick, 7).whileHeld(new ClimbCom(climb, true)); 
		//new JoystickButton(driverJoystick, 8).whileHeld(new ClimbCom(climb, false));  
		
		//new JoystickButton(driverJoystick, 7).whileHeld(new ClimbCom(climb, true)); 
		//new JoystickButton(driverJoystick, 8).whileHeld(new ClimbCom(climb, false)); 
		new JoystickButton(driverJoystick, 6).whileTrue(new ClimbCom(Winch_Up));
		//new POVButton(driverJoystick, 90).whenPressed(() -> intakeSub.activeComp());
		JoystickButton elevatorUp= new JoystickButton(driverJoystick, 5);
		elevatorSub.whileTrue(Winch_Up);
	}


}

