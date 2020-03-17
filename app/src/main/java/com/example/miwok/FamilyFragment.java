package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {

    MediaPlayer family_audio;

    private AudioManager family_audioManager;

    AudioManager.OnAudioFocusChangeListener family_OnAudioFocusChangeListener=new
            AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {


                    if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){

                        family_audio.pause();
                        family_audio.seekTo(0);


                    }

                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        family_audio.start();
                    }

                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.word, container, false);

        //CODE INDISPENSABLE POUR LE SERVICE AUDIO D'ANDROID POUR LA REQUETTE DU AUDIO FOCUS
        family_audioManager=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> familyMemberArrayList =new ArrayList<Word>();

        //Creating 10 instances of Word from the Word Class to store them in the array list above
        Word familyMember_1= new Word(R.drawable.family_father,"father","әpә"
                ,R.raw.family_father);
        Word familyMember_2= new Word(R.drawable.family_mother,"mother","әṭa"
                ,R.raw.family_mother);
        Word familyMember_3= new Word(R.drawable.family_son,"son","angsi"
                ,R.raw.family_son);
        Word familyMember_4= new Word(R.drawable.family_daughter,"daughter","tune"
                ,R.raw.family_daughter);
        Word familyMember_5= new Word(R.drawable.family_older_brother,"older brother","taachi"
                ,R.raw.family_older_brother);
        Word familyMember_6= new Word(R.drawable.family_younger_brother,"younger brother"
                ,"chalitti",R.raw.family_younger_brother);
        Word familyMember_7= new Word(R.drawable.family_older_sister,"older sister"
                ,"teṭe",R.raw.family_older_sister);
        Word familyMember_8= new Word(R.drawable.family_younger_sister,"younger sister"
                ,"kolliti",R.raw.family_younger_sister);
        Word familyMember_9= new Word(R.drawable.family_grandmother,"grandmother"
                ,"ama",R.raw.family_grandmother);
        Word familyMember_10= new Word(R.drawable.family_grandfather,"grandfather"
                ,"paapa",R.raw.family_grandfather);

        // Storing instances created from the Word Class in the array list
        // created above to store Word Class instances
        familyMemberArrayList.add(familyMember_1);
        familyMemberArrayList.add(familyMember_2);
        familyMemberArrayList.add(familyMember_3);
        familyMemberArrayList.add(familyMember_4);
        familyMemberArrayList.add(familyMember_5);
        familyMemberArrayList.add(familyMember_6);
        familyMemberArrayList.add(familyMember_7);
        familyMemberArrayList.add(familyMember_8);
        familyMemberArrayList.add(familyMember_9);
        familyMemberArrayList.add(familyMember_10);

        WordAdapter adapter= new WordAdapter(getActivity(), familyMemberArrayList,R.color.category_family);
        ListView list =(ListView) rootView.findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=familyMemberArrayList.get(position);
                releaseMediaPlayer();
                int result =family_audioManager.requestAudioFocus(
                        family_OnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    family_audio = MediaPlayer.create(getContext(), word.getAudioResourceId());

                    family_audio.start();

                    family_audio.setOnCompletionListener(onCompletionListener);

                }

            }
        });
return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (family_audio != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            family_audio.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            family_audio = null;
            family_audioManager.abandonAudioFocus(family_OnAudioFocusChangeListener);
        }
    }
}
