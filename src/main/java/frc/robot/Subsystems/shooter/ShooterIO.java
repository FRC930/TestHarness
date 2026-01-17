package frc.robot.Subsystems.shooter;

import edu.wpi.first.units.measure.Voltage;

public interface ShooterIO {

    // @AutoLog
    public static class ShooterInputs{
        public double voltage;
    }
    
    public void shoot(Voltage target);

    public void updateInputs(ShooterInputs input);

    public void stop();
}
