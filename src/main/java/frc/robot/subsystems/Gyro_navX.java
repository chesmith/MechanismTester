// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro_navX extends SubsystemBase {
  private final AHRS _gyro;
  private long _start;

  public Gyro_navX(AHRS gyro) {
    _gyro = gyro;

    _gyro.reset();
    _start = System.currentTimeMillis();
  }

  @Override
  public void periodic() {
    double elapsed = (double)(System.currentTimeMillis() - _start);
    double angle = _gyro.getAngle();
    double drift = (angle / elapsed) * 1000;
    
    SmartDashboard.putNumber("AHRS.Angle", angle);
    SmartDashboard.putNumber("AHRS.Drift", drift);
    SmartDashboard.putNumber("AHRS.Time", elapsed);
    SmartDashboard.putNumber("AHRS.Pitch", _gyro.getPitch());
    SmartDashboard.putNumber("AHRS.Roll", _gyro.getRoll());
    SmartDashboard.putBoolean("AHRS.Stable", !_gyro.isMoving());
    SmartDashboard.putBoolean("AHRS.Flat", (Math.abs(_gyro.getPitch()) <= 5 && Math.abs(_gyro.getRoll()) <= 5));

    SmartDashboard.putData("AHRS", _gyro);
  }
}
