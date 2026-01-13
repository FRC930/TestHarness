// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.motor.*;

public class RobotContainer {

  private static CommandXboxController m_DriverController = new CommandXboxController(0);
  private static MotorIO motor = new MotorIOSpark(0);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_DriverController.a()
      .onTrue(new InstantCommand(() -> motor.setTarget(Volts.of(12.0))))
      .onFalse(new InstantCommand(() -> motor.stop()));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
