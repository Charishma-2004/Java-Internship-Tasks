// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAPIClient {
   public WeatherAPIClient() {
   }

   public static void main(String[] var0) {
      try {
         String var1 = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=9d261b18d3eb374d104daa0c2b753a3f";
         URL var2 = new URL(var1);
         HttpURLConnection var3 = (HttpURLConnection)var2.openConnection();
         var3.setRequestMethod("GET");
         BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.getInputStream()));
         StringBuilder var6 = new StringBuilder();

         String var5;
         while((var5 = var4.readLine()) != null) {
            var6.append(var5);
         }

         var4.close();
         JSONObject var7 = new JSONObject(var6.toString());
         double var8 = var7.getJSONObject("main").getDouble("temp");
         double var10 = var8 - 273.15;
         System.out.printf("Weather: %.2f°C\n", var10);
      } catch (Exception var12) {
         var12.printStackTrace();
      }

   }
}
