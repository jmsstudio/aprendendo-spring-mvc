package br.com.jmsstudio.resolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.util.Locale;

public class XmlViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        MappingJackson2XmlView mappingJackson2XmlView = new MappingJackson2XmlView();
        mappingJackson2XmlView.setPrettyPrint(true);
        return mappingJackson2XmlView;
    }
}
