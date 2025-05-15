package com.yash.women_safety_app.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.yash.women_safety_app.fragments.ClubsFragment;
import com.yash.women_safety_app.fragments.PopularFragment;
import com.yash.women_safety_app.fragments.YouFragment;

public class CommunityPagerAdapter extends FragmentStateAdapter {

    public CommunityPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PopularFragment();
            case 1:
                return new YouFragment();
            case 2:
                return new ClubsFragment();
            default:
                return new PopularFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
} 