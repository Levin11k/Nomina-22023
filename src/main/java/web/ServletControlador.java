package web;

import data.EmpleadosDAO;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String accion = req.getParameter("accion");
        
        if(accion!=null){
            switch(accion){
                case "eliminar":
                    eliminarEmpleado(req,res);
                    break;
                case "editar":
                    editarEmpleado(req, res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        }else{
            accionDefault(req, res);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String queryParam = req.getParameter("accion");
        if(queryParam!=null){
            switch(queryParam){
                case "insertar":
                    insertarEmpleado(req,res);
                    break;
                case "modificar":
                    modificarEmpleado(req,res);
                    break;
                default:
                    accionDefault(req,res);
                    break;
            }
        }
    }
    
    private void accionDefault(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        List <Plantilla> empleados = new EmpleadosDAO().findAll();
        empleados.forEach(System.out::println);
        HttpSession sesion = req.getSession();
        sesion.setAttribute("empleados", empleados);
//        sesion.setAttribute("cantidadLibros",calcularCopias(libros));
//        sesion.setAttribute("precioTotal", calcularPrecio(libros));
//        req.getRequestDispatcher("libros.jsp").forward(req, res);
        res.sendRedirect("empleados.jsp");
    }
    
    private void insertarEmpleado(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String area = req.getParameter("area");
        double sueldo = Double.parseDouble(req.getParameter("sueldo"));
        int telefono = Integer.parseInt(req.getParameter("telefono"));
        
        Plantilla empleado = new Plantilla(nombre, apellido, area, sueldo, telefono);
        
        int registrosMod = new EmpleadosDAO().insert(empleado);
        
        System.out.println("insertados = " + registrosMod);
        
        accionDefault(req, res);
    }
    
    private void eliminarEmpleado(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idEmpleado = Integer.parseInt(req.getParameter("idempleado"));
        
        int regMod = new EmpleadosDAO().deleteEmpleado(idEmpleado);
        
        System.out.println("SE ELIMINARON: "+ regMod +" REGISTROS");
        
        accionDefault(req, res);
    }
    
    private void editarEmpleado(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idEmpleado = Integer.parseInt(req.getParameter("idempleado"));
        Plantilla empleado = new EmpleadosDAO().findById(idEmpleado);
        req.setAttribute("empleado", empleado);
        String jspEditar = "/WEB-INF/paginas/operaciones/editar.jsp";
        req.getRequestDispatcher(jspEditar).forward(req, res);
    }
    
    private void modificarLibro(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String nombre = req.getParameter("nombre");
        String autor = req.getParameter("autor");
        int cantPaginas = Integer.parseInt(req.getParameter("cantPaginas"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        int copias = Integer.parseInt(req.getParameter("copias"));
        
        int idLibro = Integer.parseInt(req.getParameter("idLibro"));
        
        Biblioteca lib = new Biblioteca(idLibro,nombre,autor,cantPaginas,precio,copias);
        
        int regMod = new LibrosDAO().update(lib);
        
        System.out.println("SE ACTUALIZARON: "+ regMod +" REGISTROS");
        
        accionDefault(req, res);
    }
    
    private int calcularCopias(List<Biblioteca> lib){
        int cant=0;
        for (int i = 0; i < lib.size(); i++) {
            cant += lib.get(i).getCopias();
        }
        return cant;
    }
    
    private double calcularPrecio(List<Biblioteca> lib){
        double precio = 0;
        for (int i = 0; i < lib.size(); i++) {
            precio += (lib.get(i).getPrecio() * lib.get(i).getCopias());
        }
        return precio;
    }
}
