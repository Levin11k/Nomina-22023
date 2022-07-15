package data;

import static data.Conexion.*;
import java.sql.*;
import java.util.*;
import model.Plantilla;

public class EmpleadosDAO {

    private static final String SQL_CREATE = "INSERT INTO nomina(nombre, apellido, area, sueldo, telefono) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_READ = "SELECT * FROM nomina";
    private static final String SQL_UPDATE_SUELDO = "UPDATE nomina SET sueldo = ? WHERE idempleado = ?";
    private static final String SQL_UPDATE_AREA = "UPDATE nomina SET area = ? WHERE idempleado = ?";
    private static final String SQL_UPDATE="UPDATE nomina SET nombre = ?, apellido = ?, area = ?, sueldo = ?,telefono= ? WHERE idlempleado = ?";   
    private static final String SQL_DELETE = "DELETE FROM nomina WHERE idempleado = ?";

    public List<Plantilla> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Plantilla empleado;
        List<Plantilla> empleados = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int idempleado = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                String area = rs.getString(4);
                double sueldo = rs.getDouble(5);
                int telefono = rs.getInt(6);

                empleado = new Plantilla(idempleado, nombre, apellido, area, sueldo, telefono);

                empleados.add(empleado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return empleados;
    }

    public int insert(Plantilla empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_CREATE);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getArea());
            stmt.setDouble(4, empleado.getSueldo());
            stmt.setInt(5, empleado.getTelefono());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int updateSueldo(Plantilla empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_SUELDO);
            stmt.setDouble(1, empleado.getSueldo());
            stmt.setInt(2, empleado.getIdempleado());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int updateArea(Plantilla empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_AREA);
            stmt.setString(1, empleado.getArea());
            stmt.setInt(2, empleado.getIdempleado());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
     public int update(Plantilla empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getArea());
            stmt.setDouble(4, empleado.getSueldo());
            stmt.setInt(5, empleado.getTelefono());
            stmt.setInt(6, empleado.getIdempleado());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int deleteEmpleado(Plantilla empleado) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, empleado.getIdempleado());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
