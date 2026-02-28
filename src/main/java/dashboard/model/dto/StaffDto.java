package dashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StaffDto {
    private Integer scode;
    private String sname;
    private String srank;
    private Integer dcode;
    private String dname;
}
