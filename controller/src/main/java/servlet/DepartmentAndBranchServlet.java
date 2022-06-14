package servlet;

import accounting.entity.Department;
import dto.BranchDto;
import managment.implementation.BranchServiceImpl;
import managment.implementation.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Const.*;

@WebServlet(name = "DepartmentAndBranchServlet", value = "/department_branch")
public class DepartmentAndBranchServlet extends HttpServlet {

    public final BranchServiceImpl branchService = new BranchServiceImpl();
    public final DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<BranchDto> listOfAllBranchDto = branchService.getListOfBranchDto();
        req.setAttribute(LIST_OF_ALL_BRANCH_DTO, listOfAllBranchDto);
        List<Department> listOfDepartments = departmentService.getAllDepartments();
        req.setAttribute(LIST_OF_ALL_DEPARTMENTS, listOfDepartments);
        req.getRequestDispatcher(DEPARTMENT_BRANCH_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD_IN_BRANCH:
                addDepartment(req, resp);
                break;
            case DELETE_FROM_BRANCH:
                deleteDepartment(req, resp);
                break;
            case GET_DEPARTMENT_FOR_ACTION:
                getDepartmentForAction(req, resp);
                break;
            default:
                resp.sendRedirect(DEPARTMENT_BRANCH_SERVLET);
        }
    }

    private void addDepartment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idDepartment = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        Integer idBranch = Integer.parseInt(req.getParameter(BRANCH_ID));
        branchService.addDepartmentInBranch(idBranch, idDepartment);
        resp.sendRedirect(DEPARTMENT_BRANCH_SERVLET);
    }

    private void deleteDepartment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idBranch = Integer.parseInt(req.getParameter(BRANCH_ID));
        Integer idDepartment = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        branchService.delDepartmentFromBranch(idBranch, idDepartment);
        resp.sendRedirect(DEPARTMENT_BRANCH_SERVLET);
    }

    private void getDepartmentForAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute(ACTION, ADD_IN_BRANCH);
        Integer idBranch = Integer.parseInt(req.getParameter(BRANCH_ID));
        req.setAttribute(BRANCH_ID, idBranch);
        req.setAttribute(LIST_OF_ALL_DEPARTMENTS, departmentService.getAllDepartments());
        req.getRequestDispatcher(DEPARTMENT_FORM_JSP).forward(req, resp);
    }
}

