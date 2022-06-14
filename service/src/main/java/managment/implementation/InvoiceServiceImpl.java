package managment.implementation;

import accounting.dao.EntityDaoImplInvoice;
import accounting.dao.EntityDaoImplReceiver;
import accounting.dao.EntityDaoImplSupplier;
import accounting.entity.Invoice;
import accounting.entity.Receiver;
import accounting.entity.Supplier;
import dto.InvoiceDto;
import managment.interfaces.InvoiceService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceServiceImpl implements InvoiceService {

    private final EntityDaoImplInvoice daoImplInvoice
            = new EntityDaoImplInvoice();

    private final EntityDaoImplReceiver daoImplReceiver
            = new EntityDaoImplReceiver();

    private final EntityDaoImplSupplier daoImplSupplier
            = new EntityDaoImplSupplier();

    @Override
    public Invoice addInvoice(Integer number, LocalDate date,
                              String cause, Integer idSupplier,
                              Integer idReceiver) {
        Invoice invoice = Invoice.builder()
                .number(number)
                .date(date)
                .cause(cause)
                .supplier(daoImplSupplier.getEntity(idSupplier))
                .receiver(daoImplReceiver.getEntity(idReceiver))
                .build();
        daoImplInvoice.insert(invoice);
        return invoice;
    }

    @Override
    public List<InvoiceDto> getAllInvoiceDto() {
        List<Invoice> invoiceList = daoImplInvoice.select();
        return invoiceList.stream().map(invoice -> InvoiceDto.builder()
                        .id(invoice.getId())
                        .number(invoice.getNumber())
                        .date(invoice.getDate())
                        .cause(invoice.getCause())
                        .equipmentList(invoice.getEquipment())
                        .idSupplier(invoice.getSupplier().getId())
                        .supplierName(invoice.getSupplier().getName())
                        .idReceiver(invoice.getReceiver().getId())
                        .receiverName(invoice.getReceiver().getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void delInvoice(Integer id) {
        daoImplInvoice.deleteById(id);
    }

    @Override
    public void updateInvoice(Integer idInvoice, Integer number,
                              String cause, Integer idSupplier,
                              Integer idReceiver) {
        Invoice invoice = getInvoice(idInvoice);
        Receiver receiver = daoImplReceiver.getEntity(idReceiver);
        Supplier supplier = daoImplSupplier.getEntity(idSupplier);
        invoice.setNumber(number);
        invoice.setCause(cause);
        invoice.setSupplier(supplier);
        invoice.setReceiver(receiver);
        daoImplInvoice.update(invoice);
    }

    @Override
    public List<?> getAllInvoices() {
        return daoImplInvoice.select();
    }

    @Override
    public Invoice getInvoice(Integer id) {
        return daoImplInvoice.getEntity(id);
    }
}
