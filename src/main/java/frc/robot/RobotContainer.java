// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.controls.ColorFlowAnimation;
import com.ctre.phoenix6.controls.FireAnimation;
import com.ctre.phoenix6.controls.LarsonAnimation;
import com.ctre.phoenix6.controls.RainbowAnimation;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  private static CommandXboxController m_DriverController = new CommandXboxController(0);
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    LED led = new LED();
    m_DriverController.y().onTrue(new InstantCommand(() -> {led.setColor(0, 7, colors.eRed);}));
    m_DriverController.x().onTrue(new InstantCommand(() -> {led.setColor(0, 7, colors.eViolet);}));
    m_DriverController.b().onTrue(new InstantCommand(() -> {led.setColor(0, 7, colors.eGreen);}));
    m_DriverController.a().onTrue(new InstantCommand(() -> {led.setColor(0, 7, colors.eWhite);}));
    m_DriverController.povUp().onTrue(new InstantCommand(() -> {led.setAnimation(new ColorFlowAnimation(0, 7));}));
    m_DriverController.povDown().onTrue(new InstantCommand(() -> {led.setAnimation(new FireAnimation(0, 7));}));
    m_DriverController.povLeft().onTrue(new InstantCommand(() -> {led.setAnimation(new RainbowAnimation(0, 7));}));
    m_DriverController.povRight().onTrue(new InstantCommand(() -> {led.setAnimation(new LarsonAnimation(0, 7));}));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
