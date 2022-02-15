// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.JoystickConstants;

public class ServoMotor extends SubsystemBase {
  private final Servo _servo = new Servo(Constants.SERVO_ID);
  
  public ServoMotor() {}

  public void set(Joystick joystick) {
    double value = joystick.getRawAxis(JoystickConstants.RIGHT_STICK_X);
    value = (value + 1)/2;
    _servo.set(value);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Servo Value", _servo.get());
    SmartDashboard.putNumber("Servo Angle", _servo.getAngle());
  }
}
