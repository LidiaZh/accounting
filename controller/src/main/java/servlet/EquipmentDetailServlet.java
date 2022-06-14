package servlet;

import accounting.entity.EquipmentDetail;
import managment.implementation.EquipmentDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Const.*;

@WebServlet(name = "EquipmentDetailServlet", value = "/equipmentDetail")
public class EquipmentDetailServlet extends HttpServlet {

    public final EquipmentDetailServiceImpl equipmentDetailService
            = new EquipmentDetailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<EquipmentDetail> listOfAllEqDetails = equipmentDetailService.getAllEquipmentDetail();
        req.getSession().setAttribute(LIST_OF_ALL_EQ_DETAILS, listOfAllEqDetails);
        req.getRequestDispatcher(EQUIPMENT_DETAIL_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addEqDetail(req, resp);
                break;
            case EDIT:
                editEqDetail(req, resp);
                break;
            case DELETE:
                deleteEqDetail(req, resp);
                break;
            default:
                resp.sendRedirect(EQUIPMENT_DETAIL_SERVLET);
        }
    }

    private void addEqDetail(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter(NAME_OF_EQUIPMENT_DET);
        String desc = req.getParameter(DESC_OF_EQUIPMENT_DET);
        int termOfUse = Integer.parseInt(req.getParameter(TERM_OF_USE_FOR_EQUIPMENT_DET));
        String producer = req.getParameter(PRODUCER_OF_EQUIPMENT_DET);
        equipmentDetailService.addEquipmentDetail(name, desc, producer, termOfUse);
        resp.sendRedirect(EQUIPMENT_DETAIL_SERVLET);
    }

    private void editEqDetail(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idEqDetail = Integer.parseInt(req.getParameter(EQ_DETAIL_ID));
        String name = req.getParameter(NAME_OF_EQUIPMENT_DET);
        String desc = req.getParameter(DESC_OF_EQUIPMENT_DET);
        int termOfUse = Integer.parseInt(req.getParameter(TERM_OF_USE_FOR_EQUIPMENT_DET));
        String producer = req.getParameter(PRODUCER_OF_EQUIPMENT_DET);
        equipmentDetailService.updateEquipmentDetail(idEqDetail, name, desc, producer, termOfUse);
        resp.sendRedirect(EQUIPMENT_DETAIL_SERVLET);
    }

    private void deleteEqDetail(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idEqDetail = Integer.parseInt(req.getParameter(EQ_DETAIL_ID));
        equipmentDetailService.delEquipmentDetail(idEqDetail);
        resp.sendRedirect(EQUIPMENT_DETAIL_SERVLET);
    }
}
