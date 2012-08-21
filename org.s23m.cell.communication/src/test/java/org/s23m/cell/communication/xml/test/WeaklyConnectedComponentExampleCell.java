/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is S23M.
 *
 * The Initial Developer of the Original Code is
 * The S23M Foundation.
 * Portions created by the Initial Developer are
 * Copyright (C) 2012 The S23M Foundation.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.communication.xml.test;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.s23m.cell.api.models.S23MSemanticDomains;

import org.s23m.cell.platform.api.Instantiation;
import org.s23m.cell.platform.models.CellEngineering;

import org.s23m.cell.platform.models.Organization;
import org.s23m.cell.platform.testfoundation.AgencyTestFoundation;
import org.s23m.cell.Set;
import static org.s23m.cell.S23MKernel.coreGraphs;
import static org.s23m.cell.api.SetAlgebra.*;


public class WeaklyConnectedComponentExampleCell {
	
	public static void main(String[] args) {
		Set graph = createGraph();
		
		System.out.println("Input:\n" + graph);
		
		Set result = anEmptySet();
		
		// all vertices are initially unvisited
		java.util.Set<Set> unvisitedVertices = new HashSet<Set>();
		for (Set v : graph.filterProperClass(coreGraphs.vertex)) {
			unvisitedVertices.add(v);
		}		

        while (!unvisitedVertices.isEmpty()) {
            Set weaklyConnectedComponent = anEmptySet();
            // pick the next available unvisited vertex
            Set root = unvisitedVertices.iterator().next();
            unvisitedVertices.remove(root);
            addElementToOrderedSet(weaklyConnectedComponent, root);

            Queue<Set> queue = new LinkedList<Set>();
            queue.add(root);

            while (!queue.isEmpty()) {
            	Set currentVertex = queue.remove();
            	// iterate through adjacent neighbours (via outgoing edges)
                Set neighbours = currentVertex.container().filterArrows().filterByLinkedFrom(currentVertex);

                for (Set outgoingEdge : neighbours) {
                    Set neighbor = outgoingEdge.to();
                    if (unvisitedVertices.contains(neighbor)) {
                        queue.add(neighbor);
                        unvisitedVertices.remove(neighbor);
                        addElementToOrderedSet(weaklyConnectedComponent, neighbor);
                    }
                }
            }
            addElementToOrderedSet(result, weaklyConnectedComponent);
        }

        // print result
        System.out.println("Number of weakly connected components: " + result.size());
        
        System.out.println("Output:\n" + result);
	}

	private static Set createGraph() {
		Set root = AgencyTestFoundation.test1.filter(CellEngineering.organization).extractFirst();
		Set g = root.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("g", "g", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
		Set one = g.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("1", "1", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
		Set two = g.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("2", "2", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
		Set three = g.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("3", "3", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
		Set four = g.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("4", "4", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
		Set five = g.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("5", "5", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
		Set six = g.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("6", "6", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
		Set seven = g.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("7", "7", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
		Set eight = g.addConcrete(Organization.cell, Instantiation.addDisjunctSemanticIdentitySet("8", "8", Instantiation.toSemanticDomain(AgencyTestFoundation.test1)));
				
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				one,
				one,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				five,
				five,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				one,
				one,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				six,
				six,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);		
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				four,
				four,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				five,
				five,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);	
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				three,
				three,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				six,
				six,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);			
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				three,
				three,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				eight,
				eight,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);	
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				five,
				five,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				two,
				two,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);	
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				five,
				five,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				seven,
				seven,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);			
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				five,
				five,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				eight,
				eight,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);			
		Instantiation.arrow(coreGraphs.edge,
				S23MSemanticDomains.anonymous,
				six,
				six,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_FALSE,
				S23MSemanticDomains.isContainer_FALSE,
				eight,
				eight,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.is_NOTAPPLICABLE,
				S23MSemanticDomains.isNavigable_TRUE,
				S23MSemanticDomains.isContainer_FALSE);	
		return g;
	}
}
