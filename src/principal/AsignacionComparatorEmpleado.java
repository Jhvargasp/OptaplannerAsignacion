package principal;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import beans.Asignacion;

public class AsignacionComparatorEmpleado implements Comparator<Asignacion> {

	@Override
	public int compare(Asignacion o1, Asignacion o2) {
		return new CompareToBuilder().append(o1.getEmpleado().getNombre(),
				o2.getEmpleado().getNombre()).toComparison();
	}

}
