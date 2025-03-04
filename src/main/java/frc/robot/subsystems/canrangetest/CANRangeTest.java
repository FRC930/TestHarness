package frc.robot.subsystems.canrangetest;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.hardware.CANrange;

import edu.wpi.first.math.estimator.KalmanFilter;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANRangeTest extends SubsystemBase {
    private CANrange rangeSensor;

    public CANRangeTest(int canID) {
        rangeSensor = new CANrange(canID);
    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("RangeSensor/Distance",rangeSensor.getDistance().getValue().in(Centimeters));
        SmartDashboard.putNumber("RangeSensor/AmbientLight",rangeSensor.getAmbientSignal().getValue());
        SmartDashboard.putNumber("RangeSensor/DistanceStDev",rangeSensor.getDistanceStdDev().getValue().in(Centimeters));
        SmartDashboard.putNumber("RangeSensor/fovX/center",rangeSensor.getRealFOVCenterX().getValue().in(Degrees));
        SmartDashboard.putNumber("RangeSensor/fovX/range",rangeSensor.getRealFOVRangeX().getValue().in(Degrees));
        SmartDashboard.putNumber("RangeSensor/fovY/center",rangeSensor.getRealFOVCenterY().getValue().in(Degrees));
        SmartDashboard.putNumber("RangeSensor/fovY/range",rangeSensor.getRealFOVRangeY().getValue().in(Degrees));
        SmartDashboard.putNumber("RangeSensor/measurementHealth",rangeSensor.getMeasurementTime().getValue().in(Second));
        SmartDashboard.putString("RangeSensor/measurementHealth",rangeSensor.getMeasurementHealth().getValue().toString());
    }
}