package frc.robot.subsystems.shooter;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.MutAngularVelocity;
import edu.wpi.first.units.measure.MutCurrent;
import edu.wpi.first.units.measure.MutVoltage;
import edu.wpi.first.units.measure.Voltage;

public interface ShooterIO {

    @AutoLog
    public static class ShooterInputs{
        public MutAngularVelocity angularVelocity;
        public MutVoltage voltage;
        public MutVoltage voltageSetPoint;
        public MutCurrent supplyCurrent;
        public MutCurrent torqueCurrent;
    }
    
    public void shoot(Voltage target);

    public void updateInputs(ShooterInputs input);

    public void stop();
}
