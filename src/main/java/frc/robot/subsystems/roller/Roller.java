package frc.robot.subsystems.roller;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.units.VoltageUnit;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.subsystems.roller.io.RollerIO;
import frc.robot.subsystems.roller.io.RollerIOSim;
import frc.robot.subsystems.roller.io.RollerIOTalon;
import frc.robot.utils.MathUtils.Bounds;
import team2679.atlantiskit.logfields.LogFieldsTable;
import static frc.robot.subsystems.roller.RollerConstants.*;

public class Roller extends SubsystemBase {
    private final LogFieldsTable logFieldsTable = new LogFieldsTable("Rolelr");
    private final RollerIO io = Robot.isReal() ? new RollerIOTalon(logFieldsTable) : new RollerIOSim(logFieldsTable);

    private final Bounds<VoltageUnit, Voltage> rollerVoltageBounds = new Bounds<>(MAX_VOLTAGE.unaryMinus(), MAX_VOLTAGE);

    public Roller() {}

    @Override
    public void periodic() {
        logFieldsTable.recordOutput("Current command: ",
            getCurrentCommand() != null? getCurrentCommand().getName() : "None");
    }

    public void stop() {
        setVoltage(Volts.zero());;
    }

    public void setVoltage(Voltage voltage) {
        voltage = rollerVoltageBounds.clamp(voltage);
        io.setVoltage(voltage);
    }

    public Current getCurrent() {
        return io.current.get();
    }
}
