package frc.robot.subsystems.roller.io;

import java.util.function.Supplier;

import static edu.wpi.first.units.Units.Amps;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import team2679.atlantiskit.logfields.IOBase;
import team2679.atlantiskit.logfields.LogFieldsTable;

public abstract class RollerIO extends IOBase{
    public Supplier<Current> supplyCurrent = fields.addMeasure("Supply current", 
                                                                this::getSupplyCurrent, 
                                                                Amps.of(0));

    public Supplier<Current> torqueCurrent = fields.addMeasure("Torque current", 
                                                                this::getTorqueCurrent, 
                                                                Amps.of(0));

    public RollerIO(LogFieldsTable fields){
        super(fields);
    }

    protected abstract Current getSupplyCurrent();
    protected abstract Current getTorqueCurrent();

    public abstract void setVoltage(Voltage voltage);
}
