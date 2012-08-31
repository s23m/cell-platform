package org.s23m.cell.platform.impl;

import org.s23m.cell.Set;
import org.s23m.cell.platform.api.models.CellPlatformDomain;
import org.s23m.cell.platform.api.models.Jargon;


public  class F_NamingConventions {

	public static final String codeNameAsString(final Set name, final Set jargon) {
		return applyConventions(name.identity().codeName(), jargon);
	}

	public static final String pluralCodeNameAsString(final Set name, final Set jargon) {
		return applyConventions(name.identity().pluralCodeName(), jargon);
	}

	private static final String applyConventions(final String name, final Set jargon) {
		String result = name;
		for (final Set convention : jargon.filter(Jargon.characterTransformation)) {
			if (convention.isEqualTo(CellPlatformDomain.allCharactersToLower)) {result = allCharactersToLower(result);}
			if (convention.isEqualTo(CellPlatformDomain.allCharactersToUpper)) {result = allCharactersToUpper(result);}
		}
		for (final Set convention : jargon.filter(Jargon.wordTransformation)) {
			if (convention.isEqualTo(CellPlatformDomain.firstCharacterOfAllWordsToUpper)) {result = firstCharacterOfAllWordsToUpper(result);}
		}
		for (final Set convention : jargon.filter(Jargon.statementTransformation)) {
			if (convention.isEqualTo(CellPlatformDomain.firstCharacterToLower)) {result = firstCharacterToLower(result);}
		}
		for (final Set convention : jargon.filter(Jargon.whiteTransformation)) {
			if (convention.isEqualTo(CellPlatformDomain.whiteToNoCharacter)) {result = whiteToNoCharacter(result);}
			if (convention.isEqualTo(CellPlatformDomain.whiteToMinus)) {result = whiteToMinus(result);}
			if (convention.isEqualTo(CellPlatformDomain.whiteToUnderscore)) {result = whiteToUnderscore(result);}
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

	private static final String whiteToNoCharacter(final String name) {
		return name.replaceAll("\\s", "");
	}
	private static final String whiteToMinus (final String name) {
		return name.replaceAll("\\s", "-");
	}
	private static final String whiteToUnderscore (final String name) {
		return name.replaceAll("\\s", "_");
	}


}
