package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Symptoms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISymptomsRepo extends JpaRepository<Symptoms, Integer> {
}
