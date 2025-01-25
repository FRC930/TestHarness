// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.elevator.Elevator;
import frc.robot.subsystems.elevator.ElevatorIOTalonFX;
import frc.robot.subsystems.fingeys.Fingeys;
import frc.robot.subsystems.fingeys.FingeysIOTalonFX;
import frc.robot.subsystems.wrist.Wrist;
import frc.robot.subsystems.wrist.WristIOTalonFX;

public class RobotContainer {
  // private final Fingeys fingeys;
  //private final Wrist wrist;
  private final Elevator elevator;

  private static CommandXboxController m_DriverController = new CommandXboxController(0);
  public RobotContainer() {
    // fingeys = new Fingeys(new FingeysIOTalonFX(11));
    //wrist = new Wrist(new WristIOTalonFX(11));
    elevator = new Elevator(new ElevatorIOTalonFX(13));
    configureBindings();
  }

  private void configureBindings() {
    // m_DriverController.a().onTrue(fingeys.getNewSetAngleCommand(2)).onFalse(fingeys.getNewSetAngleCommand(0));
    // m_DriverController.b().onTrue(fingeys.getNewSetAngleCommand(-2)).onFalse(fingeys.getNewSetAngleCommand(0));
    //m_DriverController.a().onTrue(wrist.getNewWristTurnCommand(90)).onFalse(wrist.getNewWristTurnCommand(0));
    //m_DriverController.b().onTrue(wrist.getNewWristTurnCommand(-90)).onFalse(wrist.getNewWristTurnCommand(0));
    m_DriverController.a().onTrue(elevator.getNewSetDistanceCommand(1)).onFalse(elevator.getNewSetDistanceCommand(0));
    m_DriverController.b().onTrue(elevator.getNewSetDistanceCommand(-1)).onFalse(elevator.getNewSetDistanceCommand(0));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
