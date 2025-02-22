package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepo extends JpaRepository<Billing,Long> {

}
