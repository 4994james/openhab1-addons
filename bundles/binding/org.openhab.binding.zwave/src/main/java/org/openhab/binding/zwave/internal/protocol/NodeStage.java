/**
 * Copyright (c) 2010-2014, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.zwave.internal.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * Node Stage Enumeration. Represents the state the node
 * is in.
 * @author Brian Crosby
 * @since 1.3.0
 */
public enum NodeStage {
	EMPTYNODE(0, "Empty New Node"),
	PROTOINFO(1, "Protocol Information"),
	WAIT(2, "Waiting"),
	PING(3, "Ping Node"),
	DETAILS(4, "Node Information"),
	MANUFACTURER(5, "Manufacture Name and Product Identification"),
	VERSION(6, "Command Class Versions"),
	APP_VERSION(7, "Application Version"),
	ENDPOINTS(8, "Command Class Endpoints"),
	UPDATE_DATABASE(9, "Updating database"),
	STATIC_VALUES(10, "Static Information"),
	// States below are not restored from the configuration files
	SESSION_START(11, "Restore Marker"),
	NEIGHBORS(12, "Node Neighbor Information"),
	SESSION(13, "Infrequently Changed Information"),
	DYNAMIC_VALUES(14, "Frequently Changed Information"),
	CONFIG(15, "Parameter Information"),
	ASSOCIATIONS(16, "Associations"),
	DONE(17, "Node Complete"),
	INIT(18, "Node Not Started"),
	DEAD(19, "Node Dead"),
	FAILED(20,"Node Failed");
	
	private int stage;
	private String label;
	
	/**
	 * A mapping between the integer code and its corresponding
	 * Node Stage to facilitate lookup by code.
	 */
	private static Map<Integer, NodeStage> codeToNodeStageMapping;
	
	private NodeStage (int s, String l) {
		stage = s;
		label = l;
	}
	
	private static void initMapping() {
		codeToNodeStageMapping = new HashMap<Integer, NodeStage>();
		for (NodeStage s : values()) {
			codeToNodeStageMapping.put(s.stage, s);
		}
	}
	
	/**
	 * Get the stage protocol number.
	 * @return number
	 */
	public int getStage() {
		return this.stage;
	}
	
	/**
	 * Get the stage label
	 * @return label
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * Lookup function based on the command class code.
	 * Returns null if there is no command class with code i
	 * @param i the code to lookup
	 * @return enumeration value of the command class.
	 */
	public static NodeStage getNodeStage(int i) {
		if (codeToNodeStageMapping == null) {
			initMapping();
		}
		
		return codeToNodeStageMapping.get(i);
	}
	
	/**
	 * Return the next stage after the current stage
	 * @return the next stage
	 */
	public NodeStage getNextStage() {
		for (NodeStage s : values()) {
			if(s.stage == this.stage + 1) {
				return s;
			}
		}
		
		return null;
	}
}

