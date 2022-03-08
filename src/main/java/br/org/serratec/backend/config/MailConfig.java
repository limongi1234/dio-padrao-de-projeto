package br.org.serratec.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void enviarEmail(String para, String assunto, String texto) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("grupo4apiserratec@gmail.com");
		simpleMailMessage.setTo(para);
		simpleMailMessage.setSubject(assunto);
		simpleMailMessage.setText("Seu pedido foi finalizado com sucesso" +"\n\n\n\n Data de envio: Data de entrega: Produtos: Quantidade: Valor Final do Pedido: \n"+ texto);
		javaMailSender.send(simpleMailMessage);
	}
	//precisa completar -> Data de envio: Data de entrega: Produtos: Quantidade: 
	//Valor Final do Pedido: 
	//em simpleMailMessage.setText
}
