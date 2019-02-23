package com.zthc.ewms.base.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;


public class GetIp {


    /**
     * ͨ��HttpServletRequest����IP��ַ
     * @param request HttpServletRequest
     * @return ip String
     * @throws Exception
     */
    public static String getIpAddr(HttpServletRequest request) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * ͨ��IP��ַ��ȡMAC��ַ
     * @param ip String,127.0.0.1��ʽ
     * @return mac String
     * @throws Exception
     */
    public static String getMACAddress(String ip) throws Exception {
        String line = "";
        String macAddress = "";
        final String MAC_ADDRESS_PREFIX = "MAC Address = ";
        final String LOOPBACK_ADDRESS = "127.0.0.1";
        //���Ϊ127.0.0.1,���ȡ����MAC��ַ��
        if (LOOPBACK_ADDRESS.equals(ip)) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            //ò�ƴ˷�����ҪJDK1.6��
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            //��������ǰ�mac��ַƴװ��String
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //mac[i] & 0xFF ��Ϊ�˰�byteת��Ϊ������
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            //���ַ�������Сд��ĸ��Ϊ��д��Ϊ�����mac��ַ������
            macAddress = sb.toString().trim().toUpperCase();
            return macAddress;
        }
        //��ȡ�Ǳ���IP��MAC��ַ
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    int index = line.indexOf(MAC_ADDRESS_PREFIX);
                    if (index != -1) {
                        macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return macAddress;
    }
    /**
     *
     * @param content
     *            ����Ĳ��� ��ʽΪ��name=xxx&pwd=xxx
     * @param encoding
     *            ��������������롣��GBK,UTF-8��
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // ��������Ա�API
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // ��http://whois.pconline.com.cnȡ��IP���ڵ�ʡ������Ϣ
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // �����ص�ʡ������Ϣ
            System.out.println("(1) unicodeת��������ǰ��returnStr : " + returnStr);
            returnStr = decodeUnicode(returnStr);
            System.out.println("(2) unicodeת�������ĺ��returnStr : " + returnStr);
            String[] temp = returnStr.split(",");
            if(temp.length<3){
                return "0";//��ЧIP������������
            }
            return returnStr;
        }
        return null;
    }
    /**
     * @param urlStr
     *            ����ĵ�ַ
     * @param content
     *            ����Ĳ��� ��ʽΪ��name=xxx&pwd=xxx
     * @param encoding
     *            ��������������롣��GBK,UTF-8��
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// �½�����ʵ��
            connection.setConnectTimeout(2000);// �������ӳ�ʱʱ�䣬��λ����
            connection.setReadTimeout(2000);// ���ö�ȡ���ݳ�ʱʱ�䣬��λ����
            connection.setDoOutput(true);// �Ƿ������� true|false
            connection.setDoInput(true);// �Ƿ��������true|false
            connection.setRequestMethod("POST");// �ύ����POST|GET
            connection.setUseCaches(false);// �Ƿ񻺴�true|false
            connection.connect();// �����Ӷ˿�
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// ����������Զ˷�����д����
            out.writeBytes(content);// д����,Ҳ�����ύ��ı� name=xxx&pwd=xxx
            out.flush();// ˢ��
            out.close();// �ر������
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// ���Զ�д�����ݶԶ˷�������������
            // ,��BufferedReader������ȡ
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// �ر�����
            }
        }
        return null;
    }
    /**
     * unicode ת���� ����
     *
     * @author fanhui 2007-3-15
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
    // ����
    public static void main(String[] args) {
        // ����ip 219.136.134.157 �й�=����=�㶫ʡ=������=Խ����=����
        String ip = "219.136.134.157";
        String address = "";
        try {
            address =getAddresses("ip="+ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(address);
        // ������Ϊ���㶫ʡ,������,Խ����
    }

}
