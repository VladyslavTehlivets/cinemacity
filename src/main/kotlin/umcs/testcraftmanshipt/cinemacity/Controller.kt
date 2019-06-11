package umcs.testcraftmanshipt.cinemacity

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestForController(val returnValue: String = "Hello World") {

    @GetMapping("/greeting")
    fun returnHelloWorldResponse(): ResponseEntity<String> {
        return ResponseEntity.ok(returnValue)
    }
}
