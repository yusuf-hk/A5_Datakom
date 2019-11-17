public class Main
{
    private static String host = "datakomm.work";
    private static int port = 80;

    private static String email = "yusufh@stud.ntnu.no";
    private static String phone = "95864173";

    public static void main(String[] args)
    {
        //HTTPPost httpPost = new HTTPPost(host, port);;
        //httpPost.sendAuth(email,phone);

        JSONParse jsonParse = new JSONParse(host, port);
        HTTPGet httpGet = new HTTPGet(host, port);
        HTTPPost httpPost = new HTTPPost(host, port);

        //task 1
        int sessionId = jsonParse.getSessionId(email, phone);
        httpGet.sendRequestTask(1, sessionId);
        httpPost.sendMessage(sessionId,"Hello");

        //task 2
        String argument = jsonParse.getArgument(2, sessionId);
        httpPost.sendMessage(sessionId, argument);

        //task 3
        int arraySum = jsonParse.getArraySum(3, sessionId);
        httpPost.sendResultMessage(sessionId, arraySum);

        //task 4
        String md5 = jsonParse.getArgument(4, sessionId);
        int md5Text = jsonParse.getTextFromMd5(md5);
        httpPost.sendPinMessage(sessionId, md5Text);

        //secret task
        String ipAddress = jsonParse.getArgument(2016, sessionId);
        String macAddress = jsonParse.getSecondArgument(2016, sessionId);
        String availableIp = jsonParse.getIpAddress(ipAddress, macAddress);
        httpPost.sendIpAddress(sessionId, availableIp);

        //receive feedback
        httpGet.sendRequestReceieve(sessionId);
    }
}
