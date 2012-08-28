package org.s23m.cell.platform.impl;

import static org.s23m.cell.S23MKernel.coreSets;

import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.core.F_InstantiationImpl;
import org.s23m.cell.platform.S23MPlatform;
import org.s23m.cell.platform.api.models.Agency;
import org.s23m.cell.platform.api.models.CellEngineering;
import org.s23m.cell.platform.api.models.CellPlatformAgent;
import org.s23m.cell.platform.api.models.CellPlatformDomain;
import org.s23m.cell.platform.api.models.Language;
import org.s23m.cell.platform.api.models.Legal;
import org.s23m.cell.platform.api.models.Organization;
import org.s23m.cell.platform.api.models.Terminology;


public  class F_CellQueries {


	public static final Set copyrightHolders(final Set cell) {
		Set result = null;
		for (final Set license : availableLicenses(cell)) {
			result = result.union(license.filter(Legal.copyrightHolders));
		}
		if (result == null) {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		} else {
			return result;
		}
	}

	public static final Set availableLicenses(final Set cell) {
		final Set localLicenses = cell.container().filter(Organization.semanticUnit_to_availableLicenses).filterTo();
		if (localLicenses.size() < 1) {
				if (S23MSemanticDomains.cellKernel.filterInstances().containsSemanticMatch(cell)) {
					return CellPlatformAgent.s23mCellKernelOrg.container().filter(Organization.semanticUnit_to_availableLicenses).filterTo();
				} else {
					if (S23MPlatform.coreGraphs.vertex.isSuperSetOf(cell.category()).is_TRUE()
							|| CellPlatformDomain.cellPlatformDomain.filterInstances().containsSemanticMatch(cell)) {
						return CellPlatformAgent.s23mCellPlatformOrg.container().filter(Organization.semanticUnit_to_availableLicenses).filterTo();

					} else {
						return availableLicenses(cell.container());
					}
				}
		} else {
			if (S23MPlatform.coreGraphs.vertex.isSuperSetOf(cell.category()).is_TRUE()
					|| CellPlatformDomain.cellPlatformDomain.filterInstances().containsSemanticMatch(cell)) {
				return CellPlatformAgent.s23mCellPlatformOrg.container().filter(Organization.semanticUnit_to_availableLicenses).filterTo();
			} else {
				return localLicenses;
			}
		}
	}

	public static final Set usageLicense(final Set user, final Set product) {
		return null;
	}
	public static final Set agent(final Set cell) {
		if (Agency.agent.isSuperSetOf(cell.category()).is_TRUE()) {
			return cell;
		} else {
			if (cell.category().isEqualTo(cell)) {
				return S23MSemanticDomains.is_NOTAPPLICABLE;
				} else {
				return F_CellQueries.agent(cell.container());
			}
		}
	}
	public static final Set isCell(final Set cell) {
		if (Organization.cell.isSuperSetOf(cell.category()).is_TRUE()) {
			return S23MSemanticDomains.is_TRUE;
		} else {
			if (cell.category().isEqualTo(cell)) {
				return S23MSemanticDomains.is_FALSE;
			} else {
				return isCell(cell.category());
			}
		}
	}

