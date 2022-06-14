package servlet;

import accounting.entity.EquipmentDetail;
import accounting.entity.Invoice;
import accounting.entity.ResponsiblePerson;
import dto.EquipmentDto;
import managment.implementation.EquipmentDetailServiceImpl;
import managment.implementation.EquipmentServiceImpl;
import managment.implementation.InvoiceServiceImpl;
import managment.implementation.ResPersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static constants.Const.*;

@WebServlet(name = "EquipmentServlet", value = "/equipment")
public class EquipmentServlet extends HttpServlet {

    public static final String START_DATE_FOR_EQUIPMENT = "start_date";
    public final EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();
    public final InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
    public final EquipmentDetailServiceImpl eqDetailService = new EquipmentDetailServiceImpl();
    public final ResPersonServiceImpl resPersonService = new ResPersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<EquipmentDto> listOfAllEquipmentDto = equipmentService.getEquipmentDto();
        req.getSession().setAttribute(LIST_OF_ALL_EQUIPMENT_DTO, listOfAllEquipmentDto);
        req.getRequestDispatcher(EQUIPMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addEquipment(req, resp);
                break;
            case EDIT:
                editEquipment(req, resp);
                break;
            case DELETE:
                deleteEquipment(req, resp);
                break;
            case GET_EQUIPMENT_FOR_ACTION:
                getEquipmentForAction(req, resp);
                break;
            case INFO:
                getInfoAboutEquipment(req, resp);
            default:
                resp.sendRedirect(EQUIPMENT_SERVLET);
        }
    }

    private void addEquipment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer account_number = Integer.parseInt(req.getParameter(ACCOUNT_NUMBER_OF_EQUIPMENT));
        String s_number = req.getParameter(S_NUMBER_OF_EQUIPMENT);
        Integer idEqDetail = Integer.parseInt(req.getParameter(EQ_DETAIL_ID));
        String status = req.getParameter(STATUS_OF_EQUIPMENT);
        Float price = Float.parseFloat(req.getParameter(PRICE_OF_EQUIPMENT));
        Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
        equipmentService.addEquipment(account_number, s_number, status,
                price, idInvoice, idEqDetail);
        resp.sendRedirect(EQUIPMENT_SERVLET);
    }

    private void editEquipment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        LocalDate start_date = null;
        Integer idEquipment = Integer.parseInt(req.getParameter(EQUIPMENT_ID));
        Integer account_number = Integer.parseInt(req.getParameter(ACCOUNT_NUMBER_OF_EQUIPMENT));
        String s_number = req.getParameter(S_NUMBER_OF_EQUIPMENT);
        Integer idEqDetail = Integer.parseInt(req.getParameter(EQ_DETAIL_ID));
        String status = req.getParameter(STATUS_OF_EQUIPMENT);
        Float price = Float.parseFloat(req.getParameter(PRICE_OF_EQUIPMENT));
        Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
        Integer idInvoiceCurrent = Integer.parseInt(req.getParameter(INVOICE_CURRENT_ID));
        Integer idRPerson = Integer.parseInt(req.getParameter(R_PERSON_ID));
        boolean indicator = true;
        try {
            LocalDate.parse(req.getParameter(START_DATE_FOR_EQUIPMENT),
                    DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (Exception exception) {
            indicator = false;
        }
        if (indicator) {
            start_date = LocalDate.parse(req.getParameter(START_DATE_FOR_EQUIPMENT),
                    DateTimeFormatter.ofPattern(DATE_FORMAT));
        }
        equipmentService.updateEquipment(idEquipment, account_number, s_number,
                idEqDetail, status, price, idInvoice, idInvoiceCurrent, idRPerson, start_date);
        resp.sendRedirect(EQUIPMENT_SERVLET);
    }

    private void deleteEquipment(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idEquipment = Integer.parseInt(req.getParameter(EQUIPMENT_ID));
        equipmentService.delEquipment(idEquipment);
        resp.sendRedirect(EQUIPMENT_SERVLET);
    }

    private void getInfoAboutEquipment(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer idEquipment = Integer.parseInt(req.getParameter(EQUIPMENT_ID));
        req.setAttribute(EQUIPMENT, equipmentService.getEquipment(idEquipment));
        req.getRequestDispatcher(EQUIPMENT_INFO_JSP).forward(req, resp);
    }

    private void getEquipmentForAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action_2 = req.getParameter(ACTION_2);
        if (action_2.equals(ADD)) {
            req.setAttribute(ACTION, ADD);
        } else if (action_2.equals(EDIT)) {
            req.setAttribute(ACTION, EDIT);
            Integer idEquipment = Integer.parseInt(req.getParameter(EQUIPMENT_ID));
            req.setAttribute(EQUIPMENT, equipmentService.getEquipment(idEquipment));
            Integer idInvoiceCurrent = Integer.parseInt(req.getParameter(INVOICE_CURRENT_ID));
            Invoice invoiceCurrent = invoiceService.getInvoice(idInvoiceCurrent);
            req.setAttribute(INVOICE_CURRENT, invoiceCurrent);
            List<ResponsiblePerson> listOfResPersons = resPersonService.getAllPersons();
            req.setAttribute(LIST_OF_RES_PERSONS, listOfResPersons);

        }
        List<Invoice> listOfAllInvoices = invoiceService.getAllInvoices();
        req.setAttribute(LIST_OF_ALL_INVOICES, listOfAllInvoices);
        List<EquipmentDetail> listOfEqDetails = eqDetailService.getAllEquipmentDetail();
        req.setAttribute(LIST_OF_EQ_DETAILS, listOfEqDetails);
        req.getRequestDispatcher(EQUIPMENT_FORM_JSP).forward(req, resp);
    }
}

