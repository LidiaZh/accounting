package servlet;

import accounting.entity.Receiver;
import accounting.entity.Supplier;
import dto.EquipmentDto;
import dto.InvoiceDto;
import managment.implementation.EquipmentServiceImpl;
import managment.implementation.InvoiceServiceImpl;
import managment.implementation.OrganizationServiceImpl;

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

@WebServlet(name = "InvoiceServlet", value = "/invoice")
public class InvoiceServlet extends HttpServlet {

    public final InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
    public final OrganizationServiceImpl organizationService = new OrganizationServiceImpl();
    private final EquipmentServiceImpl equipmentService = new EquipmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<InvoiceDto> listOfAllInvoiceDto = invoiceService.getAllInvoiceDto();
        req.getSession().setAttribute(LIST_OF_ALL_INVOICE_DTO, listOfAllInvoiceDto);
        req.getRequestDispatcher(INVOICE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addInvoice(req, resp);
                break;
            case NEW:
                writeNewInvoice(req, resp);
                break;
            case EDIT:
                editInvoice(req, resp);
                break;
            case DELETE:
                deleteInvoice(req, resp);
                break;
            case GET_INVOICE_FOR_ACTION:
                getInvoiceForAction(req, resp);
                break;
            case GET_EQUIPMENT_FOR_INVOICE:
                getEquipmentForInvoice(req, resp);
                break;
            case INFO:
                getInfo(req, resp);
                break;
            default:
                resp.sendRedirect(INVOICE_SERVLET);
        }
    }

    private void addInvoice(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer number = Integer.parseInt(req.getParameter(NUMBER_OF_INVOICE));
        LocalDate date = LocalDate.parse(req.getParameter(DATE_FOR_INVOICE),
                DateTimeFormatter.ofPattern(DATE_FORMAT));
        String cause = req.getParameter(CAUSE_FOR_INVOICE);
        Integer idSupplier = Integer.parseInt(req.getParameter(SUPPLIER_ID));
        Integer idReceiver = Integer.parseInt(req.getParameter(RECEIVER_ID));
        invoiceService.addInvoice(number, date, cause, idSupplier, idReceiver);
        resp.sendRedirect(INVOICE_SERVLET);
    }

    private void editInvoice(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
        Integer number = Integer.parseInt(req.getParameter(NUMBER_OF_INVOICE));
        String cause = req.getParameter(CAUSE_FOR_INVOICE);
        Integer idSupplier = Integer.parseInt(req.getParameter(SUPPLIER_ID));
        Integer idReceiver = Integer.parseInt(req.getParameter(RECEIVER_ID));
        invoiceService.updateInvoice(idInvoice, number, cause, idSupplier, idReceiver);
        resp.sendRedirect(INVOICE_SERVLET);
    }

    private void deleteInvoice(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
        invoiceService.delInvoice(idInvoice);
        resp.sendRedirect(INVOICE_SERVLET);
    }

    private void getInvoiceForAction(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String action_2 = req.getParameter(ACTION_2);
        switch (action_2) {
            case ADD:
                req.setAttribute(ACTION, ADD);
                break;
            case NEW:
                req.setAttribute(ACTION, NEW);
                break;
            case EDIT: {
                req.setAttribute(ACTION, EDIT);
                Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
                req.setAttribute(INVOICE, invoiceService.getInvoice(idInvoice));
                break;
            }
            case DELETE: {
                req.setAttribute(ACTION, DELETE);
                Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
                req.setAttribute(INVOICE, invoiceService.getInvoice(idInvoice));
                break;
            }
        }
        List<Supplier> listOfSuppliers = organizationService.getAllSuppliers();
        req.setAttribute(LIST_OF_SUPPLIERS, listOfSuppliers);
        List<Receiver> listOfReceivers = organizationService.getAllReceivers();
        req.setAttribute(LIST_OF_RECEIVER, listOfReceivers);
        req.getRequestDispatcher(INVOICE_FORM_JSP).forward(req, resp);
    }

    public void getInfo(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
        req.setAttribute(INVOICE_INFO, invoiceService.getInvoice(idInvoice));
        req.getRequestDispatcher(INVOICE_INFO_JSP).forward(req, resp);
    }

    public void writeNewInvoice(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Integer number = Integer.parseInt(req.getParameter(NUMBER_OF_INVOICE));
        LocalDate date = LocalDate.parse(req.getParameter(DATE_FOR_INVOICE), DateTimeFormatter.ofPattern(DATE_FORMAT));
        String cause = req.getParameter(CAUSE_FOR_INVOICE);
        Integer idSupplier = Integer.parseInt(req.getParameter(SUPPLIER_ID));
        Integer idReceiver = Integer.parseInt(req.getParameter(RECEIVER_ID));
        Integer idInvoice = (invoiceService.writeNewInvoice(number, date, cause,
                idSupplier, idReceiver)).getId();
        List<EquipmentDto> listOfAllEquipmentDto = equipmentService.getEquipmentDto();
        req.getSession().setAttribute(LIST_OF_ALL_EQUIPMENT_DTO, listOfAllEquipmentDto);
        req.setAttribute(INVOICE_ID, idInvoice);
        req.setAttribute(ACTION, CHOOSE_EQ);
        req.getRequestDispatcher("/equipment.jsp?action=chooseEq").forward(req, resp);
    }

    public void getEquipmentForInvoice(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] equipments = req.getParameterValues(BOX_NAME_OF_EQ);
        Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
        invoiceService.getEquipmentForInvoice(equipments, idInvoice);
        resp.sendRedirect(INVOICE_SERVLET);
    }
}
