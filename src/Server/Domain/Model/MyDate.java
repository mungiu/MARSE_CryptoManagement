package Server.Domain.Model;

import java.util.GregorianCalendar;

/**
 * A class representing Date with day, month and year.
 *
 * @author Group 1
 */
public class MyDate implements Comparable<MyDate>
{
	private int day;
	private int month;
	private int year;

	/**
	 * Three-argument constructor.
	 *
	 * @param day   the MyDate day
	 * @param month the MyDate month
	 * @param year  the MyDate year
	 */
	public MyDate(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;

		if (day > daysInMonth())
			for (int i = 0; i <= (Math.abs(day - daysInMonth())); i++)
				nextDay();
	}

	/**
	 * One argument constructor taking another date as parameter
	 *
	 * @param obj is another MyDate object
	 */
	public MyDate(MyDate obj) throws NullPointerException
	{
		day = obj.day;
		month = obj.month;
		year = obj.year;
	}

	/**
	 * No-argument constructor initializing MyDate.
	 */
	public MyDate()
	{
		this.day = 1;
		this.month = 1;
		this.year = 1700;
	}

	/**
	 * Sets the MyDate day
	 *
	 * @param day will be set as day in MyDate
	 */
	public void setDay(int day)
	{
		this.day = day;
	}

	/**
	 * Sets the MyDate month
	 *
	 * @param month will be set as month in MyDate
	 */
	public void setMonth(int month)
	{
		this.month = month;
	}

	/**
	 * Sets the MyDate year
	 *
	 * @param year will be set as year in MyDate
	 */
	public void setYear(int year)
	{
		this.year = year;
	}

	/**
	 * Gets the MyDate day.
	 *
	 * @return the MyDate day.
	 */
	public int getDay()
	{
		return day;
	}

	/**
	 * Gets the MyDate month.
	 *
	 * @return the MyDate month.
	 */
	public int getMonth()
	{
		return month;
	}

	/**
	 * Gets the MyDate year.
	 *
	 * @return the MyDate year.
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Gets the MyDate months name
	 *
	 * @return the MyDate months name according to number.
	 */
	public String getMonthName()
	{
		switch (month)
		{
			case 1:
				return "January";
			case 2:
				return "February";
			case 3:
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6:
				return "June";
			case 7:
				return "July";
			case 8:
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11:
				return "November";
			case 12:
				return "December";
			default:
				return "Error in month number";
		}
	}

	/**
	 * How the date will be displayed in MyDate.
	 *
	 * @return the date in given order.
	 */
	public String displayDate()
	{
		String disP = "";

		disP += day + "/" + month + "/" + year;
		return disP;
	}

