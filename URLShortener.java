package Hashmap;
import java.util.HashMap;

public class URLShortener {
    private HashMap<String, String> urlMap;

    public URLShortener() {
        this.urlMap = new HashMap<>();
    }

    public String shortenURL(String originalURL) {
        String shortURL = generateShortURL();
        urlMap.put(shortURL, originalURL);
        return shortURL;
    }

    public String redirectToOriginalURL(String shortURL) {
        return urlMap.getOrDefault(shortURL, "URL not found");
    }

    private String generateShortURL() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        
        String originalURL = "https://github.com/ParneetKaur7";
        String shortURL = urlShortener.shortenURL(originalURL);
        System.out.println("Shortened URL: " + shortURL);
        
        String redirectedURL = urlShortener.redirectToOriginalURL(shortURL);
        System.out.println("Redirected URL: " + redirectedURL);
    }
}
