--
-- Table structure for table `identity`
--

CREATE TABLE IF NOT EXISTS `identity` (
  `uuid` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pluralName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `codeName` varchar(100) COLLATE utf8_unicode_ci,
  `pluralCodeName` varchar(100) COLLATE utf8_unicode_ci,
  `payload` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`uuid`),
  KEY `IDENTITY_name_uuid` (`name`,`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `graph`
--

CREATE TABLE IF NOT EXISTS `graph` (
  `urr` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `uuid` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `container` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `isAbstractValue` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `maxCardinalityValueInContainer` varchar(36) COLLATE utf8_unicode_ci,
  `properClass` enum('Vertex', 'Edge', 'EdgeEnd', 'Visibility', 'SuperSetReference') COLLATE utf8_unicode_ci NOT NULL,
  `contentAsXml` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`urr`),
  KEY `GRAPH_uuid_urr` (`uuid`,`urr`),
  KEY `categoryFK` (`category`),
  KEY `containerFK` (`container`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Constraints for table `graph`
--
ALTER TABLE `graph`
  ADD CONSTRAINT `urrFK` FOREIGN KEY (`urr`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `graph`
  ADD CONSTRAINT `uuidFK` FOREIGN KEY (`uuid`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `graph`
  ADD CONSTRAINT `categoryFK` FOREIGN KEY (`category`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `graph`
  ADD CONSTRAINT `containerFK` FOREIGN KEY (`container`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `graph`
  ADD CONSTRAINT `isAbstractValueFK` FOREIGN KEY (`isAbstractValue`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `graph`
  ADD CONSTRAINT `maxCardinalityValueInContainerFK` FOREIGN KEY (`maxCardinalityValueInContainer`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
  
-- --------------------------------------------------------

--
-- Table structure for table `arrow`
--

CREATE TABLE IF NOT EXISTS `arrow` (
  `urr` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `properClass` enum('Edge', 'Visibility', 'SuperSetReference') COLLATE utf8_unicode_ci NOT NULL,
  `fromGraph` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `toGraph` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`urr`),
  KEY `GRAPH_from_to_properClass` (`fromGraph`,`toGraph`,`properClass`,`category`),
  KEY `GRAPH_to_properClass` (`toGraph`,`properClass`,`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Constraints for table `arrow`
--
ALTER TABLE `arrow`
  ADD CONSTRAINT `arrowFK` FOREIGN KEY (`urr`) REFERENCES `graph` (`urr`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `arrow`
  ADD CONSTRAINT `fromGraphFK` FOREIGN KEY (`fromGraph`) REFERENCES `graph` (`urr`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `arrow`
  ADD CONSTRAINT `toGraphFK` FOREIGN KEY (`toGraph`) REFERENCES `graph` (`urr`) ON DELETE NO ACTION ON UPDATE NO ACTION;

  
-- --------------------------------------------------------

--
-- Table structure for table `edge`
--

CREATE TABLE IF NOT EXISTS `edge` (
  `urr` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `minCardinalityValueFromEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `minCardinalityValueToEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `maxCardinalityValueFromEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `maxCardinalityValueToEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL, 
  `isNavigableValueFromEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `isNavigableValueToEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `isContainerValueFromEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `isContainerValueToEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `fromEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `toEdgeEnd` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`urr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Constraints for table `edge`
--
ALTER TABLE `edge`
  ADD CONSTRAINT `edgeFK` FOREIGN KEY (`urr`) REFERENCES `arrow` (`urr`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `edge`
  ADD CONSTRAINT `minCardinalityValueFromEdgeEndFK` FOREIGN KEY (`minCardinalityValueFromEdgeEnd`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `minCardinalityValueToEdgeEndFK` FOREIGN KEY (`minCardinalityValueToEdgeEnd`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `maxCardinalityValueFromEdgeEndFK` FOREIGN KEY (`maxCardinalityValueFromEdgeEnd`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `maxCardinalityValueToEdgeEndFK` FOREIGN KEY (`maxCardinalityValueToEdgeEnd`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `isNavigableValueFromEdgeEndFK` FOREIGN KEY (`isNavigableValueFromEdgeEnd`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `isNavigableValueToEdgeEndFK` FOREIGN KEY (`isNavigableValueToEdgeEnd`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `isContainerValueFromEdgeEndFK` FOREIGN KEY (`isContainerValueFromEdgeEnd`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `isContainerValueToEdgeEndFK` FOREIGN KEY (`isContainerValueToEdgeEnd`) REFERENCES `identity` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `fromEdgeEndFK` FOREIGN KEY (`fromEdgeEnd`) REFERENCES `graph` (`urr`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
ALTER TABLE `edge`
  ADD CONSTRAINT `toEdgeEndFK` FOREIGN KEY (`toEdgeEnd`) REFERENCES `graph` (`urr`) ON DELETE NO ACTION ON UPDATE NO ACTION;  
