package com.example.yp01mobile.boottomNav;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.yp01mobile.boottomNav.MenuFragment.DrinksFragment;
import com.example.yp01mobile.boottomNav.MenuFragment.FoodsFragment;
import com.example.yp01mobile.boottomNav.MenuFragment.SauceFragment;
import com.example.yp01mobile.boottomNav.MenuFragment.SnacksFragment;

public class HomeAdapter extends FragmentStateAdapter {
    public HomeAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position)
        {
            case 0: return new FoodsFragment();
            case 1: return new DrinksFragment();
            case 2: return new SnacksFragment();
            case 3: return new SauceFragment();
            default: return new FoodsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
