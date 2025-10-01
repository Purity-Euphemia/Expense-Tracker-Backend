package com.ExpenseTrackerApp.data.repository;

import com.ExpenseTrackerApp.data.model.ReportData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReportRepository extends MongoRepository<ReportData, String> {
    List<ReportData> findByUserId(String userId);

}
