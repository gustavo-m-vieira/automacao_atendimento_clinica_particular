package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.dto.ConsultaProcessDTO;
import com.mycompany.myapp.service.dto.ConsultaDTO;
import com.mycompany.myapp.repository.ConsultaRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.mycompany.myapp.domain.Consulta;
import org.springframework.stereotype.Component;

@Component
public class PersistindoDelegate implements JavaDelegate {
  private final ConsultaRepository consultaRepo;

  public PersistindoDelegate(ConsultaRepository consultaRepository) {
    this.consultaRepo = consultaRepository;
  }
  
  @Override
  public void execute(DelegateExecution delegateExecution) {
    ConsultaProcessDTO consultaProcess = (ConsultaProcessDTO) delegateExecution.getVariable("processInstance");

    ConsultaDTO consulta = consultaProcess.getConsulta();
    Consulta consultaObj = consultaRepo.findById(consulta.getId()).get();

    Double duration = consulta.getDurationInMinutes();
    Double price = duration*2;

    consultaObj.setPrice(price);
    consulta.setPrice(price);

    consultaRepo.save(consultaObj);
  }
}
