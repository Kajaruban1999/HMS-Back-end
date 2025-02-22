package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Contact_form;


import java.util.List;
import java.util.Optional;

public interface Contact_FormService {
    Contact_form saveContactForm(Contact_form contact_form);
    Optional<Contact_form> getContactById(Long id);
    List<Contact_form> getAllContact();
    void deleteContact(Long id);
}
