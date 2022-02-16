// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MotorWithSoftLimits extends SubsystemBase {
  private final CANSparkMax _motor = new CANSparkMax(Constants.MOTORWITHLIMIT_ID, MotorType.kBrushless);
  private RelativeEncoder _encoder = _motor.getEncoder();

  public MotorWithSoftLimits() {
    setDefaultCommand(new RunCommand(this::stop, this));

    _motor.restoreFactoryDefaults();

    _motor.setIdleMode(IdleMode.kBrake);

    _motor.enableSoftLimit(SoftLimitDirection.kForward, true);
    _motor.setSoftLimit(SoftLimitDirection.kForward, Constants.FORWARD_LIMIT);
    _motor.enableSoftLimit(SoftLimitDirection.kReverse, true);
    _motor.setSoftLimit(SoftLimitDirection.kReverse, Constants.REVERSE_LIMIT);

    _motor.setOpenLoopRampRate(0.5);

    _encoder.setPosition(0);

    _motor.burnFlash();
  }

  public void stop() {
    _motor.stopMotor();
  }

  public void forward() {
    _motor.set(1);
  }

  public void reverse() {
    _motor.set(-1);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Motor Position", _encoder.getPosition());
  }
}
