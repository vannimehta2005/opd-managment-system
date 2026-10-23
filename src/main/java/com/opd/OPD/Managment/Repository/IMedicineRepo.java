package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicineRepo extends JpaRepository<Medicine, Integer> {
}
