package com.opd.OPD.Managment.Controller;

import com.opd.OPD.Managment.DTO.CreateUpdateAttachmentDto;
import com.opd.OPD.Managment.Model.Attachment;
import com.opd.OPD.Managment.Repository.IAttachmentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/attachment")
@RestController

public class AttachmentController {
    public IAttachmentRepo attachmentController;
    public AttachmentController(IAttachmentRepo attachmentController){
        this.attachmentController = attachmentController;
    }

    @PostMapping
    public ResponseEntity<String> addAttachment(@RequestBody CreateUpdateAttachmentDto createAttachmentDto){
        Attachment attachmentObj = new Attachment();
        attachmentObj.fileURL = createAttachmentDto.fileURL;
        attachmentObj.description = createAttachmentDto.description;
        attachmentController.save(attachmentObj);
        return ResponseEntity.ok("Attachment Added Successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Attachment>> getAllAttachment(){
        List<Attachment> attachments = attachmentController.findAll();
        return ResponseEntity.ok(attachments);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateAttachment(@PathVariable Integer id , @RequestBody CreateUpdateAttachmentDto updateAttachmentDto){
        Optional<Attachment> existingAttachment = attachmentController.findById(id);
        if(existingAttachment.isEmpty()){
            return ResponseEntity.badRequest().body("Attachment Not Found!");
        }else {
            existingAttachment.get().description = updateAttachmentDto.description;
            existingAttachment.get().fileURL = updateAttachmentDto.fileURL;
            attachmentController.save(existingAttachment.get());
            return ResponseEntity.ok("Attachment Updated Successfully");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAttachmentById(@PathVariable Integer id){
        Optional<Attachment> existingAttachment = attachmentController.findById(id);
        if(existingAttachment.isEmpty()){
            return ResponseEntity.badRequest().body("Attachment Not Found!");
        }else{
            attachmentController.deleteById(id);
            return ResponseEntity.ok("Attachment Deleted Successfully!");
        }
    }

}
