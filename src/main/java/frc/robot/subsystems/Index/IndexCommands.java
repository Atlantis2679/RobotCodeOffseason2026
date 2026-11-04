package frc.robot.subsystems.Index;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import com.ctre.phoenix6.configs.VoltageConfigs;

import edu.wpi.first.units.Unit;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import team2679.atlantiskit.tunables.TunablesManager;
import team2679.atlantiskit.tunables.extensions.TunableCommand;
import team2679.atlantiskit.valueholders.DoubleHolder;

public class IndexCommands {
    private Index index;

    public IndexCommands(Index index){
        this.index = index;
        //tuneables maneger
    }

    public Command spinBoth(Supplier<Voltage> feederVolt, Supplier<Voltage> hopperVolt){
        return index.run(() -> {
            index.setFeederVolt(feederVolt.get());
            index.setHopperVolt(feederVolt.get());  
        }).finallyDo(index::stop)
        .withName("Index spin both motors");
    }

    public Command spinBoth(Voltage feederVolt, Voltage hopperVolt){
        return spinBoth(() -> feederVolt, () -> hopperVolt);
    }

    public Command stopBoth(){
        return spinBoth(Units.Volts.zero(), Units.Volts.zero());
    }

    public Command manualController(DoubleSupplier speed){
        return spinBoth(() -> Units.Volts.of(speed.getAsDouble()*IndexConstants.MAX_FEEDER_VOLT),
                        () -> Units.Volts.of(speed.getAsDouble()*IndexConstants.MAX_HOPPER_VOLT));
    }

    public TunableCommand tunableSetVoltage(){
        return TunableCommand.wrap((tunablesTable) -> {
            DoubleHolder voltageFeeder = tunablesTable.addNumber("FeederVolt", 0.0);
            DoubleHolder voltageHopper = tunablesTable.addNumber("HopperVolt", 0.0);

            return spinBoth(Units.Volts.of(voltageFeeder.get()),
                            Units.Volts.of(voltageHopper.get())).withName("TunableIndex");
        });
    }
}
