package beans;


import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import beans.configuration.Requerimiento;

@PlanningEntity(difficultyComparatorClass= AsignacionDifficultyComparator.class)
public class Asignacion{
	private String id;
	private String nombre;
	private String dia;
	private Empleado empleado;
	private Requerimiento requerimiento;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@PlanningVariable(valueRangeProviderRefs = {"employeeRange"}
    ,strengthComparatorClass = EmpleadoStrengthComparator.class)//, nullable = false
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	} 
	
	public Requerimiento getRequerimiento() {
		return requerimiento;
	}

	public void setRequerimiento(Requerimiento requerimiento) {
		this.requerimiento = requerimiento;
	}
	
	@Override
	public String toString() {
		
		//if (getEmpleado()!=null)
			return "Asignación nombre: "+getNombre()+ " día: " +getDia() +" Req: "+getRequerimiento()+" empleado: "+getEmpleado();
		/*else
			return "Asignación nombre: SIN EMPLEADO "+getNombre()+" día: "+getDia() ;*/
	}

}
