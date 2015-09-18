package beans;

import java.util.Date;
import java.util.Random;

public class Empleado {
	private static Random RND_RANDOM = new Random((new Date()).getTime());
	private int rndOrden;
	private int id;
	private String nombre;
	private String rol;
	private double salarioBase;

	public Empleado() {
		rndOrden = RND_RANDOM.nextInt();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public int getRndOrden() {
		return rndOrden;
	}

	public void setRndOrden(int rndOrden) {
		this.rndOrden = rndOrden;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNombre() + " " + getRol();
	}

}
