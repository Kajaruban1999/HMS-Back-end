package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Contact_form;
import com.HMS.HMS.Services.Contact_FormServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
public class Contact_formController {
    private Contact_FormServiceIMP contactForm;

    @Autowired
    public Contact_formController(Contact_FormServiceIMP contactForm) {
        this.contactForm = contactForm;
    }
    @PostMapping("/create")
    public ResponseEntity<Contact_form>createContact(@RequestBody Contact_form contact_form){
        return ResponseEntity.ok((contactForm).saveContactForm(contact_form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contact_form>> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactForm.getContactById(id));
    }

    @GetMapping
    public ResponseEntity<List<Contact_form>> getAllContact() {
        return ResponseEntity.ok(contactForm.getAllContact());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactForm.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
