package utilities;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
@Sources({
    "classpath:${env}.properties" 
})
public interface Environment extends Config {

    String url();	

    String username();

    String password();

    @Key("darrts.db.url")
    String getDARRTSurl();
    
    @Key("darrts.db.hostname")
    String getDARRTSDBHostname();

    @Key("darrts.db.port")
    int getDARRTSDBPort();

    @Key("darrts.db.username")
    String getDARRTSDBUsername();

    @Key("darrts.db.password")
    String getDARRTSDBPassword();
}

