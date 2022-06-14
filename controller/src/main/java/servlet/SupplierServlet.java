package servlet;

import accounting.entity.Supplier;
import managment.implementation.OrganizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Const.*;

@WebServlet(name = "SupplierServlet", value = "/supplier")
public class SupplierServlet extends HttpServlet {

    public final OrganizationServiceImpl organizationService = new OrganizationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Supplier> suppliers = organizationService.getAllSuppliers();
        req.setAttribute(LIST_OF_SUPPLIERS, suppliers);
        req.getRequestDispatcher(SUPPLIERS_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addSupplier(req, resp);
                break;
            case EDIT:
                editSupplier(req, resp);
                break;
            case DELETE:
                deleteSupplier(req, resp);
                break;
            default:
                resp.sendRedirect(SUPPLIER_SERVLET);
        }
    }

    private void addSupplier(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter(NAME_OF_SUPPLIER);
        String address = req.getParameter(ADDRESS_OF_SUPPLIER);
        String contact = req.getParameter(CONTACT_OF_SUPPLIER);
        String phone = req.getParameter(PHONE_OF_SUPPLIER);
        organizationService.addSupplier(name, address, contact, phone);
        resp.sendRedirect(SUPPLIER_SERVLET);
    }

    private void editSupplier(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idSupplier = Integer.parseInt(req.getParameter(SUPPLIER_ID));
        String name = req.getParameter(NAME_OF_SUPPLIER);
        String address = req.getParameter(ADDRESS_OF_SUPPLIER);
        String contact = req.getParameter(CONTACT_OF_SUPPLIER);
        String phone = req.getParameter(PHONE_OF_SUPPLIER);
        organizationService.updateSupplier(idSupplier, name, address, contact, phone);
        resp.sendRedirect(SUPPLIER_SERVLET);
    }

    private void deleteSupplier(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idSupplier = Integer.parseInt(req.getParameter(SUPPLIER_ID));
        organizationService.delSupplier(idSupplier);
        resp.sendRedirect(SUPPLIER_SERVLET);
    }
}
