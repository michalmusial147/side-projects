package client.notes;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name="snapshotdata")
@AllArgsConstructor
@NoArgsConstructor
public class SnapshotData implements Serializable {

    @Serial
    private static final long serialVersionUID = 4330123309133827907L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToMany(mappedBy = "snapshotData", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<Note> notes;

}
