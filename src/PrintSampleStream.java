import twitter4j.*;

/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class PrintSampleStream {
    /**
     * Main entry of this application.
     *
     * @param args
     */
	
	
    public static void main(String[] args) throws TwitterException {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
            	User user = status.getUser();   
            	if(user.isGeoEnabled()){
            	URLEntity[] urls=status.getURLEntities();
            	
            	if(urls[0] != null){
            		String displayURL=urls[0].getDisplayURL();
            		String subURL=displayURL.substring(0, 3);
         
            		if(subURL.equals("4sq")){
        
                String username = user.getScreenName();
                String profileLocation = user.getLocation();
                long tweetId = status.getId();
                GeoLocation geo= status.getGeoLocation();
                
                System.out.println("UTENTE: " + username + " ID: "+tweetId+ " LOC: " + profileLocation );
                if(geo != null)
                System.out.println("#: " + geo.getLatitude() + "," + geo.getLongitude());
                
                String content = status.getText();
                System.out.println("TWEET: " + content +"\n");
                System.out.println("URLS: " + urls[0].getDisplayURL() +"\n");
            	
            		}
            	}
            }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.sample();
        FilterQuery filtro=new FilterQuery();
        String[] arrayQuery={"4sq"}; 
        filtro.track(arrayQuery);
        twitterStream.filter(filtro);
    }
}