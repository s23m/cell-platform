package org.s23m.cell.communication.xml;

import java.util.UUID;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
import org.s23m.cell.communication.xml.IdentityReferenceAttributes;
import org.s23m.cell.communication.xml.NamespaceConstants;
import org.s23m.cell.communication.xml.NamespaceExtensions;
import org.s23m.cell.communication.xml.XmlSchemaTerminology;
import org.s23m.cell.communication.xml.model.dom.Namespace;
import org.s23m.cell.communication.xml.model.schemainstance.ArtifactSet;
import org.s23m.cell.communication.xml.model.schemainstance.CategoryIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Command;
import org.s23m.cell.communication.xml.model.schemainstance.ContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Edge;
import org.s23m.cell.communication.xml.model.schemainstance.EdgeEnd;
import org.s23m.cell.communication.xml.model.schemainstance.FromIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Identity;
import org.s23m.cell.communication.xml.model.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsNavigableIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.LanguageIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MaximumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MinimumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.Parameter;
import org.s23m.cell.communication.xml.model.schemainstance.Query;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticDomainNode;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticIdentityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.ToIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;

@SuppressWarnings("all")
public class InstanceBuilder {
  private Set chosenLanguage;
  
  private Namespace namespace;
  
  private XmlSchemaTerminology terminology;
  
  private boolean populateIdentityNameAttributes;
  
  public InstanceBuilder(final Namespace namespace, final XmlSchemaTerminology terminology, final Set chosenLanguage) {
    this.namespace = namespace;
    this.terminology = terminology;
    this.chosenLanguage = chosenLanguage;
    boolean _isMachineEncoding = terminology.isMachineEncoding();
    boolean _not = (!_isMachineEncoding);
    this.populateIdentityNameAttributes = _not;
  }
  
