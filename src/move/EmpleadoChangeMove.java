package move;


import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import beans.Asignacion;
import beans.Empleado;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by JG on 10/11/14.
 */
public class EmpleadoChangeMove implements Move {
    private Asignacion asignacion;
    private Empleado empleado;

    public EmpleadoChangeMove(Asignacion asignacion, Empleado empleado) {
        this.asignacion = asignacion;
        this.empleado = empleado;
    }
    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !ObjectUtils.equals(this.asignacion.getEmpleado(), this.empleado);
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new EmpleadoChangeMove(this.asignacion, this.asignacion.getEmpleado());
    }

    public void doMove(ScoreDirector scoreDirector) {
        scoreDirector.beforeVariableChanged(this.asignacion, "empleado");
        this.asignacion.setEmpleado(this.empleado);
        scoreDirector.afterVariableChanged(this.asignacion, "empleado");
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(this.asignacion);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(this.empleado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof EmpleadoChangeMove) {
            EmpleadoChangeMove other = (EmpleadoChangeMove) o;
            return new EqualsBuilder()
                    .append(this.asignacion, other.asignacion)
                    .append(this.empleado, other.empleado)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(asignacion)
                .append(empleado)
                .toHashCode();
    }
	@Override
	public String getSimpleMoveTypeDescription() {
		return null;
	}
}
