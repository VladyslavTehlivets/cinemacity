package umcs.testcraftmanshipt.cinemacity.ui

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler

@RestController
@RequestMapping("")
class HomePageController(val commandHandler: CommandHandler) {
    @GetMapping
    fun home(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello")
    }
}
