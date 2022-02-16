// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Piston extends SubsystemBase {
  private final Solenoid _solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.SOLENOID_ID);
  private boolean _value = false;

  public Piston() {
    // setDefaultCommand(new RunCommand(this::in, this));
  }

  public void in() {
    _value = false;
    _solenoid.set(_value);
  }

  public void out() {
    _value = true;
    _solenoid.set(_value);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("piston", _value);
  }
}
