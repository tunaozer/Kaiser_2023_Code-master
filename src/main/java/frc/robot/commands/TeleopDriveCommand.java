package frc.robot.commands;


import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
public class TeleopDriveCommand extends CommandBase {
	private final PS4Controller driverJoystick;
	//private final Joystick coJoystick;
	private final DriveTrain driveTrain;
	public TeleopDriveCommand(PS4Controller joy, DriveTrain dt) {
		this.driverJoystick = joy;
		this.driveTrain = dt;
		addRequirements(dt);
	}

	@Override
	public void initialize() {

	}
	@Override
	public void execute() {
		driveTrain.driveCartesian(driverJoystick.getLeftY(), driverJoystick.getLeftX(), -driverJoystick.getRawAxis(2));
	}

	@Override
	public void end(boolean interrupted) {

	}

	@Override
	public boolean isFinished() {
		return false;
	}
}