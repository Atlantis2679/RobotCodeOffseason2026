package frc.robot.subsystems.slaptake.io;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Degrees;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import team2679.atlantiskit.logfields.LogFieldsTable;

public class SlaptakeIOSim extends SlaptakeIO {

    public SlaptakeIOSim(LogFieldsTable logfields) {
        super(logfields);
    }

    protected Current getCurrent() {
        return Amps.of(0);
    }

    protected Angle getAngle() {
        return Degrees.of(0);
    }

    public void setVoltage(Voltage voltage) {}



}
