import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static final String REQ_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet httpGet = new HttpGet(REQ_URI);
        httpGet.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(httpGet);

    //   String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        List<Cats> cats = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {});

        cats.stream().filter(cat -> cat.getUpvotes() > 0).forEach(System.out::println);
    }
}
