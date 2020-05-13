package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import net.guides.springboot2.springboot2jpacrudexample.model.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.repository.PartyRepository;

@CrossOrigin(origins = "http://localhost:3306")
@RestController
@RequestMapping("/api/v1")
public class PartyController {
    @Autowired
    private PartyRepository partyRepository;

    @GetMapping("/parties")
    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    @GetMapping("/parties/{id}")
    public ResponseEntity<Party> getPartyById(@PathVariable(value = "id") Long partyId)
            throws ResourceNotFoundException {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException("Party not found for this id :: " + partyId));
        return ResponseEntity.ok().body(party);
    }

    @PostMapping("/parties")
    public Party createParty(@Valid @RequestBody Party party) {
        return partyRepository.save(party);
    }

    @PutMapping("/parties/{id}")
    public ResponseEntity<Party> updateParty(@PathVariable(value = "id") Long partyId,
                                             @Valid @RequestBody Party partyDetails) throws ResourceNotFoundException {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException("Party not found for this id :: " + partyId));

        party.setEmailId(partyDetails.getEmailId());
        party.setLastName(partyDetails.getLastName());
        party.setFirstName(partyDetails.getFirstName());
        final Party updatedParty = partyRepository.save(party);
        return ResponseEntity.ok(updatedParty);
    }

    @DeleteMapping("/parties/{id}")
    public Map<String, Boolean> deleteParty(@PathVariable(value = "id") Long partyId)
            throws ResourceNotFoundException {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new ResourceNotFoundException("Party not found for this id :: " + partyId));

        partyRepository.delete(party);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
