package lazyinstagram.lab07.lazyinstagram;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private Spinner select_id_spin;
    Context context = this;
    private String user;
    private String type_show = "grid";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        select_id_spin = (Spinner) findViewById(R.id.spinner);
        final String[] id_array = getResources().getStringArray(R.array.id_array);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, id_array);
        select_id_spin.setAdapter(adapter);

        select_id_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user = id_array[position];
                getUserProfile(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





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

                    PostAdapter postAdapter = new PostAdapter(context, userProfile );
                    postAdapter.setType_show(type_show);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    //recyclerView.setLayoutManager(new LinearLayoutManager(context));


                    if(type_show.equals("grid")){
                        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));}
                    if(type_show.equals("list")){
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }
                    recyclerView.setAdapter(postAdapter);

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    public void onGrid(View view) {
        getUserProfile(user);
        type_show = "grid";

    }

    public void onList(View view) {
        getUserProfile(user);
        type_show = "list";

    }
}
