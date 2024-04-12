package pet.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Employee {
	private String employeeFirstName, employeeLastName, employeePhone, employeeJobTitle;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_store_id")
	private PetStore petStore;
}
