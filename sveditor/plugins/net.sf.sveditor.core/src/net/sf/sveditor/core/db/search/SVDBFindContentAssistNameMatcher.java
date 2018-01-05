/****************************************************************************
 * Copyright (c) 2008-2014 Matthew Ballance and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Ballance - initial implementation
 ****************************************************************************/


package net.sf.sveditor.core.db.search;

import net.sf.sveditor.core.db.ISVDBNamedItem;
import net.sf.sveditor.core.db.SVDBItemType;

public class SVDBFindContentAssistNameMatcher implements ISVDBFindNameMatcher {
	private SVDBItemType			fItemTypes[];
	
	public SVDBFindContentAssistNameMatcher(SVDBItemType ... types) {
		fItemTypes = types;
	}

	public boolean match(ISVDBNamedItem it, String name) {
		if ((fItemTypes.length == 0 || it.getType().isElemOf(fItemTypes)) && it.getName() != null) {
			String it_lower = it.getName().toLowerCase();
			String n_lower = name.toLowerCase();

			if (name.equals("")) {
				// Don't accept built-in type proxy classes
				return (!it_lower.startsWith("__sv_builtin_"));
			} else if (it_lower.startsWith(n_lower)) {
				return true;
			}
		}
		
		return false;
	}
	
}
