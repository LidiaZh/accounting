package managment.implementation;

import accounting.dao.*;
import accounting.entity.*;
import managment.interfaces.AccountantService;

import java.time.LocalDate;
import java.util.List;

public class AccountantServiceImpl implements AccountantService {

    EntityDaoImplSupplier entityDaoImplSupplier
            = new EntityDaoImplSupplier();

    EntityDaoImplReceiver entityDaoImplReceiver
            = new EntityDaoImplReceiver();

    EntityDaoImplInvoice entityDaoImplInvoice
            = new EntityDaoImplInvoice();

    EntityDaoImplEquipment entityDaoImplEquipment
            = new EntityDaoImplEquipment();

    EntityDaoImplEquipmentDetail entityDaoImplEquipmentDetail
            = new EntityDaoImplEquipmentDetail();

    @Override
    public Supplier addSupplier(String name, String address, String phone) {
        Supplier supplier = Supplier.builder()
                .name(name)
                .address(address)
                .phone(phone)
                .build();
        entityDaoImplSupplier.insert(supplier);
        return supplier;
    }

    @Override
    public void delSupplier(Integer id) {
        entityDaoImplSupplier.deleteById(id);

    }

    @Override
    public void updateSupplier(Integer id,
                               String name, String address, String phone) {
        Supplier supplier = entityDaoImplSupplier.getEntity(id);
        supplier.setName(name);
        supplier.setAddress(address);
        supplier.setPhone(phone);
        entityDaoImplSupplier.update(supplier);
    }

    @Override
    public List<?> getAllSuppliers() {
        return entityDaoImplSupplier.select();
    }

    @Override
    public Supplier getSupplier(Integer id) {
        return entityDaoImplSupplier.getEntity(id);
    }

    @Override
    public Receiver addReceiver(String name, String address, String phone) {
        Receiver receiver = Receiver.builder()
                .name(name)
                .address(address)
                .phone(phone)
                .build();
        entityDaoImplReceiver.insert(receiver);
        return receiver;
    }

    @Override
    public void delReceiver(Integer id) {
        entityDaoImplReceiver.deleteById(id);
    }

    @Override
    public void updateReceiver(Integer id,
                               String name, String address, String phone) {
        Receiver receiver = getReceiver(id);
        receiver.setName(name);
        receiver.setAddress(address);
        receiver.setPhone(phone);
    }

    @Override
    public List<?> getAllReceivers() {
        return entityDaoImplReceiver.select();
    }

    @Override
    public Receiver getReceiver(Integer id) {
        return entityDaoImplReceiver.getEntity(id);
    }

    @Override
    public Invoice addInvoice(Integer number, LocalDate date,
                              String cause, Float price,
                              Supplier supplier, Receiver receiver) {
        Invoice invoice = Invoice.builder()
                .number(number)
                .date(date)
                .cause(cause)
                .price(price)
                .supplier(supplier)
                .receiver(receiver)
                .build();
        entityDaoImplInvoice.insert(invoice);
        return invoice;
    }

    @Override
    public void delInvoice(Integer id) {
        entityDaoImplInvoice.deleteById(id);
    }

    @Override
    public void updateInvoice(Integer id, Integer number, LocalDate date,
                              String cause, Float price,
                              Supplier supplier, Receiver receiver) {
        Invoice invoice = getInvoice(id);
        invoice.setNumber(number);
        invoice.setDate(date);
        invoice.setCause(cause);
        invoice.setPrice(price);
        invoice.setSupplier(supplier);
        invoice.setReceiver(receiver);
        entityDaoImplInvoice.update(invoice);
    }

    @Override
    public List<?> getAllInvoices() {
        return entityDaoImplInvoice.select();
    }

    @Override
    public Invoice getInvoice(Integer id) {
        return entityDaoImplInvoice.getEntity(id);
    }

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
        entityDaoImplEquipment.insert(equipment);
        return null;
    }

    @Override
    public void delEquipment(Integer id) {
        entityDaoImplEquipment.deleteById(id);
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
        entityDaoImplEquipment.update(equipment);
    }

    @Override
    public void updateEquipmentAccountNumber(Integer id, Integer account_number) {
        Equipment equipment = getEquipment(id);
        equipment.setAccount_number(account_number);
        entityDaoImplEquipment.update(equipment);
    }

    @Override
    public void updateEquipmentResponsiblePerson(Integer id,
                                                 ResponsiblePerson person) {
        Equipment equipment = getEquipment(id);
        equipment.setResponsiblePerson(person);
        entityDaoImplEquipment.update(equipment);
    }

    @Override
    public void updateEquipmentStatus(Integer id, String status) {
        Equipment equipment = getEquipment(id);
    }

    @Override
    public List<?> getAllEquipment() {
        return entityDaoImplEquipment.select();
    }

    @Override
    public Equipment getEquipment(Integer id) {
        return entityDaoImplEquipment.getEntity(id);
    }

    @Override
    public EquipmentDetail addEquipmentDetail(String name,
                                              String desc, String producer,
                                              int termOfUse) {
        EquipmentDetail equipmentDetail = EquipmentDetail.builder()
                .name(name)
                .desc(desc)
                .producer(producer)
                .termOfUse(termOfUse)
                .build();
        entityDaoImplEquipment.insert(equipmentDetail);
        return equipmentDetail;
    }

    @Override
    public void delEquipmentDetail(Integer id) {
        entityDaoImplEquipmentDetail.deleteById(id);
    }

    @Override
    public void updateEquipmentDetail(Integer id,
                                      String name, String desc,
                                      String producer, int termOfUse) {
        EquipmentDetail equipmentDetail = getEquipmentDetail(id);
        equipmentDetail.setName(name);
        equipmentDetail.setDesc(desc);
        equipmentDetail.setProducer(producer);
        equipmentDetail.setTermOfUse(termOfUse);
        entityDaoImplEquipmentDetail.update(equipmentDetail);
    }

    @Override
    public List<?> getAllEquipmentDetail() {
        return entityDaoImplEquipmentDetail.select();
    }

    @Override
    public EquipmentDetail getEquipmentDetail(Integer id) {
        return entityDaoImplEquipmentDetail.getEntity(id);
    }

    private float getDepreciation(Integer id) {
        float depreciation = 0F;
        if (getEquipmentDetail(id) != null && getInvoice(id) != null) {
           depreciation = entityDaoImplEquipmentDetail.getEntity(id).getTermOfUse() /
                   (entityDaoImplInvoice.getEntity(id).getPrice() * 12);
        }
        return depreciation;
    }
}
