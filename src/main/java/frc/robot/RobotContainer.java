// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIOTalonFX;

public class RobotContainer {
  private final Intake intake;
  private static CommandXboxController m_DriverController = new CommandXboxController(0);
  public RobotContainer() {
    intake = new Intake(new IntakeIOTalonFX(15, 98) );
    configureBindings();
  }

  private void configureBindings() {
    m_DriverController.a().onTrue(intake.getNewSetVoltsCommand(2)).onFalse(intake.getNewSetVoltsCommand(0));
    m_DriverController.b().onTrue(intake.getNewSetVoltsCommand(-2)).onFalse(intake.getNewSetVoltsCommand(0));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
