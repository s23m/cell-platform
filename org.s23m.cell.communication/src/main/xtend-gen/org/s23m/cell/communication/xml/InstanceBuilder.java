package org.s23m.cell.communication.xml;

import java.util.UUID;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.s23m.cell.Identity;
import org.s23m.cell.Set;
import org.s23m.cell.api.models.S23MSemanticDomains;
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
import org.s23m.cell.communication.xml.model.schemainstance.IsAbstractIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsContainerIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.IsNavigableIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MaximumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.MinimumCardinalityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Model;
import org.s23m.cell.communication.xml.model.schemainstance.Query;
import org.s23m.cell.communication.xml.model.schemainstance.SemanticIdentityIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.SuperSetReference;
import org.s23m.cell.communication.xml.model.schemainstance.ToIdentityReference;
import org.s23m.cell.communication.xml.model.schemainstance.Vertex;
import org.s23m.cell.communication.xml.model.schemainstance.Visibility;

@SuppressWarnings("all")
public class InstanceBuilder {
  private ArtifactSet artifactSet;
  
  private Namespace namespace;
  
  private XmlSchemaTerminology terminology;
  
  public InstanceBuilder(final Namespace namespace, final XmlSchemaTerminology terminology, final String languageIdentifier) {
      this.namespace = namespace;
      this.terminology = terminology;
      ArtifactSet _artifactSet = new ArtifactSet(namespace, terminology, languageIdentifier);
      this.artifactSet = _artifactSet;
      String _xmlns = NamespaceExtensions.xmlns(NamespaceConstants.INSTANCE_NAMESPACE_PREFIX);
      this.artifactSet.setAttribute(_xmlns, NamespaceConstants.INSTANCE_SCHEMA_URI);
      String _xmlns_1 = NamespaceExtensions.xmlns(NamespaceConstants.S23M);
      this.artifactSet.setAttribute(_xmlns_1, NamespaceConstants.S23M_SCHEMA_URI);
  }
  
  public ArtifactSet build() {
    return this.artifactSet;
  }
  
