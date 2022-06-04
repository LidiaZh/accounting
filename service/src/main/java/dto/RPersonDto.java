package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RPersonDto {
    private Integer id;
    private String surname;
    private String name;
    private String branchName;
    private Integer idBranch;
    private String departmentName;
    private Integer idDepartment;
}
