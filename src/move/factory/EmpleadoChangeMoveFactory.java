package move.factory;


import move.EmpleadoChangeMove;

import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

import beans.Asignacion;
import beans.Empleado;
import problem.SolucionAsignacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JG on 10/11/14.
 */
public class EmpleadoChangeMoveFactory implements MoveListFactory<SolucionAsignacion> {
    //Aqui puedo poner los filtros si es del caso?
    public List<? extends Move> createMoveList(SolucionAsignacion solution) {
        List<Move> movimientos = new ArrayList<Move>();
        List<Empleado> empleados = solution.getEmpleados();
        for (Asignacion asignacion : solution.getAsignaciones()) {
                for (Empleado empleado : empleados) {
                    movimientos.add(new EmpleadoChangeMove(asignacion, empleado));
                }
        }

        return movimientos;
    }
}
