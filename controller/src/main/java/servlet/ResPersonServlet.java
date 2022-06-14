package servlet;

import accounting.entity.Department;
import dto.BranchDto;
import dto.RPersonDto;
import managment.implementation.BranchServiceImpl;
import managment.implementation.DepartmentServiceImpl;
import managment.implementation.ResPersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Const.*;

@WebServlet(name = "ResPersonServlet", value = {"/rPerson"})
public class ResPersonServlet extends HttpServlet {

    public final ResPersonServiceImpl resPersonService = new ResPersonServiceImpl();
    public final BranchServiceImpl branchService = new BranchServiceImpl();
    public final DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RPersonDto> listOfrPersonDto = resPersonService.getAllPersonsDto();
        req.setAttribute(LIST_OF_R_PERSON_DTO, listOfrPersonDto);
        req.getRequestDispatcher(R_PERSON_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addResPerson(req, resp);
                break;
            case EDIT:
                editResPerson(req, resp);
                break;
            case EDIT_DEPARTMENT_AND_BRANCH:
                editDepartmentAndBranch(req, resp);
                break;
            case DELETE:
                deleteResPerson(req, resp);
                break;
            case GET_BRANCH_DTO_FOR_R_PERSON:
                getBranchDtoForRPerson(req, resp);
                break;
            default:
                resp.sendRedirect(R_PERSON_SERVLET);
        }
    }

    private void addResPerson(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter(NAME_OF_RES_PERSON);
        String surname = req.getParameter(SURNAME_OF_RES_PERSON);
        Integer idBranch = Integer.parseInt(req.getParameter(BRANCH_ID));
        Integer idDepartment = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        resPersonService.addPerson(name, surname, idBranch, idDepartment);
        resp.sendRedirect(R_PERSON_SERVLET);
    }

    private void editResPerson(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idRPerson = Integer.parseInt(req.getParameter(R_PERSON_ID));
        String name = req.getParameter(NAME_OF_RES_PERSON);
        String surname = req.getParameter(SURNAME_OF_RES_PERSON);
        resPersonService.updateResPerson(idRPerson, name, surname);
        resp.sendRedirect(R_PERSON_SERVLET);
    }

    private void editDepartmentAndBranch(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idRPerson = Integer.parseInt(req.getParameter(R_PERSON_ID));
        Integer idBranch = Integer.parseInt(req.getParameter(BRANCH_ID));
        Integer idDepartment = Integer.parseInt(req.getParameter(DEPARTMENT_ID));
        resPersonService.editDepAndBranchForRPerson(idRPerson, idBranch, idDepartment);
        resp.sendRedirect(R_PERSON_SERVLET);
    }

    private void deleteResPerson(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idRPerson = Integer.parseInt(req.getParameter(R_PERSON_ID));
        resPersonService.delPerson(idRPerson);
        resp.sendRedirect(R_PERSON_SERVLET);
    }

    private void getBranchDtoForRPerson(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action_2 = req.getParameter(ACTION_2);
        if (action_2.equals(ADD)) {
            req.setAttribute(ACTION, ADD);
        } else if (action_2.equals(EDIT_DEPARTMENT_AND_BRANCH)) {
            Integer idRPerson = Integer.parseInt(req.getParameter(R_PERSON_ID));
            req.setAttribute(R_PERSON_DTO, resPersonService.getPerson(idRPerson));
            req.setAttribute(ACTION, EDIT_DEPARTMENT_AND_BRANCH);
        }
        List<BranchDto> listOfAllBranchDto = branchService.getListOfBranchDto();
        req.setAttribute(LIST_OF_ALL_BRANCH_DTO, listOfAllBranchDto);
        List<Department> listOfAllDepartments = departmentService.getAllDepartments();
        req.setAttribute(LIST_OF_ALL_DEPARTMENTS, listOfAllDepartments);
        req.getRequestDispatcher(R_PERSON_FORM_JSP).forward(req, resp);
    }
}

