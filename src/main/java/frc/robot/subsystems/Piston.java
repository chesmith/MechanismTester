// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Piston extends SubsystemBase {
  private final Solenoid _solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.SOLENOID_ID);

  public Piston() {
    setDefaultCommand(new RunCommand(this::in, this));
  }

  public void in() {
    _solenoid.set(false);
  }

  public void out() {
    _solenoid.set(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
