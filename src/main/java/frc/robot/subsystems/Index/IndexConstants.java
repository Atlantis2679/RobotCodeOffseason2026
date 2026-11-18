package frc.robot.subsystems.index;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;

public final class IndexConstants {
    public static final Voltage MAX_FEEDER_VOLT = Units.Volts.of(12);
    public static final Voltage MAX_HOPPER_VOLT = Units.Volts.of(12);

    public static final Current FEEDER_CURRENT_LIMIT = Units.Amps.of(40);
    public static final Current HOPPER_CURRENT_LIMIT = Units.Amps.of(40);
}
