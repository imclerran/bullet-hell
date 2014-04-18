package com.logicalfallacy.shooter00;

import android.os.Bundle;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useImmersiveMode =true;
        cfg.useGL20 = false;
        initialize(new MyGdxGame(), cfg);
    }

    public void onStartButtonClick(View view) 
    {
		//AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        //cfg.useGL20 = false;
		//initialize(new MyGdxGame(), cfg);

	}
}
