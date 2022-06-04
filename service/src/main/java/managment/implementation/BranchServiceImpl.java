package managment.implementation;

import accounting.dao.EntityDaoImplBranch;
import accounting.dao.EntityDaoImplDepartment;
import accounting.entity.Branch;
import accounting.entity.Department;
import dto.BranchDto;
import managment.interfaces.BranchService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BranchServiceImpl implements BranchService {

    private final EntityDaoImplBranch daoImplBranch
            = new EntityDaoImplBranch();

    private final EntityDaoImplDepartment daoImplDepartment
            = new EntityDaoImplDepartment();

    @Override
    public List<BranchDto> getListOfBranchDto() {
        List<Branch> list = daoImplBranch.select();
        return list.stream().map(branch -> BranchDto.builder()
                        .id(branch.getId())
                        .nameBranch(branch.getName())
                        .department(branch.getDepartment())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Department> getDepartmentInBranch(Integer idBranch) {
        return daoImplBranch.showDepartmentInBranch(idBranch);
    }

    @Override
    public void addDepartmentInBranch(Integer idBranch, Integer idDepartment) {
        Branch branch = daoImplBranch.getEntity(idBranch);
        Department department = daoImplDepartment.getEntity(idDepartment);
        Set<Department> departments = new HashSet<>();
        if (branch.getDepartment() != null) {
            departments = branch.getDepartment();
        }
        departments.add(department);
        branch.setDepartment(departments);
        daoImplBranch.update(branch);
    }

    @Override
    public void delDepartmentFromBranch(Integer idBranch, Integer idDepartment) {
        Branch branch = daoImplBranch.getEntity(idBranch);
        Department department = daoImplDepartment.getEntity(idDepartment);
        Set<Department> departments;
        if (branch.getDepartment() != null) {
            departments = branch.getDepartment();
            departments.remove(department);
            branch.setDepartment(departments);
            daoImplBranch.update(branch);
        }
    }
}
