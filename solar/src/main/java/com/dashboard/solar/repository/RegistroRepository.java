package com.dashboard.solar.repository;

import com.dashboard.solar.model.RegistroEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<RegistroEnergia, Long> {
}