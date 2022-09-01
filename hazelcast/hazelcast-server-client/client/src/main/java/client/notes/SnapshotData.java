package client.notes;


import client.users.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "snapshotData")
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name="appuser_id", nullable=false)
    private AppUser appUser;

}
