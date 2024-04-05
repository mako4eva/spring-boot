package pet.store.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class PetStore {
	String petStoreName, petStoreAddress, petStoreCity, petStoreState, petStoreZip, petStorePhone;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long petStoreId;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "pet_store_customer", joinColumns =
	@JoinColumn(name = "pet_store_id"), inverseJoinColumns =
	@JoinColumn(name = "customer_id"))
	Set<Customer> customers;
	@OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	Set<Employee> employees;
}
