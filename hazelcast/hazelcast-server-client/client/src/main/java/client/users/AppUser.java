package client.users;

import client.notes.Note;
import client.notes.SnapshotData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name="appuser")
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "appUser")
    private List<SnapshotData> snapshotData;


}
