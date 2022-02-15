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

  private final float FORWARD_LIMIT = 20.0f;
  private final float REVERSE_LIMIT = 0.0f;

  public MotorWithSoftLimits() {
    setDefaultCommand(new RunCommand(this::stop, this));

    _motor.restoreFactoryDefaults();

    _motor.setIdleMode(IdleMode.kBrake);

    _motor.enableSoftLimit(SoftLimitDirection.kForward, true);
    _motor.setSoftLimit(SoftLimitDirection.kForward, FORWARD_LIMIT);
    _motor.enableSoftLimit(SoftLimitDirection.kReverse, true);
    _motor.setSoftLimit(SoftLimitDirection.kReverse, REVERSE_LIMIT);

    _encoder.setPosition(0);

    _motor.burnFlash();
  }

  public void stop() {
    _motor.stopMotor();
  }

  public void forward() {
    _motor.set(0.25);
  }

  public void reverse() {
    _motor.set(-0.25);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Motor Position", _encoder.getPosition());
  }
}
