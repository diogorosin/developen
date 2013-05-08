package developen.server.application.mvc;

import developen.common.framework.mvc.Model;

public class ServerModel extends Model implements Server {


	private static final long serialVersionUID = 1831593178580066468L;
	
	private ServerState serverState;
	

	public void setServerState(ServerState newValue) {

		
		ServerState oldValue = this.serverState;
		
		this.serverState = newValue;
		
		firePropertyChange("ServerState", oldValue, newValue);
		
		
	}


	public ServerState getServerState() {

		return serverState;
		
	}

	
}