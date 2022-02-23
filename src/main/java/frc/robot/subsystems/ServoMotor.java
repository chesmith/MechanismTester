// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ServoMotor extends SubsystemBase {
  private Servo _servo;
  private final int _port;
  
  public ServoMotor(int port) {
    _port = port;
    _servo = new Servo(port);
  }

  public void set(double value) {
    _servo.set(value);
  }

  public void setAngle(double degrees) {
    _servo.setAngle(degrees);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Servo " + _port + " Value", _servo.get());
    SmartDashboard.putNumber("Servo " + _port + " Angle", _servo.getAngle());
  }
}
