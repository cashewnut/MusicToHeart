import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ArrayList;

public class TestPost {


    public static final String ADD_URL = "http://10.141.212.24:14567/postSongs";

    public static void appadd() throws IOException {
        HttpURLConnection connection=null;
        try {
            //创建连接
            URL url = new URL(ADD_URL);
            connection = (HttpURLConnection) url.openConnection();


            //设置http连接属性

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST"); // 可以根据需要 提交 GET、POST、DELETE、INPUT等http提供的功能
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            //设置http头 消息
            connection.setRequestProperty("Content-Type","application/json");  //设定 请求格式 json，也可以设定xml格式的
            //connection.setRequestProperty("Content-Type", "text/xml");   //设定 请求格式 xml，
            connection.setRequestProperty("Accept","application/json");//设定响应的信息的格式为 json，也可以设定xml格式的
//             connection.setRequestProperty("X-Auth-Token","xx");  //特定http服务器需要的信息，根据服务器所需要求添加
            connection.connect();

            //添加 请求内容

            PostSongInfo info = new PostSongInfo();
            info.setEmotionType(0);
            ArrayList<String> list = new ArrayList<String>();
            list.add("All About You-Hilary Duff_new.mp3");
            list.add("All Falls Down-Alan Walker,Noah Cyrus,Digital Farm Animals_new");
           
            info.setSongNameList(list);


            Gson gson = new Gson();
            String data = gson.toJson(info);
            System.out.println(data);

            OutputStream out = connection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();


//            读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
////              断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        appadd();
    }


}
