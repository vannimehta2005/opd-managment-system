package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepo extends JpaRepository<Department , Integer> {
}
