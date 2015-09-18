package beans.configuration;


/**
 * Created by JG on 27/10/14.
 */
public class Requerimiento {
    private int id;
    private Turno turno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
    	
    	return "Requerimiento "+getId()+" "+getTurno();
    }
}
