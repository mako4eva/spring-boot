package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
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
	
	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Received PUT request for updating pet store ID={} to {}",petStoreId,petStoreData);
		return petStoreService.savePetStore(petStoreData);
		
	}
}
