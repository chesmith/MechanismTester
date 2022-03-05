// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Accel extends SubsystemBase {
  private final Accelerometer _accelerometer = new BuiltInAccelerometer();
  LinearFilter xAccelFilter = LinearFilter.movingAverage(10);

  public Accel() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double filteredXAccel = xAccelFilter.calculate(_accelerometer.getX());
  }
}
