package com.example.carstoreapi.car.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

        Page<Car> findCarsByIsDel(String isDel, Pageable pageable);

        Page<Car> findCarByCarNameContainingAndIsDel(String carName, String isDel, Pageable pageable);

        Page<Car> findCarByAreaContainingAndIsDel(String area, String isDel, Pageable pageable);

        Page<Car> findCarByAuthorContainingAndIsDel(String author, String isDel, Pageable pageable);
}
