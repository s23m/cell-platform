-- add sequence number to all tables

ALTER TABLE Identity
ADD sequenceNumber bigint auto_increment;

ALTER TABLE Agent
ADD sequenceNumber bigint auto_increment;

ALTER TABLE Graph
ADD sequenceNumber bigint auto_increment;

ALTER TABLE Arrow
ADD sequenceNumber bigint auto_increment;

ALTER TABLE Edge
ADD sequenceNumber bigint auto_increment;
