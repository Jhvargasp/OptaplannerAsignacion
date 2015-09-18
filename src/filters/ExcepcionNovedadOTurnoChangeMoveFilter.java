package filters;


import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionFilter;
import org.optaplanner.core.impl.heuristic.selector.move.generic.ChangeMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import beans.Asignacion;
import beans.Empleado;
import beans.configuration.Turno;

import java.util.Collection;
import java.util.List;

/**
 * Created by JG on 20/01/15.
 */
public class ExcepcionNovedadOTurnoChangeMoveFilter implements SelectionFilter<Move> {
    public boolean accept(ScoreDirector scoreDirector, Move selection) {
        Asignacion asignacion = (Asignacion) selection.getPlanningEntities().iterator().next();
        Turno turno = asignacion.getRequerimiento().getTurno();
        
        Empleado empleado = (Empleado) selection.getPlanningValues().iterator().next();

        return true; 
    }
}
