package com.mycompany.myapp.process.consultaProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consulta-process/cadastro-process")
public class CadastroProcessController {

    private final Logger log = LoggerFactory.getLogger(CadastroProcessController.class);

    private final CadastroProcessService cadastroProcessService;

    public CadastroProcessController(CadastroProcessService cadastroProcessService) {
        this.cadastroProcessService = cadastroProcessService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroProcessContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        CadastroProcessContextDTO cadastroProcessContext = cadastroProcessService.loadContext(id);
        return ResponseEntity.ok(cadastroProcessContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<CadastroProcessContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        CadastroProcessContextDTO cadastroProcessContext = cadastroProcessService.claim(id);
        return ResponseEntity.ok(cadastroProcessContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody CadastroProcessContextDTO cadastroProcessContext) {
        log.debug("REST request to complete ConsultaProcess.CadastroProcess {}", cadastroProcessContext.getTaskInstance().getId());
        cadastroProcessService.complete(cadastroProcessContext);
        return ResponseEntity.noContent().build();
    }
}
