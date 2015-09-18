package problem;

import java.util.List;
import java.util.Collection;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import beans.Asignacion;
import beans.Empleado;

@PlanningSolution
public class SolucionAsignacion implements Solution<HardSoftScore>{
	HardSoftScore score;
	List<Empleado> empleados;
	List<Asignacion> asignaciones;
	
	@Override
	public Collection<? extends Object> getProblemFacts() {
		
		return getEmpleados();
	}

	@Override
	public HardSoftScore getScore() {
		return score;
	}

	@Override
	public void setScore(HardSoftScore score) {
		this.score = score;
		
	}
	@ValueRangeProvider(id="employeeRange")
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	
	@PlanningEntityCollectionProperty
	public List<Asignacion> getAsignaciones() {
		return asignaciones;
	}

	public void setAsignaciones(List<Asignacion> asignaciones) {
		this.asignaciones = asignaciones;
	}
	
	
	
}
