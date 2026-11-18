package frc.robot.subsystems.roller;

import static frc.robot.subsystems.roller.RollerConstants.*;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;

public class RollerCommands {
    private Roller roller;
    public RollerCommands(Roller roller){
        this.roller = roller;
    }

    public Command stop() {
        return roller.run(roller::stop).withName("stop roller");
    }

    public Command setVoltage(Supplier<Voltage> volt) {
        return roller.run(() -> roller.setVoltage(volt.get())).withName("set roller voltage");
    }

    public Command setVoltage(Voltage volt) {
        return setVoltage(() -> volt);
    }

    public Command manualController(DoubleSupplier speed) {
        return setVoltage(() -> MAX_VOLTAGE.times(speed.getAsDouble())).withName("manual roller controller");
    }
}
