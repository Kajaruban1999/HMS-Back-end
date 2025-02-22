package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Billing;
import com.HMS.HMS.dto.BillingDto;

public interface BillingService {
    Billing saveBilling(BillingDto billing);
}
