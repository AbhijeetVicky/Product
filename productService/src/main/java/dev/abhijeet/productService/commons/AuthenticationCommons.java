package dev.abhijeet.productService.commons;

import dev.abhijeet.productService.dtos.UserDto;
import dev.abhijeet.productService.exceptions.InvalidTokenException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AuthenticationCommons {

    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate=restTemplate;

    }

    public UserDto validateToken(String token){
        if(token==null){
            throw new InvalidTokenException("Invalid Token or Expired");
        }

        HttpHeaders headers  = new  HttpHeaders();
        headers.set("Authorization",token);

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Use exchange method to make the request with headers
        try{
            ResponseEntity<UserDto> response =  restTemplate.exchange(
                    "http://localhost:8080/user/validate",  //while fetching the allproduct, we are validating the token
                    HttpMethod.POST,
                    entity,
                    UserDto.class
            );
            return response.getBody();

        } catch(Exception ex){
            throw new InvalidTokenException("Bad Request");
        }


    }
}
