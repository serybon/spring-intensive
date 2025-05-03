package by.bnd.intensive_spring.controller;

import by.bnd.intensive_spring.common.util.ServerResponseHelper;
import by.bnd.intensive_spring.model.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/foo1")
    public ResponseEntity<ServerResponse<String>> foo1() {
        return ServerResponseHelper.ok("foo1");
    }

    @GetMapping("/foo2")
    public ResponseEntity<ServerResponse<String>> foo2() {
        return ServerResponseHelper.notFound("foo2 Не найден");
    }
}
