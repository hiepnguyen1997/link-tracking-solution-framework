package link.TransportRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class test {

	public static void main(String[] args) {
//		LocalDate today = LocalDate.now(ZoneId.of("America/Anchorage"));
//		System.out.println(today);
//		Locale local  = new Locale("en");
//		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, local);
//		ZoneId defaultZoneId = ZoneId.of("America/Anchorage");
//		Date date  = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
//		String formattedDate = df.format(date);
//		System.out.println(formattedDate);
		
		System.out.println(getTheDateOrder("en", "America/Anchorage"));
		
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Anchorage"));
//		Date currentDate = calendar.getTime();
//		System.out.println(currentDate);
		
		Locale local = new Locale("en");
		LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Anchorage"));
        System.out.println(now);
		Date time = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dateFormat1 = DateFormat.getDateInstance(DateFormat.FULL, local);
		System.out.println(dateFormat.format(time));
		System.out.println(dateFormat1.format(time));
		
		String file = "Bollore.com";
		System.out.println(file.split("\\.")[0]);
		
	}
	
	public static String getTheDateOrder(String location, String zoneID) {
		Locale local = new Locale(location);
		ZoneId zoneId = ZoneId.of(zoneID);
		LocalDate localDate = LocalDate.now(zoneId);
		Date date = Date.from(localDate.atStartOfDay(zoneId).toInstant());
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, local);
		return dateFormat.format(date);
		
	}

}
