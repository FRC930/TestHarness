package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ServoSubsystem extends SubsystemBase{
    private Servo exampleServo;
        public ServoSubsystem(int id){
            exampleServo = new Servo(id);
            exampleServo.set(.5);
        }
        public void setAngle(double degrees){
            exampleServo.setAngle(degrees);
        }
        public Command getNewSetAngleCommand(double i) {
        return new InstantCommand(
            () -> {
                setAngle(i);
        },
        this);
  }
}