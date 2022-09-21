package cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NestedItemModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -4092256533856781296L;

    private String id;

    private String name;

    private double price;
}
