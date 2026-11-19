package frc.robot.subsystems.slaptake.io;

import java.util.function.Supplier;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import team2679.atlantiskit.logfields.IOBase;
import team2679.atlantiskit.logfields.LogFieldsTable;

public abstract class SlaptakeIO extends IOBase{
    public Supplier<Current> current = fields.addMeasure("current", 
        this::getCurrent, Units.Amps.zero());
    public Supplier<Angle> angle = fields.addMeasure("angle", 
        this::getAngle, Units.Degrees.zero());

    public SlaptakeIO(LogFieldsTable logFieldsTable) {
        super(logFieldsTable);
    }

    protected abstract Current getCurrent();
    protected abstract Angle getAngle();

    public abstract void setVoltage(Voltage voltage);
    public abstract void setCoast(boolean coast);
}