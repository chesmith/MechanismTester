// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.util.XboxTrigger;
import edu.wpi.first.wpilibj2.command.Command;
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
  private final ServoMotor _servo = new ServoMotor();
  private final MotorWithSoftLimits _motorWithSoftLimits = new MotorWithSoftLimits();

  public RobotContainer() {
    configureButtonBindings();

    _motor.setDefaultCommand(new MotorGoJoystick(_motor, _joystick));
    _servo.setDefaultCommand(new ServoMove(_servo, _joystick));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(_joystick, JoystickConstants.A).whileHeld(new MotorGoPower(_motor, 0.25));
    new JoystickButton(_joystick, JoystickConstants.Y).whileHeld(new MotorGoPower(_motor, 0.5));
    new JoystickButton(_joystick, JoystickConstants.X).whileHeld(new MotorGoPower(_motor, 0.75));
    new JoystickButton(_joystick, JoystickConstants.B).whileHeld(new MotorGoPower(_motor, 1.0));

    new JoystickButton(_joystick, JoystickConstants.BUMPER_LEFT).whileHeld(new PistonIn(_piston));
    new JoystickButton(_joystick, JoystickConstants.BUMPER_RIGHT).whileHeld(new PistonOut(_piston));

    new JoystickButton(_joystick, JoystickConstants.LEFT_STICK_BUTTON).whenPressed(new MotorGoToAmp(_motor));
    new JoystickButton(_joystick, JoystickConstants.RIGHT_STICK_BUTTON).whenPressed(new MotorGoToLimit(_motor));

    new JoystickButton(_joystick, JoystickConstants.LOGO_LEFT).whileHeld(new MotorToForwardLimit(_motorWithSoftLimits));
    new JoystickButton(_joystick, JoystickConstants.LOGO_RIGHT).whileHeld(new MotorToReverseLimit(_motorWithSoftLimits));

    new XboxTrigger(_joystick, JoystickConstants.LEFT_TRIGGER).whenPressed(new PistonOut(_piston));
    new XboxTrigger(_joystick, JoystickConstants.RIGHT_TRIGGER).whenPressed(new PistonIn(_piston));
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
