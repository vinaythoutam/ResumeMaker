package com.example.vinay.resumebuilder.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PersonalInfoFragment tab1 = new PersonalInfoFragment();
                return tab1;
            case 1:
                CareerObjectiveFragment tab2 = new CareerObjectiveFragment();
                return tab2;
            case 2:
                AcademicInfoFragment tab3 = new AcademicInfoFragment();
                return tab3;
            case 3:
                ExperienceFragment tab4 = new ExperienceFragment();
                return tab4;
            case 4:
                AcheivementsFragment tab5 = new AcheivementsFragment();
                return tab5;
            case 5:
                StrengthsHobbiesFragment tab6 = new StrengthsHobbiesFragment();
                return tab6;
            case 6:
                SkillsFragment tab7 = new SkillsFragment();
                return tab7;
            case 7:
                DeclarationFragment tab8 = new DeclarationFragment();
                return tab8;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}