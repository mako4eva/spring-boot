package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.*;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
	private Long petStoreId;
	private String petStoreName, petStoreAddress, petStoreCity, petStoreState, petStoreZip, petStorePhone;
	private Set<PetStoreCustomer> customers = new HashSet<>();
	private Set<PetStoreEmployee> employees = new HashSet<>();

	public PetStoreData(PetStore petStore) {
		petStoreId = petStore.getPetStoreId();
		petStoreName = petStore.getPetStoreName();
		petStoreAddress = petStore.getPetStoreAddress();
		petStoreCity = petStore.getPetStoreCity();
		petStoreState = petStore.getPetStoreState();
		petStoreZip = petStore.getPetStoreZip();
		petStorePhone = petStore.getPetStorePhone();
		if (petStore.getCustomers()!=null)
			for (Customer customer : petStore.getCustomers()) {
				customers.add(new PetStoreCustomer(customer));
			}
		if (petStore.getEmployees()!=null)
			for (Employee employee : petStore.getEmployees()) {
				employees.add(new PetStoreEmployee(employee));
			}

	}

	@Data
	@NoArgsConstructor
	public static class PetStoreCustomer {
		public PetStoreCustomer(Customer customer) {
			customerFirstName = customer.getCustomerFirstName();
			customerLastName = customer.getCustomerLastName();
			customerEmail = customer.getCustomerEmail();
			customerId = customer.getCustomerId();
		}

		private String customerFirstName, customerLastName, customerEmail;
		private Long customerId;
	}

	@Data
	@NoArgsConstructor
	public static class PetStoreEmployee {
		public PetStoreEmployee(Employee employee) {
			employeeFirstName = employee.getEmployeeFirstName();
			employeeLastName = employee.getEmployeeLastName();
			employeePhone = employee.getEmployeePhone();
			employeeJobTitle = employee.getEmployeeJobTitle();
			employeeId = employee.getEmployeeId();
		}

		private String employeeFirstName, employeeLastName, employeePhone, employeeJobTitle;
		private Long employeeId;
	}
}
