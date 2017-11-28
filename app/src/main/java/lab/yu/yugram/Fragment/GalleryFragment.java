package lab.yu.yugram.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;

import lab.yu.yugram.Activity.NextActivity;
import lab.yu.yugram.Activity.PostActivity;
import lab.yu.yugram.Adapter.UploadAdapter;
import lab.yu.yugram.R;
import lab.yu.yugram.Utils.FileSearch;


public class GalleryFragment extends Fragment {



    private TextView textView;
    FragmentManager fm;
    private static final int NUM_GRID_COLUMNS = 3;
    private GridView gridView;
    private ImageView galleryImage;
    private UploadAdapter uploadAdapter;
    private ListView listView;
    private Spinner spinner;
    private ProgressBar progressBar;
    private ArrayList<String> listImage = new ArrayList<String>();
    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();
    private String mSelectedImage;
    public String PICTURES = ROOT_DIR + "/Pictures/";
    public String CAMERA = ROOT_DIR + "/DCIM/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryImage = (ImageView) view.findViewById(R.id.galleryImageView);
        gridView = (GridView) view.findViewById(R.id.gridView);
        spinner = (Spinner) view.findViewById(R.id.spinner);
/*        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);*/

        ImageView uploadClose = (ImageView) view.findViewById(R.id.ivCloseShare);
        uploadClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.tvNext);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Fragment Gallery", "onClick: navigating to the final share screen.");
            }
        });




        init();

        TextView nextScreen = (TextView) view.findViewById(R.id.tvNext);
        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NextActivity.class);
                intent.putExtra("selected_image", mSelectedImage);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void init(){
        if(FileSearch.getDirectoryPaths(PICTURES) != null){
            listImage = FileSearch.getDirectoryPaths(PICTURES);
        }
        Log.d("Dai","haizzzz");
        ArrayList<String> listImageName = new ArrayList<>();
        for(int i = 0; i < listImage.size(); i++){
            int index = listImage.get(i).lastIndexOf("/");
            index++;
            String string = listImage.get(i).substring(index);
            listImageName.add(string);

        }

        for(int j = 0; j < listImageName.size(); j++){
            Log.d("Dai", listImageName.get(j).toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listImageName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //setup our image grid for the directory chosen
                setupGridView(listImage.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void setupGridView(String selectedDirectory){
        final ArrayList<String> imgURLs = FileSearch.getFilePaths(selectedDirectory);

        //set the grid column width


        //use the grid adapter to adapter the images to gridview
        UploadAdapter adapter = new UploadAdapter(getActivity(), imgURLs, R.layout.item_image_upload);
        gridView.setAdapter(adapter);

        mSelectedImage = imgURLs.get(0);
        Glide.with(getActivity()).load(imgURLs.get(0)).apply(RequestOptions.placeholderOf(R.mipmap.ic_camera)).into(galleryImage);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Glide.with(getActivity()).load(imgURLs.get(position)).apply(RequestOptions.placeholderOf(R.mipmap.ic_camera)).into(galleryImage);
                mSelectedImage = imgURLs.get(position);
            }
        });

    }


}
