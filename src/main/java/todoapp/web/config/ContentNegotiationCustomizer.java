package todoapp.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import todoapp.web.support.servlet.view.CommaSeparatedValuesView;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ContentNegotiationCustomizer {
    @Autowired
    public void customizeContentNegotiation(ContentNegotiatingViewResolver resolver) {
        List<View> defaultResolver = new ArrayList<>(resolver.getDefaultViews());
        defaultResolver.add(new CommaSeparatedValuesView());
        resolver.setDefaultViews(defaultResolver);
    }
}
