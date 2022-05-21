package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @version 1.0
 * @program: cloud-demo
 * @description: Customized Filter
 * @author: Philip.Zhang
 * @date: 2021/12/22 11:37 PM
 */
@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();
        //2. 获取参数重的authorization参数
        String authorization = params.getFirst("authorization");
        //3. 判断参数值是否等于admin
        if ("admin".equals(authorization)) {
            //4. 是，方形
            return chain.filter(exchange);
        }
        //5. 否，拦截
        //5.1 设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //5.2 拦截请求
        return exchange.getResponse().setComplete();
    }
}
