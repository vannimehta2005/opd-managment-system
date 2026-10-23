package com.opd.OPD.Managment.Repository;

import com.opd.OPD.Managment.Model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttachmentRepo extends JpaRepository<Attachment , Integer> {
}
