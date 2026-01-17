package frc.robot.Subsystems.shooter;

import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private ShooterIO m_IO;
    private ShooterInputsAutoLogged logged = new ShooterInputsAutoLogged();
    public TalonFX roller;
    public Shooter(ShooterIO io){
        m_IO = io;
        logged.voltage = Volts.mutable(0);
    }
    public void shoot(Voltage target){
        m_IO.setTarget(target);
    }
}
