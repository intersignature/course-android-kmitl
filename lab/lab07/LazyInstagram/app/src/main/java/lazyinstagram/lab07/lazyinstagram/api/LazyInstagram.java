package lazyinstagram.lab07.lazyinstagram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by student on 10/6/2017 AD.
 */

public interface LazyInstagram {
    public String base = "https://us-central1-retrofit-course.cloudfunctions.net";
    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String userName);
}
