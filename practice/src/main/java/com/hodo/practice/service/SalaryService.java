package com.hodo.practice.service;

import org.springframework.stereotype.Service;

@Service
public interface SalaryService {
    Object findSalaryByName(String name);
}
