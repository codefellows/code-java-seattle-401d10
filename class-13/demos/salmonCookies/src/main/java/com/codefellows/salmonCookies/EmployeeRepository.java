package com.codefellows.salmonCookies;

import org.springframework.data.jpa.repository.JpaRepository;

//JPA repository defines all the save, add, update, select, getOne methods for interacting with data

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
