package com.example.connectpie;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connectpie.Entities.Friendlist_withstatus;
import com.example.connectpie.Entities.postsEntities;
import com.example.connectpie.databinding.FragmentHomeBinding;
import com.example.connectpie.databinding.ProfilesAndStatusLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Friendlist_withstatus> Friendlist_withStatus;
    List<postsEntities> postlist;

    FragmentHomeBinding binding;



    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        Friendlist_withStatus = new ArrayList<>();
        postlist = new ArrayList<>();

        DefualtFriendlist();
        defaultPost();

        binding.listViewStatusView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.postRCview.setLayoutManager(new LinearLayoutManager(getContext()));

        RecycleView RCviewForStatusList = new RecycleView(Friendlist_withStatus);
        binding.listViewStatusView.setAdapter(RCviewForStatusList);

        RecycleViewPosts AdapterForPost = new RecycleViewPosts(postlist);
        binding.postRCview.setAdapter(AdapterForPost);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void defaultPost(){
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));

        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));
        postlist.add(new postsEntities("susheel verma","susheel12","1234 likes","no caption"));


    }


    public void DefualtFriendlist(){
        Friendlist_withStatus.add(new Friendlist_withstatus("susheel121","susheel","active"));
        Friendlist_withStatus.add(new Friendlist_withstatus("Kapil12","Kapil Sharma","inactive"));
        Friendlist_withStatus.add(new Friendlist_withstatus("devendu12","devendu sharma","active"));
        Friendlist_withStatus.add(new Friendlist_withstatus("susheel1234","susheel verma","inctive"));
        Friendlist_withStatus.add(new Friendlist_withstatus("susheel121","susheel","active"));
        Friendlist_withStatus.add(new Friendlist_withstatus("Kapil12","Kapil Sharma","inactive"));
        Friendlist_withStatus.add(new Friendlist_withstatus("devendu12","devendu sharma","active"));
        Friendlist_withStatus.add(new Friendlist_withstatus("susheel1234","susheel verma","active"));
        Friendlist_withStatus.add(new Friendlist_withstatus("susheel121","susheel","active"));
        Friendlist_withStatus.add(new Friendlist_withstatus("Kapil12","Kapil Sharma","inactive"));
        Friendlist_withStatus.add(new Friendlist_withstatus("devendu12","devendu sharma","active"));
        Friendlist_withStatus.add(new Friendlist_withstatus("susheel1234","susheel verma","active"));

    }
    class ViewHolderForstatusview extends RecyclerView.ViewHolder{
        ImageView image;
        CardView status;
        TextView textviewName;




        public ViewHolderForstatusview(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageDp);
            status = itemView.findViewById(R.id.status_indigator);
            textviewName = itemView.findViewById(R.id.PSLUsersname);


        }
    }
    class ViewHolderPosts extends RecyclerView.ViewHolder{
        TextView username,Likes,caption,username_withcaption;
        public ViewHolderPosts(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.usernametop);
            Likes = itemView.findViewById(R.id.tvuserlikes);
            caption = itemView.findViewById(R.id.captionshowtv);
            username_withcaption = itemView.findViewById(R.id.tvuseridshowbottom);
        }
    }

    class RecycleViewPosts extends RecyclerView.Adapter<ViewHolderPosts>{
        List<postsEntities> list;

        public RecycleViewPosts(List<postsEntities> list) {
            this.list = list;

        }

        @NonNull
        @Override
        public ViewHolderPosts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.post_layouts,null);
            ViewHolderPosts viewholder = new ViewHolderPosts(view);

            return viewholder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderPosts holder, int position) {
                postsEntities obj = list.get(position);
                holder.username.setText(obj.getUsername());
                holder.Likes.setText(obj.getLikes());
                holder.caption.setText(obj.getCaptions());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
    class RecycleView extends RecyclerView.Adapter<ViewHolderForstatusview>{
        List<Friendlist_withstatus> Friendlistl;

        public RecycleView(List<Friendlist_withstatus> Fri) {
            this.Friendlistl = Fri;
        }

        @NonNull
        @Override
        public ViewHolderForstatusview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.profiles_and_status_layout,null);

            ViewHolderForstatusview viewholder = new ViewHolderForstatusview(view);

            return viewholder;
        }


        @Override
        public void onBindViewHolder(@NonNull ViewHolderForstatusview holder, int position) {
            Friendlist_withstatus obj = Friendlistl.get(position);
            holder.textviewName.setText(obj.getName());
            if(obj.getStatus().equals("inactive")){
                holder.status.setBackgroundColor(getResources().getColor(R.color.black,null));
            }
        }

        @Override
        public int getItemCount() {
            return Friendlistl.size() ;
        }
    }
}
