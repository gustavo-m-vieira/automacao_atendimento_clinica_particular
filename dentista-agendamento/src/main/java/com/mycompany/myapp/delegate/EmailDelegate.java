package com.mycompany.myapp.delegate;

import java.util.Locale;



import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.mycompany.myapp.service.dto.ConsultaProcessDTO;
import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.dto.ConsultaDTO;

public class EmailDelegate implements JavaDelegate {
  
  @Autowired
  MailService mailService;

  @Autowired
  SpringTemplateEngine templateEngine;
  
  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    ConsultaProcessDTO consultaProcess = (ConsultaProcessDTO) delegateExecution.getVariable("processInstance");
    ConsultaDTO consulta = consultaProcess.getConsulta();

    String to = consulta.getEmail();
    String subject = "Confirmação de agendamento";

    Context context = new Context(Locale.getDefault());
    context.setVariable("consulta", consulta);
    String content = templateEngine.process("consultaProcess/consultaEmail", context);

    mailService.sendEmail(to, subject, content, false, true);
  }  
}