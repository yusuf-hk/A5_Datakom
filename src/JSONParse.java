import org.json.JSONArray;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class JSONParse
{
    private HTTPPost httpPost;
    private HTTPGet httpGet;

    public JSONParse(String host, int port)
    {
        httpPost = new HTTPPost(host, port);
        httpGet = new HTTPGet(host, port);
    }

    public int getSessionId(String email, String phone)
    {
        String response = httpPost.sendAuth(email, phone);
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getInt("sessionId");
    }

    public String getArgument(int task, int sessionId)
    {
        String response = httpGet.sendRequestTask(task, sessionId);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("arguments");
        String argument = jsonArray.getString(0);
        return argument;
    }

    public String getSecondArgument(int task, int sessionId)
    {
        String response = httpGet.sendRequestTask(task, sessionId);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("arguments");
        String secondArgument = jsonArray.getString(1);
        return secondArgument;
    }

    public String getIpAddress(String ipAddress, String macAddress)
    {
        IPv4 iPv4 = new IPv4(ipAddress, macAddress);
        return iPv4.getAvailableIPs(2).get(0);
    }

    public int getArraySum(int task, int sessionId)
    {
        String response = httpGet.sendRequestTask(task, sessionId);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("arguments");
        int sum = 1;
        for(int i = 0; i<jsonArray.length(); i++)
        {
            sum *= jsonArray.getInt(i);
        }
        return sum;
    }

    public String getIpAddress(String ipMacAddress)
    {
        String ipAddress = ipMacAddress.replaceAll("^\"|\"$", "").substring(0, 10);
        Random random = new Random();
        int randomInt = random.nextInt(255);
        return ipAddress + "." + randomInt;
    }

    public Integer getTextFromMd5(String md5)
    {
        Integer password = 0;
        try
        {
            String md5Hash = md5;
            String passwordMd5 = "";

                while (md5Hash != passwordMd5)
                {
                    if(password < 9999)
                    {
                        password += 1;
                        passwordMd5 += password.toString();
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        md.update(passwordMd5.getBytes());

                        byte byteData[] = md.digest();

                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < byteData.length; i++)
                        {
                            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                        }
                        if (md5Hash.equals(sb.toString()))
                        {
                            break;
                        }
                        else
                        {
                            passwordMd5 = "";
                        }
                    }
                }
        }
        catch(NoSuchAlgorithmException e)
        {
            System.out.println("Something went wrong:" + e.getMessage());
        }
        return password;
    }
}
