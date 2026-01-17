// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Volts;

import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;

import com.ctre.phoenix6.CANBus;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.launcher.LauncherIOTalonFX;
import frc.robot.subsystems.launcher.LauncherSubsystem;
import frc.robot.subsystems.shooter.ShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterIOTalonFX;


public class RobotContainer {

private final LauncherSubsystem launcher;

  private static CommandXboxController m_DriverController = new CommandXboxController(0);

  private final ShooterSubsystem shooter;

  public RobotContainer() {
    CANBus rioCANBus = new CANBus("rio");
        launcher =
            new LauncherSubsystem(
            new LauncherIOTalonFX(19, 11, rioCANBus));
    shooter = new ShooterSubsystem(new ShooterIOTalonFX(9, rioCANBus));
    configureBindings();

  }

  private void configureBindings() {
    m_DriverController
        .b()
        .onTrue(
            new InstantCommand(
                () -> {
                  launcher.setLaunchSpeed(Volts.of(2));
                  launcher.setIndexerSpeed(Volts.of(2));
                }))
                .onFalse(new InstantCommand(
                  () -> {
                    launcher.stop();
                  }
                ));
    m_DriverController.rightTrigger().onTrue(new InstantCommand(() -> shooter.shoot(Voltage.ofBaseUnits(3, Volts)))).onFalse(new InstantCommand(() -> shooter.stop()));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
