package todoapp.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * Spring Web MVC 설정 정보이다.
 *
 * @author springrunner.kr@gmail.com
 */
@Configuration
class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * spring boot 3.4.2 공식 문서내용
     * 기본적으로 Spring Boot는 클래스패스 내의
     * /static, /public, /resources, /META-INF/resources 디렉토리 또는 서블릿 컨텍스트의 루트에서 정적 콘텐츠를 제공합니다.
     * <p>
     * Spring Boot는 Spring MVC의 ResourceHttpRequestHandler를 사용하여 이를 처리하며,
     * 원하는 동작으로 변경하려면 WebMvcConfigurer를 구현하고 addResourceHandlers 메서드를 오버라이딩하면 됩니다.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // registry.enableContentNegotiation();
        // 위와 같이 직접 설정하면, 스프링부트가 구성한 ContentNegotiatingViewResolver 전략이 무시된다.
    }

    /**
     * 스프링부트가 생성한 ContentNegotiatingViewResolver를 조작할 목적으로 작성된 설정 정보이다.
     */
    public static class ContentNegotiationCustomizer {

        public void configurer(ContentNegotiatingViewResolver viewResolver) {
            // TODO ContentNegotiatingViewResolver 조작하기
        }

    }

}
