package KodlamaIo.hrms.entity.concretes;

import KodlamaIo.hrms.entity.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="system_staff")
@SuperBuilder
public class SystemStaff extends User {

    @NotEmpty
    @Column(name="staff_name")
    private String name;

    @NotEmpty
    @Column(name="staff_surname")
    private String surname;

    @NotEmpty
    @Column(name="department_name")
    private String departmentName;
}
