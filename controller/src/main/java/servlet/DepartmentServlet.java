package servlet;

import accounting.entity.Department;
import managment.implementation.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Const.*;

@WebServlet(name = "DepartmentServlet", value = "/department")
public class DepartmentServlet extends HttpServlet {

    public final DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Department> listOfAllDepartments = departmentService.getAllDepartments();
        req.setAttribute(LIST_OF_ALL_DEPARTMENTS, listOfAllDepartments);
        req.getServletContext().getRequestDispatcher(DEPARTMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addDepartment(req, resp);
                break;
            case EDIT:
                editDepartment(req, resp);
                break;
            case DELETE:
                deleteDepartment(req, resp);
                break;
            default:
                resp.sendRedirect(DEPARTMENT_SERVLET);
        }
    }

    private void addDepartment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter(NAME_OF_DEPARTMENT);
        departmentService.addDepartment(name);
        resp.sendRedirect(DEPARTMENT_SERVLET);
    }

    private void editDepartment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idDepartment = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        String name = req.getParameter(NAME_OF_DEPARTMENT);
        departmentService.updateDepartment(idDepartment, name);
        resp.sendRedirect(DEPARTMENT_SERVLET);
    }

    private void deleteDepartment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idDepartment = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        departmentService.delDepartment(idDepartment);
        resp.sendRedirect(DEPARTMENT_SERVLET);
    }
}
