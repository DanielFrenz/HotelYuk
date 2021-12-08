package com.example.hotelyuk.ui.nav.accounts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hotelyuk.R;
import com.example.hotelyuk.adapter.RVBantuanAdapter;
import com.example.hotelyuk.data.DaftarBantuan;
import com.example.hotelyuk.entity.HelpData;

import java.util.List;

public class PusatBantuanActivity extends AppCompatActivity {
    RecyclerView rvBantuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pusat_bantuan);

        setTitle("Pusat Bantuan");

        rvBantuan = findViewById(R.id.rv_item_bantuan);

        RVBantuanAdapter adapter = new RVBantuanAdapter(getListBantuan());
        rvBantuan.setAdapter(adapter);
        rvBantuan.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private List<HelpData> getListBantuan() {
        List<HelpData> listData = new DaftarBantuan().daftarBantuan;
        return listData;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}