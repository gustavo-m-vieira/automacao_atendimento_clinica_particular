package com.mycompany.myapp.process.consultaProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta-process/info-cadastro")
public class InfoCadastroController {

    private final Logger log = LoggerFactory.getLogger(InfoCadastroController.class);

    private final InfoCadastroService infoCadastroService;

    public InfoCadastroController(InfoCadastroService infoCadastroService) {
        this.infoCadastroService = infoCadastroService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoCadastroContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        InfoCadastroContextDTO infoCadastroContext = infoCadastroService.loadContext(id);
        return ResponseEntity.ok(infoCadastroContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<InfoCadastroContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        InfoCadastroContextDTO infoCadastroContext = infoCadastroService.claim(id);
        return ResponseEntity.ok(infoCadastroContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody InfoCadastroContextDTO infoCadastroContext) {
        log.debug("REST request to complete ConsultaProcess.InfoCadastro {}", infoCadastroContext.getTaskInstance().getId());
        infoCadastroService.complete(infoCadastroContext);
        return ResponseEntity.noContent().build();
    }
}
