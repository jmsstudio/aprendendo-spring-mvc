package br.com.jmsstudio.controller;

import br.com.jmsstudio.infra.FileManager;
import br.com.jmsstudio.model.Produto;
import br.com.jmsstudio.model.TipoPreco;
import br.com.jmsstudio.repository.ProdutoRepository;
import br.com.jmsstudio.validation.ProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FileManager fileManager;

    @InitBinder
    public void bindValidation(WebDataBinder binder) {
        binder.addValidators(new ProdutoValidator());
    }

    @RequestMapping("/form")
    public ModelAndView form(Produto produto) {
        ModelAndView modelAndView = new ModelAndView("produto/form");
        modelAndView.addObject("tipos", TipoPreco.values());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView save(@Valid Produto produto, BindingResult bindingResult, MultipartFile sumario, RedirectAttributes redirectAttributes) {
        ModelAndView retorno;
        if (bindingResult.hasErrors()) {
            retorno = form(produto);
        } else {

            try {
                String filePath = fileManager.save("files", sumario);
                produto.setPathSumario(filePath);

                produtoRepository.save(produto);
            } catch (IOException e) {
                e.printStackTrace();
            }

            redirectAttributes.addFlashAttribute("successMessage", "Produto cadastrado com sucesso!");

            retorno = new ModelAndView("redirect:/produto");
        }

        return retorno;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        List<Produto> produtos = produtoRepository.list();

        ModelAndView modelAndView = new ModelAndView("produto/list");
        modelAndView.addObject("produtos", produtos);

        return modelAndView;
    }

    @RequestMapping("/detalhe/{id}")
    public ModelAndView detalhe(@PathVariable("id") Long id) {
        Produto produto = produtoRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView("produto/detalhe");
        modelAndView.addObject("produto", produto);

        return modelAndView;
    }

}
