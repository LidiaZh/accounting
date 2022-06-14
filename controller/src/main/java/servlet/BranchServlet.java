package servlet;

import accounting.entity.Branch;
import accounting.entity.Department;
import managment.implementation.BranchServiceImpl;
import managment.implementation.OrganizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Const.*;

@WebServlet(name = "BranchServlet", value = "/branch")
public class BranchServlet extends HttpServlet {

    public final BranchServiceImpl branchService = new BranchServiceImpl();
    public final OrganizationServiceImpl organizationService
            = new OrganizationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Branch> listOfAllBranches = organizationService.getAllBranches();
        req.getSession().setAttribute(LIST_OF_ALL_BRANCHES, listOfAllBranches);
        req.getRequestDispatcher(BRANCH_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addBranch(req, resp);
                break;
            case EDIT:
                editBranch(req, resp);
                break;
            case DELETE:
                deleteBranch(req, resp);
                break;
//            case INFO:
//                resp.sendRedirect("department");
//                infoBranch(req, resp);
//                break;
            default:
                resp.sendRedirect(BRANCH_SERVLET);
        }
    }

    private void addBranch(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String contact = req.getParameter("contact");
        String phone = req.getParameter("phone");
        organizationService.addBranch(name, address, contact, phone);
        resp.sendRedirect(BRANCH_SERVLET);
    }

    private void editBranch(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idBranch = Integer.parseInt(req.getParameter(BRANCH_ID));
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String contact = req.getParameter("contact");
        String phone = req.getParameter("phone");
        organizationService.updateBranch(idBranch, name, address, contact, phone);
        resp.sendRedirect(BRANCH_SERVLET);
    }

    private void deleteBranch(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idBranch = Integer.parseInt(req.getParameter(BRANCH_ID));
        organizationService.delBranch(idBranch);
        resp.sendRedirect(BRANCH_SERVLET);
    }

    private void infoBranch(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Integer idBranch = Integer.parseInt(req.getParameter(BRANCH_ID));
        List<Department> listOfDepartments = branchService.getDepartmentInBranch(idBranch);
        req.setAttribute("listOfDepartments", listOfDepartments);
        req.getRequestDispatcher("/rPerson.jsp").forward(req, resp);
//        Integer idBranch = Integer.parseInt(req.getParameter("idBranch"));
//        List<Department> listOfAllDepartments = branchService.listOfDepartmentInBranch(idBranch);
//        req.setAttribute("listOfAllDepartments", listOfAllDepartments);
//        req.getRequestDispatcher("/department.jsp").forward(req, resp);
    }
}
