// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro_ADIS16470 extends SubsystemBase {
  private final ADIS16470_IMU _gyro;
  private long _start;

  public Gyro_ADIS16470(ADIS16470_IMU gyro) {
    _gyro = gyro;

    _gyro.reset();
    _start = System.currentTimeMillis();
  }

  @Override
  public void periodic() {
    double elapsed = (double)(System.currentTimeMillis() - _start);
    double angle = _gyro.getAngle();
    double drift = (angle / elapsed) * 1000;
    
    SmartDashboard.putNumber("ADIS.Angle", angle);
    SmartDashboard.putNumber("ADIS.Drift", drift);
    SmartDashboard.putNumber("ADIS.Time", elapsed);

    SmartDashboard.putData("ADIS", _gyro);
  }
}
