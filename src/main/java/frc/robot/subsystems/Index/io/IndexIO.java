package frc.robot.subsystems.Index.io;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import team2679.atlantiskit.logfields.IOBase;
import team2679.atlantiskit.logfields.LogFieldsTable;

public abstract class IndexIO extends IOBase{

    public Supplier<Current> hopperCurrent = fields.addMeasure("hopperCurrent", this::getHopperCurrent, Units.Amps.zero());
    public Supplier<Current> feederCurrent = fields.addMeasure("feederCurrent", this::getFeederCurrent, Units.Amps.zero());

    public IndexIO(LogFieldsTable fieldsTable){
        super (fieldsTable);
    }
    

    protected abstract Current getHopperCurrent();
    protected abstract Current getFeederCurrent();

    public abstract void setHopperVolt(Voltage volt);
    public abstract void setFeederVolt(Voltage volt);

}
