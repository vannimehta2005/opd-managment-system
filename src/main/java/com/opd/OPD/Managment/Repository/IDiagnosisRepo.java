package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiagnosisRepo extends JpaRepository<Diagnosis, Integer> {

}
