package net.yazeed44.imagepicker.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yazeed44.imagepicker.library.R;
import net.yazeed44.imagepicker.util.Events;
import net.yazeed44.imagepicker.util.Picker;

import de.greenrobot.event.EventBus;


/**
 * Created by yazeed44 on 11/23/14.
 */
public class ImagesThumbnailFragment extends Fragment {

    protected RecyclerView mImagesRecycler;
    protected Picker mPickOptions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mImagesRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_image_browse, container, false);

        setupRecycler();

        mPickOptions = EventBus.getDefault().getStickyEvent(Events.OnPublishPickOptionsEvent.class).options;

        return mImagesRecycler;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    protected void setupRecycler() {

        mImagesRecycler.setHasFixedSize(true);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.num_columns_images));
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mImagesRecycler.setLayoutManager(gridLayoutManager);


    }


    public void onEvent(final Events.OnClickAlbumEvent event) {
        mImagesRecycler.setAdapter(new ImagesThumbnailAdapter(event.albumEntry, mImagesRecycler, mPickOptions));
    }


    public void onEvent(final Events.OnAttachFabEvent fabEvent) {
        fabEvent.fab.attachToRecyclerView(mImagesRecycler);
    }


}
