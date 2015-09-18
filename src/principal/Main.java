package principal;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import beans.Asignacion;
import beans.Empleado;
import beans.configuration.Requerimiento;
import beans.configuration.Turno;
import problem.SolucionAsignacion;

public class Main {
	public static void main(String[] args) {
		Date d1=new Date();
		// Configuración
		SolverFactory solverFactory = SolverFactory
				.createFromXmlResource("solverconfig.xml");
		Solver solver = solverFactory.buildSolver();
		SolucionAsignacion problema = new SolucionAsignacion();

		Date fechaInicio = null;
		Date fechaLlegada = null;
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Turnos
		Turno turno1 = new Turno();
		turno1.setId(1);
		turno1.setName("06:00 a 12:00");
		try {
			fechaInicio = formato.parse("2015-09-08 06:00:00");
			fechaLlegada = formato.parse("2015-09-08 12:00:00");
			turno1.setHoraInicio(new Time(fechaInicio.getTime()));
			turno1.setHoraFin(new Time(fechaLlegada.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Turno turno2 = new Turno();
		turno2.setId(2);
		turno2.setName("14:00 a 18:00");

		try {
			fechaInicio = formato.parse("2015-09-08 14:00:00");
			fechaLlegada = formato.parse("2015-09-08 18:00:00");
			turno2.setHoraInicio(new Time(fechaInicio.getTime()));
			turno2.setHoraFin(new Time(fechaLlegada.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Partimos los turnos en dos medios turnos
		Turno[] splitTurno1 = turno1.splitHalf();
		Turno[] splitTurno2 = turno2.splitHalf();

		// Creación de asignaciones
		Map<String, Asignacion> asignaciones = new TreeMap<String, Asignacion>();
		String id = "";
		for (int d = 0; d < splitTurno1.length; d++) {

			id = String.format("%d-%d", splitTurno1[d].getId(), 10 + d);

			Requerimiento req = new Requerimiento();
			req.setId(10 + d);
			req.setTurno(splitTurno1[d]);

			Asignacion asig1 = new Asignacion();
			asig1.setNombre("asig1");
			asig1.setDia("Jueves");
			asig1.setRequerimiento(req);
			asig1.setId(id);
			asignaciones.put(id, asig1);
		}

		for (int d = 0; d < splitTurno1.length; d++) {

			id = String.format("%d-%d", splitTurno1[d].getId(), 20 + d);

			Requerimiento req = new Requerimiento();
			req.setId(20 + d);
			req.setTurno(splitTurno1[d]);

			Asignacion asig2 = new Asignacion();
			asig2.setNombre("asig2");
			asig2.setDia("Miércoles");
			asig2.setRequerimiento(req);

			asig2.setId(id);
			asignaciones.put(id, asig2);
		}

		for (int d = 0; d < splitTurno1.length; d++) {
			id = String.format("%d-%d", splitTurno1[d].getId(), 30 + d);
			Requerimiento req = new Requerimiento();
			req.setId(30 + d);
			req.setTurno(splitTurno1[d]);

			Asignacion asig3 = new Asignacion();
			asig3.setNombre("asig3");
			asig3.setDia("Viernes");
			asig3.setRequerimiento(req);
			asig3.setId(id);

			asignaciones.put(id, asig3);
		}

		for (int d = 0; d < splitTurno2.length; d++) {

			id = String.format("%d-%d", splitTurno2[d].getId(), 40 + d);

			Requerimiento req = new Requerimiento();
			req.setId(40 + d);
			req.setTurno(splitTurno2[d]);

			Asignacion asig4 = new Asignacion();
			asig4.setNombre("asig4");
			asig4.setDia("Jueves");
			asig4.setRequerimiento(req);
			asig4.setId(id);

			asignaciones.put(id, asig4);
		}

		for (int d = 0; d < splitTurno2.length; d++) {

			id = String.format("%d-%d", splitTurno2[d].getId(), 50 + d);

			Requerimiento req = new Requerimiento();
			req.setId(50 + d);
			req.setTurno(splitTurno2[d]);

			Asignacion asig5 = new Asignacion();
			asig5.setNombre("asig5");
			asig5.setDia("Jueves");
			asig5.setRequerimiento(req);
			asig5.setId(id);

			asignaciones.put(id, asig5);
		}

		// Creación set de empleados
		Map<Integer, Empleado> empleados = new HashMap<Integer, Empleado>();
		Empleado emp1 = new Empleado();
		emp1.setId(1);
		emp1.setNombre("Pepito");
		emp1.setRol("Analista");
		empleados.put(emp1.getId(), emp1);

		Empleado emp2 = new Empleado();
		emp2.setId(2);
		emp2.setNombre("Lina");
		emp2.setRol("Jefe");
		empleados.put(emp2.getId(), emp2);
		
		Empleado emp3 = new Empleado();
		emp3.setId(3);
		emp3.setNombre("Jorge");
		emp3.setRol("Jefe");
		empleados.put(emp3.getId(), emp3);
		
		Empleado emp4 = new Empleado();
		emp4.setId(4);
		emp4.setNombre("Juan");
		emp4.setRol("Jefe");
		empleados.put(emp4.getId(), emp4);


		problema.setAsignaciones(new ArrayList<Asignacion>(asignaciones
				.values()));
		problema.setEmpleados(new ArrayList<Empleado>(empleados.values()));

		solver.addEventListener(new Listener());
		solver.solve(problema);

		SolucionAsignacion bestSolucion = (SolucionAsignacion) solver
				.getBestSolution();
		System.out
				.println("******************* Mejor Solución *******************");
		List<Asignacion> asig = bestSolucion.getAsignaciones();
		asig = sortAsignaciones(asig);
		for (Asignacion asignacion : asig) {
			System.out.println(asignacion);
		}
		printcarga(asig);
		System.out.println("Puntaje: " + bestSolucion.getScore());
		System.out
		.println("******************* Tiempo sgs *******************");
		System.out.println((new Date().getTime()-d1.getTime())/1000 );

	}

	private static List<Asignacion> sortAsignaciones(List<Asignacion> asig) {

		Collections.sort(asig, new AsignacionComparator());
		return asig;
	}

	private static void printcarga(List<Asignacion> asig) {
		
		System.out
				.println("******************* Carga empleado *******************");
		String name = "";
		double total = 0;
		Collections.sort(asig, new AsignacionComparatorEmpleado());
		for (Asignacion asignacion : asig) {
			if (!asignacion.getEmpleado().getNombre().equalsIgnoreCase(name)) {
				System.out.println("Total>" + total + ", "
						+ name);
				name = asignacion.getEmpleado().getNombre();
				total = asignacion.getRequerimiento().getTurno()
						.getTotalHoras();
			} else {
				total += asignacion.getRequerimiento().getTurno()
						.getTotalHoras();
			}
		}
		System.out.println("Total>" + total + ", " + name);
	}

}
