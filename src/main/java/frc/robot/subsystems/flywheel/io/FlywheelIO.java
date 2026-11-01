package frc.robot.subsystems.flywheel.io;

import java.util.function.Supplier;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import team2679.atlantiskit.logfields.IOBase;
import team2679.atlantiskit.logfields.LogFieldsTable;

public abstract class FlywheelIO extends IOBase{
    public Supplier<Current> current = fields.addMeasure("current", 
        this::getCurrent, Units.Amps.of(0));

    public FlywheelIO(LogFieldsTable logFieldsTable) {
        super(logFieldsTable);
    }

    protected abstract Current getCurrent();

    public abstract void setVoltage(Voltage voltage);
}
