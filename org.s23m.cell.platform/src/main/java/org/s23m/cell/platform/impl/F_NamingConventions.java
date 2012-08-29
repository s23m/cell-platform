package org.s23m.cell.platform.impl;

import org.s23m.cell.Set;
import org.s23m.cell.platform.api.models.CellPlatformDomain;
import org.s23m.cell.platform.api.models.Jargon;


public  class F_NamingConventions {

	public static final String codeNameAsString(final Set name, final Set jargon) {
		return applyConventions(name.identity().codeName(), jargon.filter(Jargon.namingConvention));
	}

	public static final String pluralCodeNameAsString(final Set name, final Set jargon) {
		return applyConventions(name.identity().pluralCodeName(), jargon.filter(Jargon.namingConvention));
	}

	private static final String applyConventions(final String name, final Set transformations) {
		String result = name;
		if (transformations.containsSemanticMatch(CellPlatformDomain.allCharactersToLower)) {
			result = allCharactersToLower(result);
		}
		if (transformations.containsSemanticMatch(CellPlatformDomain.allCharactersToUpper)) {
			result = allCharactersToUpper(result);
		}
		if (transformations.containsSemanticMatch(CellPlatformDomain.firstCharacterOfAllWordsToUpper)) {
			result = firstCharacterOfAllWordsToUpper(result);
		}
		if (transformations.containsSemanticMatch(CellPlatformDomain.firstCharacterToLower)) {
			result = firstCharacterToLower(result);
		}
		if (transformations.containsSemanticMatch(CellPlatformDomain.whiteToNoCharacter)) {
			return blanksToNoCharacter(result);
		}
		if (transformations.containsSemanticMatch(CellPlatformDomain.whiteToMinus)) {
			return blanksToMinus(result);
		}
		if (transformations.containsSemanticMatch(CellPlatformDomain.whiteToUnderscore)) {
			return blanksToUnderscore(result);
		}
		return result;
	}

	private static final String allCharactersToLower(final String name) {
		return name.toLowerCase();
	}

	private static final String allCharactersToUpper (final String name) {
		return name.toUpperCase();
	}
	private static final String firstCharacterOfAllWordsToUpper (final String name) {
		String result = "";
		final String[] words = name.split("+\\s");
		for (final String element : words) {
			result = result + element.substring(0, 0).toUpperCase() + element.substring(1) + " ";
		}
		if (result.length() < 1) {
			return result;
		} else {
			return result.substring(0, (result.length()-1));
		}
	}

	private static final String firstCharacterToLower (final String name) {
		String result = "";
		final String[] words = name.split("+\\s");
		for (final String element : words) {
			result = result + element.substring(0, 0).toLowerCase() + element.substring(1) + " ";
		}
		if (result.length() < 1) {
			return result;
		} else {
			return result.substring(0, (result.length()-1));
		}
	}

	private static final String blanksToNoCharacter(final String name) {
		return name.replaceAll("\\s", "");
	}
	private static final String blanksToMinus (final String name) {
		return name.replaceAll("\\s", "-");
	}
	private static final String blanksToUnderscore (final String name) {
		return name.replaceAll("\\s", "_");
	}


}
