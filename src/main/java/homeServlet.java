import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class homeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnknownHostException {
		
                request.getRequestDispatcher("/WEB-INF/jsp_pages/home.html").forward(request, response);
          
    }
	
	
	private Map<String, String> parseApiResponse(String apiResponse) {
        // Parse JSON response and convert it into a map
        // You can use a JSON library for this purpose (e.g., Jackson, Gson)
        // Here, we provide a simple example for illustration purposes:
        Map<String, String> apiResponseMap = new HashMap<>();
        // Parse your JSON response and populate the map
        // For simplicity, we manually add some key-value pairs
        apiResponseMap.put("ip", "103.127.187.221");
        apiResponseMap.put("continent_name", "Asia");
        
        return apiResponseMap;
    }
	
	
	private static String getPublicIPAddress() {
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            return reader.readLine().trim();
        } catch (Exception e) {
            return "Cannot Determine Properly";
        }
    }


    private static String callExternalAPI(String ipAddress) {
        try {
            String apiUrl = "https://api.ipgeolocation.io/ipgeo?apiKey=" + "0695d69231de4d6aabed57984787b104" + "&ip=" + ipAddress;
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error calling API";
        }
    }
	
	
	
}