  public ArtifactSet artifactSet() {
    ArtifactSet _xblockexpression = null;
    {
      final LanguageIdentityReference languageReference = this.language(this.chosenLanguage);
      final ArtifactSet result = new ArtifactSet(this.namespace, this.terminology, languageReference);
      String _xmlns = NamespaceExtensions.xmlns(NamespaceConstants.INSTANCE_NAMESPACE_PREFIX);
      result.setAttribute(_xmlns, NamespaceConstants.INSTANCE_SCHEMA_URI);
      String _xmlns_1 = NamespaceExtensions.xmlns(NamespaceConstants.S23M);
      result.setAttribute(_xmlns_1, NamespaceConstants.S23M_SCHEMA_URI);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  /**
   * Model
   */
  public Model model(final Set set) {
    Model _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      final ContainerIdentityReference container = this.container(set);
      final IsAbstractIdentityReference isAbstract = this.isAbstract(set);
      _xblockexpression = this.model(semanticIdentity, category, container, isAbstract);
    }
    return _xblockexpression;
  }
  
  public Model model(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final ContainerIdentityReference container, final IsAbstractIdentityReference isAbstract) {
    Model _xblockexpression = null;
    {
      final Model result = new Model(
        this.namespace, 
        this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      result.setContainer(container);
      result.setIsAbstract(isAbstract);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  /**
   * Semantic Domain
   */
  public SemanticDomainNode semanticDomain(final Set set) {
    SemanticDomainNode _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      final ContainerIdentityReference container = this.container(set);
      final IsAbstractIdentityReference isAbstract = this.isAbstract(set);
      _xblockexpression = this.semanticDomain(semanticIdentity, category, container, isAbstract);
    }
    return _xblockexpression;
  }
  
  public SemanticDomainNode semanticDomain(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final ContainerIdentityReference container, final IsAbstractIdentityReference isAbstract) {
    SemanticDomainNode _xblockexpression = null;
    {
      final SemanticDomainNode result = new SemanticDomainNode(this.namespace, this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      result.setContainer(container);
      result.setIsAbstract(isAbstract);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public Vertex vertex(final Set set) {
    Vertex _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      final IsAbstractIdentityReference isAbstract = this.isAbstract(set);
      final MaximumCardinalityIdentityReference maxCardinality = this.maxCardinality(set);
      _xblockexpression = this.vertex(semanticIdentity, category, isAbstract, maxCardinality);
    }
    return _xblockexpression;
  }
  
  public Vertex vertex(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final MaximumCardinalityIdentityReference maxCardinality) {
    Vertex _xblockexpression = null;
    {
      final Vertex result = new Vertex(this.namespace, this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      result.setIsAbstract(isAbstract);
      result.setMaxCardinality(maxCardinality);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public Visibility visibility(final Set set) {
    Visibility _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      final IsAbstractIdentityReference isAbstract = this.isAbstract(set);
      Set _from = set.from();
      final FromIdentityReference from = this.from(_from);
      Set _to = set.to();
      final ToIdentityReference to = this.to(_to);
      _xblockexpression = this.visibility(semanticIdentity, category, isAbstract, from, to);
    }
    return _xblockexpression;
  }
  
  public Visibility visibility(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final FromIdentityReference from, final ToIdentityReference to) {
    Visibility _xblockexpression = null;
    {
      final Visibility result = new Visibility(this.namespace, this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      result.setIsAbstract(isAbstract);
      result.setFrom(from);
      result.setTo(to);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public Edge edge(final Set set) {
    Edge _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      final IsAbstractIdentityReference isAbstract = this.isAbstract(set);
      Set _fromEdgeEnd = set.fromEdgeEnd();
      final EdgeEnd from = this.fromEdgeEnd(_fromEdgeEnd);
      Set _edgeEnd = set.toEdgeEnd();
      final EdgeEnd to = this.toEdgeEnd(_edgeEnd);
      _xblockexpression = this.edge(semanticIdentity, category, isAbstract, from, to);
    }
    return _xblockexpression;
  }
  
  public Edge edge(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final EdgeEnd from, final EdgeEnd to) {
    Edge _xblockexpression = null;
    {
      final Edge result = new Edge(
        this.namespace, 
        this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      result.setIsAbstract(isAbstract);
      result.setFrom(from);
      result.setTo(to);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public EdgeEnd toEdgeEnd(final Set set) {
    EdgeEnd _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      final IsAbstractIdentityReference isAbstract = this.isAbstract(set);
      final MinimumCardinalityIdentityReference minCardinality = this.minCardinality(set);
      final MaximumCardinalityIdentityReference maxCardinality = this.maxCardinality(set);
      final IsContainerIdentityReference isContainer = this.isContainer(set);
      final IsNavigableIdentityReference isNavigable = this.isNavigable(set);
      _xblockexpression = this.toEdgeEnd(semanticIdentity, category, isAbstract, minCardinality, maxCardinality, isContainer, isNavigable);
    }
    return _xblockexpression;
  }
  
  public EdgeEnd fromEdgeEnd(final Set set) {
    EdgeEnd _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      final IsAbstractIdentityReference isAbstract = this.isAbstract(set);
      final MinimumCardinalityIdentityReference minCardinality = this.minCardinality(set);
      final MaximumCardinalityIdentityReference maxCardinality = this.maxCardinality(set);
      final IsContainerIdentityReference isContainer = this.isContainer(set);
      final IsNavigableIdentityReference isNavigable = this.isNavigable(set);
      _xblockexpression = this.fromEdgeEnd(semanticIdentity, category, isAbstract, minCardinality, maxCardinality, isContainer, isNavigable);
    }
    return _xblockexpression;
  }
  
  public EdgeEnd toEdgeEnd(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final MinimumCardinalityIdentityReference minCardinality, final MaximumCardinalityIdentityReference maxCardinality, final IsContainerIdentityReference isContainer, final IsNavigableIdentityReference isNavigable) {
    EdgeEnd _edgeEnd = EdgeEnd.toEdgeEnd(this.namespace, this.terminology);
    return this.initialise(_edgeEnd, semanticIdentity, category, isAbstract, minCardinality, maxCardinality, isContainer, isNavigable);
  }
  
  public EdgeEnd fromEdgeEnd(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final MinimumCardinalityIdentityReference minCardinality, final MaximumCardinalityIdentityReference maxCardinality, final IsContainerIdentityReference isContainer, final IsNavigableIdentityReference isNavigable) {
    EdgeEnd _fromEdgeEnd = EdgeEnd.fromEdgeEnd(this.namespace, this.terminology);
    return this.initialise(_fromEdgeEnd, semanticIdentity, category, isAbstract, minCardinality, maxCardinality, isContainer, isNavigable);
  }
  
  private EdgeEnd initialise(final EdgeEnd result, final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final MinimumCardinalityIdentityReference minCardinality, final MaximumCardinalityIdentityReference maxCardinality, final IsContainerIdentityReference isContainer, final IsNavigableIdentityReference isNavigable) {
    EdgeEnd _xblockexpression = null;
    {
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      result.setIsAbstract(isAbstract);
      result.setMinCardinality(minCardinality);
      result.setMaxCardinality(maxCardinality);
      result.setIsContainer(isContainer);
      result.setIsNavigable(isNavigable);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public SuperSetReference superSetReference(final Set set) {
    SuperSetReference _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      final IsAbstractIdentityReference isAbstract = this.isAbstract(set);
      Set _from = set.from();
      final FromIdentityReference from = this.from(_from);
      Set _to = set.to();
      final ToIdentityReference to = this.to(_to);
      _xblockexpression = this.superSetReference(semanticIdentity, category, isAbstract, from, to);
    }
    return _xblockexpression;
  }
  
  public SuperSetReference superSetReference(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final FromIdentityReference from, final ToIdentityReference to) {
    SuperSetReference _xblockexpression = null;
    {
      final SuperSetReference result = new SuperSetReference(this.namespace, this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      result.setIsAbstract(isAbstract);
      result.setFrom(from);
      result.setTo(to);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public Command command(final Set set) {
    Command _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      _xblockexpression = this.command(semanticIdentity, category);
    }
    return _xblockexpression;
  }
  
  public Command command(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category) {
    Command _xblockexpression = null;
    {
      final Command result = new Command(this.namespace, this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public Query query(final Set set) {
    Query _xblockexpression = null;
    {
      final SemanticIdentityIdentityReference semanticIdentity = this.semanticIdentity(set);
      final CategoryIdentityReference category = this.category(set);
      _xblockexpression = this.query(semanticIdentity, category);
    }
    return _xblockexpression;
  }
  
  public Query query(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category) {
    Query _xblockexpression = null;
    {
      final Query result = new Query(this.namespace, this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public Parameter parameter(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category) {
    Parameter _xblockexpression = null;
    {
      final Parameter result = new Parameter(this.namespace, this.terminology);
      result.setSemanticIdentity(semanticIdentity);
      result.setCategory(category);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  public SemanticIdentityIdentityReference semanticIdentity(final Set set) {
    SemanticIdentityIdentityReference _xblockexpression = null;
    {
      Set _category = set.category();
      final IdentityReferenceAttributes identityTriple = this.identityReference(_category);
      _xblockexpression = new SemanticIdentityIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public CategoryIdentityReference category(final Set set) {
    CategoryIdentityReference _xblockexpression = null;
    {
      Set _category = set.category();
      final IdentityReferenceAttributes identityTriple = this.identityReference(_category);
      _xblockexpression = new CategoryIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public ContainerIdentityReference container(final Set set) {
    ContainerIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes identityTriple = this.identityReference(set);
      _xblockexpression = new ContainerIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public IsAbstractIdentityReference isAbstract(final Set set) {
    IsAbstractIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes identityTriple = this.valueIdentityReference(set, S23MSemanticDomains.isAbstract);
      _xblockexpression = new IsAbstractIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public FromIdentityReference from(final Set set) {
    FromIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes identityTriple = this.identityReference(set);
      _xblockexpression = new FromIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public ToIdentityReference to(final Set set) {
    ToIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes identityTriple = this.identityReference(set);
      _xblockexpression = new ToIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public LanguageIdentityReference language(final Set set) {
    LanguageIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes ref = this.identityReference(set);
      _xblockexpression = new LanguageIdentityReference(
        this.namespace, 
        this.terminology, 
        ref.uniqueRepresentationReference, 
        ref.identifier, 
        ref.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public MaximumCardinalityIdentityReference maxCardinality(final Set set) {
    MaximumCardinalityIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes identityTriple = this.valueIdentityReference(set, S23MSemanticDomains.maxCardinality);
      _xblockexpression = new MaximumCardinalityIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public MinimumCardinalityIdentityReference minCardinality(final Set set) {
    MinimumCardinalityIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes identityTriple = this.valueIdentityReference(set, S23MSemanticDomains.minCardinality);
      _xblockexpression = new MinimumCardinalityIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public IsContainerIdentityReference isContainer(final Set set) {
    IsContainerIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes identityTriple = this.valueIdentityReference(set, S23MSemanticDomains.isContainer);
      _xblockexpression = new IsContainerIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public IsNavigableIdentityReference isNavigable(final Set set) {
    IsNavigableIdentityReference _xblockexpression = null;
    {
      final IdentityReferenceAttributes identityTriple = this.valueIdentityReference(set, S23MSemanticDomains.isNavigable);
      _xblockexpression = new IsNavigableIdentityReference(
        this.namespace, 
        this.terminology, 
        identityTriple.uniqueRepresentationReference, 
        identityTriple.identifier, 
        identityTriple.nameAttribute);
    }
    return _xblockexpression;
  }
  
  public Identity identity(final Set set) {
    Identity _xblockexpression = null;
    {
      final Identity result = new Identity(this.namespace, this.terminology);
      final org.s23m.cell.Identity identity = set.identity();
      UUID _identifier = identity.identifier();
      String _string = _identifier.toString();
      result.setIdentifier(_string);
      String _name = identity.name();
      result.setNameAttribute(_name);
      String _pluralName = identity.pluralName();
      result.setPluralName(_pluralName);
      String _codeName = identity.codeName();
      result.setCodeName(_codeName);
      String _pluralCodeName = identity.pluralCodeName();
      result.setPluralCodeName(_pluralCodeName);
      String _payload = identity.payload();
      result.setPayload(_payload);
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  private IdentityReferenceAttributes valueIdentityReference(final Set set, final Set variable) {
    IdentityReferenceAttributes _xblockexpression = null;
    {
      final Set retrievedValue = set.value(variable);
      _xblockexpression = this.identityReference(retrievedValue);
    }
    return _xblockexpression;
  }
  
  private IdentityReferenceAttributes identityReference(final Set set) {
    IdentityReferenceAttributes _xblockexpression = null;
    {
      final org.s23m.cell.Identity identity = set.identity();
      UUID _uniqueRepresentationReference = identity.uniqueRepresentationReference();
      final String uniqueRepresentationReference = this.uuid(_uniqueRepresentationReference);
      UUID _identifier = identity.identifier();
      final String identifier = this.uuid(_identifier);
      String _xifexpression = null;
      if (this.populateIdentityNameAttributes) {
        _xifexpression = identity.name();
      } else {
        _xifexpression = null;
      }
      final String nameAttribute = _xifexpression;
      _xblockexpression = new IdentityReferenceAttributes(uniqueRepresentationReference, identifier, nameAttribute);
    }
    return _xblockexpression;
  }
  
  private String uuid(final UUID uuid) {
    return uuid.toString();
  }
}
