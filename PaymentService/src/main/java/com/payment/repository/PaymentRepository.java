package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.model.Payment;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
