package com.mycompany.myapp.process.consultaProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta-process/confirma-cpf")
public class ConfirmaCPFController {

    private final Logger log = LoggerFactory.getLogger(ConfirmaCPFController.class);

    private final ConfirmaCPFService confirmaCPFService;

    public ConfirmaCPFController(ConfirmaCPFService confirmaCPFService) {
        this.confirmaCPFService = confirmaCPFService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfirmaCPFContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ConfirmaCPFContextDTO confirmaCPFContext = confirmaCPFService.loadContext(id);
        return ResponseEntity.ok(confirmaCPFContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<ConfirmaCPFContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        ConfirmaCPFContextDTO confirmaCPFContext = confirmaCPFService.claim(id);
        return ResponseEntity.ok(confirmaCPFContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody ConfirmaCPFContextDTO confirmaCPFContext) {
        log.debug("REST request to complete ConsultaProcess.ConfirmaCPF {}", confirmaCPFContext.getTaskInstance().getId());
        confirmaCPFService.complete(confirmaCPFContext);
        return ResponseEntity.noContent().build();
    }
}
