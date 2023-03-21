// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ElevatorConstants;


public class ElevatorSub extends SubsystemBase {
  CANSparkMax m_elevator1;
  CANSparkMax m_elevator2;
  
  public ElevatorSub() {
     m_elevator1 = new CANSparkMax(ElevatorConstants.elevatorUstPort,MotorType.kBrushless);
     m_elevator2 = new CANSparkMax(ElevatorConstants.elevatorAltPort,MotorType.kBrushless);

  }

  @Override
  public void periodic() {
  }
  public void Winch_Up(){
    m_elevator1.set(1);
    m_elevator2.set(1);
  }
  public void Winch_Stop(){
    m_elevator1.set(0);
    m_elevator2.set(0);
  }
  public void Winch_Down(){
    m_elevator1.set(-1);
    m_elevator2.set(-1);
  }
}
