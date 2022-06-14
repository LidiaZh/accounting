package managment.interfaces;

import accounting.entity.Invoice;
import accounting.entity.Receiver;
import accounting.entity.Supplier;
import dto.InvoiceDto;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    Invoice addInvoice(Integer number, LocalDate date, String cause,
                       Integer idSupplier, Integer idReceiver);

    List<InvoiceDto> getAllInvoiceDto();

    void delInvoice(Integer id);

    void updateInvoice(Integer idInvoice, Integer number,
                       String cause, Integer idSupplier,
                       Integer idReceiver);

    List<Invoice> getAllInvoices();

    Invoice getInvoice(Integer id);

    void getEquipmentForInvoice(String[] equipment, Integer idInvoice);

    Invoice writeNewInvoice(Integer number, LocalDate date,
                            String cause, Integer idSupplier,
                            Integer idReceiver);
}
