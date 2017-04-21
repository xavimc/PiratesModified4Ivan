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

import android.graphics.Canvas;
import com.gorgo.pirates.controller.GameEngine;


public interface Renderer {
	public void render(Canvas canvas, GameEngine gameEngine);
}
