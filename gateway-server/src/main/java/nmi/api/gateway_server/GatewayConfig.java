package nmi.api.gateway_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import nmi.api.gateway_server.filter.CustomCorsAndAuthGatewayFilterFactory;

@Configuration
public class GatewayConfig {

    @Autowired
    private CustomCorsAndAuthGatewayFilterFactory authenticationFilter;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
      return builder.routes()
                // First route based on path matching                
                .route(r -> r.path("/modules")
                         // Add a custom header to the request
                        .uri("lb://user-service"))  // Forward the request to this URI
                .route(r -> r.path("/auth/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new CustomCorsAndAuthGatewayFilterFactory.Config())))  // Add a custom header to the request
                        .uri("lb://auth-server"))  // Forward the request to this URI
                .route(r -> r.path("/auth/register")
                        .filters(f -> f.filter(authenticationFilter.apply(new CustomCorsAndAuthGatewayFilterFactory.Config())))  // Add a custom header to the request
                        .uri("lb://auth-server"))                  
                .route(r -> r.path("/auth/token")
                        .filters(f -> f.filter(authenticationFilter.apply(new CustomCorsAndAuthGatewayFilterFactory.Config())))  // Add a custom header to the request
                        .uri("lb://auth-server"))  // Forward the request to this URI    
                .route(r -> r.path("/user/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new CustomCorsAndAuthGatewayFilterFactory.Config()))) // Add a custom header to the request
                        .uri("lb://user-service"))                 
                .build();  // Build the routes
    }


}