	/**
	 * Compares the years if it is leap year.
	 *
	 * @return true if the year is leap year.
	 */
	public boolean isLeapYear()
	{
		if (year % 4 == 0)
		{
			if (year % 100 == 0)
			{
				if (year % 400 == 0)
				{
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Counts how many days are in a specific month
	 *
	 * @return the amount of days are in a month.
	 */
	public int daysInMonth()
	{
		switch (month)
		{
			case 1:
				return 31;
			case 2:
				if (isLeapYear())
					return 29;
				else
					return 28;
			case 3:
				return 31;
			case 4:
				return 30;
			case 5:
				return 31;
			case 6:
				return 30;
			case 7:
				return 31;
			case 8:
				return 31;
			case 9:
				return 30;
			case 10:
				return 31;
			case 11:
				return 30;
			case 12:
				return 31;
			default:
				return -1;
		}
	}

	/**
	 * Gets the astro Sign according to the date.
	 *
	 * @return the astro sign in given date.
	 */
	public String getAstroSign()
	{
		if ((month == 3 && day >= 21) || (month == 4 && day <= 19))
			return "Aries";
		else if ((month == 4 && day >= 20) || (month == 5 && day <= 20))
			return "Taurus";
		else if ((month == 5 && day >= 21) || (month == 6 && day <= 20))
			return "Gemini";
		else if ((month == 6 && day >= 21) || (month == 7 && day <= 22))
			return "Cancer";
		else if ((month == 7 && day >= 23) || (month == 8 && day <= 22))
			return "Leo";
		else if ((month == 8 && day >= 23) || (month == 9 && day <= 22))
			return "Virgo";
		else if ((month == 9 && day >= 23) || (month == 10 && day <= 22))
			return "Libra";
		else if ((month == 10 && day >= 23) || (month == 11 && day <= 21))
			return "Scorpio";
		else if ((month == 11 && day >= 22) || (month == 12 && day <= 21))
			return "Saggittarius";
		else if ((month == 12 && day >= 22) || (month == 1 && day <= 19))
			return "Capricorn";
		else if ((month == 1 && day >= 20) || (month == 2 && day <= 18))
			return "Aquarius";
		else if ((month == 2 && day >= 19) || (month == 3 && day <= 20))
			return "Pisces";
		else
			return "Error is astro sign";
	}

	/**
	 * Gets the day of the week.
	 *
	 * @return the day of the week.
	 */
	public String dayOfWeek()
	{
		int day1;
		int yearTemp = year;
		int monthTemp = month;

		if (month == 1)
		{
			monthTemp = 13;
			yearTemp = year - 1;
		}
		else if (month == 2)
		{
			monthTemp = 14;
			yearTemp = year - 1;
		}
		day1 = (((day + ((13 * (monthTemp + 1))) / 5)) + yearTemp % 100 + ((yearTemp % 100) / 4)
				+ ((yearTemp / 100) / 4) + (5 * (yearTemp / 100))) % 7;

		switch (day1)
		{
			case 0:
				return "Saturday";
			case 1:
				return "Sunday";
			case 2:
				return "Monday";
			case 3:
				return "Tuesday";
			case 4:
				return "Wednesday";
			case 5:
				return "Thursday";
			case 6:
				return "Friday";
			default:
				return "Error in value";
		}
	}

	public void nextDay()
	{
		if (day + 1 > daysInMonth())
		{
			if (month + 1 > 12)
			{
				day = 1;
				month = 1;
				year++;
			}
			else
			{
				day = 1;
				month++;
			}
		}
		else
			day++;
	}

	/**
	 * Compares the day, month and year of MyDate
	 *
	 * @param obj the object to compare with
	 * @return true if the given object is equal to this MyDate.
	 */
	public boolean equals(Object obj) throws NullPointerException
	{
		if (!(obj instanceof MyDate))
			return false;
		else
		{
			MyDate temp = (MyDate) obj;
			return (temp.day == day && temp.month == month && temp.year == year);
		}
	}

	/**
	 * Make a copy of MyDate class
	 *
	 * @return the new MyDate variables.
	 */
	public MyDate copy()
	{
		return new MyDate(day, month, year);
	}

	public void nextDays(int days)
	{
		for (int i = 1; i <= days; i++)
			nextDay();
	}

	/**
	 * Returns the current date as a MyDate object created using GregorianCalendar.
	 *
	 * @return the current being date.
	 */
	public static MyDate today()
	{
		GregorianCalendar cal = new GregorianCalendar();

		return new MyDate(cal.get(GregorianCalendar.DATE), cal.get(GregorianCalendar.MONTH) + 1,
				cal.get(GregorianCalendar.YEAR));
	}

	/**
	 * Compares if the current year is before the passed in year.
	 *
	 * @param obj
	 * @return true if the given argument is equal to itself.
	 */
	public boolean isBefore(MyDate obj) throws NullPointerException
	{
		boolean thisYearIsBefore = this.year < obj.year;
		boolean thisMonthIsBefore = this.year == obj.year && this.month < obj.month;
		boolean thisDayIsBefore = this.year == obj.year && this.month == obj.month && this.day < obj.day;

		if (thisYearIsBefore | thisMonthIsBefore | thisDayIsBefore)
			return true;
		else
			return false;
	}

	/**
	 * Returns a string representation of the MyDate
	 *
	 * @return a string representation of the MyDate in the format:
	 * "day/month/year".
	 */
	public String toString()
	{
		return day + "/" + month + "/" + year;
	}

	@Override
	public int compareTo(MyDate date) throws NullPointerException
	{
		boolean thisDateIsSameYear = this.getYear() == date.getYear();
		boolean thisDateIsSameMonth = (thisDateIsSameYear && this.getMonth() == date.getMonth());
		boolean thisDateIsSameDay = (thisDateIsSameMonth && this.getDay() == date.getDay());

		boolean thisDateIsEarlierYear = this.getYear() < date.getYear();
		boolean thisDateIsEarlierMonth = thisDateIsSameYear && this.getMonth() < date.getMonth();
		boolean thisDateIsEarlierDay = thisDateIsSameYear && thisDateIsSameMonth && this.getDay() < date.getDay();

		if (thisDateIsEarlierYear | thisDateIsEarlierMonth | thisDateIsEarlierDay)
			return -1;
		else if (thisDateIsSameDay)
			return 0;
		else
			return 1;
	}
}