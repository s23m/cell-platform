package org.s23m.cell.communication.xml;

@SuppressWarnings("all")
public class IdentityTriple {
  public String uniqueRepresentationReference;
  
  public String identifier;
  
  public String nameAttribute;
  
  public IdentityTriple(final String u, final String i, final String n) {
      this.uniqueRepresentationReference = u;
      this.identifier = i;
      this.nameAttribute = n;
  }
}
