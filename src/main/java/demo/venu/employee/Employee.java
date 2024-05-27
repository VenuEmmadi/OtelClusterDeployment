package demo.venu.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Entity
@Table(name = "employees")
@Getter
@NoArgsConstructor
public class Employee {
    @Id
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "customer_id")
    Long customerId;
    @Column(name = "location")
    String location;
    @Column(name = "experience")
    BigDecimal experience;
}
