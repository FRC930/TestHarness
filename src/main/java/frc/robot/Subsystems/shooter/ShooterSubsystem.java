package frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.Volts;

import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.shooter.ShooterIO.ShooterInputs;

public class ShooterSubsystem extends SubsystemBase {
    private ShooterIO m_IO;
    private ShooterInputsAutoLogged logged = new ShooterInputsAutoLogged();
    public TalonFX roller;
    public ShooterSubsystem(ShooterIO io){
        m_IO = io;
        logged.angularVelocity = DegreesPerSecond.mutable(0);
        logged.supplyCurrent = Amps.mutable(0);
        logged.torqueCurrent = Amps.mutable(0);
        logged.voltageSetPoint = Volts.mutable(0);
        logged.voltage = Volts.mutable(0);
    }
    public void updateInputs(ShooterInputs input){
        logged.voltage = Volts.mutable(0);
    }
    public void shoot(Voltage target){
        m_IO.shoot(target);
    }
    public void stop(){
        m_IO.stop();
    }
    public void periodic() {
        m_IO.updateInputs(logged);
        Logger.processInputs("RobotState/Shooter", logged);
    }
}
