// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Motor;

public class MotorGoToAmp extends CommandBase {
  private final Motor _motor;
  public MotorGoToAmp(Motor motor) {
    _motor = motor;
    addRequirements(_motor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _motor.init();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _motor.go(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return _motor.isAtAmperage();
  }
}
