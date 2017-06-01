package br.com.jmsstudio.controller;

import br.com.jmsstudio.model.CarrinhoCompras;
import br.com.jmsstudio.model.CarrinhoItem;
import br.com.jmsstudio.model.Produto;
import br.com.jmsstudio.model.TipoPreco;
import br.com.jmsstudio.repository.ProdutoRepository;
import br.com.jmsstudio.to.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "/finalizar", method = RequestMethod.POST)
    public Callable<ModelAndView> finalizar(RedirectAttributes redirectAttributes) {
        final String URL = "http://book-payment.herokuapp.com/payment";

        return () -> {
            try {
                String result = restTemplate.postForObject(URL, new DadosPagamento(carrinhoCompras.getTotal()), String.class);
                System.out.println(result);

                redirectAttributes.addFlashAttribute("message", result);

            } catch (HttpClientErrorException e) {
                redirectAttributes.addFlashAttribute("error", "Valor maior que o permitido");
                e.printStackTrace();
            }

            return new ModelAndView("redirect:/produto");
        };
    }

    @RequestMapping(value = "/remover", method = RequestMethod.POST)
    public ModelAndView remover(Long idProduto, TipoPreco tipoPreco) {
        Produto produto = produtoRepository.findById(idProduto);
        carrinhoCompras.remove(produto, tipoPreco);

        return new ModelAndView("redirect:/carrinho");
    }

}
