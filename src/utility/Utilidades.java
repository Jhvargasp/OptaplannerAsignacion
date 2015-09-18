package utility;


import java.util.List;

import beans.Asignacion;
import beans.configuration.Turno;


public class Utilidades {


	public static boolean Translapa(List<Asignacion> asignaciones)
    {
    	boolean resp = false;
    	for (Asignacion asignacion : asignaciones) {
	    	
	        long thisHF = asignacion.getRequerimiento().getTurno().getHoraFin().getTime();
	        long thisHI = asignacion.getRequerimiento().getTurno().getHoraInicio().getTime();
	        
	        for (int i=0;i<asignaciones.size();i++){
	        	if (	asignaciones.get(i).getId() != asignacion.getId() && 
	        			asignaciones.get(i).getDia().equals(asignacion.getDia())
	        		)
	        	{
		        	Turno turno2 = asignaciones.get(i).getRequerimiento().getTurno();
		            long tHF = turno2.getHoraFin().getTime();
		            long tHI = turno2.getHoraInicio().getTime();
		            
		            resp = (
		                    (thisHI == tHI && thisHF == tHF) // que sean el mismo o contenido
		                    || (thisHI >= tHI && thisHI < tHF && thisHF >= tHF ) //que este zolapen
		                    || (thisHI < tHI && thisHF > tHI && thisHF <= tHF)
		                    || (thisHI <= tHI && thisHF >= tHF)
		                    || (thisHI >= tHI && thisHF <= tHF)
		            ) ;
		            
		            if (resp){
		            	return resp;
		            }
	        	}
	        }
    	}
        return resp;
    }
}
