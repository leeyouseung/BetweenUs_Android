package org.techtown.betweenus_android.manager;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

/**
 *  @author 우주 최강 천재 건우
 */
public class StudyManager extends ContextWrapper {

    public StudyManager(Context context) {
        super(context);
    }

    private Boolean studyManager;

    public void setStudyManager(Integer studyIdx, Boolean studyManager) {

        SharedPreferences sharedPreferences = getSharedPreferences("betweenus",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(Integer.toString(studyIdx), studyManager);

        editor.commit();
    }

    public Boolean getStudyManager(Integer studyIdx) {

        SharedPreferences sharedPreferences = getSharedPreferences("betweenus",MODE_PRIVATE);

        studyManager = sharedPreferences.getBoolean(Integer.toString(studyIdx),false);

        return studyManager;

    }
}
