package principal;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import beans.Asignacion;

public class AsignacionComparator implements Comparator<Asignacion> {

	@Override
	public int compare(Asignacion o1, Asignacion o2) {
		// return o1.getDia().compareTo(o2.getDia());
		return new CompareToBuilder()
				.append(o1.getDia(), o2.getDia())
				.append(o1.getRequerimiento().getTurno().getHoraInicio(),
						o2.getRequerimiento().getTurno().getHoraInicio())
				.toComparison();
	}

}
