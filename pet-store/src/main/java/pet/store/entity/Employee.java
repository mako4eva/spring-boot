package pet.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Employee {
	String employeeFirstName, employeeLastName, employeePhone, employeeJobTitle;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long employeeId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_store_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	PetStore petStore;
}
