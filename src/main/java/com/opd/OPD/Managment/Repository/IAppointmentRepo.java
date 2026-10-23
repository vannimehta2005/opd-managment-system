package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment, Integer> {
}
