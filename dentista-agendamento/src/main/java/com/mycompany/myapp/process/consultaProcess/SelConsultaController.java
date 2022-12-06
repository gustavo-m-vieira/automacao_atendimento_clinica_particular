package com.mycompany.myapp.process.consultaProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta-process/sel-consulta")
public class SelConsultaController {

    private final Logger log = LoggerFactory.getLogger(SelConsultaController.class);

    private final SelConsultaService selConsultaService;

    public SelConsultaController(SelConsultaService selConsultaService) {
        this.selConsultaService = selConsultaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelConsultaContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        SelConsultaContextDTO selConsultaContext = selConsultaService.loadContext(id);
        return ResponseEntity.ok(selConsultaContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<SelConsultaContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        SelConsultaContextDTO selConsultaContext = selConsultaService.claim(id);
        return ResponseEntity.ok(selConsultaContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody SelConsultaContextDTO selConsultaContext) {
        log.debug("REST request to complete ConsultaProcess.SelConsulta {}", selConsultaContext.getTaskInstance().getId());
        selConsultaService.complete(selConsultaContext);
        return ResponseEntity.noContent().build();
    }
}
