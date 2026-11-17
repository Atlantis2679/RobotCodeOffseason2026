package frc.robot.utils;

import static edu.wpi.first.units.Units.Value;

import java.util.function.Consumer;
import java.util.function.Supplier;

import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Unit;
import team2679.atlantiskit.tunables.Tunable;
import team2679.atlantiskit.tunables.TunableBuilder;

public final class TunableMeasure<M extends Measure<?>> implements Supplier<M> {
    private M value;
    private final Tunable tunable;

    @SuppressWarnings("unchecked")
    public <U extends Unit> TunableMeasure(M initial, U unit) {
        value = initial;
        tunable = bind(() -> (Measure<U>) value, v -> value = (M) v, unit);
    }

    public static <U extends Unit, M extends Measure<U>> Tunable bind(
            Supplier<M> getter, Consumer<M> setter, U unit) {
        return new TunableMeasure<Measure<?>>
        }

    @Override public M get() { return value; }
    public Tunable tunable() { return tunable; }
}