package dto;

import accounting.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchDto {

    private Integer id;
    private String nameBranch;
    private Set<Department> department;
}
