package frc.robot.subsystems.index;

import static frc.robot.subsystems.index.IndexConstants.MAX_FEEDER_VOLT;
import static frc.robot.subsystems.index.IndexConstants.MAX_HOPPER_VOLT;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.VoltageUnit;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.index.io.IndexIO;
import frc.robot.subsystems.index.io.IndexIOSim;
import frc.robot.subsystems.index.io.IndexIOTalon;
import frc.robot.utils.MathUtils.Bounds;
import team2679.atlantiskit.logfields.LogFieldsTable;

public class Index extends SubsystemBase{
    private final LogFieldsTable logFieldsTable = new LogFieldsTable(getName());
    private final IndexIO io = Robot.isReal()? new IndexIOTalon(logFieldsTable) : new IndexIOSim(logFieldsTable);

    private final Bounds<VoltageUnit, Voltage> feederVoltsBounds = new Bounds<VoltageUnit,Voltage>(MAX_FEEDER_VOLT.unaryMinus(), MAX_FEEDER_VOLT);
    private final Bounds<VoltageUnit, Voltage> hopperVoltsBounds = new Bounds<VoltageUnit,Voltage>(MAX_HOPPER_VOLT.unaryMinus(), MAX_HOPPER_VOLT);

    public Index() {
    }

    public void periodic() {
        logFieldsTable.recordOutput("Current command: ",
            getCurrentCommand() != null? getCurrentCommand().getName() : "None");
    }

    public void stop() {
        io.setFeederVolt(Units.Volts.zero());
        io.setHopperVolt(Units.Volts.zero());
    }

    public void setFeederVolt(Voltage volt) {
        logFieldsTable.recordOutput("FeederMotorDesiredVolt", volt);
        volt = feederVoltsBounds.clamp(volt);
        io.setFeederVolt(volt);
    }

    public void setHopperVolt(Voltage volt) {
        logFieldsTable.recordOutput("HopperMotorDesiredVolt", volt);
        volt = hopperVoltsBounds.clamp(volt);
        io.setHopperVolt(volt);
    }

    public Current getFeederCurrent() {
        return io.feederCurrent.get();
    }

    public Current getHopperCurrent() {
        return io.hopperCurrent.get();
    }
}
