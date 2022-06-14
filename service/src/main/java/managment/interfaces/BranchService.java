package managment.interfaces;

import accounting.entity.Department;
import dto.BranchDto;

import java.util.List;

public interface BranchService {

    List<Department> getDepartmentInBranch(Integer idBranch);

    void addDepartmentInBranch(Integer idBranch, Integer idDepartment);

    void delDepartmentFromBranch(Integer idBranch, Integer idDepartment);

    List<BranchDto> getListOfBranchDto();
}
