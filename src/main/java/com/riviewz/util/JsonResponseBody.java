package com.riviewz.util;

public class JsonResponseBody {

    private  int server;
    private Object response;
    
	public JsonResponseBody() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JsonResponseBody(int server, Object response) {
		super();
		this.server = server;
		this.response = response;
	}

	public int getServer() {
		return server;
	}

	public void setServer(int server) {
		this.server = server;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "JsonResponseBody [server=" + server + ", response=" + response + "]";
	}
	
}


//http response -> java object ResponseEntity<JsonResponseBody>

//header (jwt)

//body - html page or a JsonMessage(JsonResponseBody(int server, Object response))