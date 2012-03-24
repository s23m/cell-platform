package org.s23m.cell.kernel.tests;

import static org.s23m.cell.S23MKernel.coreGraphs;

import org.s23m.cell.Set;
import org.s23m.cell.api.Instantiation;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.api.models2.RepositoryStructure;

public class EcoreEmulationTest extends S23MTestCase {

	private Set EReference;
	private Set EcoreDomain;
	private Set eClassReference;

	// metamodel level
    private Set ecoreERSchemaMM;
	private Set ecoreERSchema;
	private Set ecoreEntity;
	private Set ecoreAttribute;
	private Set ecoreRelationship;

	private Set eReference_SchemaToEntities;
	private Set eReference_EntityToAttribute;
	private Set eReference_SchemaToRelationships;
	private Set eReference_RelationshipToSource;
	private Set eReference_RelationshipToTarget;

	@Override
	protected void executeInstantiationSequence() {
		eclipseModellingFramework();
		ecoreERMetaModel();
	}

	public void eclipseModellingFramework() {

		//EcoreDomain = Instantiation.addSemanticDomain("EcoreDomain", "EcoreDomain", S23MSemanticDomains.agentSemanticDomains);
		EcoreDomain = Instantiation.addSemanticDomain("EcoreDomain", "EcoreDomain", S23MSemanticDomains.sandboxSemanticDomains);
		final Set ecore = Instantiation.addDisjunctSemanticIdentitySet("Ecore", "Ecore", EcoreDomain);
		final Set eObject = Instantiation.addDisjunctSemanticIdentitySet("EObject", "EObjects", EcoreDomain);
		final Set eModelElement = Instantiation.addDisjunctSemanticIdentitySet("EModelElement", "EModelElements", EcoreDomain);
		final Set eAnnotation = Instantiation.addDisjunctSemanticIdentitySet("EAnnotation", "EAnnotation", EcoreDomain);
		final Set eFactory = Instantiation.addDisjunctSemanticIdentitySet("EFactory", "EFactories", EcoreDomain);
		final Set eNamedElement = Instantiation.addDisjunctSemanticIdentitySet("ENamedElement", "ENamedElements", EcoreDomain);
		final Set ePackage = Instantiation.addDisjunctSemanticIdentitySet("EPackage", "EPackages", EcoreDomain);
		final Set eClassifier = Instantiation.addDisjunctSemanticIdentitySet("EClassifier", "EClassifiers", EcoreDomain);
		final Set eEnumLiteral = Instantiation.addDisjunctSemanticIdentitySet("EEnumLiteral", "EEnumLiterals", EcoreDomain);
		final Set eTypedElement = Instantiation.addDisjunctSemanticIdentitySet("ETypedElement", "ETypedElements", EcoreDomain);
		final Set eClass = Instantiation.addDisjunctSemanticIdentitySet("EClass", "EClasses", EcoreDomain);
		final Set eDataType = Instantiation.addDisjunctSemanticIdentitySet("EDataType", "EDataTypes", EcoreDomain);
		final Set eEnum = Instantiation.addDisjunctSemanticIdentitySet("EEnum", "EEnums", EcoreDomain);
		final Set eStructuralFeature = Instantiation.addDisjunctSemanticIdentitySet("EStructuralFeature", "EStructuralFeatures", EcoreDomain);
		final Set eOperation = Instantiation.addDisjunctSemanticIdentitySet("EOperation", "EOperations", EcoreDomain);
		final Set eParameter = Instantiation.addDisjunctSemanticIdentitySet("EParameter", "EParameters", EcoreDomain);
		final Set eAttribute = Instantiation.addDisjunctSemanticIdentitySet("EAttribute", "EAttributes", EcoreDomain);
		final Set eReference = Instantiation.addDisjunctSemanticIdentitySet("EReference", "EReferences", EcoreDomain);

		final Set ecoreDataTypes = Instantiation.addDisjunctSemanticIdentitySet("EcoreDataTypes", "EcoreDataTypes", EcoreDomain);
		final Set eBigDecimal = Instantiation.addDisjunctSemanticIdentitySet("EBigDecimal", "EBigDecimals", EcoreDomain);
		final Set eBigInteger = Instantiation.addDisjunctSemanticIdentitySet("EBigInteger", "EBigIntegers", EcoreDomain);
		final Set eBoolean = Instantiation.addDisjunctSemanticIdentitySet("EBoolean", "EBooleans", EcoreDomain);
		final Set eBooleanObject = Instantiation.addDisjunctSemanticIdentitySet("EBooleanObject", "EBooleanObjects", EcoreDomain);
		final Set eByte = Instantiation.addDisjunctSemanticIdentitySet("EByte", "EBytes", EcoreDomain);
		final Set eByteArray = Instantiation.addDisjunctSemanticIdentitySet("EByteArray", "EByteArrays", EcoreDomain);
		final Set eByteObject = Instantiation.addDisjunctSemanticIdentitySet("EByteObject", "EByteObjects", EcoreDomain);
		final Set eChar = Instantiation.addDisjunctSemanticIdentitySet("EChar", "EChars", EcoreDomain);
		final Set eCharacterObject = Instantiation.addDisjunctSemanticIdentitySet("ECharacterObject", "ECharacterObjects", EcoreDomain);
		final Set eDate = Instantiation.addDisjunctSemanticIdentitySet("EDate", "EDates", EcoreDomain);
		final Set eDiagnosticChain = Instantiation.addDisjunctSemanticIdentitySet("EDiagnosticChain", "EDiagnosticChains", EcoreDomain);
		final Set eDouble = Instantiation.addDisjunctSemanticIdentitySet("EDouble", "EDoubles", EcoreDomain);
		final Set eDoubleObject = Instantiation.addDisjunctSemanticIdentitySet("EDoubleObject", "EDoubleObjects", EcoreDomain);
		final Set eList = Instantiation.addDisjunctSemanticIdentitySet("EList", "ELists", EcoreDomain);
		final Set eEnumerator = Instantiation.addDisjunctSemanticIdentitySet("EEnumerator", "EEnumerators", EcoreDomain);
		final Set eFeatureMap = Instantiation.addDisjunctSemanticIdentitySet("EFeatureMap", "EFeatureMaps", EcoreDomain);
		final Set eFeatureMapEntry = Instantiation.addDisjunctSemanticIdentitySet("EFeatureMapEntry", "EFeatureMapEntrys", EcoreDomain);
		final Set eFloat = Instantiation.addDisjunctSemanticIdentitySet("EFloat", "EFloats", EcoreDomain);
		final Set eFloatObject = Instantiation.addDisjunctSemanticIdentitySet("EFloatObject", "EFloatObjects", EcoreDomain);
		final Set eInt = Instantiation.addDisjunctSemanticIdentitySet("EInt", "EInts", EcoreDomain);
		final Set eIntegerObject = Instantiation.addDisjunctSemanticIdentitySet("EIntegerObject", "EIntegerObjects", EcoreDomain);
		final Set eJavaClass = Instantiation.addDisjunctSemanticIdentitySet("EJavaClass", "EJavaClasss", EcoreDomain);
		final Set eJavaObject = Instantiation.addDisjunctSemanticIdentitySet("EJavaObject", "EJavaObjects", EcoreDomain);
		final Set eLong = Instantiation.addDisjunctSemanticIdentitySet("ELong", "ELongs", EcoreDomain);
		final Set eLongObject = Instantiation.addDisjunctSemanticIdentitySet("ELongObject", "ELongObjects", EcoreDomain);
		final Set eMap = Instantiation.addDisjunctSemanticIdentitySet("EMap", "EMaps", EcoreDomain);
		final Set eResource = Instantiation.addDisjunctSemanticIdentitySet("EResource", "EResources", EcoreDomain);
		final Set eResourceSet = Instantiation.addDisjunctSemanticIdentitySet("EResourceSet", "EResourceSets", EcoreDomain);
		final Set eShort = Instantiation.addDisjunctSemanticIdentitySet("EShort", "EShorts", EcoreDomain);
		final Set eShortObject = Instantiation.addDisjunctSemanticIdentitySet("EShortObject", "EShortObjects", EcoreDomain);
		final Set eString = Instantiation.addDisjunctSemanticIdentitySet("EString", "EStrings", EcoreDomain);
		final Set eStringToStringMapEntry = Instantiation.addDisjunctSemanticIdentitySet("EStringToStringMapEntry", "EStringToStringMapEntrys", EcoreDomain);
		final Set eTreeIterator = Instantiation.addDisjunctSemanticIdentitySet("ETreeIterator", "ETreeIterators", EcoreDomain);

		final Set source = Instantiation.addSemanticRole("source", "source", EcoreDomain, eString);
		final Set details = Instantiation.addSemanticRole("details", "details", EcoreDomain, eString);
		final Set name = Instantiation.addSemanticRole("name", "name", EcoreDomain, eString);
		final Set instanceClassName = Instantiation.addSemanticRole("instanceClassName", "instanceClassName", EcoreDomain, eString);
		final Set instanceClass = Instantiation.addSemanticRole("instanceClass", "instanceClass", EcoreDomain, eJavaClass);
		final Set defaultValue = Instantiation.addSemanticRole("defaultValue", "defaultValue", EcoreDomain, eJavaObject);
		final Set ordered = Instantiation.addSemanticRole("ordered", "ordered", EcoreDomain, eBoolean);
		final Set unique = Instantiation.addSemanticRole("unique", "unique", EcoreDomain, eBoolean);
		final Set lowerBound = Instantiation.addSemanticRole("lowerBound", "lowerBound", EcoreDomain, eInt);
		final Set upperBound = Instantiation.addSemanticRole("upperBound", "upperBound", EcoreDomain, eInt);
		final Set many = Instantiation.addSemanticRole("many", "many", EcoreDomain, eBoolean);
		final Set required = Instantiation.addSemanticRole("required", "required", EcoreDomain, eBoolean);
		final Set nsURI = Instantiation.addSemanticRole("nsURI", "nsURI", EcoreDomain, eString);
		final Set nsPrefix = Instantiation.addSemanticRole("nsPrefix", "nsPrefix", EcoreDomain, eString);
		final Set eCoreAbstract = Instantiation.addSemanticRole("abstract", "abstract", EcoreDomain, eBoolean);
		final Set ecoreInterface = Instantiation.addSemanticRole("interface", "interface", EcoreDomain, eBoolean);
		final Set serializable = Instantiation.addSemanticRole("serializable", "serializable", EcoreDomain, eBoolean);
		final Set value = Instantiation.addSemanticRole("value", "value", EcoreDomain, eInt);
		final Set instance = Instantiation.addSemanticRole("instance", "instance", EcoreDomain, eEnumerator);
		final Set containment = Instantiation.addSemanticRole("containment", "containment", EcoreDomain, eBoolean);
		final Set container = Instantiation.addSemanticRole("container", "container", EcoreDomain, eBoolean);
		final Set resolveProxies = Instantiation.addSemanticRole("resolveProxies", "resolveProxies", EcoreDomain, eBoolean);
		final Set iD = Instantiation.addSemanticRole("iD", "iD", EcoreDomain, eBoolean);
		final Set changeable = Instantiation.addSemanticRole("changeable", "changeable", EcoreDomain, eBoolean);
		final Set eCoreVolatile = Instantiation.addSemanticRole("eCoreVolatile", "eCoreVolatile", EcoreDomain, eBoolean);
		final Set eCoreTransient = Instantiation.addSemanticRole("eCoreTransient", "eCoreTransient", EcoreDomain, eBoolean);
		final Set defaultValueLiteral = Instantiation.addSemanticRole("defaultValueLiteral", "defaultValueLiteral", EcoreDomain, eString);
		final Set unsettable = Instantiation.addSemanticRole("unsettable", "unsettable", EcoreDomain, eBoolean);
		final Set derived = Instantiation.addSemanticRole("derived", "derived", EcoreDomain, eBoolean);
		final Set stringKey = Instantiation.addSemanticRole("key", "key", EcoreDomain, eString);
		final Set stringValue = Instantiation.addSemanticRole("value", "value", EcoreDomain, eString);

		final Set Ecore = RepositoryStructure.domainengineering.addConcrete(coreGraphs.vertex, ecore);

		final Set EObject = Ecore.addConcrete(coreGraphs.vertex,eObject);
		final Set sr1 = Instantiation.arrow(coreGraphs.superSetReference, EObject, coreGraphs.vertex);
		EReference = Instantiation.arrow(coreGraphs.edge, eReference,
				EObject, EObject, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet("eReferenceType", "eReferenceType", EcoreDomain), EObject, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);

		final Set EModelElement = Ecore.addAbstract(EObject, eModelElement);
		final Set sr2 = Instantiation.arrow(coreGraphs.superSetReference, EModelElement, EObject);
		final Set EAnnotation = Ecore.addConcrete(EObject, eAnnotation);
		final Set sr3 = Instantiation.arrow(coreGraphs.superSetReference, EAnnotation, EModelElement);
		final Set EFactory = Ecore.addConcrete(EObject, eFactory);
		final Set sr4 = Instantiation.arrow(coreGraphs.superSetReference, EFactory, EModelElement);
		final Set ENamedElement = Ecore.addAbstract(EObject, eNamedElement);
		final Set sr5 = Instantiation.arrow(coreGraphs.superSetReference, ENamedElement, EModelElement);
		final Set EPackage = Ecore.addConcrete(EObject, ePackage);
		final Set sr6 = Instantiation.arrow(coreGraphs.superSetReference, EPackage, ENamedElement);
		final Set EClassifier = Ecore.addAbstract(EObject, eClassifier);
		final Set sr7 = Instantiation.arrow(coreGraphs.superSetReference, EClassifier, ENamedElement);
		final Set EEnumLiteral = Ecore.addConcrete(EObject, eEnumLiteral);
		final Set sr8 = Instantiation.arrow(coreGraphs.superSetReference, EEnumLiteral, ENamedElement);
		final Set ETypedElement = Ecore.addAbstract(EObject, eTypedElement);
		final Set sr9 = Instantiation.arrow(coreGraphs.superSetReference, ETypedElement, ENamedElement);
		final Set EClass = Ecore.addConcrete(EObject, eClass);
		final Set sr10 = Instantiation.arrow(coreGraphs.superSetReference, EClass, EClassifier);
		final Set EDataType = Ecore.addConcrete(EObject, eDataType);
		final Set sr11 = Instantiation.arrow(coreGraphs.superSetReference, EDataType, EClassifier);
		final Set EEnum = Ecore.addConcrete(EObject, eEnum);
		final Set sr12 = Instantiation.arrow(coreGraphs.superSetReference, EEnum, EDataType);
		final Set EStructuralFeature = Ecore.addAbstract(EObject, eStructuralFeature);
		final Set sr13 = Instantiation.arrow(coreGraphs.superSetReference, EStructuralFeature, ETypedElement);
		final Set EOperation = Ecore.addConcrete(EObject, eOperation);
		final Set sr14 = Instantiation.arrow(coreGraphs.superSetReference, EOperation, ETypedElement);
		final Set EParameter = Ecore.addConcrete(EObject, eParameter);
		final Set sr15 = Instantiation.arrow(coreGraphs.superSetReference, EParameter, ETypedElement);
		final Set EAttribute = Ecore.addConcrete(EObject, eAttribute);
		final Set sr16 = Instantiation.arrow(coreGraphs.superSetReference, EAttribute, EStructuralFeature);
		final Set sr17 = Instantiation.arrow(coreGraphs.superSetReference, EReference, EStructuralFeature);

		final Set vis1 = Instantiation.arrow(coreGraphs.visibility, RepositoryStructure.domainengineering, Ecore);

		final Set eModelElement_to_eAnnotations = linkByContainment(EModelElement, "eModelElement", "eAnnotations", EAnnotation);
		final Set eFactoryInstance_to_ePackage = linkBySimpleReference(EFactory, "eFactoryInstance", "ePackage", EPackage);
		final Set ePackage_to_eClassifiers = linkByContainment(EPackage, "ePackage", "eClassifiers", EClassifier);
		final Set eSuperPackage_to_eSubpackages = linkByContainment(EPackage, "eSuperPackage", "eSubpackages", EPackage);
		final Set eOperation_to_eParameters = linkByContainment(EOperation, "eOperation", "eParameters", EParameter);
		final Set eOperation_to_eExceptions = linkBySimpleReference(EOperation, "eExceptions", EClassifier, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n);
		final Set eContainingClass_to_eOperations = linkByContainment(EClass, "eContainingClass", "eOperations", EOperation);
		final Set eContainingClass_to_eStructuralFeatures = linkByContainment(EClass, "eContainingClass", "eStructuralFeatures", EStructuralFeature);
		final Set to_eSuperTypes = linkBySimpleReference(EClass, "eSuperTypes", EClass, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n);
		// Set to_eOpposite = linkBySimpleReference(EReference, "eOpposite", EReference, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_1);
		final Set eEnum_to_eLiterals = linkByContainment(EEnum, "eEnum", "eLiterals", EEnumLiteral);
		//Set eAnnotation_to_contents = linkByContainment(EAnnotation, "contents", EObject);
		//Set eAnnotation_to_references = linkBySimpleReference(EAnnotation, "references", EObject, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_1);
		eClassReference = linkBySimpleReference(EClass, "eClassReference", EClass, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n);

		final Set EcoreDataTypes = RepositoryStructure.domainengineering.addConcrete(coreGraphs.vertex, ecoreDataTypes);
		final Set vis2 = Instantiation.arrow(coreGraphs.visibility, RepositoryStructure.domainengineering, EcoreDataTypes);
		final Set vis3 = Instantiation.arrow(coreGraphs.visibility, Ecore, EcoreDataTypes);

		final Set EBigDecimal = EcoreDataTypes.addConcrete(EDataType, eBigDecimal);
		final Set EBigInteger = EcoreDataTypes.addConcrete(EDataType, eBigInteger);
		final Set EBoolean = EcoreDataTypes.addConcrete(EDataType, eBoolean);
		final Set EBooleanObject = EcoreDataTypes.addConcrete(EDataType, eBooleanObject);
		final Set EByte = EcoreDataTypes.addConcrete(EDataType, eByte);
		final Set EByteArray = EcoreDataTypes.addConcrete(EDataType, eByteArray);
		final Set EByteObject = EcoreDataTypes.addConcrete(EDataType, eByteObject);
		final Set EChar = EcoreDataTypes.addConcrete(EDataType, eChar);
		final Set ECharacterObject = EcoreDataTypes.addConcrete(EDataType, eCharacterObject);
		final Set EDate = EcoreDataTypes.addConcrete(EDataType, eDate);
		final Set EDiagnosticChain = EcoreDataTypes.addConcrete(EDataType, eDiagnosticChain);
		final Set EDouble = EcoreDataTypes.addConcrete(EDataType, eDouble);
		final Set EDoubleObject = EcoreDataTypes.addConcrete(EDataType, eDoubleObject);
		final Set EList = EcoreDataTypes.addConcrete(EDataType, eList);
		final Set EEnumerator = EcoreDataTypes.addConcrete(EDataType, eEnumerator);
		final Set EFeatureMap = EcoreDataTypes.addConcrete(EDataType, eFeatureMap);
		final Set EFeatureMapEntry = EcoreDataTypes.addConcrete(EDataType, eFeatureMapEntry);
		final Set EFloat = EcoreDataTypes.addConcrete(EDataType, eFloat);
		final Set EFloatObject = EcoreDataTypes.addConcrete(EDataType, eFloatObject);
		final Set EInt = EcoreDataTypes.addConcrete(EDataType, eInt);
		final Set EIntegerObject = EcoreDataTypes.addConcrete(EDataType, eIntegerObject);
		final Set EJavaClass = EcoreDataTypes.addConcrete(EDataType, eJavaClass);
		final Set EJavaObject = EcoreDataTypes.addConcrete(EDataType, eJavaObject);
		final Set ELong = EcoreDataTypes.addConcrete(EDataType, eLong);
		final Set ELongObject = EcoreDataTypes.addConcrete(EDataType, eLongObject);
		final Set EMap = EcoreDataTypes.addConcrete(EDataType, eMap);
		final Set EResource = EcoreDataTypes.addConcrete(EDataType, eResource);
		final Set EResourceSet = EcoreDataTypes.addConcrete(EDataType, eResourceSet);
		final Set EShort = EcoreDataTypes.addConcrete(EDataType, eShort);
		final Set EShortObject = EcoreDataTypes.addConcrete(EDataType, eShortObject);
		final Set EString = EcoreDataTypes.addConcrete(EDataType, eString);
		final Set EStringToStringMapEntry = EcoreDataTypes.addConcrete(EDataType, eStringToStringMapEntry);
		final Set ETreeIterator = EcoreDataTypes.addConcrete(EDataType, eTreeIterator);

		final Set sourceEString = EcoreDataTypes.addConcrete(EDataType, source);
		final Set detailsEStringToStringMapEntry = EcoreDataTypes.addConcrete(EDataType, details);
		final Set nameEString = EcoreDataTypes.addConcrete(EDataType, name);
		final Set instanceClassNameEString = EcoreDataTypes.addConcrete(EDataType, instanceClassName);
		final Set instanceClassEJavaClass = EcoreDataTypes.addConcrete(EDataType, instanceClass);
		final Set defaultValueEJavaObject = EcoreDataTypes.addConcrete(EDataType, defaultValue);
		final Set orderedEBoolean = EcoreDataTypes.addConcrete(EDataType, ordered);
		final Set uniqueEBoolean = EcoreDataTypes.addConcrete(EDataType, unique);
		final Set lowerBoundEInt = EcoreDataTypes.addConcrete(EDataType, lowerBound);
		final Set upperBoundEInt = EcoreDataTypes.addConcrete(EDataType, upperBound);
		final Set manyEBoolean = EcoreDataTypes.addConcrete(EDataType, many);
		final Set requiredEBoolean = EcoreDataTypes.addConcrete(EDataType, required);
		final Set nsURIEString = EcoreDataTypes.addConcrete(EDataType, nsURI);
		final Set nsPrefixEString = EcoreDataTypes.addConcrete(EDataType, nsPrefix);
		final Set abstractEBoolean = EcoreDataTypes.addConcrete(EDataType, eCoreAbstract);
		final Set interfaceEBoolean = EcoreDataTypes.addConcrete(EDataType, ecoreInterface);
		final Set serializableEBoolean = EcoreDataTypes.addConcrete(EDataType, serializable);
		final Set valueEInt = EcoreDataTypes.addConcrete(EDataType, value);
		final Set instanceEEnumerator = EcoreDataTypes.addConcrete(EDataType, instance);
		final Set containmentEBoolean = EcoreDataTypes.addConcrete(EDataType, containment);
		final Set containerEBoolean = EcoreDataTypes.addConcrete(EDataType, container);
		final Set resolveProxiesEBoolean = EcoreDataTypes.addConcrete(EDataType, resolveProxies);
		final Set iDEBoolean = EcoreDataTypes.addConcrete(EDataType, iD);
		final Set changeableEBoolean = EcoreDataTypes.addConcrete(EDataType, changeable);
		final Set volatileEBoolean = EcoreDataTypes.addConcrete(EDataType, eCoreVolatile);
		final Set transientEBoolean = EcoreDataTypes.addConcrete(EDataType, eCoreTransient);
		final Set defaultValueLiteralEString = EcoreDataTypes.addConcrete(EDataType, defaultValueLiteral);
		final Set unsettableEBoolean = EcoreDataTypes.addConcrete(EDataType, unsettable);
		final Set derivedEBoolean = EcoreDataTypes.addConcrete(EDataType, derived);
		final Set keyEString = EcoreDataTypes.addConcrete(EDataType, stringKey);
		final Set valueEString = EcoreDataTypes.addConcrete(EDataType, stringValue);


		ecoreERSchemaMM = RepositoryStructure.domainengineering.addConcrete(EClass,
	    		Instantiation.addDisjunctSemanticIdentitySet("ecoreERSchema Metamodel", "ecoreERSchema Metamodel", EcoreDomain));
		ecoreERSchema = ecoreERSchemaMM.addConcrete(EClass,
		   		Instantiation.addDisjunctSemanticIdentitySet("ecoreERSchema", "ecoreERSchema", EcoreDomain));
		ecoreEntity = ecoreERSchemaMM.addConcrete(EClass,
		   		Instantiation.addDisjunctSemanticIdentitySet("ecoreEntity", "ecoreEntity", EcoreDomain));
		ecoreAttribute = ecoreERSchemaMM.addConcrete(EClass,
		   		Instantiation.addDisjunctSemanticIdentitySet("ecoreAttribute", "ecoreAttribute", EcoreDomain));
		ecoreRelationship = ecoreERSchemaMM.addConcrete(EClass,
		   		Instantiation.addDisjunctSemanticIdentitySet("ecoreRelationship", "ecoreRelationship", EcoreDomain));

		eReference_SchemaToEntities = refByContainment(ecoreERSchema, "Entities", ecoreEntity);
		eReference_EntityToAttribute = refByContainment(ecoreEntity, "Attributes", ecoreAttribute);
		eReference_SchemaToRelationships = refByContainment(ecoreERSchema, "Relationships", ecoreRelationship);
		eReference_RelationshipToSource = refBySimpleToOne(ecoreRelationship, "Source", ecoreEntity);
		eReference_RelationshipToTarget = refBySimpleToOne(ecoreRelationship, "Target", ecoreEntity);




		ecore.addElement(eObject);
		ecore.addElement(eModelElement);
		ecore.addElement(eAnnotation);
		ecore.addElement(eFactory);
		ecore.addElement(eNamedElement);
		ecore.addElement(ePackage);
		ecore.addElement(eClassifier);
		ecore.addElement(eEnumLiteral);
		ecore.addElement(eTypedElement);
		ecore.addElement(eClass);
		ecore.addElement(eDataType);
		ecore.addElement(eEnum);
		ecore.addElement(eStructuralFeature);
		ecore.addElement(eOperation);
		ecore.addElement(eParameter);
		ecore.addElement(eAttribute);
		ecore.addElement(eReference);

		ecoreDataTypes.addElement(eBigDecimal);
		ecoreDataTypes.addElement(eBigInteger);
		ecoreDataTypes.addElement(eBoolean);
		ecoreDataTypes.addElement(eBooleanObject);
		ecoreDataTypes.addElement(eByte);
		ecoreDataTypes.addElement(eByteArray);
		ecoreDataTypes.addElement(eByteObject);
		ecoreDataTypes.addElement(eChar);
		ecoreDataTypes.addElement(eCharacterObject);
		ecoreDataTypes.addElement(eDate);
		ecoreDataTypes.addElement(eDiagnosticChain);
		ecoreDataTypes.addElement(eDouble);
		ecoreDataTypes.addElement(eDoubleObject);
		ecoreDataTypes.addElement(eList);
		ecoreDataTypes.addElement(eEnumerator);
		ecoreDataTypes.addElement(eFeatureMap);
		ecoreDataTypes.addElement(eFeatureMapEntry);
		ecoreDataTypes.addElement(eFloat);
		ecoreDataTypes.addElement(eFloatObject);
		ecoreDataTypes.addElement(eInt);
		ecoreDataTypes.addElement(eIntegerObject);
		ecoreDataTypes.addElement(eJavaClass);
		ecoreDataTypes.addElement(eJavaObject);
		ecoreDataTypes.addElement(eLong);
		ecoreDataTypes.addElement(eLongObject);
		ecoreDataTypes.addElement(eMap);
		ecoreDataTypes.addElement(eResource);
		ecoreDataTypes.addElement(eResourceSet);
		ecoreDataTypes.addElement(eShort);
		ecoreDataTypes.addElement(eShortObject);
		ecoreDataTypes.addElement(eString);
		ecoreDataTypes.addElement(eStringToStringMapEntry);
		ecoreDataTypes.addElement(eTreeIterator);

		EAnnotation.addToVariables(sourceEString);
		EAnnotation.addToVariables(detailsEStringToStringMapEntry);
		ENamedElement.addToVariables(sourceEString);
		EClassifier.addToVariables(instanceClassNameEString);
		EClassifier.addToVariables(instanceClassEJavaClass);
		EClassifier.addToVariables(defaultValueEJavaObject);
		ETypedElement.addToVariables(orderedEBoolean);
		ETypedElement.addToVariables(uniqueEBoolean);
		ETypedElement.addToVariables(lowerBoundEInt);
		ETypedElement.addToVariables(upperBoundEInt);
		ETypedElement.addToVariables(manyEBoolean);
		ETypedElement.addToVariables(requiredEBoolean);
		EPackage.addToVariables(nsURIEString);
		EPackage.addToVariables(nsPrefixEString);
		EClass.addToVariables(abstractEBoolean);
		EClass.addToVariables(interfaceEBoolean);
		EDataType.addToVariables(serializableEBoolean);
		EEnumLiteral.addToVariables(valueEInt);
		EEnumLiteral.addToVariables(instanceEEnumerator);
		EReference.addToVariables(containmentEBoolean);
		EReference.addToVariables(containerEBoolean);
		EReference.addToVariables(resolveProxiesEBoolean);
		EAttribute.addToVariables(iDEBoolean);
		EStructuralFeature.addToVariables(changeableEBoolean);
		EStructuralFeature.addToVariables(volatileEBoolean);
		EStructuralFeature.addToVariables(transientEBoolean);
		EStructuralFeature.addToVariables(defaultValueLiteralEString);
		EStructuralFeature.addToVariables(defaultValueEJavaObject);
		EStructuralFeature.addToVariables(unsettableEBoolean);
		EStructuralFeature.addToVariables(derivedEBoolean);
		EStringToStringMapEntry.addToVariables(keyEString);
		EStringToStringMapEntry.addToVariables(valueEString);


	}

