package umcs.testcraftmanshipt.cinemacity.domain

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(BAD_REQUEST)
data class DomainException(var messageCode: String) : Throwable() {

    init {
        messageCode = "domainException.$messageCode"
    }
}
