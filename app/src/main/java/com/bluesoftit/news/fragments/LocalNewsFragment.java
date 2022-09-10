package com.bluesoftit.news.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bluesoftit.news.R;
import com.bluesoftit.news.adapters.BreakingNewsAdapter;
import com.bluesoftit.news.adapters.CardModelThumbnailAdapter;
import com.bluesoftit.news.adapters.CardModelVerticalAdapter;
import com.bluesoftit.news.adapters.NewsAdapter;
import com.bluesoftit.news.databinding.FragmentLocalNewsBinding;
import com.bluesoftit.news.models.ItemModel;
import com.bluesoftit.news.models.NewsModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LocalNewsFragment extends Fragment {

    public LocalNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentLocalNewsBinding binding;
    ArrayList<NewsModel> newsModels;
    BreakingNewsAdapter breakingNewsAdapter;
    ProgressDialog dialog;
    ArrayList<ItemModel> models;
    NewsAdapter newsAdapter;
    CardModelVerticalAdapter verticalAdapter;
    CardModelThumbnailAdapter thumbnailAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocalNewsBinding.inflate(inflater, container, false);
        // All codes are goes there ---------------->
        setCard();


        return binding.getRoot();
    }
    private void setCard(){
        newsModels = new ArrayList<>();
        binding.cardContainer.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        breakingNewsAdapter = new BreakingNewsAdapter(getContext(), newsModels);
        binding.cardContainer.setAdapter(breakingNewsAdapter);
        String country = "us";

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String api = "https://newsapi.org/v2/top-headlines?country=us&apiKey=19068b0e72d74797aaefbff18dd3b500";
        StringRequest request = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), "Massage: "+response, Toast.LENGTH_SHORT).show();
                       /* try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals("ok")){
                                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                                for (int i = 0; i<jsonArray.length();i++){
                                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                                    NewsModel newsModel = new NewsModel(
                                        jsonObj.getString("author"),
                                        jsonObj.getString("title"),
                                        jsonObj.getString("description"),
                                        jsonObj.getString("url"),
                                        jsonObj.getString("urlToImage"),
                                        jsonObj.getString("publishedAt"),
                                        jsonObj.getString("content")
                                    );
                                    newsModels.add(newsModel);

                                }
                                breakingNewsAdapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(getContext(), "There was some error!", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(getContext(), "Error: "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error: "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

    }
    private void setLatestNews(){
       /* models = new ArrayList<>();
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        binding.latestNewsContainer.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        newsAdapter = new NewsAdapter(getContext(),newsModels);
        binding.latestNewsContainer.setAdapter(newsAdapter);*/
    }
    private void setSportsNews(){
      /*  models = new ArrayList<>();
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        binding.sportsContainer.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        verticalAdapter = new CardModelVerticalAdapter(getContext(),models);
        binding.sportsContainer.setAdapter(verticalAdapter);*/
    }

    private void setEntertainmentNews(){
      /*  models = new ArrayList<>();
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        models.add(new ItemModel("Stock futures are flat as Nasdaq tries to recover from 7-day losing streak - CNBC","Stock futures were flat Wednesday morning after the major averages added to weeks of losses.","https://image.cnbcfm.com/api/v1/image/107114117-1662477628431-trad.jpg?v=1662477646&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Chelsea sack Thomas Tuchel as manager after poor start to season - ESPN","Chelsea's new owners have sacked head coach Thomas Tuchel just three months after completing their takeover of the club.","https://a1.espncdn.com/combiner/i?img=%2Fphoto%2F2022%2F0906%2Fr1058155_1296x729_16%2D9.jpg","2022-09-08"));
        models.add(new ItemModel("Deutsche Bank CEO warns recession is inevitable, says Germany must cut reliance on China - CNBC","Deutsche Bank CEO Christian Sewing has warned that a recession in Germany is inevitable, and urged the country's leaders to accelerate its decoupling from China.","https://image.cnbcfm.com/api/v1/image/106351008-1579779062182rts2zis3.jpg?v=1662536695&w=1920&h=1080","2022-09-08"));
        models.add(new ItemModel("Putin calls for review of Ukraine grain deal, accuses West of deception - Reuters","President Vladimir Putin said on Wednesday he wanted to discuss reopening a U.N.-brokered deal that allows Ukraine to export its grain via the Black Sea after accusing Kyiv and the West of using it to deceive developing countries and Russia.","https://www.reuters.com/resizer/3BkyabFOBUVUFYluMNVtR-Osx_g=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VZQ5KHKTCRKCHJMXHCZ2JZDSJE.jpg","2022-09-08"));
        models.add(new ItemModel("Sony's new PS5 has been totally redesigned inside and uses less power - The Verge","Sony has launched a new PS5 model and it has a totally overhauled design inside. The new PS5 is lighter and draws less power during gaming, too.","https://cdn.vox-cdn.com/thumbor/kspgK_1d7vkQxdzAmWT9flEWVkg=/0x32:1597x868/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/24001582/RxIiQhY.jpeg","2022-09-08"));
        models.add(new ItemModel("Myles Sanderson Made Final 'Goodbye Trip' After Saskatchewan Stabbing Rampage, Sources Say - The Daily Beast","Sources told The Daily Beast that Myles Sanderson, a suspect in the stabbing spree that killed 11, made a trip to say goodbye to loved ones “for the last time” after the attacks.","https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_1688,w_3000,x_0,y_0/dpr_2.0/c_limit,w_740/fl_lossy,q_auto/v1662529144/220906-saskatchewan-murders-hero_huvdwd","2022-09-08"));
        models.add(new ItemModel("Donald Trump has at least 19 different attorneys dealing with 8 investigations. - POLITICO","The various legal problems have created the need for a lot of legal help.","https://static.politico.com/d3/22/f46119cf43e49f5266be69f776ed/election-2022-trump-76129.jpg","2022-09-08"));
        models.add(new ItemModel("Doomsday Glacier “Holding On by Its Fingernails” – Spine-Chilling Retreat Could Raise Sea Levels by 10 Feet - SciTechDaily","Faster in the Past: New seafloor images – the highest resolution of any taken off the West Antarctic Ice Sheet – upend understanding of Thwaites Glacier retreat. At times in its past, the retreat of the massive Thwaites Glacier was even quicker than it is tod…","https://scitechdaily.com/images/Thwaites-Glacier-Nathaniel-B-Palmer-scaled.jpg","2022-09-08"));
        models.add(new ItemModel("Bam Margera Spotted at Bar After Leaving Rehab, Cuts Communication with Parents - TMZ","Bam Margera bounced early from substance abuse treatment and hit up a tavern ... and he's no longer speaking to his parents.","https://imagez.tmz.com/image/d4/16by9/2022/09/06/d4f9c5c98d224dbcae6e4774eed276bd_xl.jpg","2022-09-08"));
        models.add(new ItemModel("Ukraine backs UN call for Zaporizhzhia nuclear plant safe zone - BBC News - BBC News","Ukrainian President Volodymyr Zelensky has backed calls by the UN for a safety protection zone at the Zaporizhzhia nuclear power plant.The plant has been occ...","https://i.ytimg.com/vi/kIU9oB6wvC0/hqdefault.jpg","2022-09-08"));
        binding.entertainmentContainer.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        thumbnailAdapter = new CardModelThumbnailAdapter(getContext(),models);
        binding.entertainmentContainer.setAdapter(thumbnailAdapter);*/
    }
}