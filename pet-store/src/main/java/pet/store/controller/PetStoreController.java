package pet.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;

	@PostMapping
	public ResponseEntity<PetStoreData> createPetStore(@RequestBody PetStoreData petStoreData) {
		// Log the incoming request (you can use a logging framework like SLF4J)
		log.info("Received POST request for creating a pet store: " + petStoreData);

		// Call the service method to save or modify the pet store data
		PetStoreData savedPetStore = petStoreService.savePetStore(petStoreData);

		// Return the saved pet store data with a 201 (Created) status
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPetStore);
	}

    @GetMapping()
    public List<PetStoreData> retrieveAllPetStores() {
        return petStoreService.retrieveAllPetStores();
    }
    
    @DeleteMapping("/{petStoreId}")
    public Map<String,String> deletePetStoreById(@PathVariable Long petStoreId) {
    	log.info("Received DELETE request for pet store ID={}", petStoreId);
    	petStoreService.deletePetStoreById(petStoreId);
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("message","Pet store with Id=" + petStoreId + " has been deleted.");
    	return map;
    	
    }
	
    @GetMapping("/{petStoreId}")
    public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
        return new PetStoreData(petStoreService.findPetStoreById(petStoreId));
    }
    
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Received PUT request for updating pet store ID={} to {}", petStoreId, petStoreData);
		return petStoreService.savePetStore(petStoreData);

	}

	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(HttpStatus.CREATED)
	public PetStoreEmployee createEmployee(@PathVariable Long petStoreId,
			@RequestBody PetStoreEmployee petStoreEmployee) {
		// Log the incoming request (you can use a logging framework like SLF4J)
		log.info("Received POST request for creating an employee: " + petStoreEmployee);

		// Call the service method to save the employee
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}

	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(HttpStatus.CREATED)
	public PetStoreCustomer createCustomer(@PathVariable Long petStoreId,
			@RequestBody PetStoreCustomer petStoreCustomer) {
		// Log the incoming request (you can use a logging framework like SLF4J)
		log.info("Received POST request for creating a customer: " + petStoreCustomer);

		// Call the service method to save the employee
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}
}
