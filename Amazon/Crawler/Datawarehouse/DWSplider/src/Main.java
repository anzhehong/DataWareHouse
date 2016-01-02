import org.apache.http.HttpHost;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main implements PageProcessor {

    private static ArrayList urlArray = new ArrayList();

    private static ArrayList<Proxy> proxyList = null;
    private static int proxyIndex = 0;

    private static int count = 0;
    private static Pattern title1  = Pattern.compile("(?<=<h1 id=\"aiv-content-title\" class=\"content-title js-hide-on-play\">).*?(\\s)(?=<span)");
    private static Pattern title2 = Pattern.compile("id=\"productTitle\".*>(.*)</span>");

    private static Pattern detail1 = Pattern.compile("<h1 id=\"btf-product-details\".*<div class=\"aiv-wrapper", Pattern.DOTALL);
    private static Pattern detail2 = Pattern.compile("<div id=\"detail-bullets\".*<div id=\"revDivider\"", Pattern.DOTALL);



    private Site mysite = Site.me()
            .addHeader("Connection", "Keep-Alive")
            .addHeader("Accept", "text/html,application/xhtml+xml,applicati" +
                    "on/xml;q=0.9,image/webp,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Host", "www.amazon.com")
            .setCycleRetryTimes(3)
            .setTimeOut(3000)
            .setSleepTime(100)
            .setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");

    public void process(Page page) {


        String title = null;
        String detail = null;

        String movieInfo = page.getHtml().getDocument().toString();
        Matcher titleMatcher1 = title1.matcher(movieInfo);
        Matcher titleMatcher2 = title2.matcher(movieInfo);
        Matcher detailMatcher1 = detail1.matcher(movieInfo);
        Matcher detailMatcher2 = detail2.matcher(movieInfo);

        if (titleMatcher1.find()) {
            detailMatcher1.find();
            title = titleMatcher1.group();
            detail = detailMatcher1.group();

        } else if (titleMatcher2.find()) {
            detailMatcher2.find();
            title = titleMatcher2.group(1);
            detail = detailMatcher2.group();
        }

        if (null != title) {

            title = title.replaceAll("&[a-z]+;", "");

            page.putField("productId",page.getUrl().toString());
            page.putField("title", title);
            page.putField("detail",detail);
        }
        else {
            mysite.setHttpProxy(new HttpHost(proxyList.get(proxyIndex).ip, proxyList.get(proxyIndex).port));
            proxyIndex++;
            proxyIndex = proxyIndex%16;
        }

        if (page.getUrl().toString().equals("http://www.amazon.com/dp/6302623332")) {
            page.addTargetRequests(urlArray);
        }
    }

    public Site getSite() {
        return mysite;
    }

    public static void main(String[] args) throws Exception{

        proxyList = new ArrayList<>();
        proxyList.add(new Proxy("54.86.216.36", 3128));
        proxyList.add(new Proxy("106.186.24.144", 3128));
        proxyList.add(new Proxy("202.147.11.202", 80));
        proxyList.add(new Proxy("222.39.64.13", 8118));
        proxyList.add(new Proxy("58.23.16.245", 80));
        proxyList.add(new Proxy("180.169.106.4", 8080));
        proxyList.add(new Proxy("q.gfw.li", 40999));
        proxyList.add(new Proxy("122.76.249.2", 8118));
        proxyList.add(new Proxy("117.177.243.42", 8080));
        proxyList.add(new Proxy("39.189.106.164", 8123));
        proxyList.add(new Proxy("222.74.212.163", 3128));
        proxyList.add(new Proxy("122.96.59.106", 83));
        proxyList.add(new Proxy("163.177.155.2", 8088));
        proxyList.add(new Proxy("91.183.124.41", 80));
        proxyList.add(new Proxy("154.70.134.74", 8080));
        proxyList.add(new Proxy("112.74.26.237", 80));

        File movieUrlFile = new File("/Users/Harold_LIU/Desktop/Amazon/MovieUrlE2.txt");
        if (movieUrlFile.isFile() && movieUrlFile.exists()) {
            InputStreamReader read = new InputStreamReader(new FileInputStream(movieUrlFile));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTXT = null;

            while ((lineTXT = bufferedReader.readLine()) != null)
            {
                String url =  lineTXT.toString();
                urlArray.add(url);
            }
            System.out.println("COUNT:" + urlArray.size());
            read.close();
        } else {
            System.out.println("找不到文件");
        }


      FileCacheQueueScheduler ungetURL = new FileCacheQueueScheduler("/Users/Harold_LIU/Desktop/Amazon/");

        Spider movieSpider = Spider.create(new Main());
        movieSpider.addUrl("http://www.amazon.com/dp/6302623332")
                .addPipeline(new MovieNamePipeline())
              //  .addPipeline(new ConsolePipeline())
                .setScheduler(ungetURL)
                .thread(1024)
                .run();
    }
}
