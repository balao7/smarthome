/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.ui.webapp.internal.render;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.smarthome.ui.webapp.render.RenderException;
import org.eclipse.smarthome.ui.webapp.render.WidgetRenderer;
import org.eclipse.smarthome.model.sitemap.Webview;
import org.eclipse.smarthome.model.sitemap.Widget;

/**
 * This is an implementation of the {@link WidgetRenderer} interface, which
 * can produce HTML code for Webview widgets.
 * 
 * @author Kai Kreuzer - Initial contribution and API
 *
 */
public class WebviewRenderer extends AbstractWidgetRenderer {
	
	/**
	 * {@inheritDoc}
	 */
	public boolean canRender(Widget w) {
		return w instanceof Webview;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public EList<Widget> renderWidget(Widget w, StringBuilder sb) throws RenderException {
		Webview webview = (Webview) w;
		String snippet = getSnippet("webview");			

		int height = webview.getHeight();
		if(height==0) {
			height = 1;
		}
		
		snippet = StringUtils.replace(snippet, "%url%", webview.getUrl());
		snippet = StringUtils.replace(snippet, "%height%", Integer.toString(height*36));
		
		sb.append(snippet);
		return null;
	}
}
