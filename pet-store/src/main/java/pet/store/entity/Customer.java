package pet.store.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Customer {
	String customerFirstName, customerLastName, customerEmail;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long customerId;
	@ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	Set<PetStore> petStores;
}
