package managment.implementation;

import accounting.dao.EntityDaoImplBranch;
import accounting.dao.EntityDaoImplReceiver;
import accounting.dao.EntityDaoImplSupplier;
import accounting.entity.Branch;
import accounting.entity.Receiver;
import accounting.entity.Supplier;
import managment.interfaces.OrganizationService;

import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {

    private final EntityDaoImplSupplier entityDaoImplSupplier
            = new EntityDaoImplSupplier();

    private final EntityDaoImplReceiver entityDaoImplReceiver
            = new EntityDaoImplReceiver();

    private final EntityDaoImplBranch entityDaoImplBranch
            = new EntityDaoImplBranch();


    @Override
    public Supplier addSupplier(String name, String address, String contact, String phone) {
        Supplier supplier = Supplier.builder()
                .name(name)
                .address(address)
                .contact(contact)
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
                               String name, String address, String contact, String phone) {
        Supplier supplier = getSupplier(id);
        supplier.setName(name);
        supplier.setAddress(address);
        supplier.setContact(contact);
        supplier.setPhone(phone);
        entityDaoImplSupplier.update(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return entityDaoImplSupplier.select();
    }

    @Override
    public Supplier getSupplier(Integer id) {
        return entityDaoImplSupplier.getEntity(id);
    }

    @Override
    public Receiver addReceiver(String name, String address, String contact, String phone) {
        Receiver receiver = Receiver.builder()
                .name(name)
                .address(address)
                .contact(contact)
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
                               String name, String address, String contact, String phone) {
        Receiver receiver = getReceiver(id);
        receiver.setName(name);
        receiver.setAddress(address);
        receiver.setContact(contact);
        receiver.setPhone(phone);
        entityDaoImplSupplier.update(receiver);
    }

    @Override
    public List<Receiver> getAllReceivers() {
        return entityDaoImplReceiver.select();
    }

    @Override
    public Receiver getReceiver(Integer id) {
        return entityDaoImplReceiver.getEntity(id);
    }

    @Override
    public Branch addBranch(String name, String address, String contact, String phone) {
        Branch branch = Branch.builder()
                .name(name)
                .address(address)
                .contact(contact)
                .phone(phone)
                .build();
        entityDaoImplBranch.insert(branch);
        return branch;
    }

    @Override
    public void delBranch(Integer id) {
        entityDaoImplBranch.deleteById(id);
    }

    @Override
    public void updateBranch(Integer id, String name,
                             String address, String contact, String phone) {
        Branch branch = getBranch(id);
        branch.setName(name);
        branch.setAddress(address);
        branch.setContact(contact);
        branch.setPhone(phone);
        entityDaoImplBranch.update(branch);
    }

    @Override
    public Branch getBranch(Integer id) {
        return entityDaoImplBranch.getEntity(id);
    }

    @Override
    public List<Branch> getAllBranches() {
        return entityDaoImplBranch.select();
    }
}
