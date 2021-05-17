package xyz.subho.covidhelp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.subho.covidhelp.entity.OxygenLead;
import xyz.subho.covidhelp.service.OxygenService;

@RestController
@RequestMapping("/oxygen")
@Slf4j
public class OxygenController {
  @Autowired private OxygenService oxygenService;

  @GetMapping("/get/{OxyLeadId}")
  public ResponseEntity<OxygenLead> getOxygenById(@PathVariable Long OxyLeadId) {
    try {
      log.info("At GET Oxygen Lead");
      return ResponseEntity.ok(oxygenService.getOxygenById(OxyLeadId));
    } catch (Exception e) {
      log.info("At GET Oxygen Lead: Oxygen Lead Not Present");
      return ResponseEntity.notFound().build();
    }
  }
}
