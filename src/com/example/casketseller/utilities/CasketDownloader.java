//package com.example.casketseller.utilities;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.HttpGet;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.http.AndroidHttpClient;
//import android.os.Process;
//import android.util.Log;
//
//public class CasketDownloader {
//
//  private static ArrayList<Bitmap> casketImages = new ArrayList<Bitmap>();
//
//  private ArrayList<String> casketURLs = new ArrayList<String>();
//
//  static int downloadCount = 0;
//
//  public static ArrayList<Bitmap> getCasketImages() {
//    return casketImages;
//  }
//
//  public ArrayList<String> getCasketURLs() {
//    return casketURLs;
//  }
//
//  public static int getDownloadCount() {
//    return downloadCount;
//  }
//
//  public void setSearch() {
//
//  }
//
//  /**
//   * Download casket images and information
//   */
//  public void downloadCaskets() {
//
//    casketURLs = searchCaskets();
//
//    // execute thread to download images
//    new ImageDownloadThread().start();
//
//  }
//
//  private ArrayList<String> searchCaskets() {
//
//    // this is temp code - search logic goes here
//
//    ArrayList<String> urls = new ArrayList<String>();
//
//    urls.add("http://friendlyfuneralista.files.wordpress.com/2010/02/casket2.jpg");
//    urls.add("http://www.casketshowcase.com/caskets/casket/lacy-18gauge-steel-casket.jpg");
//    urls.add("http://www.bestpricecaskets.com/store/graphics/00000001/8525.jpg");
//    urls.add("http://www.colliercasket.com/In%20Gods%20Care480.jpg");
//    urls.add("http://www.bestpricecaskets.com/store/graphics/00000001/8684.jpg");
//
//    return urls;
//    
//  }
//
//  private class ImageDownloadThread extends Thread {
//
//    @Override
//    public void run() {
//
//      Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
//
//      for (String url : casketURLs) {
//        Bitmap b = downloadBitmap(url);
//        casketImages.add(b);
//        downloadCount++;
//      }
//
//
//    }
//
//    Bitmap downloadBitmap(String url) {
//
//      final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
//      final HttpGet getRequest = new HttpGet(url);
//
//      try {
//        HttpResponse response = client.execute(getRequest);
//        final int statusCode = response.getStatusLine().getStatusCode();
//        if (statusCode != HttpStatus.SC_OK) {
//          Log.w("ImageDownloader", "Error " + statusCode + " while retrieving bitmap from " + url);
//          return null;
//        }
//
//        final HttpEntity entity = response.getEntity();
//        if (entity != null) {
//          InputStream inputStream = null;
//          try {
//            inputStream = entity.getContent();
//            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            return bitmap;
//          } finally {
//            if (inputStream != null) {
//              inputStream.close();
//            }
//            entity.consumeContent();
//          }
//        }
//      } catch (Exception e) {
//        // Could provide a more explicit error message for IOException or IllegalStateException
//        getRequest.abort();
//        Log.w("ImageDownloader", "Error while retrieving bitmap from " + url);
//      } finally {
//        if (client != null) {
//          client.close();
//        }
//      }
//      return null;
//    }
//
//  }
//
//  public boolean downloadComplete() {
//    
//    if (downloadCount == casketURLs.size())
//      return true;
//    
//    return false;
//  }
//
//}

package com.example.casketseller.utilities;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import com.example.casketseller.backend.Casket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.Process;
import android.util.Log;

public class CasketDownloader {

  private static ArrayList<Casket> caskets;

  static int downloadCount = 0;

  static boolean downloadCaskets = false;

  static boolean firstDownload = true;
  
  public static ArrayList<Casket> getCaskets() {
    return caskets;
  }

  public static int getDownloadCount() {
    return downloadCount;
  }

  public void setSearch(String extMaterial, String color, String name, Double pFrom, Double pTo) {

    if (!firstDownload && SearchData.extMaterial != null && SearchData.extMaterial.equals(extMaterial)
            && SearchData.color != null && SearchData.color.equals(color)
            && SearchData.casketName != null && SearchData.casketName.equals(name)
            && SearchData.priceFrom != null && SearchData.priceFrom == pFrom
            && SearchData.priceTo != null && SearchData.priceTo == pTo) {
      downloadCaskets = false;
    } else {
      SearchData.extMaterial = extMaterial;
      SearchData.color = color;
      SearchData.casketName = name;
      SearchData.priceFrom = pFrom;
      SearchData.priceTo = pTo;
      downloadCaskets = true;
      firstDownload = false;
    }
  }

  /**
   * Download casket images and information
   */
  public boolean downloadCaskets() {

    if (downloadCaskets == true) {
      caskets = searchCaskets();

      if (caskets.size() == 0)
        return false;

      // execute thread to download images
      new ImageDownloadThread().start();

      return true;
    }

    return false;
  }

