package umcs.testcraftmanshipt.cinemacity

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(plugin = ["pretty", "html:target/cucumber"], features = ["classpath:features"],
        glue = ["umcs.testcraftmanshipt.cinemacity.cashier.acceptReservation"])
class RunCucumberCashierAcceptReservationTest