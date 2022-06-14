package managment.implementation;

import accounting.dao.EntityDaoImplEquipment;
import accounting.dao.EntityDaoImplEquipmentDetail;
import accounting.dao.EntityDaoImplInvoice;
import accounting.dao.EntityDaoImplResponsiblePerson;
import accounting.entity.Department;
import accounting.entity.Equipment;
import accounting.entity.Invoice;
import dto.EquipmentDto;
import managment.interfaces.EquipmentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EquipmentServiceImpl implements EquipmentService {

    private final EntityDaoImplEquipment daoImplEquipment
            = new EntityDaoImplEquipment();

    private final EntityDaoImplInvoice daoImplInvoice
            = new EntityDaoImplInvoice();

    private final EntityDaoImplEquipmentDetail daoImplEqDetail
            = new EntityDaoImplEquipmentDetail();

    private final EntityDaoImplResponsiblePerson daoImplResPerson
            = new EntityDaoImplResponsiblePerson();

    @Override
    public Equipment addEquipment(Integer account_number,
                                  String serial_number, String status, Float price,
                                  Integer idInvoice, Integer idEqDetail) {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(daoImplInvoice.getEntity(idInvoice));
        Equipment equipment = Equipment.builder()
                .account_number(account_number)
                .s_number(serial_number)
                .status(status)
                .price(price)
                .invoice(invoiceList)
                .equipmentDetail(daoImplEqDetail.getEntity(idEqDetail))
                .build();
        daoImplEquipment.insert(equipment);
        return equipment;
    }

    @Override
    public void delEquipment(Integer id) {
        daoImplEquipment.deleteById(id);
    }

    @Override
    public void updateEquipment(Integer idEquipment,
                                Integer account_number, String s_number,
                                Integer idEquipmentDetail, String status,
                                Float price, Integer idInvoice, Integer idInvoiceCurrent,
                                Integer idResPerson, LocalDate start_date) {
        Equipment equipment = getEquipment(idEquipment);
        List<Invoice> invoices = equipment.getInvoice();
        invoices.remove(daoImplInvoice.getEntity(idInvoiceCurrent));
        invoices.add(daoImplInvoice.getEntity(idInvoice));
        equipment.setAccount_number(account_number);
        equipment.setS_number(s_number);
        equipment.setEquipmentDetail(daoImplEqDetail.getEntity(idEquipmentDetail));
        equipment.setStatus(status);
        equipment.setPrice(price);
        equipment.setStart_date(start_date);
        equipment.setInvoice(invoices);
        equipment.setResponsiblePerson(daoImplResPerson.getEntity(idResPerson));
        daoImplEquipment.update(equipment);
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return daoImplEquipment.select();
    }

    @Override
    public Equipment getEquipment(Integer id) {
        return daoImplEquipment.getEntity(id);
    }

    @Override
    public List<EquipmentDto> getEquipmentDto() {
        List<Equipment> equipmentList = daoImplEquipment.select();
        return equipmentList.stream()
                .map(equipment -> EquipmentDto.builder()
                        .id(equipment.getId())
                        .account_number(equipment.getAccount_number())
                        .s_number(equipment.getS_number())
                        .invoiceNumberCurrent(equipment.getInvoice().get((equipment.getInvoice()).size()-1).getNumber())
                        .idInvoiceCurrent(equipment.getInvoice().get((equipment.getInvoice()).size()-1).getId())
                        .equipmentName(equipment.getEquipmentDetail().getName())
                        .status(equipment.getStatus())
                        .start_date(equipment.getStart_date())
                        .price(equipment.getPrice())
                        .resPerson(equipment.getResponsiblePerson())
                        .build())
                .collect(Collectors.toList());
    }

}
