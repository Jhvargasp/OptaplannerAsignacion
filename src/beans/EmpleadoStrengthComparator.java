package beans;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

@SuppressWarnings("serial")
public class EmpleadoStrengthComparator  implements Comparator<Empleado>, Serializable {

	@Override
    public int compare(Empleado a, Empleado b) {
        if (a == null) return -1;
        if (b == null) return 1;
        int comparison = new CompareToBuilder()
        		.append(a.getSalarioBase(), b.getSalarioBase())
                .append(a.getRndOrden(), b.getRndOrden())
                .toComparison();
        return comparison;
    }
    
    public static void main(String[] args) {
    	Empleado emp1 = new Empleado();
    	emp1.setId(1);
    	emp1.setSalarioBase(300);
    	Empleado emp2 = new Empleado();
    	emp2.setId(2);
    	emp2.setSalarioBase(300);
    	System.out.println(emp1.getRndOrden());
    	System.out.println(emp2.getRndOrden());
		System.out.println(new EmpleadoStrengthComparator().compare(emp1, emp2));
	}

}
