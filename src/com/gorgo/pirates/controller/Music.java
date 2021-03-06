/*  Monkey Island Swordfighting is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Monkey Island Swordfighting in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    <http://www.gnu.org/licenses/> */

package com.gorgo.pirates.controller;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

/**
 * @author Gorgo
 * 
 * La classe si occupa di istanziare il MediaPlayer per la musica. Se � gi� stato inizializzato cambia la musica passata via ReferenceID
 * 
 */

public class Music{
	
	public static MediaPlayer changeMusic(Context context, MediaPlayer mPlayer, int id){
		AssetFileDescriptor afd = context.getResources()
				.openRawResourceFd(id);
		try {
			if(mPlayer == null){
				mPlayer = MediaPlayer.create(context, id);
				return mPlayer;
			}
			mPlayer.reset();
			mPlayer.setDataSource(afd.getFileDescriptor(),
					afd.getStartOffset(), afd.getDeclaredLength());
			mPlayer.prepare();
			mPlayer.start();
			afd.close();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mPlayer;
	}
}
