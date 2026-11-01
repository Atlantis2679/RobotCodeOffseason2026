package frc.robot.subsystems.slaptake;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;

public class SlaptakeConstants {
    public static final Current CURRENT_LIMIT = Units.Amps.of(50); 
    public static final Voltage MAX_VOLTAGE = Units.Volts.of(12);
    public static final Voltage MIN_VOLTAGE = Units.Volts.of(-12);

    public static final double KS = 0;
    public static final double KV = 0;
    public static final double KG = 0;

    public static final double KP = 0;
    public static final double KI = 0;
    public static final double KD = 0;

    public static final AngularVelocity MAX_VELOCITY = Units.DegreesPerSecond.of(0);
    public static final AngularAcceleration MAX_ACCELERATION = 
        Units.DegreesPerSecondPerSecond.of(0); 
}
