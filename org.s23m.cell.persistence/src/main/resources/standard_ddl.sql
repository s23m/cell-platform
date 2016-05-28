
CREATE TABLE IF NOT EXISTS Identity (
  uuid varchar(36) NOT NULL,
  name varchar(100) NOT NULL,
  pluralName varchar(100) NOT NULL,
  codeName varchar(100),
  pluralCodeName varchar(100),
  payload CLOB,
  PRIMARY KEY (uuid),
  CONSTRAINT uc_name_uuid UNIQUE (name, uuid)
);

-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS Graph (
  urr varchar(36) NOT NULL,
  uuid varchar(36) NOT NULL,
  category varchar(36) NOT NULL,
  container varchar(36) NOT NULL,
  isAbstractValue varchar(36) NOT NULL,
  maxCardinalityValueInContainer varchar(36),
  properClass varchar(36) NOT NULL,
  contentAsXml CLOB,
  PRIMARY KEY (urr),
  CONSTRAINT uc_uuid_urr UNIQUE (uuid,urr),
  CONSTRAINT fk_urr FOREIGN KEY (urr) REFERENCES identity (uuid),
  CONSTRAINT fk_uuid FOREIGN KEY (uuid) REFERENCES identity (uuid),
  CONSTRAINT fk_category FOREIGN KEY (category) REFERENCES identity (uuid),
  CONSTRAINT fk_container FOREIGN KEY (container) REFERENCES identity (uuid),
  CONSTRAINT fk_isAbstractValue FOREIGN KEY (isAbstractValue) REFERENCES identity (uuid),
  CONSTRAINT fk_maxCardinalityValueInContainer FOREIGN KEY (maxCardinalityValueInContainer) REFERENCES identity (uuid),
  CONSTRAINT chk_graph_properClass CHECK (properClass IN ('Vertex', 'Edge', 'EdgeEnd', 'Visibility', 'SuperSetReference'))
);
  
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS Arrow (
	urr varchar(36) NOT NULL,
	category varchar(36) NOT NULL,
	properClass varchar(36) NOT NULL,
	fromGraph varchar(36) NOT NULL,
	toGraph varchar(36) NOT NULL,
	PRIMARY KEY (urr),
	CONSTRAINT chk_arrow_properClass CHECK (properClass IN ('Edge', 'Visibility', 'SuperSetReference')), 
	CONSTRAINT uc_from_to_properClass UNIQUE (fromGraph, toGraph, properClass, category),
	CONSTRAINT uc_to_properClass UNIQUE (toGraph, properClass, category),
	CONSTRAINT fk_arrow FOREIGN KEY (urr) REFERENCES graph (urr),
	CONSTRAINT fk_fromGraph FOREIGN KEY (fromGraph) REFERENCES graph (urr),
	CONSTRAINT fk_toGraph FOREIGN KEY (toGraph) REFERENCES graph (urr)
);
  
-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS Edge (
  urr varchar(36) NOT NULL,
  minCardinalityValueFromEdgeEnd varchar(36) NOT NULL,
  minCardinalityValueToEdgeEnd varchar(36) NOT NULL,
  maxCardinalityValueFromEdgeEnd varchar(36) NOT NULL,
  maxCardinalityValueToEdgeEnd varchar(36) NOT NULL, 
  isNavigableValueFromEdgeEnd varchar(36) NOT NULL,
  isNavigableValueToEdgeEnd varchar(36) NOT NULL,
  isContainerValueFromEdgeEnd varchar(36) NOT NULL,
  isContainerValueToEdgeEnd varchar(36) NOT NULL,
  fromEdgeEnd varchar(36) NOT NULL,
  toEdgeEnd varchar(36) NOT NULL,
  PRIMARY KEY (urr),
  CONSTRAINT fk_edge FOREIGN KEY (urr) REFERENCES arrow (urr),
  CONSTRAINT fk_minCardinalityValueFromEdgeEnd FOREIGN KEY (minCardinalityValueFromEdgeEnd) REFERENCES identity (uuid),
  CONSTRAINT fk_minCardinalityValueToEdgeEnd FOREIGN KEY (minCardinalityValueToEdgeEnd) REFERENCES identity (uuid),
  CONSTRAINT fk_maxCardinalityValueFromEdgeEnd FOREIGN KEY (maxCardinalityValueFromEdgeEnd) REFERENCES identity (uuid),
  CONSTRAINT fk_maxCardinalityValueToEdgeEnd FOREIGN KEY (maxCardinalityValueToEdgeEnd) REFERENCES identity (uuid),
  CONSTRAINT fk_isNavigableValueFromEdgeEnd FOREIGN KEY (isNavigableValueFromEdgeEnd) REFERENCES identity (uuid),
  CONSTRAINT fk_isNavigableValueToEdgeEnd FOREIGN KEY (isNavigableValueToEdgeEnd) REFERENCES identity (uuid),
  CONSTRAINT fk_isContainerValueFromEdgeEnd FOREIGN KEY (isContainerValueFromEdgeEnd) REFERENCES identity (uuid),
  CONSTRAINT fk_isContainerValueToEdgeEnd FOREIGN KEY (isContainerValueToEdgeEnd) REFERENCES identity (uuid),
  CONSTRAINT fk_fromEdgeEnd FOREIGN KEY (fromEdgeEnd) REFERENCES graph (urr),
  CONSTRAINT fk_toEdgeEnd FOREIGN KEY (toEdgeEnd) REFERENCES graph (urr)
);