package nmi.api.gateway_server.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import nmi.api.gateway_server.util.JwtUtil;
import reactor.core.publisher.Mono;

@Component
public class CustomCorsAndAuthGatewayFilterFactory
        extends AbstractGatewayFilterFactory<CustomCorsAndAuthGatewayFilterFactory.Config> {

    private final JwtUtil jwtUtil;
    private final RouteValidator validator;

    public CustomCorsAndAuthGatewayFilterFactory(JwtUtil jwtUtil, RouteValidator validator) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
        this.validator = validator;
    }

    @Override
    public GatewayFilter apply(Config config) {
       return (exchange, chain) -> {
            // CORS Headers for all requests
            System.out.println("working.....filter....");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");  // Replace * with your allowed origin in production
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
            exchange.getResponse().getHeaders().add("Access-Control-Max-Age", "1800");

            // Handle preflight OPTIONS request for CORS
            if (exchange.getRequest().getMethod().equals(HttpMethod.OPTIONS)) {
                exchange.getResponse().setStatusCode(HttpStatus.OK);
                return exchange.getResponse().setComplete();  // Immediately return OK for OPTIONS
            }

            // Check if the request is secured and requires JWT validation
            if (validator.isSecured.test(exchange.getRequest())) {
                // Ensure the Authorization header is present
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return Mono.error(new RuntimeException("Missing Authorization header"));
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7); // Extract token
                }

                // Validate the JWT token
                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    return Mono.error(new RuntimeException("Unauthorized access: Invalid token"));
                }
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Configuration properties can be added here if needed
    }

}
