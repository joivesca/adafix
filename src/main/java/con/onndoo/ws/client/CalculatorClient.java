package con.onndoo.ws.client;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import com.onndoo.ws.client.CalculatorWebServiceImpl;
import com.onndoo.ws.client.CalculatorWebServiceImplService;

import jakarta.xml.ws.BindingProvider;;


public class CalculatorClient {
    public static void main(String[] args) throws Exception {
    	CalculatorWebServiceImplService service = new CalculatorWebServiceImplService();
        CalculatorWebServiceImpl calculator = service.getCalculatorWebServiceImplPort();

        //Configurar autenticación Basic con BindingProvider
        BindingProvider bindingProvider = (BindingProvider) calculator;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        
        // Configurar URL del servicio (por si se requiere cambiar)
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8002/testservice/calculatorWebService");

        // Configurar encabezados de autenticación
        String username = "admin";
        String password = "password123";
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        requestContext.put("jakarta.xml.ws.http.request.headers", Map.of("Authorization", List.of(authHeader)));
        
        int result = calculator.add(8000, 10);
        System.out.println("Resultado: " + result);
    }
}