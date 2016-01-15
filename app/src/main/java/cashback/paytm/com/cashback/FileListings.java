package cashback.paytm.com.cashback;

import java.util.ArrayList;

/**
 * Created by udit on 1/15/2016.
 */
public class FileListings {

    public ArrayList<String> listings = new ArrayList<>();

    public FileListings(){
        listings = new ArrayList<>();
        addToListings();
    }
    public void addToListings(){
        listings.add("Android");
        listings.add("DCIM");
        listings.add("legacy");
        listings.add("Primary");
    }
}
