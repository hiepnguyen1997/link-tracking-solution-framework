package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:environmentRunner/${evn}.properties"})
public interface EnvironmentConfig extends Config {
	@Key("App.URL")
	String getURL();
	
	@Key("App.UserName")
	String getUserName();
	
	@Key("App.Password")
	String getPassword();
	
	@Key("App.UserNameOther")
	String getUserNameOther();
	
	@Key("App.PasswordOther")
	String getPasswordOther();
	
}
