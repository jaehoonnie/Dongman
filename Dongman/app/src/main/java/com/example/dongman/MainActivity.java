package com.example.dongman;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Post> postsNew = new ArrayList<>();
    private final List<Post> postsBeginner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seed();                         // 더미 3개씩 생성

        LayoutInflater inf = LayoutInflater.from(this);
        fillSection(findViewById(R.id.list_new),      postsNew,      inf);
        fillSection(findViewById(R.id.list_beginner), postsBeginner, inf);

        // 하단바 아무 곳이나 터치하면 로그인 화면 이동
        findViewById(R.id.bottomNav)
                .setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }

    private void fillSection(LinearLayout target, List<Post> data, LayoutInflater inf) {
        for (Post p : data) {
            View row = inf.inflate(R.layout.item_temp_row, target, false);
            ((TextView) row.findViewById(R.id.row_title)).setText(p.title);
            ((TextView) row.findViewById(R.id.row_meta)).setText(p.meta);
            ((TextView) row.findViewById(R.id.row_location)).setText(p.location);
            ((ImageView) row.findViewById(R.id.row_thumb)).setImageResource(p.imageRes);

            row.setOnClickListener(v -> {
                Intent i = new Intent(this, DetailActivity.class);
                i.putExtra("post", p);
                startActivity(i);
            });
            target.addView(row);
        }
    }

    private void seed() {
        for (int i = 0; i < 3; i++) {
            Post sample = new Post();
            sample.title     = "배드민턴 모임";
            sample.meta      = "모집 1/10   조회수 : 1";
            sample.location  = "충북대학교";
            sample.imageRes  = R.drawable.placeholder_thumbnail;
            postsNew.add(sample);
            postsBeginner.add(sample);
        }
    }
}
