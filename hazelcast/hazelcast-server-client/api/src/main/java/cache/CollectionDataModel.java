package cache;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDataModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 4330123309133827907L;

    private String id;

    private List<NestedItemModel> nestedItems;

}
