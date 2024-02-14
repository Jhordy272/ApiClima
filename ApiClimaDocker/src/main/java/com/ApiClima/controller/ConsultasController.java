package com.ApiClima.controller;

import com.ApiClima.audit.entity.Auditoria;
import com.ApiClima.audit.repository.AuditoriaRepository;
import com.ApiClima.dto.Mensaje;
import com.ApiClima.entity.Ciudad;
import com.ApiClima.security.jwt.TokenUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bucket;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j

@RequestMapping("/consultas")
@CrossOrigin(origins = "*")
@CacheConfig(cacheNames = "consultasCache")
@SecurityRequirement(name = "Authorization")
public class ConsultasController {

    private final RestTemplate restTemplate;
    @Autowired
    private AuditoriaRepository auditoriaRepository;
    @Autowired
    private TokenUtils tokenUtils;

    private final Bucket bucket;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ConsultasController(RestTemplate restTemplate, Bucket bucket) {
        this.restTemplate = restTemplate;
        this.bucket = bucket;
    }

    @GetMapping("/clima/{name}")
    @Cacheable(key = "'clima-' + #name", unless = "#result == null")
    public ResponseEntity<?> getClima(@PathVariable("name") String name, HttpServletRequest request) throws JsonProcessingException {
        String token = obtenerTokenDeSolicitud(request);
        log.info("Token: " + token);
        String email = tokenUtils.getEmailFromToken(token);
        log.info("Email: " + email);

        Auditoria auditoria = new Auditoria();
        auditoria.setUrl("/consultas/clima/" + name);
        auditoria.setFecha(new Date());
        auditoria.setRequest(name);
        auditoria.setUsuario(email);
        auditoriaRepository.save(auditoria);

        if (this.bucket.tryConsume(1)) {
            Ciudad ciudad = encontrarCiudad(name);
            if (ciudad != null) {
                String lat = ciudad.getLat();
                String lon = ciudad.getLon();
                String key = "cc8eb830185b51e71e189c90725a1807";
                String climaUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + key;
                Object clima = restTemplate.getForObject(climaUrl, Object.class);
                String climaJson = objectMapper.writeValueAsString(clima.toString());
                auditoria.setResponse(climaJson);
                auditoriaRepository.save(auditoria);
                return new ResponseEntity<>(clima, HttpStatus.OK);
            } else {
                Mensaje mensaje = new Mensaje("No se encontraron resultados para la ciudad: " + name);
                auditoria.setResponse(mensaje.toString());
                auditoriaRepository.save(auditoria);
                return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
            }
        }
        Mensaje mensaje = new Mensaje("Demasiadas consultas");
        auditoria.setResponse(mensaje.toString());
        auditoriaRepository.save(auditoria);
        return new ResponseEntity<>(mensaje, HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping("/pronostico5dias/{name}")
    @Cacheable(key = "'pronostico-' + #name", unless = "#result == null")
    public ResponseEntity<?> getPronostico5Dias(@PathVariable("name") String name, HttpServletRequest request) throws JsonProcessingException {
        String token = obtenerTokenDeSolicitud(request);
        log.info("Token: " + token);
        String email = tokenUtils.getEmailFromToken(token);
        log.info("Email: " + email);

        Auditoria auditoria = new Auditoria();
        auditoria.setUrl("/consultas/pronostico5dias/" + name);
        auditoria.setFecha(new Date());
        auditoria.setRequest(name);
        auditoria.setUsuario(email);
        auditoriaRepository.save(auditoria);
        if (this.bucket.tryConsume(1)) {
            Ciudad ciudad = encontrarCiudad(name);
            if (ciudad != null) {
                String lat = ciudad.getLat();
                String lon = ciudad.getLon();

                String key = "cc8eb830185b51e71e189c90725a1807";
                String pronosticoUrl = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + key;
                Object pronostico = restTemplate.getForObject(pronosticoUrl, Object.class);
                String pronosticoJson = objectMapper.writeValueAsString(pronostico.toString());
                auditoria.setResponse(pronosticoJson);
                auditoriaRepository.save(auditoria);
                return new ResponseEntity<>(pronostico, HttpStatus.OK);
            } else {
                Mensaje mensaje = new Mensaje("No se encontraron resultados para la ciudad: " + name);;
                auditoria.setResponse(mensaje.toString());
                auditoriaRepository.save(auditoria);
                return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
            }
        }
        Mensaje mensaje = new Mensaje("Demasiadas consultas");
        auditoria.setResponse(mensaje.toString());
        auditoriaRepository.save(auditoria);
        return new ResponseEntity<>(mensaje, HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping("/contaminacion/{name}")
    @Cacheable(key = "'contaminacion-' + #name", unless = "#result == null")
    public ResponseEntity<?> getContaminacion(@PathVariable("name") String name, HttpServletRequest request) throws JsonProcessingException {
        String token = obtenerTokenDeSolicitud(request);
        log.info("Token: " + token);
        String email = tokenUtils.getEmailFromToken(token);
        log.info("Email: " + email);

        Auditoria auditoria = new Auditoria();
        auditoria.setUrl("/consultas/contaminacion/" + name);
        auditoria.setFecha(new Date());
        auditoria.setRequest(name);
        auditoria.setUsuario(email);
        auditoriaRepository.save(auditoria);

        if (this.bucket.tryConsume(1)) {
            Ciudad ciudad = encontrarCiudad(name);
            if (ciudad != null) {
                String lat = ciudad.getLat();
                String lon = ciudad.getLon();

                String key = "9ac52d1810dd8b3021fe7e9d299b7de6";
                String contaminacionUrl = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon=" + lon + "&appid=" + key;
                Object contaminacion = restTemplate.getForObject(contaminacionUrl, Object.class);
                String contaminacionJson = objectMapper.writeValueAsString(contaminacion.toString());
                auditoria.setResponse(contaminacionJson);
                auditoriaRepository.save(auditoria);
                return new ResponseEntity<>(contaminacion, HttpStatus.OK);
            } else {
                Mensaje mensaje = new Mensaje("No se encontraron resultados para la ciudad: " + name);;
                auditoria.setResponse(mensaje.toString());
                auditoriaRepository.save(auditoria);
                return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
            }
        }
        Mensaje mensaje = new Mensaje("Demasiadas consultas");
        auditoria.setResponse(mensaje.toString());
        auditoriaRepository.save(auditoria);
        return new ResponseEntity<>(mensaje, HttpStatus.TOO_MANY_REQUESTS);
    }

    public Ciudad encontrarCiudad(String name) {
        log.info("Ciudad: " + name);
        String coordenadasUrl = "https://nominatim.openstreetmap.org/search?city=" + name + "&format=json";
        Ciudad[] ciudad = restTemplate.getForObject(coordenadasUrl, Ciudad[].class);

        if (ciudad != null && ciudad.length > 0) {
            log.info("Latitud: " + ciudad[0].getLat());
            log.info("Longitud: " + ciudad[0].getLon());
            return ciudad[0];
        } else {
            log.info("No se encontraron resultados para la ciudad: " + name);
            return null;
        }
    }

    private String obtenerTokenDeSolicitud(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
