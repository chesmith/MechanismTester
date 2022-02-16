// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;

/** Add your docs here. */
public class XboxTrigger extends Button {
    Joystick joystick;
    int axis;

    public XboxTrigger(Joystick joystick, int axis) {
        this.joystick = joystick;
        this.axis = axis;
    }

    public double getTriggerValue() {
        return joystick.getRawAxis(axis);
    }

    @Override
    public boolean get() {
        double value = getTriggerValue();
        return value > 0.15;
    }

}