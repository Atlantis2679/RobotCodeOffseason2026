package frc.robot.subsystems.slaptake;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.slaptake.io.SlaptakeIO;
import frc.robot.subsystems.slaptake.io.SlaptakeIOTalon;
import frc.robot.subsystems.slaptake.io.SlaptakeIOSim;
import team2679.atlantiskit.logfields.LogFieldsTable;
import team2679.atlantiskit.tunables.extensions.TunableArmFeedforward;
import team2679.atlantiskit.tunables.extensions.TunableTrapezoidProfile;

import static edu.wpi.first.units.Units.Seconds;
import static frc.robot.subsystems.slaptake.SlaptakeConstants.*;

public class Slaptake extends SubsystemBase{
    private LogFieldsTable logFieldsTable = new LogFieldsTable(getName());
    private SlaptakeIO io = Robot.isReal() ?
                            new SlaptakeIOTalon(logFieldsTable) :
                            new SlaptakeIOSim(logFieldsTable);
    private TunableArmFeedforward feedforward = new TunableArmFeedforward(KS, KG, KV);
    private TunableTrapezoidProfile trapezoidProfile = new TunableTrapezoidProfile(
        new TrapezoidProfile.Constraints(
            MAX_VELOCITY.magnitude(), MAX_ACCELERATION.magnitude())
    );
    private PIDController pid = new PIDController(KP, KI, KD);
    private Voltage desiredVoltage;

    public Slaptake() {
        pid.enableContinuousInput(0, 360);
    }

    @Override
    public void periodic() {
        logFieldsTable.recordOutput("current command", getCurrentCommand() != null ?
            getCurrentCommand().getName() :
            "none");
        logFieldsTable.recordOutput("desired voltage", this.desiredVoltage);
    }

    public void resetPID() {
        pid.reset();
    }

    public Angle getAngle() {
        return io.angle.get();
    }

    public TrapezoidProfile.State calcTrapezoidProfile(Time time, State current, State desired) {
        return trapezoidProfile.calculate(time.in(Seconds), 
            current, desired);
    }

    public Voltage calcFeedForward(Angle desiredAngle, AngularVelocity desiredSpeed, boolean pid){
        
    }

    public void setVoltage(Voltage voltage){
        io.setVoltage(voltage);
    }
}
