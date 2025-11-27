import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// todo: complete the implementation of the Ad, AdRequest, and AdNetwork classes

class Ad implements Comparable<Ad>{
    String id;
    String category;
    double bidValue;
    double ctr;
    String content;

    int totalScore;

    public Ad(String id, String category, double ctr, double bidValue, String content) {
        this.id = id;
        this.category = category;
        this.ctr = ctr;
        this.bidValue = bidValue;
        this.content = content;
        this.totalScore=0;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getBidValue() {
        return bidValue;
    }

    public double getCtr() {
        return ctr;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("%s %s (bid=%.2f, ctr=%.2f%%) %s" , id, category, bidValue, ctr*100, content);
    }

    @Override
    public int compareTo(Ad o) {
        int cmp = Double.compare(o.bidValue, this.bidValue);
        if(cmp != 0){
            return cmp;
        }
        return this.id.compareTo(o.id);
    }

    public int getTotalScore() {
        return totalScore;
    }
}

class AdRequest{
    String id;
    String category;
    double floorBid;
    String keywords; // with empty space

    public AdRequest(String id, String category, double floorBid, String keywords) {
        this.id = id;
        this.category = category;
        this.floorBid = floorBid;
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public double getFloorBid() {
        return floorBid;
    }

    public String getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return id + " [" + category + "] (" +"floor=" + floorBid + "): " + keywords;
    }

}


class AdNetwork {
    ArrayList<Ad> ads;

    public AdNetwork() {
        ads = new ArrayList<>();
    }

    public void readAds(BufferedReader reader) throws IOException {

        while(reader.ready()){
            String [] parts = reader.readLine().split(" ");

            if(parts.length>1){
                String id = parts[0];
                String cat = parts[1];
                double bidVal = Double.parseDouble(parts[2]);
                double ctr = Double.parseDouble(parts[3]);
                String content="";
                content+=parts[4];
                for(int i=5; i < parts.length;i++){
                    content += " "+parts[i];
                }

                Ad ad = new Ad(id, cat,ctr, bidVal, content);
                ads.add(ad);
            }else break;
        }
    }

    public List placeAds(BufferedReader reader, int k, PrintWriter writer) throws IOException {
        String[] parts = reader.readLine().split(" ");

        String id = parts[0];
        String cat = parts[1];
        double floorBid = Double.parseDouble(parts[2]);
        String keywords = "";
        keywords += parts[3];
        for (int i = 4; i < parts.length; i++) {
            keywords += " " + parts[i];
        }

        AdRequest req = new AdRequest(id, cat, floorBid, keywords);
        List<Ad> eligibleAds = new ArrayList<>();

        double x = 5.0;
        double y = 100.0;

        for (Ad ad : ads) {
            if (ad.bidValue >= floorBid) {
                ad.totalScore = relevanceScore(ad, req) + (int)(x * ad.bidValue + y * ad.ctr);
                eligibleAds.add(ad);
            }
        }

        List<Ad> topAds = eligibleAds.stream()
                .sorted(Comparator.comparingInt(Ad::getTotalScore).reversed())
                .limit(k)
                .collect(Collectors.toList());

        topAds.sort(Comparator.naturalOrder());

        System.out.println("Top ads for request " + req.getId() + ":");
        topAds.forEach(System.out::println);

        return topAds;
    }


    private int relevanceScore(Ad ad, AdRequest req) {
        int score = 0;
        if (ad.getCategory().equalsIgnoreCase(req.getCategory())) score += 10;
        String[] adWords = ad.getContent().toLowerCase().split("\\s+");
        String[] keywords = req.getKeywords().toLowerCase().split("\\s+");
        for (String kw : keywords) {
            for (String aw : adWords) {
                if (kw.equals(aw)) score++;
            }
        }
        return score;
    }
}

public class lab3_zad2 {
    public static void main(String[] args) throws IOException {
        AdNetwork network = new AdNetwork();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine().trim());

        if (k == 0) {
            network.readAds(br);
            network.placeAds(br, 1, pw);
        } else if (k == 1) {
            network.readAds(br);
            network.placeAds(br, 3, pw);
        } else {
            network.readAds(br);
            network.placeAds(br, 8, pw);
        }

        pw.flush();
    }
}