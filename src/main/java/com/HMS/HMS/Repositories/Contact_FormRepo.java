package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Contact_form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Contact_FormRepo extends JpaRepository<Contact_form,Long> {
}
