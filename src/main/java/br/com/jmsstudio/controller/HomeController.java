package br.com.jmsstudio.controller;

import br.com.jmsstudio.model.Produto;
import br.com.jmsstudio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping("/")
    @Cacheable(value = "produtosHomeCache")
    public ModelAndView index() {
        List<Produto> produtos = produtoRepository.list();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);

        return modelAndView;
    }
}
