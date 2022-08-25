package client.notes;


import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class SnapshotData implements Serializable {

    @Serial
    private static final long serialVersionUID = 4330123309133827907L;
    @Id
    private Long id;
    private List<Note> quotes;
}
