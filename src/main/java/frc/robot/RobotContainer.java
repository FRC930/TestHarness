// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.fingeys.Fingeys;
import frc.robot.subsystems.fingeys.FingeysIO;
import frc.robot.subsystems.fingeys.FingeysIOTalonFX;


public class RobotContainer {
  private final Fingeys fingeys = new Fingeys(new FingeysIOTalonFX(6));

  private static CommandXboxController m_DriverController = new CommandXboxController(0);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_DriverController.a().onTrue(fingeys.getNewSetVoltsCommand(90)).onFalse(fingeys.getNewSetVoltsCommand(0));
    m_DriverController.b().onTrue(fingeys.getNewSetVoltsCommand(-90)).onFalse(fingeys.getNewSetVoltsCommand(0));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
