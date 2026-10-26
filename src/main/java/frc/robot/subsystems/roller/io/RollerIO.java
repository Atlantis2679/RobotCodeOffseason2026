package frc.robot.subsystems.roller.io;

import java.util.function.DoubleSupplier;

import edu.wpi.first.units.measure.Voltage;
import team2679.atlantiskit.logfields.IOBase;
import team2679.atlantiskit.logfields.LogFieldsTable;

public abstract class RollerIO extends IOBase{
    public DoubleSupplier getCurrent = fields.addDouble("current", this::getCurrent);

    public RollerIO(LogFieldsTable fields){
        super(fields);
    }

    protected abstract double getCurrent();

    public abstract void setVoltage(Voltage voltage);
}
