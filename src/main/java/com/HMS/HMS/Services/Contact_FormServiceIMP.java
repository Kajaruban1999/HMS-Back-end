package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Contact_form;
import com.HMS.HMS.Repositories.Contact_FormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Contact_FormServiceIMP implements Contact_FormService {

    @Autowired
    private Contact_FormRepo contact_formRepo;

    @Override
    public Contact_form saveContactForm(Contact_form contact_form){
        return contact_formRepo.save(contact_form);
    }
    @Override
    public Optional<Contact_form>getContactById(Long id) {
        return contact_formRepo.findById(id);
    }
    @Override
    public List<Contact_form> getAllContact() {
        return contact_formRepo.findAll();
    }

    @Override
    public void deleteContact(Long id) {
        contact_formRepo.deleteById(id);
    }
}
