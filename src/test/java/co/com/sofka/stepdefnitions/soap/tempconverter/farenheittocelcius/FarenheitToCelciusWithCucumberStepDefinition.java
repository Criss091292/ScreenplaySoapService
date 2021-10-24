package co.com.sofka.stepdefnitions.soap.tempconverter.farenheittocelcius;

import co.com.sofka.stepdefnitions.soap.tempconverter.SetUpReto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.sofka.questions.ReturnStringValue.systemValue;
import static co.com.sofka.tasks.farenheittocelcius.Convert.doPost;
import static co.com.sofka.util.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

public class FarenheitToCelciusWithCucumberStepDefinition extends SetUpReto {

    private static final String CONVERT_FARENHEIT_TO_CELCIUS = System.getProperty("user.dir") + "\\src\\test\\resources\\TempConverter\\convertFarenheitToCelcius.xml";
    private static final String FARENHEIT = "[Farenheit]";

    @Given("que el usuario de la calculadora ha definido convertir {double} Farenheit a Celcius")
    public void que_el_usuario_de_la_calculadora_ha_definido_convertir_Farenheit_a_Celcius(Double double1) {
        setUp();
        bodyRequest = defineBodyRequest(double1);
    }

    @When("el usuario de la aplicacion ejecuta la conversion")
    public void el_usuario_de_la_aplicacion_ejecuta_la_conversion() {
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );
    }

    @Then("el ususario debería obtener el resultado {int}")
    public void elUsusarioDeberiaObtenerElResultado(Integer celcius) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El resultado de la suma debe ser: ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString("<FahrenheitToCelsiusResult>"+celcius+"</FahrenheitToCelsiusResult>")
                )
        );
    }

    @Given("que el usuario de la calculadora ha definido Convertir {string} a Celcius")
    public void que_el_usuario_de_la_calculadora_ha_definido_Convertir_a_Celcius(String string) {
        setUp();
        bodyRequest = defineBodyRequest(string);
    }

    @Then("el ususario debería obtener el resultado {string}")
    public void el_ususario_debería_obtener_el_resultado(String string) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El resultado de la suma debe ser: ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString("<FahrenheitToCelsiusResult>"+string+"</FahrenheitToCelsiusResult>")
                )
        );
    }


    private String defineBodyRequest(double double1){
        return readFile(CONVERT_FARENHEIT_TO_CELCIUS)
                .replace(FARENHEIT, String.valueOf(double1));
    }

    private String defineBodyRequest(String string){
        return readFile(CONVERT_FARENHEIT_TO_CELCIUS)
                .replace(FARENHEIT, string);
    }

}
