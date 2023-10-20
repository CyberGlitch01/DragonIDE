package com.dragon.ide.ui.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.dragon.ide.databinding.LayoutBlocksHolderListItemBinding;
import com.dragon.ide.objects.BlocksHolder;
import java.util.ArrayList;

public class BlocksHolderAdapter extends RecyclerView.Adapter<BlocksHolderAdapter.ViewHolder> {

  public ArrayList<BlocksHolder> list;
  public Activity activity;

  public BlocksHolderAdapter(ArrayList<BlocksHolder> _arr, Activity activity) {
    list = _arr;
    this.activity = activity;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutBlocksHolderListItemBinding item =
        LayoutBlocksHolderListItemBinding.inflate(activity.getLayoutInflater());
    View _v = item.getRoot();
    RecyclerView.LayoutParams _lp =
        new RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    _v.setLayoutParams(_lp);
    return new ViewHolder(_v);
  }

  @Override
  public void onBindViewHolder(ViewHolder _holder, int _position) {
    LayoutBlocksHolderListItemBinding binding =
        LayoutBlocksHolderListItemBinding.bind(_holder.itemView);
    binding.holderName.setText(list.get(_position).getName());
    binding.color.setBackgroundColor(Color.parseColor(list.get(_position).color));
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View v) {
      super(v);
    }
  }
}