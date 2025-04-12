package dom.lot.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int id;
}
