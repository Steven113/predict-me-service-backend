package AppCore;

public enum DayOfWeek {
	Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Invalid_Day;
	
	static DayOfWeek [] orderOfDaysInWeek = new DayOfWeek[] {Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday};
	
	static DayOfWeek getDayOfWeekFromInt(int dayOfWeekInt) {
		if (dayOfWeekInt >=0 && dayOfWeekInt < 7) {
			return orderOfDaysInWeek[dayOfWeekInt];
		}
		
		return Invalid_Day;
	}
	
	static DayOfWeek getDayOfWeekFromString(String dayOfWeekString) {
		return Enum.valueOf(DayOfWeek.class, dayOfWeekString);
	}
}
