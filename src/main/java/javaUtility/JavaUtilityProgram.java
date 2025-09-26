package javaUtility;


import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtilityProgram {
	
	public int getRandomNumber() {
		Random rd = new Random();
		int randomNumber = rd.nextInt(1000);
		return randomNumber;
	}
	
	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sim.format(date);
		return currentDate;
			}
	
	public String getCurrentDateAndTime() {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String currentDateAndTime = sim.format(date);
		return currentDateAndTime;
	}

	public String getRequireDate(int exDate) {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		
		cal.add(Calendar.DAY_OF_MONTH, exDate);
		String expectedDate = sim.format(cal.getTime());
		return expectedDate;
	}
	
	public String generateUniqueEmail() {
		String timesstamp = String.valueOf(System.currentTimeMillis());
		return "testuser" + timesstamp + "@example.com";
	}
	
	public String generateUniquePhoneNumber() {
		Random rn = new Random();
		return "9" + (100000000 + rn.nextInt(900000000));
	}
	
	public String generateRandomName() {
		String[] firstNames = {"Amit","Riya","Jay","Sheena","Aashvi","Jeel","Deep","Pankaj","Harini","Brinda"};
		String[] lastNames = {"Ranawat","Sharma","Patel","Katariya","Rana","Shah","Dekavadiya","Shekhawat","Singh","Otwani"};
		
		Random rn = new Random();
		
		String firstName = firstNames[rn.nextInt(firstNames.length)];
		String lastName = lastNames[rn.nextInt(lastNames.length)];
		
		return firstName + " " + lastName;
	}
	


	public static class generateRandomPassword {
	    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
	    private final String DIGIT = "0123456789";

	    public String getRandomPass(int length) {
	        String combined = UPPER + LOWER + DIGIT;
	        SecureRandom rn = new SecureRandom();
	        StringBuilder sb = new StringBuilder();

	        // Pehle (length - 4) characters generate kar lete hai
	        for (int i = 0; i < length - 4; i++) {
	            int index = rn.nextInt(combined.length());
	            sb.append(combined.charAt(index));
	        }

	        // ✅ Ab compulsory characters daal dete hai
	        // ek Uppercase
	        char upper = UPPER.charAt(rn.nextInt(UPPER.length()));
	        // ek Lowercase
	        char lower = LOWER.charAt(rn.nextInt(LOWER.length()));
	        // ek digit (yaha '2')
	        char digit = '2';
	        // ek special char
	        char special = '$';

	        // Random positions par insert kar dete hai
	        sb.insert(rn.nextInt(sb.length() + 1), upper);
	        sb.insert(rn.nextInt(sb.length() + 1), lower);
	        sb.insert(rn.nextInt(sb.length() + 1), digit);
	        sb.insert(rn.nextInt(sb.length() + 1), special);

	        return sb.toString();
	    }
	}

	}

	




	


	
	

