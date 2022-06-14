package servlet;

import accounting.entity.Receiver;
import managment.implementation.OrganizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Const.*;

@WebServlet(name = "ReceiverServlet", value = "/receiver")
public class ReceiverServlet extends HttpServlet {

    public final OrganizationServiceImpl organizationService = new OrganizationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Receiver> receivers = organizationService.getAllReceivers();
        req.getSession().setAttribute(LIST_OF_RECEIVER, receivers);
        req.getRequestDispatcher(RECEIVER_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter(ACTION);
        switch (action) {
            case ADD:
                addReceiver(req, resp);
                break;
            case EDIT:
                editReceiver(req, resp);
                break;
            case DELETE:
                deleteReceiver(req, resp);
                break;
            default:
                resp.sendRedirect(RECEIVER_SERVLET);
        }
    }

    private void addReceiver(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter(NAME_OF_RECEIVER);
        String address = req.getParameter(ADDRESS_OF_RECEIVER);
        String contact = req.getParameter(CONTACT_OF_RECEIVER);
        String phone = req.getParameter(PHONE_OF_RECEIVER);
        organizationService.addReceiver(name, address, contact, phone);
        resp.sendRedirect(RECEIVER_SERVLET);
    }

    private void editReceiver(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idReceiver = Integer.parseInt(req.getParameter(RECEIVER_ID));
        String name = req.getParameter(NAME_OF_RECEIVER);
        String address = req.getParameter(ADDRESS_OF_RECEIVER);
        String contact = req.getParameter(CONTACT_OF_RECEIVER);
        String phone = req.getParameter(PHONE_OF_RECEIVER);
        organizationService.updateReceiver(idReceiver, name, address, contact, phone);
        resp.sendRedirect(RECEIVER_SERVLET);
    }

    private void deleteReceiver(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Integer idReceiver = Integer.parseInt(req.getParameter(RECEIVER_ID));
        organizationService.delReceiver(idReceiver);
        resp.sendRedirect(RECEIVER_SERVLET);
    }
}
