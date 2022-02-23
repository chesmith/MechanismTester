// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.LEDStrip.LED_MODE;
import frc.robot.util.XboxPOV;
import frc.robot.util.XboxTrigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final Joystick _joystick = new Joystick(0);
  private final Motor _motor = new Motor();
  private final Piston _piston = new Piston();
  private final ServoMotor _servo1 = new ServoMotor(Constants.SERVO_1_PORT);
  private final ServoMotor _servo2 = new ServoMotor(Constants.SERVO_2_PORT);
  private final MotorWithSoftLimits _motorWithSoftLimits = new MotorWithSoftLimits();
  public LEDStrip Leds = new LEDStrip(9, Constants.LED_PORT);

  private final ADIS16470_IMU _adis16470 = new ADIS16470_IMU();
  private final Gyro _gyro = new Gyro(_adis16470);

  public RobotContainer() {
    configureButtonBindings();

    _motor.setDefaultCommand(new MotorGoJoystick(_motor, _joystick));
    _servo1.setDefaultCommand(new ServoMove(_servo1, _joystick, JoystickConstants.RIGHT_STICK_X));
    // _servo2.setDefaultCommand(new ServoMove(_servo2, _joystick, JoystickConstants.RIGHT_STICK_Y));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // new JoystickButton(_joystick, JoystickConstants.A).whileHeld(new MotorGoPower(_motor, 0.25));
    // new JoystickButton(_joystick, JoystickConstants.Y).whileHeld(new MotorGoPower(_motor, 0.5));
    // new JoystickButton(_joystick, JoystickConstants.X).whileHeld(new MotorGoPower(_motor, 0.75));
    // new JoystickButton(_joystick, JoystickConstants.B).whileHeld(new MotorGoPower(_motor, 1.0));

    new JoystickButton(_joystick, JoystickConstants.BUMPER_LEFT).whileHeld(new PistonIn(_piston));
    new JoystickButton(_joystick, JoystickConstants.BUMPER_RIGHT).whileHeld(new PistonOut(_piston));

    new JoystickButton(_joystick, JoystickConstants.LEFT_STICK_BUTTON).whenPressed(new MotorGoToAmp(_motor));
    new JoystickButton(_joystick, JoystickConstants.RIGHT_STICK_BUTTON).whenPressed(new MotorGoToLimit(_motor));

    new JoystickButton(_joystick, JoystickConstants.LOGO_LEFT).whileHeld(new MotorToForwardLimit(_motorWithSoftLimits));
    new JoystickButton(_joystick, JoystickConstants.LOGO_RIGHT).whileHeld(new MotorToReverseLimit(_motorWithSoftLimits));

    new XboxTrigger(_joystick, JoystickConstants.LEFT_TRIGGER).whenPressed(new ServoUp(_servo2));
    new XboxTrigger(_joystick, JoystickConstants.RIGHT_TRIGGER).whenPressed(new ServoDown(_servo2));

    new XboxPOV(_joystick).whenPressed(new POVAction(_joystick));

    new JoystickButton(_joystick, JoystickConstants.A).whenPressed(new InstantCommand(() -> Leds.setColor(Color.kDarkRed), Leds));
    new JoystickButton(_joystick, JoystickConstants.B).whenPressed(new InstantCommand(() -> Leds.setColor(Color.kDarkGreen), Leds));
    new JoystickButton(_joystick, JoystickConstants.X).whenPressed(new InstantCommand(() -> Leds.setColor(Color.kBlue), Leds));
    new JoystickButton(_joystick, JoystickConstants.Y).whenPressed(new InstantCommand(() -> Leds.setColor(Color.kDarkBlue), Leds));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
