package br.com.jmsstudio.controller;

import br.com.jmsstudio.model.*;
import br.com.jmsstudio.repository.ProdutoRepository;
import br.com.jmsstudio.to.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private CarrinhoCompras carrinhoCompras;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailSender mailSender;

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes redirectAttributes) {
        final String URL = "http://book-payment.herokuapp.com/payment";

        return () -> {
            try {
//                String result = restTemplate.postForObject(URL, new DadosPagamento(carrinhoCompras.getTotal()), String.class);
                String result = "Pagamento efetuado com sucesso";
                System.out.println(result);

                sendConfirmationMail(usuario);

                redirectAttributes.addFlashAttribute("message", result);

            } catch (HttpClientErrorException e) {
                redirectAttributes.addFlashAttribute("error", "Valor maior que o permitido");
                e.printStackTrace();
            }

            return new ModelAndView("redirect:/produto");
        };
    }

    private void sendConfirmationMail(Usuario usuario) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Compra realizada com sucesso");
        mailMessage.setFrom("naoresponda@loja.com");
        mailMessage.setText("Compra realizada em " + LocalDate.now().format(DateTimeFormatter.ISO_DATE_TIME) + " no valor de R$ " + carrinhoCompras.getTotal().setScale(2));
        mailMessage.setTo(usuario.getEmail());

        mailSender.send(mailMessage);
    }

    @RequestMapping(value = "/remover", method = RequestMethod.POST)
    public ModelAndView remover(Long idProduto, TipoPreco tipoPreco) {
        Produto produto = produtoRepository.findById(idProduto);
        carrinhoCompras.remove(produto, tipoPreco);

        return new ModelAndView("redirect:/carrinho");
    }

}
