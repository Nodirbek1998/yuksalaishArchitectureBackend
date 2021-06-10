package uz.cas.controllersestem.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqActivePercent {
    private Integer projectId;
    private Integer userId;
}
