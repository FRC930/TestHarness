// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command; 
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIONova;

public class RobotContainer {

  private static CommandXboxController m_DriverController = new CommandXboxController(0);

  Intake intake = new Intake(new IntakeIONova(18,26));

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_DriverController.a().whileTrue(intake.getNewSetVoltsCommand(6)).onFalse(intake.getNewSetVoltsCommand(0));
    m_DriverController.b().whileTrue(intake.getNewSetVoltsCommand(-6)).onFalse(intake.getNewSetVoltsCommand(0));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
