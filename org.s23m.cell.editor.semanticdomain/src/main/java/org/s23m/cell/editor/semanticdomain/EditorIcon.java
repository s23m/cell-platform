package org.s23m.cell.editor.semanticdomain;

import com.vaadin.terminal.ThemeResource;

public enum EditorIcon {
	SEARCH,
	RETRIEVE,
	ADD,
	ADMIN,
	DELETE,
	PERSIST,
	INSTANTIATE,
	IMPACT_ANALYSIS,
	DETAILS,
	VERTEX,
	VISIBILITY,
	SSR,
	EDGE,
	SEMANTIC_ID,
	SEMANTIC_DOMAIN,
	SEMANTIC_ROLE,
	INMEM_PERSISTENCE;

	private static final String DIRECTORY_PATH = "icons";
	private static final String FILE_EXTENSION = ".png";

	private ThemeResource iconImage;

	private EditorIcon() {
		final String path = DIRECTORY_PATH + "/" + name().toLowerCase() + FILE_EXTENSION;
		iconImage = new ThemeResource(path);
	}

	public ThemeResource getIconImage() {
		return iconImage;
	}

}
