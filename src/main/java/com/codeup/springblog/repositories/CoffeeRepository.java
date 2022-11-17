package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;


//  JPA REPO = a bunch of methods that query the database
//  by creating a repo that is specifically a coffee one that extends JPA, we create an interface that gives us access to all DB query methods specifically for the coffee table
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
