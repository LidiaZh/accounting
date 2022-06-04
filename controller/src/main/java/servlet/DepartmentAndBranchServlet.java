package servlet;

import dto.BranchDto;
import managment.implementation.BranchServiceImpl;

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

    public static final String LIST_OF_ALL_BRANCH_DTO = "listOfAllBranchDto";
    public static final String DEPARTMENT_BRANCH_JSP = "/department_branch.jsp";

    public final BranchServiceImpl branchService = new BranchServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<BranchDto> listOfAllBranchDto = branchService.getListOfBranchDto();
        req.getSession().setAttribute(LIST_OF_ALL_BRANCH_DTO, listOfAllBranchDto);
        req.getRequestDispatcher(DEPARTMENT_BRANCH_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD_IN_BRANCH:
                addDepartment(req, resp);
                break;
            case DELETE_FROM_BRANCH:
                deleteDepartment(req, resp);
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
}

