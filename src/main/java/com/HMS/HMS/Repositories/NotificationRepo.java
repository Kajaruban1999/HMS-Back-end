package com.HMS.HMS.Repositories;

import com.HMS.HMS.Entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Long> {
}
