// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class POVAction extends CommandBase {
  private final Joystick _joystick;

  public POVAction(Joystick joystick) {
    _joystick = joystick;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putString("status", "running");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //DO SOMETHING HERE BASED ON THE POV VALUE
    SmartDashboard.putNumber("POV", _joystick.getPOV());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putString("status", "idle");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (_joystick.getPOV() == -1);
  }
}
