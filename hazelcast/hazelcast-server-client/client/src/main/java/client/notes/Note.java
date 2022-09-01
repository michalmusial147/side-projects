package client.notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Note implements Serializable {

    @Serial
    private static final long serialVersionUID = -4092256533856781296L;

    private double price;

    @Id
    @GeneratedValue
    private String id;

    @ManyToOne
    @JoinColumn(name="snapshotData_id", nullable=false)
    private SnapshotData snapshotData;
}