	private final Set linkBySimpleReference(final Set fromElement, final String toRole, final Set toElement, final Set min, final Set max) {
		return Instantiation.arrow(EReference, Instantiation.addDisjunctSemanticIdentitySet("ecore Uni-directional Simple Link", "ecore Uni-directional Simple Links", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, min, max, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set linkBySimpleReference(final Set fromElement, final String fromRole, final String toRole, final Set toElement) {
		return Instantiation.arrow(EReference, Instantiation.addDisjunctSemanticIdentitySet("ecore Bi-directional Simple Link", "ecore Bi-directional Simple Links", EcoreDomain),
				Instantiation.addDisjunctSemanticIdentitySet(fromRole, fromRole, EcoreDomain), fromElement, S23MSemanticDomains.minCardinality_1, S23MSemanticDomains.maxCardinality_1, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_1, S23MSemanticDomains.maxCardinality_1, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set linkByContainment(final Set fromElement, final String fromRole, final String toRole, final Set toElement) {
		return Instantiation.arrow(EReference, Instantiation.addDisjunctSemanticIdentitySet("ecore Bi-directional Containment Link", "ecore Bi-directional Containment Links", EcoreDomain),
				Instantiation.addDisjunctSemanticIdentitySet(fromRole, fromRole, EcoreDomain), fromElement, S23MSemanticDomains.minCardinality_1, S23MSemanticDomains.maxCardinality_1, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_TRUE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set refByContainment(final Set fromElement, final String toRole, final Set toElement) {
		return Instantiation.arrow(eClassReference, Instantiation.addDisjunctSemanticIdentitySet("ecore Containment Reference", "ecore Containment References", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_1, S23MSemanticDomains.maxCardinality_1, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_TRUE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set refBySimpleToOne(final Set fromElement, final String toRole, final Set toElement) {
		return Instantiation.arrow(eClassReference, Instantiation.addDisjunctSemanticIdentitySet("ecore Simple To-One Reference", "ecore Simple To-One References", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_1, S23MSemanticDomains.maxCardinality_1, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

	public void ecoreERMetaModel() {
		// model level
		//final Set ecoreCrmM = F_SemanticStateOfInMemoryModel.instantiateConcrete(ecoreERSchemaMM, identityFactory.createIdentity("ecoreCRM Model"));
		final Set ecoreCrmM = RepositoryStructure.applicationengineering.addConcrete(ecoreERSchemaMM,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreCRM Model", "ecoreCRM Model", EcoreDomain));
		final Set ecoreCrm = ecoreCrmM.addConcrete(ecoreERSchema,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreCRM", "ecoreCRM", EcoreDomain));
		final Set ecoreCustomer = ecoreCrmM.addConcrete(ecoreEntity,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreCustomer", "ecoreCustomer", EcoreDomain));

		final Set ecoreCustomers = refToManyParts(eReference_SchemaToEntities, ecoreCrm, "", ecoreCustomer);
		final Set ecoreAddress = ecoreCrmM.addConcrete(ecoreAttribute,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreAddress", "ecoreAddress", EcoreDomain));
		final Set ecoreCustomerAttribute1 = refToOnePart(eReference_EntityToAttribute, ecoreCustomer, "", ecoreAddress);

		final Set ecoreProduct = ecoreCrmM.addConcrete(ecoreEntity,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreProduct", "ecoreProduct", EcoreDomain));
		final Set ecoreProducts = refToManyParts(eReference_SchemaToEntities, ecoreCrm, "", ecoreProduct);
		final Set ecorePrice = ecoreCrmM.addConcrete(ecoreAttribute,
				Instantiation.addDisjunctSemanticIdentitySet("ecorePrice", "ecorePrice", EcoreDomain));
		final Set ecoreProductAttribute1 = refToOnePart(eReference_EntityToAttribute, ecoreProduct, "", ecorePrice);

		final Set ecoreOrder = ecoreCrmM.addConcrete(ecoreEntity,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreOrder", "ecoreOrder", EcoreDomain));
		final Set ecoreOrders = refToManyParts(eReference_SchemaToEntities, ecoreCrm, "", ecoreOrder);
		final Set ecoreReceivedDate = ecoreCrmM.addConcrete(ecoreAttribute,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreReceivedDate", "ecoreReceivedDate", EcoreDomain));
		final Set ecorePaidDate = ecoreCrmM.addConcrete(ecoreAttribute,
				Instantiation.addDisjunctSemanticIdentitySet("ecorePaidDate", "ecorePaidDate", EcoreDomain));
		final Set ecoreOrderAttribute1 = refToOnePart(eReference_EntityToAttribute, ecoreOrder, "", ecoreReceivedDate);
		final Set ecoreOrderAttribute2 = refToOnePart(eReference_EntityToAttribute, ecoreOrder, "", ecorePaidDate);

		final Set ecoreOrderToCustomer = ecoreCrmM.addConcrete(ecoreRelationship,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreOrderToCustomer", "ecoreOrderToCustomer", EcoreDomain));
		final Set ecoreOrderToCustomerRel = refToManyParts(eReference_SchemaToRelationships, ecoreCrm, "", ecoreOrderToCustomer);
		final Set ecoreOrderToCustomerSource = refToOne(eReference_RelationshipToSource, ecoreOrderToCustomer, "order", ecoreOrder);
		final Set ecoreOrderToCustomerTarget = refToOne(eReference_RelationshipToTarget, ecoreOrderToCustomer, "customer", ecoreCustomer);

		final Set ecoreOrderToProduct = ecoreCrmM.addConcrete(ecoreRelationship,
				Instantiation.addDisjunctSemanticIdentitySet("ecoreOrderToProduct", "ecoreOrderToProduct", EcoreDomain));
		final Set ecoreOrderToProductRel = refToManyParts(eReference_SchemaToRelationships, ecoreCrm, "", ecoreOrderToProduct);
		final Set ecoreOrderToProductSource = refToOne(eReference_RelationshipToSource, ecoreOrderToProduct, "order", ecoreOrder);
		final Set ecoreOrderToProductTarget = refToOne(eReference_RelationshipToTarget, ecoreOrderToProduct, "product", ecoreProduct);

		//instance level
		//final Set ecoreCrmMSofismo = F_SemanticStateOfInMemoryModel.instantiateConcrete(ecoreCrmM, identityFactory.createIdentity("Sofismo Instance"));
		final Set ecoreCrmMSofismo = RepositoryStructure.applicationoperation.addConcrete(ecoreCrmM,
				Instantiation.addDisjunctSemanticIdentitySet("Sofismo Instance", "Sofismo Instance", EcoreDomain));
		final Set ecoreCrmSofismo = ecoreCrmMSofismo.addConcrete(ecoreCrm,
				Instantiation.addDisjunctSemanticIdentitySet("Sofismo", "Sofismo", EcoreDomain));

		final Set ecoreJoeBloggs = ecoreCrmMSofismo.addConcrete(ecoreCustomer,
				Instantiation.addDisjunctSemanticIdentitySet("Joe Bloggs", "Joe Bloggs", EcoreDomain));
		final Set ecoreSofismoCustomer1 = refToInstancePart(ecoreCustomers, ecoreCrmSofismo, "", ecoreJoeBloggs);

		final Set ecoreLife = ecoreCrmMSofismo.addConcrete(ecoreProduct,
				Instantiation.addDisjunctSemanticIdentitySet("BasicLifeInsurance", "BasicLifeInsurance", EcoreDomain));
		final Set ecoreSofismoProduct1 = refToInstancePart(ecoreProducts, ecoreCrmSofismo, "", ecoreLife);

		final Set ecoreJoeBloggsLife = ecoreCrmMSofismo.addConcrete(ecoreOrder,
				Instantiation.addDisjunctSemanticIdentitySet("123456", "123456", EcoreDomain));
		final Set ecoreSofismoOrder1 = refToInstancePart(ecoreOrders, ecoreCrmSofismo, "", ecoreJoeBloggsLife);

		final Set ecoreLifeToJoeBoggs = ecoreCrmMSofismo.addConcrete(ecoreOrderToCustomer,
				Instantiation.addDisjunctSemanticIdentitySet("123456--JoeBoggs", "123456--JoeBoggs", EcoreDomain));
		final Set ecoreSofismoOrderToCustomerRel = refToInstancePart(ecoreOrderToCustomerRel, ecoreCrmSofismo, "", ecoreLifeToJoeBoggs);
		final Set ecoreSofismoOrderToCustomerSource = refToInstance(ecoreOrderToCustomerSource, ecoreLifeToJoeBoggs, "", ecoreJoeBloggsLife);
		final Set ecoreSofismoOrderToCustomerTarget = refToInstance(ecoreOrderToCustomerTarget, ecoreLifeToJoeBoggs, "", ecoreJoeBloggs);

		final Set ecoreLifeToSofismoProduct1 = ecoreCrmMSofismo.addConcrete(ecoreOrderToProduct,
				Instantiation.addDisjunctSemanticIdentitySet("123456--BasicLifeInsurance", "123456--BasicLifeInsurance", EcoreDomain));
		final Set ecoreSofismoOrderToProduct1Rel = refToInstancePart(ecoreOrderToProductRel, ecoreCrmSofismo, "", ecoreLifeToSofismoProduct1);
		final Set ecoreSofismoOrderToCustomer1Source = refToInstance(ecoreOrderToProductSource, ecoreLifeToSofismoProduct1, "", ecoreJoeBloggsLife);
		final Set ecoreSofismoOrderToCustomer1Target = refToInstance(ecoreOrderToProductTarget, ecoreLifeToSofismoProduct1, "", ecoreLife);

	}

	private final Set refToInstance(final Set refType, final Set fromElement, final String toRole, final Set toElement) {
		return Instantiation.arrow(refType, Instantiation.addDisjunctSemanticIdentitySet("ecore Reference to Instance", "ecore Reference to Instances", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set refToInstancePart(final Set refType, final Set fromElement, final String toRole, final Set toElement) {
		return Instantiation.arrow(refType,Instantiation.addDisjunctSemanticIdentitySet("ecore Reference to Instance Part", "ecore Reference to Instance Parts", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_TRUE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_NOTAPPLICABLE, S23MSemanticDomains.maxCardinality_NOTAPPLICABLE, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set refToOne(final Set refType, final Set fromElement, final String toRole, final Set toElement) {
		return Instantiation.arrow(refType, Instantiation.addDisjunctSemanticIdentitySet("ecore Reference to One", "set of ecore Reference to One", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_1, S23MSemanticDomains.maxCardinality_1, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set refToOnePart(final Set refType, final Set fromElement, final String toRole, final Set toElement) {
		return Instantiation.arrow(refType, Instantiation.addDisjunctSemanticIdentitySet("ecore Reference to One Part", "set of ecore Reference to One Part", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_1, S23MSemanticDomains.maxCardinality_1, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_TRUE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_1, S23MSemanticDomains.maxCardinality_1, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set refToMany(final Set refType, final Set fromElement, final String toRole, final Set toElement) {
		return Instantiation.arrow(refType, Instantiation.addDisjunctSemanticIdentitySet("ecore Reference to Many", "set of ecore Reference to Many", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_FALSE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}

	private final Set refToManyParts(final Set refType, final Set fromElement, final String toRole, final Set toElement) {
		return Instantiation.arrow(refType, Instantiation.addDisjunctSemanticIdentitySet("ecore Reference to Many Parts", "set of ecore Reference to Many Parts", EcoreDomain),
				fromElement, fromElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_FALSE, S23MSemanticDomains.isContainer_TRUE,
				Instantiation.addDisjunctSemanticIdentitySet(toRole, toRole, EcoreDomain), toElement, S23MSemanticDomains.minCardinality_0, S23MSemanticDomains.maxCardinality_n, S23MSemanticDomains.isNavigable_TRUE, S23MSemanticDomains.isContainer_FALSE);
	}
}
