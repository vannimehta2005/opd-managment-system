package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDesignationRepo extends JpaRepository<Designation,Integer> {
}
