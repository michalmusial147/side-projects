package client.notes;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Data
public class Note implements Serializable {

    @Serial
    private static final long serialVersionUID = -4092256533856781296L;
    private double price;
    @Id
    private String id;


}
