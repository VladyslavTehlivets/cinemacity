package umcs.testcraftmanshipt.cinemacity

import cucumber.api.java8.En

class CashierFunctions : En {
    init {
        Given("^show \"([^\"]*)\" at \"([^\"]*)\" today$") { arg0: String, arg1: String -> }
        Given("^(\\d+)-th seat at (\\d+)-th row on show \"([^\"]*)\" is reserved by person with first name \"([^\"]*)\" and second name \"([^\"]*)\"$"
        ) { arg0: Int, arg1: Int, arg2: String, arg3: String, arg4: String -> }
        When("^this person pay to cashier$") { }
        Then("^the cashier mark (\\d+)-th seat at (\\d+)-th row as \"([^\"]*)\"$") { arg0: Int, arg1: Int, arg2: String -> }
        And("^now is (\\d+) min before given show$") { arg0: Int -> }
        When("^person is not pay to cashier$") { }
        Given("^show \"([^\"]*)\"$") { arg0: String -> }
        And("^(\\d+)-th seat at (\\d+)-th row on show \"([^\"]*)\" is \"([^\"]*)\" yet$") { arg0: Int, arg1: Int, arg2: String, arg3: String -> }
        When("^person pay for cashier and order this place$") { }
    }
}
