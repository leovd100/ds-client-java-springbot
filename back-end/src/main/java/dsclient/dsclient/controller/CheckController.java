package dsclient.dsclient.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CheckController {
    @ApiOperation(value = "This method is used to get all clients.")
    @GetMapping("/server")
    public ResponseEntity<String> checkServer(){
        return ResponseEntity.ok().body("Server on");
    }
}
