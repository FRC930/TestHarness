// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.launcher.LauncherIOTalonFX;
import frc.robot.subsystems.launcher.LauncherSubsystem;
import frc.robot.util.CANDef;
import frc.robot.util.CANDef.CANBus;


public class RobotContainer {

private final LauncherSubsystem launcher;

  private static CommandXboxController m_DriverController = new CommandXboxController(0);
  public RobotContainer() {
    CANDef.Builder rioCANBuilder = CANDef.builder().bus(CANBus.Rio);
    launcher =
        new LauncherSubsystem(
        new LauncherIOTalonFX(rioCANBuilder.id(19).build(), rioCANBuilder.id(11).build()));
    configureBindings();
  }

  private void configureBindings() {
    m_DriverController
        .b()
        .whileTrue(
            new InstantCommand(
                () -> {
                  launcher.setLaunchSpeed(Volts.of(2));
                  launcher.setIndexerSpeed(Volts.of(2));
                }))
                .whileFalse(new InstantCommand(
                  () -> {
                    launcher.stop();
                  }
                ));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
