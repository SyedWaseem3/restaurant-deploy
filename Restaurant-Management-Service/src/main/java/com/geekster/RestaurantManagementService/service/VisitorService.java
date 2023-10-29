package com.geekster.RestaurantManagementService.service;

import com.geekster.RestaurantManagementService.repo.IVisitorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {
    @Autowired
    IVisitorRepo visitorRepo;
}
