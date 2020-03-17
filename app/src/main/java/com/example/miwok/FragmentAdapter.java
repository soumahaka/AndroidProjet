package com.example.miwok;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

//*****************************************************************************//

// ADAPTEUR PERSONNALISÉ POUR GERER LES CLASSES FRAGMENT

public class FragmentAdapter extends FragmentPagerAdapter {

    // LE CONTEXT(LA CLASSE QU'IL VA GERER NOTAMMENT)
    private Context mContext;

    public FragmentAdapter(Context context,FragmentManager fm) {

        super(fm);
        this.mContext=context;
    }

    // REDEFINITION DE LA METHODE GET ITEM POUR RECUPER LES BALAYEMENTS DE L'UTILISATEUR
    // ET LUI AFFICHER LES FRAGMENTS QUI CORRESPONDENT NOTAMMENT C'EST ICI QU'ON UTILISE LES
    //DIFFERENTS FRAGMENTS CREÉS

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new ColorsFragment();
        } else if(position==2) {
            return new FamilyFragment();
        }
        else
            return new PhrasesFragment();
    }

    //REDEFINITIONS DE LA METHODE GET COUNT POUR DIRE AU FRAGMENT ADAPTEUR
    // QUE NOUS AVONS 4 FRAGMENTS A GERER

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable

    // REDEFINITION DE LA METHODE GET PAGE TITLE POUR AFFICHER DES STRINGS DANS LA TAB LAYOUT AVEC
    // LA METHODE GET STRING
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_colors);
        } else if (position == 2) {
            return mContext.getString(R.string.category_family);
        } else {
            return mContext.getString(R.string.category_phrases);
        }
    }
    }
