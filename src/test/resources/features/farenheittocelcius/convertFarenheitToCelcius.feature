Feature: Convertir temperaturas - Farenheit a Celcius
  Como usuario de la aplicacion
  necesito validar que la funcionalidad de convertir farenheit a celcius trabaje adecuadamente
  para poder tener seguridad en los reportes obtenidos por el aplicativo.

  Scenario: convertir numeros
    Given que el usuario de la calculadora ha definido convertir -220.0 Farenheit a Celcius
    When el usuario de la aplicacion ejecuta la conversion
    Then el ususario debería obtener el resultado -140

  Scenario: convertir texto
    Given que el usuario de la calculadora ha definido Convertir "CadenaTexto" a Celcius
    When el usuario de la aplicacion ejecuta la conversion
    Then el ususario debería obtener el resultado "Error"