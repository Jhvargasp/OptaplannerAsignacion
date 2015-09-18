package beans;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

public class AsignacionDifficultyComparator implements Comparator<Asignacion>{

	@Override
	public int compare(Asignacion o1, Asignacion o2) {
		
		return new CompareToBuilder().append(o1.getNombre(), o2.getNombre()).toComparison();
	}

}
