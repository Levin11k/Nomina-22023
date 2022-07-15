package model;

public class Plantilla {
    private int idempleado;
    private String nombre;
    private String apellido;
    private String area;
    private double sueldo;
    private int telefono;
    

    public Plantilla(int idempleado, String nombre, String apellido, String area, double sueldo, int telefono) {
        this.idempleado = idempleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.area = area;
        this.sueldo = sueldo;
        this.telefono = telefono;
    }

    public Plantilla(String nombre, String apellido, String area, double sueldo, int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.area = area;
        this.sueldo = sueldo;
        this.telefono = telefono;
    }
    

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override 
    public String toString() {
        return "Empleado{" + "nombre= " + nombre + ", apellido= " + apellido + ", area= " + area + ", sueldo= " + sueldo + ", telefono= " + telefono + '}';
    }
}
   