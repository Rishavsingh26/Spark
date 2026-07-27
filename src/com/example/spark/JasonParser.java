package com.example.spark;

import java.util.*;
import java.io.*;
import java.net.*;

public class JasonParser
{
    InputStream is;
    String result;

    JasonParser()
    {
    }

    public String request(String url)
    {
        try
        {
            URL u = new URL(url);
            HttpURLConnection hc = (HttpURLConnection) u.openConnection();
            hc.setRequestMethod("POST");
            hc.setDoOutput(true);
            is = hc.getInputStream();
        }
        catch(IOException e)
        {
        }

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            String line = "";
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null)
            {
                sb.append(line+"\n");
            }
            is.close();
            result = sb.toString();
        }
        catch(Exception e)
        {
        }

        return result;
    }
}
