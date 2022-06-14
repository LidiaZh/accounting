package managment.interfaces;

import accounting.entity.Branch;
import accounting.entity.Receiver;
import accounting.entity.Supplier;

import java.util.List;

public interface OrganizationService {

    Supplier addSupplier(String name, String address, String contact, String phone);

    void delSupplier(Integer id);

    void updateSupplier(Integer id, String name,
                        String address, String contact, String phone);

    List<Supplier> getAllSuppliers();

    Supplier getSupplier(Integer id);

    Receiver addReceiver(String name, String address, String contact, String phone);

    void delReceiver(Integer id);

    void updateReceiver(Integer id, String name,
                        String address, String contact, String phone);

    List<Receiver> getAllReceivers();

    Receiver getReceiver(Integer id);

    Branch addBranch(String name, String address, String contact, String phone);

    void delBranch(Integer id);

    void updateBranch(Integer id, String name,
                      String address, String contact, String phone);

    List<Branch> getAllBranches();

    Branch getBranch(Integer id);


}
