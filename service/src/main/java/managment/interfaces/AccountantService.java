package managment.interfaces;

import accounting.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface AccountantService {

    Supplier addSupplier(String name, String address, String phone);

    void delSupplier(Integer id);

    void updateSupplier(Integer id, String name,
                        String address, String phone);

    List<?> getAllSuppliers();

    Supplier getSupplier(Integer id);

    Receiver addReceiver(String name, String address, String phone);

    void delReceiver(Integer id);

    void updateReceiver(Integer id, String name,
                        String address, String phone);

    List<?> getAllReceivers();

    Receiver getReceiver(Integer id);

    Invoice addInvoice(Integer number, LocalDate date, String cause,
                       Float price, Supplier supplier, Receiver receiver);

    void delInvoice(Integer id);

    void updateInvoice(Integer id, Integer number, LocalDate date, String cause,
                       Float price, Supplier supplier, Receiver receiver);

    List<?> getAllInvoices();

    Invoice getInvoice(Integer id);

    Equipment addEquipment(Integer account_number,
                           String serial_number, String status,
                           Invoice invoice, ResponsiblePerson person,
                           EquipmentDetail equipmentDetail);

    void delEquipment(Integer id);

    void updateEquipment(Integer id, String serial_number,
                         String status, Invoice invoice,
                         EquipmentDetail equipmentDetail);

    void updateEquipmentAccountNumber(Integer id, Integer account_number);

    void updateEquipmentResponsiblePerson(Integer id, ResponsiblePerson person);

    void updateEquipmentStatus(Integer id, String status);

    List<?> getAllEquipment();

    Equipment getEquipment(Integer id);

    EquipmentDetail addEquipmentDetail(String name, String desc,
                                       String producer, int termOfUse);

    void delEquipmentDetail(Integer id);

    void updateEquipmentDetail(Integer id, String name, String desc,
                               String producer, int termOfUse);

    List<?> getAllEquipmentDetail();

    EquipmentDetail getEquipmentDetail(Integer id);
}
