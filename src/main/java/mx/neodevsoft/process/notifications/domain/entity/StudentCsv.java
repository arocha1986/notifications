package mx.neodevsoft.process.notifications.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class StudentCsv {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;


    public String toString() {


        return "StudentCsv{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    
}

