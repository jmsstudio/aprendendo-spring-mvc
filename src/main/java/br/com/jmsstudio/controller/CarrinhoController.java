package br.com.jmsstudio.controller;

import br.com.jmsstudio.model.CarrinhoCompras;
import br.com.jmsstudio.model.CarrinhoItem;
import br.com.jmsstudio.model.Produto;
import br.com.jmsstudio.model.TipoPreco;
import br.com.jmsstudio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoCompras carrinhoCompras;

    @RequestMapping("/add")
    public ModelAndView adiciona(Long produtoId, TipoPreco tipoPreco) {
        ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
        CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);

        carrinhoCompras.add(carrinhoItem);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("carrinho/itens");

        return modelAndView;
    }

    private CarrinhoItem criaItem(Long produtoId, TipoPreco tipoPreco) {
        Produto produto = produtoRepository.findById(produtoId);

        return new CarrinhoItem(produto, tipoPreco);
    }
}
