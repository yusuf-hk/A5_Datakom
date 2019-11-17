import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamHandler
{
    /**
     * Read the whole content from an InputStream, return it as a string
     *
     * @param is Inputstream to read the body from
     * @return The whole body as a string
     */
    public String convertStreamToString(InputStream is)
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        try
        {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
                response.append('\n');
            }
        } catch (IOException ex)
        {
            System.out.println("Could not read the data from HTTP response: " + ex.getMessage());
        }
        return response.toString();
    }
}
