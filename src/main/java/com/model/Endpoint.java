package com.model;

import java.util.List;

/**
 * endpoint model
 * @author pavansachi
 *
 */
public class Endpoint {

	private String path;
	private List<MappingResource> resources;
	
	public void setResources(List<MappingResource> resources) {
		this.resources = resources;
	}

	public Endpoint() {
		
	}
	
	public String getPath() {
		return path;
	}

	public List<MappingResource> getResources() {
		return resources;
	}
	
}
