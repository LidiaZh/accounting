package servlet;

import dto.EquipmentDto;
import managment.implementation.EquipmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.Const.EQUIPMENT_JSP;
import static constants.Const.LIST_OF_ALL_EQUIPMENT_DTO;

@WebServlet(name = "EquipmentServlet", value = "/equipment")
public class EquipmentServlet extends HttpServlet {

    public final EquipmentServiceImpl equipmentService
            = new EquipmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<EquipmentDto> listOfAllEquipmentDto = equipmentService.getEquipmentDto();
        req.getSession().setAttribute(LIST_OF_ALL_EQUIPMENT_DTO, listOfAllEquipmentDto);
        req.getRequestDispatcher(EQUIPMENT_JSP).forward(req, resp);
    }
}
