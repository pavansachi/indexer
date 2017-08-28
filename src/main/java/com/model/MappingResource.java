package com.model;

public class MappingResource {

	public class MappingResourceRequest {

		private String resource;

		public String getResource() {
			return resource;
		}

		public MappingResourceRequest() {

		}
	}

	public class MappingResourceResponse {

		private int status;
		private String resource;

		public int getStatus() {
			return status;
		}

		public String getResource() {
			return resource;
		}

		public MappingResourceResponse() {

		}
	}

	public MappingResource() {

	}

	private String path;
	private MappingResourceRequest request;
	private MappingResourceResponse response;
	public String getPath() {
		return path;
	}
	public MappingResourceRequest getRequest() {
		return request;
	}
	public MappingResourceResponse getResponse() {
		return response;
	}

}
