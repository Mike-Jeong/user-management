package com.assessmentmin.usermanagement.history.repository;

import com.assessmentmin.usermanagement.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
