package managment.implementation;

import accounting.dao.EntityDaoImplEquipment;
import accounting.entity.Equipment;
import accounting.entity.EquipmentDetail;
import accounting.entity.Invoice;
import accounting.entity.ResponsiblePerson;
import dto.EquipmentDto;
import managment.interfaces.EquipmentService;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentServiceImpl implements EquipmentService {

    private final EntityDaoImplEquipment daoImplEquipment
            = new EntityDaoImplEquipment();

    @Override
    public Equipment addEquipment(Integer account_number,
                                  String serial_number, String status,
                                  Invoice invoice, ResponsiblePerson person,
                                  EquipmentDetail equipmentDetail) {
        Equipment equipment = Equipment.builder()
                .account_number(account_number)
                .s_number(serial_number)
                .status(status)
                .invoice(invoice)
                .responsiblePerson(person)
                .equipmentDetail(equipmentDetail)
                .build();
        daoImplEquipment.insert(equipment);
        return null;
    }

    @Override
    public void delEquipment(Integer id) {
        daoImplEquipment.deleteById(id);
    }

    @Override
    public void updateEquipment(Integer id,
                                String serial_number, String status,
                                Invoice invoice, EquipmentDetail equipmentDetail) {
        Equipment equipment = getEquipment(id);
        equipment.setS_number(serial_number);
        equipment.setStatus(status);
        equipment.setInvoice(invoice);
        equipment.setEquipmentDetail(equipmentDetail);
        daoImplEquipment.update(equipment);
    }

    @Override
    public void updateEquipmentAccountNumber(Integer id, Integer account_number) {
        Equipment equipment = getEquipment(id);
        equipment.setAccount_number(account_number);
        daoImplEquipment.update(equipment);
    }

    @Override
    public void updateEquipmentResponsiblePerson(Integer id,
                                                 ResponsiblePerson person) {
        Equipment equipment = getEquipment(id);
        equipment.setResponsiblePerson(person);
        daoImplEquipment.update(equipment);
    }

    @Override
    public void updateEquipmentStatus(Integer id, String status) {
        Equipment equipment = getEquipment(id);
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
                        .invoiceNumber(equipment.getInvoice().getNumber())
                        .equipmentName(equipment.getEquipmentDetail().getName())
                        .status(equipment.getStatus())
                        .start_date(equipment.getStart_date())
                        .price(equipment.getPrice())
                        .rPersonSurname(equipment.getResponsiblePerson().getSurname())
                        .rPersonName(equipment.getResponsiblePerson().getName())
                        .department(equipment.getResponsiblePerson().getDepartment().getName())
                        .branch(equipment.getResponsiblePerson().getBranch().getName())
                        .build())
                .collect(Collectors.toList());
    }


}
