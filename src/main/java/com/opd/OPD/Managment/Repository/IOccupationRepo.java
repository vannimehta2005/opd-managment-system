package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOccupationRepo extends JpaRepository<Occupation,Integer > {
}
