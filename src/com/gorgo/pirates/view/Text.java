/*  Monkey Island Swordfighting is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Monkey Island Swordfighting in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    <http://www.gnu.org/licenses/> */

package com.gorgo.pirates.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.DynamicLayout;
import android.text.Editable;
import android.text.Layout;
import android.text.TextPaint;

/**
 * @author Gorgo
 * 
 *         Classe che inizializza e disegna le scritte sul video
 * 
 */

public class Text {

	private int DEFAULT_SIZE = 14; // 55 for Nexus 6 // 19 for default
	private int DEFAULT_SHADOWN_SIZE = 5;  // 10 for default
	private Context context;

	// Timing
	private boolean finished, initTime = true;
	private long timeShow, beginTime;

	// Posizione
	private float x = 0, y = 0;

	// Contenuto
	private String stringToShow;

	// Stile
	private int color;
	private Typeface chops;
	private TextPaint textPaint;

	// Layout
	private DynamicLayout dynamicLayout;
	private Editable edit;

	public Text(Context context) {
		this.context = context;

		/*if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
			DEFAULT_SIZE = 10;
			DEFAULT_SHADOWN_SIZE = 3;
		}*/
		float densityValue = context.getResources().getDisplayMetrics().density;

		chops = Typeface.createFromAsset(this.context.getAssets(),
				"Commodore.ttf");

		textPaint = new TextPaint();
		textPaint.setColor(color);
		textPaint.setTextSize(DEFAULT_SIZE * densityValue);
		textPaint.setShadowLayer(DEFAULT_SHADOWN_SIZE * densityValue, 5, 5, Color.BLACK);
		textPaint.setTypeface(chops);
	}

	public void draw(Canvas canvas) {

		canvas.save();
		if (initTime == true) { // Prima chiamata draw(), salvo beginTime
			finished = false;
			saveTime();
		}

		// System.out.println(System.currentTimeMillis()-beginTime + " < " +
		// timeShow);
		if (System.currentTimeMillis() - beginTime < timeShow) {

			canvas.translate(x, y); // Sposto la scritta in posizione (x,y)
			dynamicLayout.draw(canvas); // Disegno la scritta

		} else {

			initTime = true;
			finished = true;
		}
		canvas.restore();
	}

	// Imposta il colore alla scritta
	public void setColor(int color) {
		this.textPaint.setColor(color);
	}

	// Imposto la dimensione della scritta
	public void setSize(int i) {
		this.textPaint.setTextSize(i);
	}

	// Imposta il tempo di visualizzazione
	public void setTimeShow(long timeShow) {
		this.timeShow = timeShow;
	}

	// Salva il tempo attuale prima di far partire il timer
	private void saveTime() {
		beginTime = System.currentTimeMillis();
		initTime = false;
	}

	// Aggiorna la scritta da visualizzare
	public void setString(String stringToShow) {
		this.stringToShow = stringToShow;
		if (edit != null)
			edit.replace(0, edit.length(), stringToShow);
	}

	// Creo il DynamicLayout da visualizzare
	public void createLayout(Canvas canvas, int padding) {
		Editable.Factory fac = Editable.Factory.getInstance();
		edit = fac.newEditable(stringToShow);
		dynamicLayout = new DynamicLayout(edit, textPaint, canvas.getWidth()
				- padding, Layout.Alignment.ALIGN_CENTER, 1, 0, true);
	}

	// Controllo se è finito il tempo di visualizzazione
	public boolean getFinished() {
		return finished;
	}

	// Posiziono la scritta
	public void setXY(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public DynamicLayout getLayout() {
		return this.dynamicLayout;
	}
}
