package beans.configuration;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import beans.Asignacion;



/**
 * Created by JG on 27/10/14.
 */
public class Turno {
    private Time horaInicio;
    private Time horaFin;
    private String name;
    private int id;
    private int nextId;
    private int prevId;
    private boolean inicial;

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public int getPrevId() {
		return prevId;
	}

	public void setPrevId(int prevId) {
		this.prevId = prevId;
	}

	public boolean isInicial() {
        return inicial;
    }

    public void setInicial(boolean inicial) {
        this.inicial = inicial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Horario %s - %s Total horas %s", horaInicio.toString(), horaFin.toString(),getTotalHoras());
    }

    public Turno[] splitHalf() {
        Turno first = new Turno();
        Turno second = new Turno();

        long horaInicio = this.horaInicio.getTime();
        long horaFin = this.horaFin.getTime();

        if (horaFin < horaInicio ) //next day
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.horaFin);
            cal.add(Calendar.DATE,1);
            horaFin = cal.getTime().getTime();
            this.horaFin = new Time(horaFin);
        }
        long totalTime = (horaFin - horaInicio)/2;
        Time halfTime = new Time(this.horaInicio.getTime() + totalTime);

        first.setId(50000 + this.id);
        first.setInicial(true);
        first.setHoraInicio(this.horaInicio);
        first.setHoraFin(halfTime);
        first.setNextId(90000 + this.id);
        
        second.setId(90000 + this.id);
        second.setInicial(false);
        second.setHoraInicio(halfTime);
        second.setPrevId(50000 + this.id);
        second.setHoraFin(this.horaFin);
        
        return new Turno[]{first,second};
    }

    public double getTotalHoras(){
        long diffMiliseconds = (this.horaFin.getTime() - this.horaInicio.getTime());

        return diffMiliseconds / (60.0 * 60 * 1000);
    }

    public boolean Translapa(List<Asignacion> asignacion)
    {
    	boolean resp = false;
        long thisHF = this.horaFin.getTime();
        long thisHI = this.horaInicio.getTime();
        
        for (int i=0;i<asignacion.size();i++){
        	Turno turno2 = asignacion.get(i).getRequerimiento().getTurno();
            long tHF = turno2.horaFin.getTime();
            long tHI = turno2.horaInicio.getTime();
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
        return resp;
    }
    
    
}