  public Model model(final Set set) {
    Model _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(set);
      final SemanticIdentityIdentityReference semanticIdentity = _semanticIdentity;
      CategoryIdentityReference _category = this.category(set);
      final CategoryIdentityReference category = _category;
      ContainerIdentityReference _container = this.container(set);
      final ContainerIdentityReference container = _container;
      IsAbstractIdentityReference _isAbstract = this.isAbstract(set);
      final IsAbstractIdentityReference isAbstract = _isAbstract;
      Model _model = this.model(semanticIdentity, category, container, isAbstract);
      _xblockexpression = (_model);
    }
    return _xblockexpression;
  }
  
  public Model model(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final ContainerIdentityReference container, final IsAbstractIdentityReference isAbstract) {
    Model _xblockexpression = null;
    {
      Model _model = new Model(this.namespace, this.terminology, semanticIdentity, category, container, isAbstract);
      final Model result = _model;
      this.artifactSet.addModel(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public Vertex vertex(final Set set) {
    Vertex _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(set);
      final SemanticIdentityIdentityReference semanticIdentity = _semanticIdentity;
      CategoryIdentityReference _category = this.category(set);
      final CategoryIdentityReference category = _category;
      IsAbstractIdentityReference _isAbstract = this.isAbstract(set);
      final IsAbstractIdentityReference isAbstract = _isAbstract;
      MaximumCardinalityIdentityReference _maxCardinality = this.maxCardinality(set);
      final MaximumCardinalityIdentityReference maxCardinality = _maxCardinality;
      Vertex _vertex = this.vertex(semanticIdentity, category, isAbstract, maxCardinality);
      _xblockexpression = (_vertex);
    }
    return _xblockexpression;
  }
  
  public Vertex vertex(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final MaximumCardinalityIdentityReference maxCardinality) {
    Vertex _vertex = new Vertex(this.namespace, this.terminology, semanticIdentity, category, isAbstract, maxCardinality);
    return _vertex;
  }
  
  public Visibility visibility(final Set set) {
    Visibility _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(set);
      final SemanticIdentityIdentityReference semanticIdentity = _semanticIdentity;
      CategoryIdentityReference _category = this.category(set);
      final CategoryIdentityReference category = _category;
      IsAbstractIdentityReference _isAbstract = this.isAbstract(set);
      final IsAbstractIdentityReference isAbstract = _isAbstract;
      Set _from = set.from();
      FromIdentityReference _from_1 = this.from(_from);
      final FromIdentityReference from = _from_1;
      Set _to = set.to();
      ToIdentityReference _to_1 = this.to(_to);
      final ToIdentityReference to = _to_1;
      Visibility _visibility = this.visibility(semanticIdentity, category, isAbstract, from, to);
      _xblockexpression = (_visibility);
    }
    return _xblockexpression;
  }
  
  public Visibility visibility(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final FromIdentityReference from, final ToIdentityReference to) {
    Visibility _visibility = new Visibility(this.namespace, this.terminology, semanticIdentity, category, isAbstract, from, to);
    return _visibility;
  }
  
  public Edge edge(final Set set) {
    Edge _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(set);
      final SemanticIdentityIdentityReference semanticIdentity = _semanticIdentity;
      CategoryIdentityReference _category = this.category(set);
      final CategoryIdentityReference category = _category;
      IsAbstractIdentityReference _isAbstract = this.isAbstract(set);
      final IsAbstractIdentityReference isAbstract = _isAbstract;
      Set _fromEdgeEnd = set.fromEdgeEnd();
      EdgeEnd _edgeEnd = this.edgeEnd(_fromEdgeEnd);
      final EdgeEnd from = _edgeEnd;
      Set _edgeEnd_1 = set.toEdgeEnd();
      EdgeEnd _edgeEnd_2 = this.edgeEnd(_edgeEnd_1);
      final EdgeEnd to = _edgeEnd_2;
      Edge _edge = this.edge(semanticIdentity, category, isAbstract, from, to);
      _xblockexpression = (_edge);
    }
    return _xblockexpression;
  }
  
  public Edge edge(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final EdgeEnd from, final EdgeEnd to) {
    Edge _edge = new Edge(this.namespace, this.terminology, semanticIdentity, category, isAbstract, from, to);
    return _edge;
  }
  
  public EdgeEnd edgeEnd(final Set set) {
    EdgeEnd _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(set);
      final SemanticIdentityIdentityReference semanticIdentity = _semanticIdentity;
      CategoryIdentityReference _category = this.category(set);
      final CategoryIdentityReference category = _category;
      IsAbstractIdentityReference _isAbstract = this.isAbstract(set);
      final IsAbstractIdentityReference isAbstract = _isAbstract;
      MinimumCardinalityIdentityReference _minCardinality = this.minCardinality(set);
      final MinimumCardinalityIdentityReference minCardinality = _minCardinality;
      MaximumCardinalityIdentityReference _maxCardinality = this.maxCardinality(set);
      final MaximumCardinalityIdentityReference maxCardinality = _maxCardinality;
      IsContainerIdentityReference _isContainer = this.isContainer(set);
      final IsContainerIdentityReference isContainer = _isContainer;
      IsNavigableIdentityReference _isNavigable = this.isNavigable(set);
      final IsNavigableIdentityReference isNavigable = _isNavigable;
      EdgeEnd _edgeEnd = this.edgeEnd(semanticIdentity, category, isAbstract, minCardinality, maxCardinality, isContainer, isNavigable);
      _xblockexpression = (_edgeEnd);
    }
    return _xblockexpression;
  }
  
  public EdgeEnd edgeEnd(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final MinimumCardinalityIdentityReference minCardinality, final MaximumCardinalityIdentityReference maxCardinality, final IsContainerIdentityReference isContainer, final IsNavigableIdentityReference isNavigable) {
    EdgeEnd _edgeEnd = new EdgeEnd(this.namespace, this.terminology, semanticIdentity, category, isAbstract, minCardinality, maxCardinality, isContainer, isNavigable);
    return _edgeEnd;
  }
  
  public SuperSetReference superSetReference(final Set set) {
    SuperSetReference _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(set);
      final SemanticIdentityIdentityReference semanticIdentity = _semanticIdentity;
      CategoryIdentityReference _category = this.category(set);
      final CategoryIdentityReference category = _category;
      IsAbstractIdentityReference _isAbstract = this.isAbstract(set);
      final IsAbstractIdentityReference isAbstract = _isAbstract;
      Set _from = set.from();
      FromIdentityReference _from_1 = this.from(_from);
      final FromIdentityReference from = _from_1;
      Set _to = set.to();
      ToIdentityReference _to_1 = this.to(_to);
      final ToIdentityReference to = _to_1;
      SuperSetReference _superSetReference = this.superSetReference(semanticIdentity, category, isAbstract, from, to);
      _xblockexpression = (_superSetReference);
    }
    return _xblockexpression;
  }
  
  public SuperSetReference superSetReference(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category, final IsAbstractIdentityReference isAbstract, final FromIdentityReference from, final ToIdentityReference to) {
    SuperSetReference _superSetReference = new SuperSetReference(this.namespace, this.terminology, semanticIdentity, category, isAbstract, from, to);
    return _superSetReference;
  }
  
  public Command command(final Set set) {
    Command _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(set);
      final SemanticIdentityIdentityReference semanticIdentity = _semanticIdentity;
      CategoryIdentityReference _category = this.category(set);
      final CategoryIdentityReference category = _category;
      Command _command = this.command(semanticIdentity, category);
      _xblockexpression = (_command);
    }
    return _xblockexpression;
  }
  
  public Command command(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category) {
    Command _command = new Command(this.namespace, this.terminology, semanticIdentity, category);
    return _command;
  }
  
  public Query query(final Set set) {
    Query _xblockexpression = null;
    {
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(set);
      final SemanticIdentityIdentityReference semanticIdentity = _semanticIdentity;
      CategoryIdentityReference _category = this.category(set);
      final CategoryIdentityReference category = _category;
      Query _query = this.query(semanticIdentity, category);
      _xblockexpression = (_query);
    }
    return _xblockexpression;
  }
  
  public Query query(final SemanticIdentityIdentityReference semanticIdentity, final CategoryIdentityReference category) {
    Query _query = new Query(this.namespace, this.terminology, semanticIdentity, category);
    return _query;
  }
  
  public SemanticIdentityIdentityReference semanticIdentity(final Set set) {
    SemanticIdentityIdentityReference _xblockexpression = null;
    {
      Identity _identity = set.identity();
      final Identity identity = _identity;
      Pair<String,String> _identityPair = this.identityPair(identity);
      SemanticIdentityIdentityReference _semanticIdentity = this.semanticIdentity(_identityPair);
      _xblockexpression = (_semanticIdentity);
    }
    return _xblockexpression;
  }
  
  public SemanticIdentityIdentityReference semanticIdentity(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    SemanticIdentityIdentityReference _semanticIdentityIdentityReference = new SemanticIdentityIdentityReference(this.namespace, this.terminology, _key, _value);
    return _semanticIdentityIdentityReference;
  }
  
  public CategoryIdentityReference category(final Set set) {
    CategoryIdentityReference _xblockexpression = null;
    {
      Set _category = set.category();
      Identity _identity = _category.identity();
      final Identity identity = _identity;
      Pair<String,String> _identityPair = this.identityPair(identity);
      CategoryIdentityReference _category_1 = this.category(_identityPair);
      _xblockexpression = (_category_1);
    }
    return _xblockexpression;
  }
  
  public CategoryIdentityReference category(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    CategoryIdentityReference _categoryIdentityReference = new CategoryIdentityReference(this.namespace, this.terminology, _key, _value);
    return _categoryIdentityReference;
  }
  
  public ContainerIdentityReference container(final Set set) {
    ContainerIdentityReference _xblockexpression = null;
    {
      Set _container = set.container();
      Identity _identity = _container.identity();
      final Identity identity = _identity;
      Pair<String,String> _identityPair = this.identityPair(identity);
      ContainerIdentityReference _container_1 = this.container(_identityPair);
      _xblockexpression = (_container_1);
    }
    return _xblockexpression;
  }
  
  public ContainerIdentityReference container(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    ContainerIdentityReference _containerIdentityReference = new ContainerIdentityReference(this.namespace, this.terminology, _key, _value);
    return _containerIdentityReference;
  }
  
  public IsAbstractIdentityReference isAbstract(final Set set) {
    IsAbstractIdentityReference _xblockexpression = null;
    {
      Pair<String,String> _valueIdentityPair = this.valueIdentityPair(set, S23MSemanticDomains.isAbstract);
      final Pair<String,String> pair = _valueIdentityPair;
      IsAbstractIdentityReference _isAbstract = this.isAbstract(pair);
      _xblockexpression = (_isAbstract);
    }
    return _xblockexpression;
  }
  
  public IsAbstractIdentityReference isAbstract(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    IsAbstractIdentityReference _isAbstractIdentityReference = new IsAbstractIdentityReference(this.namespace, this.terminology, _key, _value);
    return _isAbstractIdentityReference;
  }
  
  public FromIdentityReference from(final Set set) {
    FromIdentityReference _xblockexpression = null;
    {
      Identity _identity = set.identity();
      final Identity identity = _identity;
      Pair<String,String> _identityPair = this.identityPair(identity);
      FromIdentityReference _from = this.from(_identityPair);
      _xblockexpression = (_from);
    }
    return _xblockexpression;
  }
  
  public FromIdentityReference from(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    FromIdentityReference _fromIdentityReference = new FromIdentityReference(this.namespace, this.terminology, _key, _value);
    return _fromIdentityReference;
  }
  
  public ToIdentityReference to(final Set set) {
    ToIdentityReference _xblockexpression = null;
    {
      Identity _identity = set.identity();
      final Identity identity = _identity;
      Pair<String,String> _identityPair = this.identityPair(identity);
      ToIdentityReference _to = this.to(_identityPair);
      _xblockexpression = (_to);
    }
    return _xblockexpression;
  }
  
  public ToIdentityReference to(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    ToIdentityReference _toIdentityReference = new ToIdentityReference(this.namespace, this.terminology, _key, _value);
    return _toIdentityReference;
  }
  
  public MaximumCardinalityIdentityReference maxCardinality(final Set set) {
    MaximumCardinalityIdentityReference _xblockexpression = null;
    {
      Pair<String,String> _valueIdentityPair = this.valueIdentityPair(set, S23MSemanticDomains.maxCardinality);
      final Pair<String,String> pair = _valueIdentityPair;
      MaximumCardinalityIdentityReference _maxCardinality = this.maxCardinality(pair);
      _xblockexpression = (_maxCardinality);
    }
    return _xblockexpression;
  }
  
  public MaximumCardinalityIdentityReference maxCardinality(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    MaximumCardinalityIdentityReference _maximumCardinalityIdentityReference = new MaximumCardinalityIdentityReference(this.namespace, this.terminology, _key, _value);
    return _maximumCardinalityIdentityReference;
  }
  
  public MinimumCardinalityIdentityReference minCardinality(final Set set) {
    MinimumCardinalityIdentityReference _xblockexpression = null;
    {
      Pair<String,String> _valueIdentityPair = this.valueIdentityPair(set, S23MSemanticDomains.minCardinality);
      final Pair<String,String> pair = _valueIdentityPair;
      MinimumCardinalityIdentityReference _minCardinality = this.minCardinality(pair);
      _xblockexpression = (_minCardinality);
    }
    return _xblockexpression;
  }
  
  public MinimumCardinalityIdentityReference minCardinality(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    MinimumCardinalityIdentityReference _minimumCardinalityIdentityReference = new MinimumCardinalityIdentityReference(this.namespace, this.terminology, _key, _value);
    return _minimumCardinalityIdentityReference;
  }
  
  public IsContainerIdentityReference isContainer(final Set set) {
    IsContainerIdentityReference _xblockexpression = null;
    {
      Pair<String,String> _valueIdentityPair = this.valueIdentityPair(set, S23MSemanticDomains.isContainer);
      final Pair<String,String> pair = _valueIdentityPair;
      IsContainerIdentityReference _isContainer = this.isContainer(pair);
      _xblockexpression = (_isContainer);
    }
    return _xblockexpression;
  }
  
  public IsContainerIdentityReference isContainer(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    IsContainerIdentityReference _isContainerIdentityReference = new IsContainerIdentityReference(this.namespace, this.terminology, _key, _value);
    return _isContainerIdentityReference;
  }
  
  public IsNavigableIdentityReference isNavigable(final Set set) {
    IsNavigableIdentityReference _xblockexpression = null;
    {
      Pair<String,String> _valueIdentityPair = this.valueIdentityPair(set, S23MSemanticDomains.isNavigable);
      final Pair<String,String> pair = _valueIdentityPair;
      IsNavigableIdentityReference _isNavigable = this.isNavigable(pair);
      _xblockexpression = (_isNavigable);
    }
    return _xblockexpression;
  }
  
  public IsNavigableIdentityReference isNavigable(final Pair<String,String> pair) {
    String _key = pair.getKey();
    String _value = pair.getValue();
    IsNavigableIdentityReference _isNavigableIdentityReference = new IsNavigableIdentityReference(this.namespace, this.terminology, _key, _value);
    return _isNavigableIdentityReference;
  }
  
  private Pair<String,String> valueIdentityPair(final Set set, final Set variable) {
    Pair<String,String> _xblockexpression = null;
    {
      Set _value = set.value(variable);
      final Set retrievedValue = _value;
      Identity _identity = retrievedValue.identity();
      final Identity identity = _identity;
      Pair<String,String> _identityPair = this.identityPair(identity);
      _xblockexpression = (_identityPair);
    }
    return _xblockexpression;
  }
  
  private Pair<String,String> identityPair(final Identity identity) {
    UUID _uniqueRepresentationReference = identity.uniqueRepresentationReference();
    String _uuid = this.uuid(_uniqueRepresentationReference);
    UUID _identifier = identity.identifier();
    String _uuid_1 = this.uuid(_identifier);
    Pair<String,String> _operator_mappedTo = ObjectExtensions.<String, String>operator_mappedTo(_uuid, _uuid_1);
    return _operator_mappedTo;
  }
  
  private String uuid(final UUID uuid) {
    String _string = uuid.toString();
    return _string;
  }
}