	public static final Set stage(final Set cell) {
		if (Agency.stage.isSuperSetOf(cell.category()).is_TRUE()) {
			return cell;
		} else {
			if (cell.category().isEqualTo(cell)) {
				return S23MSemanticDomains.is_NOTAPPLICABLE;
			} else {
				return F_CellQueries.stage(cell.container());
			}
		}
	}
	private static final Set nativeLanguageContainer(final Set agent) {
		final Set nativeLanguageContainer = agent.filter(Agency.agent_to_nativeLanguage);
		if (nativeLanguageContainer.size() > 0) {
			return nativeLanguageContainer;
		} else {
			if (Agency.agent.isSuperSetOf(agent.container().category()).is_TRUE()) {
				return F_CellQueries.nativeLanguageContainer(agent.container());
			} else {
				return CellPlatformAgent.cellMetaLanguage;
			}
		}
	}
	public static final Set nativeLanguage(final Set agent) {
		if (Agency.agent.isSuperSetOf(agent.category()).is_TRUE()) {
			final Set nativeLanguageContainer = F_CellQueries.nativeLanguageContainer(agent);
			if (nativeLanguageContainer.size() > 0) {
				return nativeLanguageContainer.extractFirst();
			} else {
				if (Agency.agent.isSuperSetOf(agent.container().category()).is_TRUE()) {
					return F_CellQueries.nativeLanguage(agent.container());
				} else {
					return CellPlatformAgent.cellMetaLanguage;
				}
			}
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	public static final Set perspectiveLanguage(final Set perspective) {
		final Set perspectiveLanguageContainerV = perspective.filter(Agency.perspectiveV);
		if (perspectiveLanguageContainerV.size() > 0) {
			final Set perspectiveLanguageContainer = perspectiveLanguageContainerV.extractFirst().filter(Agency.perspective_to_jargon).filterTo();
			if (perspectiveLanguageContainer.size() > 0) {
				return perspectiveLanguageContainer.extractFirst();
			} else {
				return F_CellQueries.nativeLanguage(perspective.from());
			}
		} else {
			return F_CellQueries.nativeLanguage(perspective.from());
		}
	}

	private static final Set cellLanguageContainer(final Set cell) {
		final Set cellLanguageContainer = cell.filter(Organization.cell_to_nativeLanguage);
		if (cellLanguageContainer.size() > 0) {
			return cellLanguageContainer;
		} else {
			if (Agency.agent.isSuperSetOf(cell.container().category()).is_TRUE()) {
				return F_CellQueries.nativeLanguageContainer(cell.container());
			} else {
				if (Organization.cell.isSuperSetOf(cell.container().category()).is_TRUE()) {
					return F_CellQueries.cellLanguageContainer(cell.container());
				} else {
					return CellPlatformAgent.cellMetaLanguage;
				}
			}
		}
	}
	public static final Set cellLanguage(final Set cell) {
		if (Organization.cell.isSuperSetOf(cell.container().category()).is_TRUE()) {
			final Set cellLanguageContainer = F_CellQueries.cellLanguageContainer(cell);
			if (cellLanguageContainer.size() > 0) {
				return cellLanguageContainer.extractFirst();
			} else {
				return cellLanguageContainer;
			}
		} else {
			return F_InstantiationImpl.raiseError(coreSets.semanticErr_operationIsIllegalOnThisInstance.identity(), coreSets.semanticErr);
		}
	}

	public static final Set transformToSemanticUnit(final Set set) {
		if (F_CellQueries.isCell(set).is_TRUE()) {
			return set;
		} else {
			final Set semanticUnitContainer = set.container().filter(Organization.semanticUnit).filterBySemanticIdentity(set);
			if (semanticUnitContainer.size() > 0) {
				return semanticUnitContainer.extractFirst().to();
			} else {
				return S23MSemanticDomains.is_UNKNOWN;
			}
		}
	}

	public static final Set nameInCellLanguage(final Set cell) {
		final Set semanticUnit = F_CellQueries.transformToSemanticUnit(cell);
		if (semanticUnit.is_UNKNOWN()) {
			return semanticUnit;
		} else {
			final Set cellLanguage = F_CellQueries.cellLanguage(cell);
			return F_CellQueries.nameOfSemanticUnit(semanticUnit, cellLanguage);
		}
	}

	private static final Set nameInTargetLanguage(final Set cellMetaLanguageWord, final Set targetLanguage) {
		final Set targetLanguageInCellPlatformContainer = CellPlatformAgent.production.filter(CellEngineering.language).extractFirst().filterBySemanticIdentity(targetLanguage);
		if (targetLanguageInCellPlatformContainer.size() > 0) {
			final Set targetLanguageInCellPlatform = targetLanguageInCellPlatformContainer.extractFirst();
			final Set targetLanguageWordsInCellPlatform = targetLanguageInCellPlatform.filterPolymorphic(Language.word);
			for (final Set word : targetLanguageWordsInCellPlatform) {
				if (word.filterBySemanticIdentity(cellMetaLanguageWord).size() > 0) {
					return word; // translated cell engineering concept
				}
			}
		}
		return CellPlatformDomain.toBeTranslated;
	}

	public static final Set nameInAgentLanguage(final Set cell, final Set session) {
		final Set semanticUnit = F_CellQueries.transformToSemanticUnit(cell);
		if (semanticUnit.is_UNKNOWN()) {
		final Set cellAgent = F_CellQueries.agent(cell);
			if (cellAgent.is_NOTAPPLICABLE()) {
				// Either the "cell engineering - JAVA" model or a semantic domain
				final Set cellMetaLanguageNames = CellPlatformAgent.cellMetaLanguage.filterBySemanticIdentity(cell);
				if (cellMetaLanguageNames.size() > 0) {
					final Set cellMetaLanguageWord = cellMetaLanguageNames.extractFirst();
					final Set agentLanguage =  F_CellQueries.nativeLanguage(F_CellQueries.agent(session));
					return F_CellQueries.nameInTargetLanguage(cellMetaLanguageWord, agentLanguage);
				} else {
					return cell.semanticIdentity(); // element of a semantic domain
				}
			} else {
				if (cellAgent.isEqualTo(CellPlatformAgent.s23mCellPlatform)) {
					return cell.semanticIdentity(); // element of "cell platform : agent" model, never to be translated
				} else {
					return CellPlatformDomain.toBeTranslated; // an element of a regular agent model, lacking a corresponding semantic unit
				}
			}
		} else {
			final Set agent = F_CellQueries.agent(session);
			final Set nativeLanguage = F_CellQueries.nativeLanguage(agent);
			return F_CellQueries.nameOfSemanticUnit(semanticUnit, nativeLanguage);
		}
	}

	public static final Set nameInPerspective(final Set cell, final Set perspective) {
		final Set semanticUnit = F_CellQueries.transformToSemanticUnit(cell);
		if (semanticUnit.is_UNKNOWN()) {
		final Set cellAgent = F_CellQueries.agent(cell);
			if (cellAgent.is_NOTAPPLICABLE()) {
				// Either the "cell engineering - JAVA" model or a semantic domain
				final Set cellMetaLanguageNames = CellPlatformAgent.cellMetaLanguage.filterBySemanticIdentity(cell);
				if (cellMetaLanguageNames.size() > 0) {
					final Set cellMetaLanguageWord = cellMetaLanguageNames.extractFirst();
					final Set perspectiveLanguage = F_CellQueries.perspectiveLanguage(perspective);
					final Set perspectiveLanguageWord = F_CellQueries.nameInTargetLanguage(cellMetaLanguageWord, perspectiveLanguage);
					if (!perspectiveLanguageWord.isEqualTo(CellPlatformDomain.toBeTranslated)) {
						return perspectiveLanguageWord; // translated cell engineering concept
					} else {
						final Set agentLanguage = F_CellQueries.nativeLanguage(perspective.from());
						return F_CellQueries.nameInTargetLanguage(cellMetaLanguageWord, agentLanguage);
					}
				} else {
					return cell.semanticIdentity(); // element of a semantic domain
				}
			} else {
				if (cellAgent.isEqualTo(CellPlatformAgent.s23mCellPlatform)) {
					return cell.semanticIdentity(); // element of "cell platform : agent" model, never to be translated
				} else {
					return CellPlatformDomain.toBeTranslated; // an element of a regular agent model, lacking a corresponding semantic unit
				}
			}
		} else {
			final Set perspectiveLanguage = F_CellQueries.perspectiveLanguage(perspective);
			final Set perspectiveWord = F_CellQueries.nameOfSemanticUnit(semanticUnit, perspectiveLanguage);
			if (perspectiveWord.isEqualTo(CellPlatformDomain.toBeTranslated)) {
				final Set agent = perspective.from();
				final Set nativeLanguage = F_CellQueries.nativeLanguage(agent);
				return F_CellQueries.nameOfSemanticUnit(semanticUnit, nativeLanguage);
			} else {
				return perspectiveWord;
			}
		}
	}

	public static final Set name(final Set cell, final Set language) {
		final Set semanticUnit = F_CellQueries.transformToSemanticUnit(cell);
		if (semanticUnit.is_UNKNOWN()) {
			final Set cellAgent = F_CellQueries.agent(cell);
			if (cellAgent.is_NOTAPPLICABLE()) {
				// Either the "cell engineering - JAVA" model or a semantic domain
				final Set cellMetaLanguageNames = CellPlatformAgent.cellMetaLanguage.filterBySemanticIdentity(cell);
				if (cellMetaLanguageNames.size() > 0) {
					final Set cellMetaLanguageWord = cellMetaLanguageNames.extractFirst();
					return F_CellQueries.nameInTargetLanguage(cellMetaLanguageWord, language);
				} else {
					return cell.semanticIdentity(); // element of a semantic domain
				}
			} else {
				if (cellAgent.isEqualTo(CellPlatformAgent.s23mCellPlatform)) {
					return cell.semanticIdentity(); // element of "cell platform : agent" model, never to be translated
				} else {
					return CellPlatformDomain.toBeTranslated; // an element of a regular agent model, lacking a corresponding semantic unit
				}
			}

		} else {
			return F_CellQueries.nameOfSemanticUnit(semanticUnit, language);
		}
	}

	private static final Set nameOfSemanticUnit(final Set semanticUnit, final Set language) {
		final Set nameContainer1 = semanticUnit.container().filterByLinkedFrom(semanticUnit).filter(Organization.semanticUnit_to_abstractWords).filterByLinkedToVia(language).filter(Language.word);
		if (nameContainer1.size() > 0) {
			return nameContainer1.extractFirst().to();
		} else {
			final Set nameContainer2 = semanticUnit.container().filterByLinkedFrom(semanticUnit).filter(Organization.semanticUnit_to_abstractWords).filterTo().filter(Language.word);
			for (final Set word : nameContainer2) {
				if (word.container().isEqualTo(language)) {
					return word;
				}
			}
			return CellPlatformDomain.toBeTranslated;
		}
	}

	public static final String nameAsString(final Set name) {
		return name.identity().name();
	}
	public static final String pluralNameAsString(final Set name) {
		return name.identity().pluralName();
	}

	public static final Set abbreviations(final Set name, final Set language) {
		final Set semanticUnit = F_CellQueries.transformToSemanticUnit(name);
		if (semanticUnit.is_UNKNOWN()) {
			return name.container().filterByLinkedFrom(name).filter(Organization.semanticUnit_to_abstractWords).filterTo().filter(Terminology.abbreviation); // empty set
		} else {
			final Set abbreviations = semanticUnit.container().filterByLinkedFrom(semanticUnit).filter(Organization.semanticUnit_to_abstractWords).filterTo().filter(Terminology.abbreviation);
			Set result = F_InstantiationImpl.createResultSet();
			for (final Set abbreviation : abbreviations) {
				result = result.union(abbreviation.container().filterByLinkedFromAndTo(abbreviation, language).filterFrom());
			}
			return result;
		}
	}

}
