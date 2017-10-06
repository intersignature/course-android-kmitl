package lazyinstagram.lab07.lazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lazyinstagram.lab07.lazyinstagram.adapter.PostAdapter;
import lazyinstagram.lab07.lazyinstagram.api.LazyInstagram;
import lazyinstagram.lab07.lazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("cartoon");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);
    }

    private void getUserProfile(String usrName) {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(LazyInstagram.base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LazyInstagram lazyInstagram = retrofit.create(LazyInstagram.class);
        Call<UserProfile> call = lazyInstagram.getProfile(usrName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    UserProfile userProfile = response.body();
                    TextView TextUser = (TextView) findViewById(R.id.user);
                    TextUser.setText("@"+userProfile.getUser());

                    TextView TextFollower = (TextView) findViewById(R.id.followerCnt);
                    TextFollower.setText("Follower\n"+userProfile.getFollower());

                    TextView TextFollowing = (TextView) findViewById(R.id.followingCnt);
                    TextFollowing.setText("Following\n"+userProfile.getFollowing());

                    TextView TextPost = (TextView) findViewById(R.id.postCnt);
                    TextPost.setText("Post\n"+userProfile.getPost());

                    TextView TextBio = (TextView) findViewById(R.id.bio);
                    TextBio.setText(userProfile.getBio());

                    ImageView imageProfile = (ImageView) findViewById(R.id.imageView);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
}
