package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<Employee, Integer> {
}