  private ArrayList<Casket> searchCaskets() {

    // this is temp code - search logic goes here

     ArrayList<Casket> caskets = getCasketsFromInternet();
//    ArrayList<Casket> caskets = getCasketsFromSDCard();

    return caskets;
  }

//  private ArrayList<Casket> getCasketsFromSDCard() {
//    
//    Casket searchCasket = new Casket(SearchData.extMaterial, SearchData.color,
//            SearchData.casketName, 0, "", "");
//
//    ArrayList<Casket> caskets = new ArrayList<Casket>();
//    ArrayList<Casket> removeCaskets = new ArrayList<Casket>();
//
//    caskets.add(new Casket("wood", "Brown", "Casket 1", 2000.0, "", ""));
//    caskets.add(new Casket("18 gauge", "Silver", "Casket 2", 3000,
//            "http://www.casketshowcase.com/caskets/casket/lacy-18gauge-steel-casket.jpg"));
//    caskets.add(new Casket("20 gauge", "Gold", "Casket 3", 4000,
//            "http://www.bestpricecaskets.com/store/graphics/00000001/8525.jpg"));
//    caskets.add(new Casket("Steel", "", "Casket 4", 5000,
//            "http://www.colliercasket.com/In%20Gods%20Care480.jpg"));
//    caskets.add(new Casket("Wood", "Brown", "Casket 5", 6000,
//            "http://www.bestpricecaskets.com/store/graphics/00000001/8684.jpg"));
//
//    for (Casket c : caskets) {
//
//      if (!searchCasket.isEmpty() && !c.equals(searchCasket))
//        removeCaskets.add(c);
//      else {
//        if (SearchData.priceFrom != null && SearchData.priceFrom != 0
//                && c.getPrice() <= SearchData.priceFrom) {
//          removeCaskets.add(c);
//          continue;
//        } else if (SearchData.priceTo != null && SearchData.priceTo != 0
//                && c.getPrice() >= SearchData.priceTo) {
//          removeCaskets.add(c);
//          continue;
//        }
//      }
//    }
//
//    caskets.removeAll(removeCaskets);
//
//    return caskets;
//
//  }

  private ArrayList<Casket> getCasketsFromInternet() {

    Casket searchCasket = new Casket(SearchData.extMaterial, SearchData.color,
            SearchData.casketName, 0, "", "");

    ArrayList<Casket> caskets = new ArrayList<Casket>();
    ArrayList<Casket> removeCaskets = new ArrayList<Casket>();

    caskets.add(new Casket("wood", "Brown", "Casket 1", 2000,
            "http://friendlyfuneralista.files.wordpress.com/2010/02/casket2.jpg", ""));
    caskets.add(new Casket("18 gauge", "Silver", "Casket 2", 3000,
            "http://www.casketshowcase.com/caskets/casket/lacy-18gauge-steel-casket.jpg", ""));
    caskets.add(new Casket("20 gauge", "Gold", "Casket 3", 4000,
            "http://www.bestpricecaskets.com/store/graphics/00000001/8525.jpg", ""));
    caskets.add(new Casket("Steel", "", "Casket 4", 5000,
            "http://www.colliercasket.com/In%20Gods%20Care480.jpg", ""));
    caskets.add(new Casket("Wood", "Brown", "Casket 5", 6000,
            "http://www.bestpricecaskets.com/store/graphics/00000001/8684.jpg", ""));

    for (Casket c : caskets) {

      if (!searchCasket.isEmpty() && !c.equals(searchCasket))
        removeCaskets.add(c);
      else {
        if (SearchData.priceFrom != null && SearchData.priceFrom != 0
                && c.getPrice() <= SearchData.priceFrom) {
          removeCaskets.add(c);
          continue;
        } else if (SearchData.priceTo != null && SearchData.priceTo != 0
                && c.getPrice() >= SearchData.priceTo) {
          removeCaskets.add(c);
          continue;
        }
      }
    }

    caskets.removeAll(removeCaskets);

    return caskets;
  }

  private class ImageDownloadThread extends Thread {

    @Override
    public void run() {

      Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

      downloadCount = 0;
      
      for (Casket c : caskets) {
        c.setImage(downloadBitmap(c.getUrl()));
        downloadCount++;
      }

    }

    Bitmap downloadBitmap(String url) {

      final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
      final HttpGet getRequest = new HttpGet(url);

      try {
        HttpResponse response = client.execute(getRequest);
        final int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
          Log.w("ImageDownloader", "Error " + statusCode + " while retrieving bitmap from " + url);
          return null;
        }

        final HttpEntity entity = response.getEntity();
        if (entity != null) {
          InputStream inputStream = null;
          try {
            inputStream = entity.getContent();
            
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inDither = true;
            o.inPurgeable = true;
            o.inInputShareable = true;
            
            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
          } finally {
            if (inputStream != null) {
              inputStream.close();
            }
            entity.consumeContent();
          }
        }
      } catch (Exception e) {
        // Could provide a more explicit error message for IOException or IllegalStateException
        getRequest.abort();
        Log.w("ImageDownloader", "Error while retrieving bitmap from " + url);
      } finally {
        if (client != null) {
          client.close();
        }
      }
      return null;
    }

  }

  public boolean downloadComplete() {

    if (downloadCount == caskets.size()){
      recycleBitmaps();
      return true;
    }
    return false;
  }

  
  public void recycleBitmaps() {
    for (Casket c : caskets) {
        ((Bitmap) c.getImage()).recycle();
    }
}
  
  private static class SearchData {

    static String extMaterial = "";

    static String color = "";

    static String casketName = "";

    static Double priceFrom = (double) 0;

    static Double priceTo = (double) 0;

  }

}
