package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepo extends JpaRepository<Patient, Integer> {
}
