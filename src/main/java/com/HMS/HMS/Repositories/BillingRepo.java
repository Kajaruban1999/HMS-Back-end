package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepo extends JpaRepository<Billing,Long> {
}
