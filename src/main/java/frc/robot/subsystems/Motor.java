// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.JoystickConstants;

public class Motor extends SubsystemBase {
  private final CANSparkMax _motor = new CANSparkMax(Constants.MOTOR_ID, MotorType.kBrushless);

  private int _rampCounter = 0;   // as the motor ramps up to target speed, the current will spike - this counter will prevent the current limit from triggering too early
  private int _limitCounter = 0;  // ensure the current is past the limit for some cycles before asserting the motor is at limit (avoids stopping on momentary spikes)

  private final int RAMPLIMIT = 25; // # of cycles to allow the motor to ramp up after an initial current spike
  private final double STOPCURRENT = 7.0; // current at which to stop the motor
  private final int STOPLIMIT = 25; // once we detect over the stop current, # of cycles after which to actually stop the motor (1 cycle = 20 ms ... 50 cycles = 1 s)

  private final DigitalInput _topLimitSwitch = new DigitalInput(0);
  private final DigitalInput _bottomLimitSwitch = new DigitalInput(1);

  public Motor() {
    setDefaultCommand(new RunCommand(this::stop, this));

    _motor.restoreFactoryDefaults();

    _motor.setIdleMode(IdleMode.kBrake);

    _motor.burnFlash();
  }

  public void init() {
    _rampCounter = 0;
    _limitCounter = 0;
  }

  public void stop() {
    _motor.stopMotor();
  }

  public void go(Joystick joystick) {
    double power = joystick.getRawAxis(JoystickConstants.LEFT_STICK_Y);
    if(Math.abs(power) < 0.1) power = 0;

    _motor.set(power);
  }

  public void go(double power) {
    _motor.set(power);
  }

  public boolean isAtAmperage() {
    if(_limitCounter > STOPLIMIT)
      return true;
    else
      return false;
  }

  public boolean isAtLimit() {
    return (_topLimitSwitch.get() || _bottomLimitSwitch.get());
  }

  @Override
  public void periodic() {
    double current = _motor.getOutputCurrent();
    if(current > STOPCURRENT && _rampCounter > RAMPLIMIT)
      _limitCounter++;
    else
      _limitCounter = 0;

    _rampCounter++;

    SmartDashboard.putNumber("Power", _motor.get());
    SmartDashboard.putNumber("Amerage", _motor.getOutputCurrent());
    SmartDashboard.putBoolean("Test.TopLimit", _topLimitSwitch.get());
    SmartDashboard.putBoolean("Test.BottomLimit", _bottomLimitSwitch.get());
  }
}
