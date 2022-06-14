package servlet;

import dto.InvoiceDto;
import managment.implementation.InvoiceServiceImpl;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<InvoiceDto> listOfAllInvoiceDto = invoiceService.getAllInvoiceDto();
        req.getSession().setAttribute(LIST_OF_ALL_INVOICE_DTO, listOfAllInvoiceDto);
        req.getRequestDispatcher(INVOICE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addInvoice(req, resp);
                break;
            case EDIT:
                editInvoice(req, resp);
                break;
            case DELETE:
                deleteInvoice(req, resp);
                break;
            default:
                resp.sendRedirect(INVOICE_SERVLET);
        }
    }

    private void addInvoice(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer number = Integer.parseInt(req.getParameter("number"));
        LocalDate date = LocalDate.parse(req.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String cause = req.getParameter("cause");
        Integer idSupplier = Integer.parseInt(req.getParameter(SUPPLIER_ID));
        Integer idReceiver = Integer.parseInt(req.getParameter(RECEIVER_ID));
        invoiceService.addInvoice(number, date, cause, idSupplier, idReceiver);
        resp.sendRedirect(INVOICE_SERVLET);
    }

    private void editInvoice(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idInvoice = Integer.parseInt(req.getParameter(INVOICE_ID));
        Integer number = Integer.parseInt(req.getParameter("number"));
        String cause = req.getParameter("cause");
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
}
