// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;



import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.Constants.TrajectoryConstants;

public class DriveTrain extends SubsystemBase {
	enum SpeedMode{
		Low,
		Default,
		High
	}
	//private SpeedMode speedMode = SpeedMode.Default;

	private WPI_VictorSPX leftMaster;
	private WPI_VictorSPX leftSlave;
	private WPI_VictorSPX rightMaster;
	private WPI_VictorSPX rightSlave;

	private MecanumDrive drive;

	
    public final AHRS m_gyro = new AHRS(SPI.Port.kMXP);
	private final Field2d m_field = new Field2d();
	public DriveTrain() {

		leftMaster = new WPI_VictorSPX(Constants.DriveTrainConstants.leftMasterPort);
		leftSlave = new WPI_VictorSPX(Constants.DriveTrainConstants.leftSlavePort);
		rightMaster = new WPI_VictorSPX(Constants.DriveTrainConstants.rightMasterPort);
		rightSlave = new WPI_VictorSPX(Constants.DriveTrainConstants.rightSlavePort);

		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);

		drive = new MecanumDrive(leftMaster,leftSlave,rightMaster,rightSlave);
		leftMaster.setInverted(Constants.DriveTrainConstants.leftMasterInvert);
		leftSlave.setInverted(Constants.DriveTrainConstants.leftSlaveInvert);
		rightMaster.setInverted(Constants.DriveTrainConstants.rightMasterInvert);
		rightSlave.setInverted(Constants.DriveTrainConstants.rightSlaveInvert);
		leftMaster.setNeutralMode(Constants.DriveTrainConstants.leftMasterBrake);
		leftSlave.setNeutralMode(Constants.DriveTrainConstants.leftSlaveBrake);
		rightMaster.setNeutralMode(Constants.DriveTrainConstants.rightMasterBrake);
		rightSlave.setNeutralMode(Constants.DriveTrainConstants.rightSlaveBrake);
		leftMaster.setSafetyEnabled(Constants.DriveTrainConstants.leftMasterSafety);
		leftSlave.setSafetyEnabled(Constants.DriveTrainConstants.leftSlaveSafety);
		rightMaster.setSafetyEnabled(Constants.DriveTrainConstants.rightMasterSafety);
		rightSlave.setSafetyEnabled(Constants.DriveTrainConstants.rightSlaveSafety);
		drive.setSafetyEnabled(Constants.DriveTrainConstants.driveSafety);
		leftMaster.setSensorPhase(true);
		rightMaster.setSensorPhase(true);
/////////
//m_odometry = new MecanumDriveOdometry(TrajectoryConstants.kDriveKinematics, m_gyro.getRotation2d(), new MecanumDriveWheelPositions());
SmartDashboard.putData("Field", m_field);
		zeroHeading();
        resetEncoders();
	}


	@Override
	public void periodic() {
		
	}

	public double getHeading() {
		return Math.IEEEremainder(m_gyro.getAngle(), 360) * (TrajectoryConstants.kGyroReversed ? -1.0 : 1.0);
    }
	public void zeroHeading() {
        m_gyro.reset();
    }
    public void resetEncoders() {
        leftMaster.setSelectedSensorPosition(0);
        rightMaster.setSelectedSensorPosition(0);
    }

	boolean isSlowMode = false;

	double Deadband(double i){
		return ((i >= +0.09) ? i : ((i <= -0.09) ? i : 0));
	}
	public void changeSlowMode(){
		if (isSlowMode){
			isSlowMode = false;
		}else{
			isSlowMode = true;
		}
	}


  public void driveCartesian(double xSpeed,double ySpeed, double zRotation) {

    double forward=xSpeed;
    double horizontal=ySpeed;
    double rotation=zRotation;
    if(forward==1 &&horizontal==1){
      rotation*=1;
    }
    else{
      rotation*=1;
      
    }
    if (isSlowMode) {
		 forward*= 0.3;
      horizontal*=0.3;
		  rotation*= 0.6;
		}
    forward=Deadband(xSpeed);
    horizontal=Deadband(horizontal);
    rotation=Deadband(rotation);

  }
}