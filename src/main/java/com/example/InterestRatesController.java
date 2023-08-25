package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
public class InterestRatesController {
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/interest-rates")
    public ResponseEntity<InterestRatesResponse> getModifiedInterestRates() {
        try {
            // Llamar al método para raspar y extraer la tasa de interés nominal
            InterestRatesResponse interestRates = scrapeAndExtractNominalRate();
            if (interestRates != null) {
                return ResponseEntity.ok(interestRates);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            // Si ocurre una excepción durante el proceso, devolver una respuesta de error del servidor interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private InterestRatesResponse scrapeAndExtractNominalRate() throws IOException {
        String url = "https://www.larepublica.co/indicadores-economicos/bancos/tasa-de-usura";
        Document document = Jsoup.connect(url).get();
        Element rateSpan = document.selectFirst("#vue-container > div.InternaIndicadores > div > div.flex-grow-1.wrapContentBody > div > div > div.grid-container > div > div > div.d-flex.CardDetailIndicator.multiple > div > div:nth-child(1) > div.priceIndicator.down > div > div.flex-grow-1 > span.price");

        if (rateSpan != null) {
            String rateText = rateSpan.text().trim(); // Recorta cualquier espacio en blanco inicial/posterior
            String numericValue = rateText.replace(",", ".").replace("%", ""); // Eliminar coma y símbolo de porcentaje
            Double originalRate = Double.valueOf(numericValue);
            Double creditInterest = (originalRate - 2.0) / 100.0;
            Double monthlyCreditInterest = creditInterest/12;

            // Asignar los valores de las otras variables
            Double betweenness = monthlyCreditInterest / 2;
            Double administration = 0.01;
            Double ivaAdministration = 0.19;

            return new InterestRatesResponse(creditInterest, monthlyCreditInterest, betweenness, administration, ivaAdministration);
        }

        return null; // Devuelve nulo o lanza una excepción si no se encuentra el rango de la tasa de interés
    }
}
