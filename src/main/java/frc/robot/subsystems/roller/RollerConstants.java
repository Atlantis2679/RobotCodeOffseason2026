package frc.robot.subsystems.roller;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;

public class RollerConstants {
    public static final Current CURRENT_LIMIT = Amps.of(40);
    public static final Voltage MAX_VOLTAGE = Volts.of(12);
}
