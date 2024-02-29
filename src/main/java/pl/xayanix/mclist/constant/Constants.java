package pl.xayanix.mclist.constant;

import pl.xayanix.mclist.message.ColouredMessage;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Constants {

    public static ColouredMessage SUCCESS = new ColouredMessage( "&aDziekujemy za oddanie glos na nasz serwer, oto Twoja nagroda!" );
    public static ColouredMessage CONNECTING = new ColouredMessage( "&cWysylanie zapytania do serwera API McList.pl..." );
    public static ColouredMessage FAILED = new ColouredMessage( "&cNie polubiles jeszcze dzis naszego serwera, zrob to na www.mclist.pl" );
    public static ColouredMessage COOLDOWN = new ColouredMessage( "&cTa komende mozna uzywac raz na 30 sekund." );
    public static ColouredMessage ALREADY_RECEIVED_REWARD = new ColouredMessage( "&cOtrzymales juz nagrode, sprobuj ponownie po 24:00." );
    public static ColouredMessage NO_PERMISSION_MESSAGE = new ColouredMessage( "&cNie posiadasz uprawnienia &6mclist.odbierz&c do uzycia tej komendy." );
    public static ColouredMessage API_EXCEPTION = new ColouredMessage( "&cWystapil problem po stronie serwera, zglos ta sytuacje administracji." );

    public static String SERVER_IP = "twojserwer.pl";
    public static long COMMAND_COOLDOWN = TimeUnit.SECONDS.toMillis(30);
    public static List<String> COMMANDS_TO_EXECUTE = Arrays.asList("/give %player% DIAMOND 3");

}
